package com.example.leeyoungjae.my.DB.DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.example.leeyoungjae.my.DB.Entity.movie;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unchecked")
public class movieDAO_Impl implements movieDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfmovie;

  public movieDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfmovie = new EntityInsertionAdapter<movie>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `movie`(`id`,`title`,`date_val`,`user_rating`,`audience_rating`,`reviewer_rating`,`reservation_rate`,`reservation_grade`,`grade`,`thumb`,`image`,`photos`,`videos`,`outlink`,`genre`,`duration`,`audience`,`synopsis`,`director`,`actor`,`_like`,`_dislike`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, movie value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDate_val() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate_val());
        }
        stmt.bindDouble(4, value.getUser_rating());
        stmt.bindDouble(5, value.getAudience_rating());
        stmt.bindDouble(6, value.getReviewer_rating());
        stmt.bindDouble(7, value.getReservation_rate());
        stmt.bindLong(8, value.getReservation_grade());
        stmt.bindLong(9, value.getGrade());
        if (value.getThumb() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getThumb());
        }
        if (value.getImage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getImage());
        }
        if (value.getPhotos() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getPhotos());
        }
        if (value.getVideos() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getVideos());
        }
        if (value.getOutlink() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getOutlink());
        }
        if (value.getGenre() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getGenre());
        }
        stmt.bindLong(16, value.getDuration());
        stmt.bindLong(17, value.getAudience());
        if (value.getSynopsis() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getSynopsis());
        }
        if (value.getDirector() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getDirector());
        }
        if (value.getActor() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getActor());
        }
        stmt.bindLong(21, value.get_like());
        stmt.bindLong(22, value.get_dislike());
      }
    };
  }

  @Override
  public void InsertMovie(movie item) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfmovie.insert(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public movie selectMovie(String m_id) {
    final String _sql = "Select * from movie where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (m_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, m_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDateVal = _cursor.getColumnIndexOrThrow("date_val");
      final int _cursorIndexOfUserRating = _cursor.getColumnIndexOrThrow("user_rating");
      final int _cursorIndexOfAudienceRating = _cursor.getColumnIndexOrThrow("audience_rating");
      final int _cursorIndexOfReviewerRating = _cursor.getColumnIndexOrThrow("reviewer_rating");
      final int _cursorIndexOfReservationRate = _cursor.getColumnIndexOrThrow("reservation_rate");
      final int _cursorIndexOfReservationGrade = _cursor.getColumnIndexOrThrow("reservation_grade");
      final int _cursorIndexOfGrade = _cursor.getColumnIndexOrThrow("grade");
      final int _cursorIndexOfThumb = _cursor.getColumnIndexOrThrow("thumb");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfPhotos = _cursor.getColumnIndexOrThrow("photos");
      final int _cursorIndexOfVideos = _cursor.getColumnIndexOrThrow("videos");
      final int _cursorIndexOfOutlink = _cursor.getColumnIndexOrThrow("outlink");
      final int _cursorIndexOfGenre = _cursor.getColumnIndexOrThrow("genre");
      final int _cursorIndexOfDuration = _cursor.getColumnIndexOrThrow("duration");
      final int _cursorIndexOfAudience = _cursor.getColumnIndexOrThrow("audience");
      final int _cursorIndexOfSynopsis = _cursor.getColumnIndexOrThrow("synopsis");
      final int _cursorIndexOfDirector = _cursor.getColumnIndexOrThrow("director");
      final int _cursorIndexOfActor = _cursor.getColumnIndexOrThrow("actor");
      final int _cursorIndexOfLike = _cursor.getColumnIndexOrThrow("_like");
      final int _cursorIndexOfDislike = _cursor.getColumnIndexOrThrow("_dislike");
      final movie _result;
      if(_cursor.moveToFirst()) {
        _result = new movie();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _result.setTitle(_tmpTitle);
        final String _tmpDate_val;
        _tmpDate_val = _cursor.getString(_cursorIndexOfDateVal);
        _result.setDate_val(_tmpDate_val);
        final float _tmpUser_rating;
        _tmpUser_rating = _cursor.getFloat(_cursorIndexOfUserRating);
        _result.setUser_rating(_tmpUser_rating);
        final float _tmpAudience_rating;
        _tmpAudience_rating = _cursor.getFloat(_cursorIndexOfAudienceRating);
        _result.setAudience_rating(_tmpAudience_rating);
        final float _tmpReviewer_rating;
        _tmpReviewer_rating = _cursor.getFloat(_cursorIndexOfReviewerRating);
        _result.setReviewer_rating(_tmpReviewer_rating);
        final float _tmpReservation_rate;
        _tmpReservation_rate = _cursor.getFloat(_cursorIndexOfReservationRate);
        _result.setReservation_rate(_tmpReservation_rate);
        final int _tmpReservation_grade;
        _tmpReservation_grade = _cursor.getInt(_cursorIndexOfReservationGrade);
        _result.setReservation_grade(_tmpReservation_grade);
        final int _tmpGrade;
        _tmpGrade = _cursor.getInt(_cursorIndexOfGrade);
        _result.setGrade(_tmpGrade);
        final String _tmpThumb;
        _tmpThumb = _cursor.getString(_cursorIndexOfThumb);
        _result.setThumb(_tmpThumb);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _result.setImage(_tmpImage);
        final String _tmpPhotos;
        _tmpPhotos = _cursor.getString(_cursorIndexOfPhotos);
        _result.setPhotos(_tmpPhotos);
        final String _tmpVideos;
        _tmpVideos = _cursor.getString(_cursorIndexOfVideos);
        _result.setVideos(_tmpVideos);
        final String _tmpOutlink;
        _tmpOutlink = _cursor.getString(_cursorIndexOfOutlink);
        _result.setOutlink(_tmpOutlink);
        final String _tmpGenre;
        _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
        _result.setGenre(_tmpGenre);
        final int _tmpDuration;
        _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
        _result.setDuration(_tmpDuration);
        final int _tmpAudience;
        _tmpAudience = _cursor.getInt(_cursorIndexOfAudience);
        _result.setAudience(_tmpAudience);
        final String _tmpSynopsis;
        _tmpSynopsis = _cursor.getString(_cursorIndexOfSynopsis);
        _result.setSynopsis(_tmpSynopsis);
        final String _tmpDirector;
        _tmpDirector = _cursor.getString(_cursorIndexOfDirector);
        _result.setDirector(_tmpDirector);
        final String _tmpActor;
        _tmpActor = _cursor.getString(_cursorIndexOfActor);
        _result.setActor(_tmpActor);
        final int _tmp_like;
        _tmp_like = _cursor.getInt(_cursorIndexOfLike);
        _result.set_like(_tmp_like);
        final int _tmp_dislike;
        _tmp_dislike = _cursor.getInt(_cursorIndexOfDislike);
        _result.set_dislike(_tmp_dislike);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
