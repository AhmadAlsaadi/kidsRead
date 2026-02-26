package com.kidsread.app.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kidsread.app.data.model.Word;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WordDao_Impl implements WordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Word> __insertionAdapterOfWord;

  private final EntityDeletionOrUpdateAdapter<Word> __updateAdapterOfWord;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllWords;

  public WordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWord = new EntityInsertionAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `words` (`id`,`word`,`length`,`diacriticType`,`difficulty`,`reviewCount`,`masteredCount`,`failedCount`,`lastReviewDate`,`nextReviewDate`,`interval`,`easeFactor`,`isMastered`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getWord());
        }
        statement.bindLong(3, entity.getLength());
        if (entity.getDiacriticType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDiacriticType());
        }
        statement.bindLong(5, entity.getDifficulty());
        statement.bindLong(6, entity.getReviewCount());
        statement.bindLong(7, entity.getMasteredCount());
        statement.bindLong(8, entity.getFailedCount());
        statement.bindLong(9, entity.getLastReviewDate());
        statement.bindLong(10, entity.getNextReviewDate());
        statement.bindLong(11, entity.getInterval());
        statement.bindDouble(12, entity.getEaseFactor());
        final int _tmp = entity.isMastered() ? 1 : 0;
        statement.bindLong(13, _tmp);
      }
    };
    this.__updateAdapterOfWord = new EntityDeletionOrUpdateAdapter<Word>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `words` SET `id` = ?,`word` = ?,`length` = ?,`diacriticType` = ?,`difficulty` = ?,`reviewCount` = ?,`masteredCount` = ?,`failedCount` = ?,`lastReviewDate` = ?,`nextReviewDate` = ?,`interval` = ?,`easeFactor` = ?,`isMastered` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Word entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getWord());
        }
        statement.bindLong(3, entity.getLength());
        if (entity.getDiacriticType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDiacriticType());
        }
        statement.bindLong(5, entity.getDifficulty());
        statement.bindLong(6, entity.getReviewCount());
        statement.bindLong(7, entity.getMasteredCount());
        statement.bindLong(8, entity.getFailedCount());
        statement.bindLong(9, entity.getLastReviewDate());
        statement.bindLong(10, entity.getNextReviewDate());
        statement.bindLong(11, entity.getInterval());
        statement.bindDouble(12, entity.getEaseFactor());
        final int _tmp = entity.isMastered() ? 1 : 0;
        statement.bindLong(13, _tmp);
        statement.bindLong(14, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllWords = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM words";
        return _query;
      }
    };
  }

  @Override
  public Object insertWord(final Word word, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWord.insertAndReturnId(word);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertWords(final List<Word> words, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWord.insert(words);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateWord(final Word word, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWord.handle(word);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllWords(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllWords.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllWords.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getWordById(final long wordId, final Continuation<? super Word> $completion) {
    final String _sql = "SELECT * FROM words WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, wordId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Word>() {
      @Override
      @Nullable
      public Word call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLength = CursorUtil.getColumnIndexOrThrow(_cursor, "length");
          final int _cursorIndexOfDiacriticType = CursorUtil.getColumnIndexOrThrow(_cursor, "diacriticType");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfReviewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewCount");
          final int _cursorIndexOfMasteredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "masteredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final int _cursorIndexOfLastReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReviewDate");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfIsMastered = CursorUtil.getColumnIndexOrThrow(_cursor, "isMastered");
          final Word _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final int _tmpLength;
            _tmpLength = _cursor.getInt(_cursorIndexOfLength);
            final String _tmpDiacriticType;
            if (_cursor.isNull(_cursorIndexOfDiacriticType)) {
              _tmpDiacriticType = null;
            } else {
              _tmpDiacriticType = _cursor.getString(_cursorIndexOfDiacriticType);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpReviewCount;
            _tmpReviewCount = _cursor.getInt(_cursorIndexOfReviewCount);
            final int _tmpMasteredCount;
            _tmpMasteredCount = _cursor.getInt(_cursorIndexOfMasteredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            final long _tmpLastReviewDate;
            _tmpLastReviewDate = _cursor.getLong(_cursorIndexOfLastReviewDate);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final boolean _tmpIsMastered;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsMastered);
            _tmpIsMastered = _tmp != 0;
            _result = new Word(_tmpId,_tmpWord,_tmpLength,_tmpDiacriticType,_tmpDifficulty,_tmpReviewCount,_tmpMasteredCount,_tmpFailedCount,_tmpLastReviewDate,_tmpNextReviewDate,_tmpInterval,_tmpEaseFactor,_tmpIsMastered);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getWordsForReview(final int length, final List<String> diacriticTypes,
      final long currentTime, final int limit, final Continuation<? super List<Word>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM words WHERE length = ");
    _stringBuilder.append("?");
    _stringBuilder.append(" AND diacriticType IN (");
    final int _inputSize = diacriticTypes.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") AND nextReviewDate <= ");
    _stringBuilder.append("?");
    _stringBuilder.append(" ORDER BY nextReviewDate ASC LIMIT ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 3 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, length);
    _argIndex = 2;
    for (String _item : diacriticTypes) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _item);
      }
      _argIndex++;
    }
    _argIndex = 2 + _inputSize;
    _statement.bindLong(_argIndex, currentTime);
    _argIndex = 3 + _inputSize;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLength = CursorUtil.getColumnIndexOrThrow(_cursor, "length");
          final int _cursorIndexOfDiacriticType = CursorUtil.getColumnIndexOrThrow(_cursor, "diacriticType");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfReviewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewCount");
          final int _cursorIndexOfMasteredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "masteredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final int _cursorIndexOfLastReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReviewDate");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfIsMastered = CursorUtil.getColumnIndexOrThrow(_cursor, "isMastered");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item_1;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final int _tmpLength;
            _tmpLength = _cursor.getInt(_cursorIndexOfLength);
            final String _tmpDiacriticType;
            if (_cursor.isNull(_cursorIndexOfDiacriticType)) {
              _tmpDiacriticType = null;
            } else {
              _tmpDiacriticType = _cursor.getString(_cursorIndexOfDiacriticType);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpReviewCount;
            _tmpReviewCount = _cursor.getInt(_cursorIndexOfReviewCount);
            final int _tmpMasteredCount;
            _tmpMasteredCount = _cursor.getInt(_cursorIndexOfMasteredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            final long _tmpLastReviewDate;
            _tmpLastReviewDate = _cursor.getLong(_cursorIndexOfLastReviewDate);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final boolean _tmpIsMastered;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsMastered);
            _tmpIsMastered = _tmp != 0;
            _item_1 = new Word(_tmpId,_tmpWord,_tmpLength,_tmpDiacriticType,_tmpDifficulty,_tmpReviewCount,_tmpMasteredCount,_tmpFailedCount,_tmpLastReviewDate,_tmpNextReviewDate,_tmpInterval,_tmpEaseFactor,_tmpIsMastered);
            _result.add(_item_1);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRandomWords(final int length, final List<String> diacriticTypes, final int limit,
      final Continuation<? super List<Word>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM words WHERE length = ");
    _stringBuilder.append("?");
    _stringBuilder.append(" AND diacriticType IN (");
    final int _inputSize = diacriticTypes.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") ORDER BY RANDOM() LIMIT ");
    _stringBuilder.append("?");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 2 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, length);
    _argIndex = 2;
    for (String _item : diacriticTypes) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _item);
      }
      _argIndex++;
    }
    _argIndex = 2 + _inputSize;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLength = CursorUtil.getColumnIndexOrThrow(_cursor, "length");
          final int _cursorIndexOfDiacriticType = CursorUtil.getColumnIndexOrThrow(_cursor, "diacriticType");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfReviewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewCount");
          final int _cursorIndexOfMasteredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "masteredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final int _cursorIndexOfLastReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReviewDate");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfIsMastered = CursorUtil.getColumnIndexOrThrow(_cursor, "isMastered");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item_1;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final int _tmpLength;
            _tmpLength = _cursor.getInt(_cursorIndexOfLength);
            final String _tmpDiacriticType;
            if (_cursor.isNull(_cursorIndexOfDiacriticType)) {
              _tmpDiacriticType = null;
            } else {
              _tmpDiacriticType = _cursor.getString(_cursorIndexOfDiacriticType);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpReviewCount;
            _tmpReviewCount = _cursor.getInt(_cursorIndexOfReviewCount);
            final int _tmpMasteredCount;
            _tmpMasteredCount = _cursor.getInt(_cursorIndexOfMasteredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            final long _tmpLastReviewDate;
            _tmpLastReviewDate = _cursor.getLong(_cursorIndexOfLastReviewDate);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final boolean _tmpIsMastered;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsMastered);
            _tmpIsMastered = _tmp != 0;
            _item_1 = new Word(_tmpId,_tmpWord,_tmpLength,_tmpDiacriticType,_tmpDifficulty,_tmpReviewCount,_tmpMasteredCount,_tmpFailedCount,_tmpLastReviewDate,_tmpNextReviewDate,_tmpInterval,_tmpEaseFactor,_tmpIsMastered);
            _result.add(_item_1);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Word>> getAllWords() {
    final String _sql = "SELECT * FROM words ORDER BY word ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLength = CursorUtil.getColumnIndexOrThrow(_cursor, "length");
          final int _cursorIndexOfDiacriticType = CursorUtil.getColumnIndexOrThrow(_cursor, "diacriticType");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfReviewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewCount");
          final int _cursorIndexOfMasteredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "masteredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final int _cursorIndexOfLastReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReviewDate");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfIsMastered = CursorUtil.getColumnIndexOrThrow(_cursor, "isMastered");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final int _tmpLength;
            _tmpLength = _cursor.getInt(_cursorIndexOfLength);
            final String _tmpDiacriticType;
            if (_cursor.isNull(_cursorIndexOfDiacriticType)) {
              _tmpDiacriticType = null;
            } else {
              _tmpDiacriticType = _cursor.getString(_cursorIndexOfDiacriticType);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpReviewCount;
            _tmpReviewCount = _cursor.getInt(_cursorIndexOfReviewCount);
            final int _tmpMasteredCount;
            _tmpMasteredCount = _cursor.getInt(_cursorIndexOfMasteredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            final long _tmpLastReviewDate;
            _tmpLastReviewDate = _cursor.getLong(_cursorIndexOfLastReviewDate);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final boolean _tmpIsMastered;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsMastered);
            _tmpIsMastered = _tmp != 0;
            _item = new Word(_tmpId,_tmpWord,_tmpLength,_tmpDiacriticType,_tmpDifficulty,_tmpReviewCount,_tmpMasteredCount,_tmpFailedCount,_tmpLastReviewDate,_tmpNextReviewDate,_tmpInterval,_tmpEaseFactor,_tmpIsMastered);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getWordCount(final int length, final List<String> diacriticTypes,
      final Continuation<? super Integer> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT COUNT(*) FROM words WHERE length = ");
    _stringBuilder.append("?");
    _stringBuilder.append(" AND diacriticType IN (");
    final int _inputSize = diacriticTypes.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 1 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, length);
    _argIndex = 2;
    for (String _item : diacriticTypes) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _item);
      }
      _argIndex++;
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Word>> getMasteredWords() {
    final String _sql = "SELECT * FROM words WHERE isMastered = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<Word>>() {
      @Override
      @NonNull
      public List<Word> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfLength = CursorUtil.getColumnIndexOrThrow(_cursor, "length");
          final int _cursorIndexOfDiacriticType = CursorUtil.getColumnIndexOrThrow(_cursor, "diacriticType");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfReviewCount = CursorUtil.getColumnIndexOrThrow(_cursor, "reviewCount");
          final int _cursorIndexOfMasteredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "masteredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final int _cursorIndexOfLastReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReviewDate");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfIsMastered = CursorUtil.getColumnIndexOrThrow(_cursor, "isMastered");
          final List<Word> _result = new ArrayList<Word>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Word _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final int _tmpLength;
            _tmpLength = _cursor.getInt(_cursorIndexOfLength);
            final String _tmpDiacriticType;
            if (_cursor.isNull(_cursorIndexOfDiacriticType)) {
              _tmpDiacriticType = null;
            } else {
              _tmpDiacriticType = _cursor.getString(_cursorIndexOfDiacriticType);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpReviewCount;
            _tmpReviewCount = _cursor.getInt(_cursorIndexOfReviewCount);
            final int _tmpMasteredCount;
            _tmpMasteredCount = _cursor.getInt(_cursorIndexOfMasteredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            final long _tmpLastReviewDate;
            _tmpLastReviewDate = _cursor.getLong(_cursorIndexOfLastReviewDate);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final float _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
            final boolean _tmpIsMastered;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsMastered);
            _tmpIsMastered = _tmp != 0;
            _item = new Word(_tmpId,_tmpWord,_tmpLength,_tmpDiacriticType,_tmpDifficulty,_tmpReviewCount,_tmpMasteredCount,_tmpFailedCount,_tmpLastReviewDate,_tmpNextReviewDate,_tmpInterval,_tmpEaseFactor,_tmpIsMastered);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTotalWordCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM words";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
