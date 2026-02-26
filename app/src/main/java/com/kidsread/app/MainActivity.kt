package com.kidsread.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kidsread.app.databinding.ActivityMainBinding
import com.kidsread.app.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Locale

/**
 * النشاط الرئيسي للتطبيق
 */
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        
        setupUI()
        observeViewModel()
    }
    
    override fun onResume() {
        super.onResume()
        // بدء جلسة جديدة عند العودة للنشاط إذا كانت الجلسة السابقة منتهية أو لا توجد كلمة
        if (viewModel.sessionCompleted.value || viewModel.currentWord.value == null) {
            viewModel.startNewSession()
        }
    }
    
    private fun setupUI() {
        // زر الإعدادات
        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        
        // زر بدء الجلسة
        binding.btnStartSession.setOnClickListener {
            viewModel.startNewSession()
        }
        
        // زر: متقن
        binding.btnMastered.setOnClickListener {
            viewModel.evaluateWord(true)
        }
        
        // زر: لم يتقن
        binding.btnNotMastered.setOnClickListener {
            viewModel.evaluateWord(false)
        }
    }
    
    private fun observeViewModel() {
        lifecycleScope.launch {
            // مراقبة الكلمة الحالية
            viewModel.currentWord.collect { word ->
                if (word != null) {
                    binding.tvWord.text = word.word
                    binding.tvWord.visibility = View.VISIBLE
                    binding.tvStatus.visibility = View.GONE
                    binding.layoutButtons.visibility = View.VISIBLE
                } else {
                    binding.tvWord.visibility = View.GONE
                    binding.layoutButtons.visibility = View.GONE
                    binding.tvStatus.visibility = if (!viewModel.isLoading.value) View.VISIBLE else View.GONE
                }
            }
        }
        
        lifecycleScope.launch {
            // مراقبة التقدم
            viewModel.currentWordIndex.collect { index ->
                val total = viewModel.sessionWords.value.size
                if (total > 0) {
                    binding.tvProgress.text = getString(R.string.progress_format, index + 1, total)
                    binding.tvProgress.visibility = View.VISIBLE
                } else {
                    binding.tvProgress.visibility = View.GONE
                }
            }
        }
        
        lifecycleScope.launch {
            // مراقبة حالة التحميل
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                binding.btnStartSession.isEnabled = !isLoading
            }
        }
        
        lifecycleScope.launch {
            // مراقبة اكتمال الجلسة
            viewModel.sessionCompleted.collect { completed ->
                if (completed) {
                    showSessionCompletedDialog()
                }
            }
        }
        
        lifecycleScope.launch {
            // مراقبة الإحصائيات
            viewModel.sessionStats.collect { stats ->
                if (stats.totalReviewed > 0) {
                    binding.layoutStats.visibility = View.VISIBLE
                    binding.tvMasteredCount.text = stats.masteredCount.toString()
                    binding.tvFailedCount.text = stats.failedCount.toString()
                    binding.tvSuccessRate.text = String.format(Locale.getDefault(), "%.0f%%", stats.successRate)
                } else {
                    binding.layoutStats.visibility = View.GONE
                }
            }
        }
        
        lifecycleScope.launch {
            // مراقبة رسائل الخطأ
            viewModel.errorMessage.collect { error ->
                if (error != null) {
                    showErrorDialog(error)
                    viewModel.clearError()
                }
            }
        }
        
        lifecycleScope.launch {
            // مراقبة الإعدادات وتطبيق الألوان والخط فوراً على الجلسة الحالية
            viewModel.appSettings.collect { settings ->
                // تطبيق الألوان فوراً
                binding.containerWord.setBackgroundColor(settings.backgroundColor.toInt())
                binding.tvWord.setTextColor(settings.wordColor.toInt())
                binding.tvWord.textSize = settings.fontSize.toFloat() / 100f * 72f // تحويل إلى sp
            }
        }
    }
    
    private fun showSessionCompletedDialog() {
        val stats = viewModel.sessionStats.value
        
        AlertDialog.Builder(this)
            .setTitle(R.string.session_completed)
            .setMessage(
                getString(
                    R.string.session_completed_message,
                    stats.totalReviewed,
                    stats.masteredCount,
                    stats.failedCount,
                    String.format(Locale.getDefault(), "%.0f%%", stats.successRate)
                )
            )
            .setPositiveButton(R.string.start_new_session) { _, _ ->
                viewModel.startNewSession()
            }
            .setNegativeButton(R.string.close) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
    
    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.error)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
