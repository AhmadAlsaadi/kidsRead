package com.kidsread.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kidsread.app.data.model.DiacriticType
import com.kidsread.app.databinding.ActivitySettingsBinding
import com.kidsread.app.ui.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

/**
 * نشاط الإعدادات
 */
class SettingsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var viewModel: SettingsViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.settings)
        
        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        
        setupUI()
        observeViewModel()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    
    private fun setupUI() {
        // طول الكلمة
        binding.sliderWordLength.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                viewModel.updateWordLength(value.toInt())
            }
        }
        
        // عدد الكلمات في الجلسة
        binding.sliderWordsPerSession.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                viewModel.updateWordsPerSession(value.toInt())
            }
        }
        
        // حجم الخط
        binding.sliderFontSize.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                viewModel.updateFontSize(value.toInt())
            }
        }
        
        // أنواع التشكيل
        setupDiacriticChips()
        
        // خصائص الألوان
        setupColorButtons()
    }
    
    private fun setupColorButtons() {
        // لون الخلفية
        binding.btnBgWhite.setOnClickListener {
            viewModel.updateBackgroundColor(0xFFFFFFFF) // أبيض
        }
        binding.btnBgBlue.setOnClickListener {
            viewModel.updateBackgroundColor(0xFFE3F2FD) // أزرق فاتح
        }
        
        // لون الكلمة
        binding.btnWordBlack.setOnClickListener {
            viewModel.updateWordColor(0xFF000000) // أسود
        }
        binding.btnWordGreen.setOnClickListener {
            viewModel.updateWordColor(0xFF1B5E20) // أخضر غامق
        }
        
        // لون الحركات
        binding.btnDiacriticsRed.setOnClickListener {
            viewModel.updateDiacriticsColor(0xFFFF0000) // أحمر
        }
        binding.btnDiacriticsPurple.setOnClickListener {
            viewModel.updateDiacriticsColor(0xFF6A1B9A) // بنفسجي
        }
    }
    
    private fun setupDiacriticChips() {
        val chips = mapOf(
            binding.chipFatha to DiacriticType.FATHA,
            binding.chipKasra to DiacriticType.KASRA,
            binding.chipDamma to DiacriticType.DAMMA,
            binding.chipSukoon to DiacriticType.SUKOON,
            binding.chipTanween to DiacriticType.TANWEEN,
            binding.chipMixed to DiacriticType.MIXED
        )
        
        chips.forEach { (chip, type) ->
            chip.setOnClickListener {
                viewModel.toggleDiacriticType(type)
            }
        }
    }
    
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.settings.collect { settings ->
                // تحديث طول الكلمة
                binding.sliderWordLength.value = settings.wordLength.toFloat()
                binding.tvWordLengthValue.text = settings.wordLength.toString()
                
                // تحديث عدد الكلمات في الجلسة
                binding.sliderWordsPerSession.value = settings.wordsPerSession.toFloat()
                binding.tvWordsPerSessionValue.text = settings.wordsPerSession.toString()
                
                // تحديث حجم الخط
                binding.sliderFontSize.value = settings.fontSize.toFloat()
                binding.tvFontSizeValue.text = settings.fontSize.toString()
                
                // تحديث أنواع التشكيل
                val diacriticChips = mapOf(
                    DiacriticType.FATHA to binding.chipFatha,
                    DiacriticType.KASRA to binding.chipKasra,
                    DiacriticType.DAMMA to binding.chipDamma,
                    DiacriticType.SUKOON to binding.chipSukoon,
                    DiacriticType.TANWEEN to binding.chipTanween,
                    DiacriticType.MIXED to binding.chipMixed
                )
                
                diacriticChips.forEach { (type, chip) ->
                    chip.isChecked = settings.selectedDiacritics.contains(type)
                }
                
                // تحديث ألوان الأزرار (استخدام toInt() مباشرة بدلاً من Color.valueOf للأجهزة القديمة)
                binding.btnBackgroundColor.setBackgroundColor(settings.backgroundColor.toInt())
                binding.btnWordColor.setBackgroundColor(settings.wordColor.toInt())
                binding.btnDiacriticsColor.setBackgroundColor(settings.diacriticsColor.toInt())
            }
        }
    }
}
