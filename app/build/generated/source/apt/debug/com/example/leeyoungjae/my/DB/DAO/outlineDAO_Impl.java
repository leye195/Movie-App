package com.example.leeyoungjae.my.DB.DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.example.leeyoungjae.my.DB.Entity.outline;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class outlineDAO_Impl implements outlineDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfoutline;

  public outlineDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfoutline = new EntityInsertionAdapter<outline>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `outline`(`id`,`title`,`title_eng`,`dateValue`,`user_rating`,`audience_rating`,`reviewer_rating`,`reservation_rate`,`reservation_grade`,`grade`,`thumb`,`image`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, outline value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getTitle_eng() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle_eng());
        }
        if (value.getDateValue() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDateValue());
        }
        stmt.bindDouble(5, value.getUser_rating());
        stmt.bindDouble(6, value.getAudience_rating());
        stmt.bindDouble(7, value.getReviewer_rating());
        stmt.bindDouble(8, value.getReservation_rate());
        stmt.bindLong(9, value.getReservation_grade());
        stmt.bindLong(10, value.getGrade());
        if (value.getThumb() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getThumb());
        }
        if (value.getImage() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getImage());
        }
      }
    };
  }

  @Override
  public void InsertOutline(outline out) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfoutline.insert(out);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<outline> getoutline_ord1() {
    final String _sql = "Select * from outline";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfTitleEng = _cursor.getColumnIndexOrThrow("title_eng");
      final int _cursorIndexOfDateValue = _cursor.getColumnIndexOrThrow("dateValue");
      final int _cursorIndexOfUserRating = _cursor.getColumnIndexOrThrow("user_rating");
      final int _cursorIndexOfAudienceRating = _cursor.getColumnIndexOrThrow("audience_rating");
      final int _cursorIndexOfReviewerRating = _cursor.getColumnIndexOrThrow("reviewer_rating");
      final int _cursorIndexOfReservationRate = _cursor.getColumnIndexOrThrow("reservation_rate");
      final int _cursorIndexOfReservationGrade = _cursor.getColumnIndexOrThrow("reservation_grade");
      final int _cursorIndexOfGrade = _cursor.getColumnIndexOrThrow("grade");
      final int _cursorIndexOfThumb = _cursor.getColumnIndexOrThrow("thumb");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final List<outline> _result = new ArrayList<outline>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final outline _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpTitle_eng;
        _tmpTitle_eng = _cursor.getString(_cursorIndexOfTitleEng);
        final String _tmpDateValue;
        _tmpDateValue = _cursor.getString(_cursorIndexOfDateValue);
        final float _tmpUser_rating;
        _tmpUser_rating = _cursor.getFloat(_cursorIndexOfUserRating);
        final float _tmpAudience_rating;
        _tmpAudience_rating = _cursor.getFloat(_cursorIndexOfAudienceRating);
        final float _tmpReviewer_rating;
        _tmpReviewer_rating = _cursor.getFloat(_cursorIndexOfReviewerRating);
        final float _tmpReservation_rate;
        _tmpReservation_rate = _cursor.getFloat(_cursorIndexOfReservationRate);
        final int _tmpReservation_grade;
        _tmpReservation_grade = _cursor.getInt(_cursorIndexOfReservationGrade);
        final int _tmpGrade;
        _tmpGrade = _cursor.getInt(_cursorIndexOfGrade);
        final String _tmpThumb;
        _tmpThumb = _cursor.getString(_cursorIndexOfThumb);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item = new outline(_tmpId,_tmpTitle,_tmpTitle_eng,_tmpDateValue,_tmpUser_rating,_tmpAudience_rating,_tmpReviewer_rating,_tmpReservation_rate,_tmpReservation_grade,_tmpGrade,_tmpThumb,_tmpImage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<outline> getoutline_ord2() {
    final String _sql = "Select * from outline order by id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfTitleEng = _cursor.getColumnIndexOrThrow("title_eng");
      final int _cursorIndexOfDateValue = _cursor.getColumnIndexOrThrow("dateValue");
      final int _cursorIndexOfUserRating = _cursor.getColumnIndexOrThrow("user_rating");
      final int _cursorIndexOfAudienceRating = _cursor.getColumnIndexOrThrow("audience_rating");
      final int _cursorIndexOfReviewerRating = _cursor.getColumnIndexOrThrow("reviewer_rating");
      final int _cursorIndexOfReservationRate = _cursor.getColumnIndexOrThrow("reservation_rate");
      final int _cursorIndexOfReservationGrade = _cursor.getColumnIndexOrThrow("reservation_grade");
      final int _cursorIndexOfGrade = _cursor.getColumnIndexOrThrow("grade");
      final int _cursorIndexOfThumb = _cursor.getColumnIndexOrThrow("thumb");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final List<outline> _result = new ArrayList<outline>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final outline _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpTitle_eng;
        _tmpTitle_eng = _cursor.getString(_cursorIndexOfTitleEng);
        final String _tmpDateValue;
        _tmpDateValue = _cursor.getString(_cursorIndexOfDateValue);
        final float _tmpUser_rating;
        _tmpUser_rating = _cursor.getFloat(_cursorIndexOfUserRating);
        final float _tmpAudience_rating;
        _tmpAudience_rating = _cursor.getFloat(_cursorIndexOfAudienceRating);
        final float _tmpReviewer_rating;
        _tmpReviewer_rating = _cursor.getFloat(_cursorIndexOfReviewerRating);
        final float _tmpReservation_rate;
        _tmpReservation_rate = _cursor.getFloat(_cursorIndexOfReservationRate);
        final int _tmpReservation_grade;
        _tmpReservation_grade = _cursor.getInt(_cursorIndexOfReservationGrade);
        final int _tmpGrade;
        _tmpGrade = _cursor.getInt(_cursorIndexOfGrade);
        final String _tmpThumb;
        _tmpThumb = _cursor.getString(_cursorIndexOfThumb);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item = new outline(_tmpId,_tmpTitle,_tmpTitle_eng,_tmpDateValue,_tmpUser_rating,_tmpAudience_rating,_tmpReviewer_rating,_tmpReservation_rate,_tmpReservation_grade,_tmpGrade,_tmpThumb,_tmpImage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<outline> getoutline_ord3() {
    final String _sql = "Select * from outline order by dateValue desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfTitleEng = _cursor.getColumnIndexOrThrow("title_eng");
      final int _cursorIndexOfDateValue = _cursor.getColumnIndexOrThrow("dateValue");
      final int _cursorIndexOfUserRating = _cursor.getColumnIndexOrThrow("user_rating");
      final int _cursorIndexOfAudienceRating = _cursor.getColumnIndexOrThrow("audience_rating");
      final int _cursorIndexOfReviewerRating = _cursor.getColumnIndexOrThrow("reviewer_rating");
      final int _cursorIndexOfReservationRate = _cursor.getColumnIndexOrThrow("reservation_rate");
      final int _cursorIndexOfReservationGrade = _cursor.getColumnIndexOrThrow("reservation_grade");
      final int _cursorIndexOfGrade = _cursor.getColumnIndexOrThrow("grade");
      final int _cursorIndexOfThumb = _cursor.getColumnIndexOrThrow("thumb");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final List<outline> _result = new ArrayList<outline>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final outline _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpTitle_eng;
        _tmpTitle_eng = _cursor.getString(_cursorIndexOfTitleEng);
        final String _tmpDateValue;
        _tmpDateValue = _cursor.getString(_cursorIndexOfDateValue);
        final float _tmpUser_rating;
        _tmpUser_rating = _cursor.getFloat(_cursorIndexOfUserRating);
        final float _tmpAudience_rating;
        _tmpAudience_rating = _cursor.getFloat(_cursorIndexOfAudienceRating);
        final float _tmpReviewer_rating;
        _tmpReviewer_rating = _cursor.getFloat(_cursorIndexOfReviewerRating);
        final float _tmpReservation_rate;
        _tmpReservation_rate = _cursor.getFloat(_cursorIndexOfReservationRate);
        final int _tmpReservation_grade;
        _tmpReservation_grade = _cursor.getInt(_cursorIndexOfReservationGrade);
        final int _tmpGrade;
        _tmpGrade = _cursor.getInt(_cursorIndexOfGrade);
        final String _tmpThumb;
        _tmpThumb = _cursor.getString(_cursorIndexOfThumb);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item = new outline(_tmpId,_tmpTitle,_tmpTitle_eng,_tmpDateValue,_tmpUser_rating,_tmpAudience_rating,_tmpReviewer_rating,_tmpReservation_rate,_tmpReservation_grade,_tmpGrade,_tmpThumb,_tmpImage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
