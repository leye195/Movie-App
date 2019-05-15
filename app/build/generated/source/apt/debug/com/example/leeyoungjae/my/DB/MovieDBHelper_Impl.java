package com.example.leeyoungjae.my.DB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.example.leeyoungjae.my.DB.DAO.movieDAO;
import com.example.leeyoungjae.my.DB.DAO.movieDAO_Impl;
import com.example.leeyoungjae.my.DB.DAO.outlineDAO;
import com.example.leeyoungjae.my.DB.DAO.outlineDAO_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class MovieDBHelper_Impl extends MovieDBHelper {
  private volatile outlineDAO _outlineDAO;

  private volatile movieDAO _movieDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `outline` (`id` INTEGER NOT NULL, `title` TEXT, `title_eng` TEXT, `dateValue` TEXT, `user_rating` REAL NOT NULL, `audience_rating` REAL NOT NULL, `reviewer_rating` REAL NOT NULL, `reservation_rate` REAL NOT NULL, `reservation_grade` INTEGER NOT NULL, `grade` INTEGER NOT NULL, `thumb` TEXT, `image` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `movie` (`id` INTEGER NOT NULL, `title` TEXT, `date_val` TEXT, `user_rating` REAL NOT NULL, `audience_rating` REAL NOT NULL, `reviewer_rating` REAL NOT NULL, `reservation_rate` REAL NOT NULL, `reservation_grade` INTEGER NOT NULL, `grade` INTEGER NOT NULL, `thumb` TEXT, `image` TEXT, `photos` TEXT, `videos` TEXT, `outlink` TEXT, `genre` TEXT, `duration` INTEGER NOT NULL, `audience` INTEGER NOT NULL, `synopsis` TEXT, `director` TEXT, `actor` TEXT, `_like` INTEGER NOT NULL, `_dislike` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"fca33c77d1259a639460e4c9f1e8cd58\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `outline`");
        _db.execSQL("DROP TABLE IF EXISTS `movie`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsOutline = new HashMap<String, TableInfo.Column>(12);
        _columnsOutline.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsOutline.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsOutline.put("title_eng", new TableInfo.Column("title_eng", "TEXT", false, 0));
        _columnsOutline.put("dateValue", new TableInfo.Column("dateValue", "TEXT", false, 0));
        _columnsOutline.put("user_rating", new TableInfo.Column("user_rating", "REAL", true, 0));
        _columnsOutline.put("audience_rating", new TableInfo.Column("audience_rating", "REAL", true, 0));
        _columnsOutline.put("reviewer_rating", new TableInfo.Column("reviewer_rating", "REAL", true, 0));
        _columnsOutline.put("reservation_rate", new TableInfo.Column("reservation_rate", "REAL", true, 0));
        _columnsOutline.put("reservation_grade", new TableInfo.Column("reservation_grade", "INTEGER", true, 0));
        _columnsOutline.put("grade", new TableInfo.Column("grade", "INTEGER", true, 0));
        _columnsOutline.put("thumb", new TableInfo.Column("thumb", "TEXT", false, 0));
        _columnsOutline.put("image", new TableInfo.Column("image", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOutline = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOutline = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOutline = new TableInfo("outline", _columnsOutline, _foreignKeysOutline, _indicesOutline);
        final TableInfo _existingOutline = TableInfo.read(_db, "outline");
        if (! _infoOutline.equals(_existingOutline)) {
          throw new IllegalStateException("Migration didn't properly handle outline(com.example.leeyoungjae.my.DB.Entity.outline).\n"
                  + " Expected:\n" + _infoOutline + "\n"
                  + " Found:\n" + _existingOutline);
        }
        final HashMap<String, TableInfo.Column> _columnsMovie = new HashMap<String, TableInfo.Column>(22);
        _columnsMovie.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsMovie.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsMovie.put("date_val", new TableInfo.Column("date_val", "TEXT", false, 0));
        _columnsMovie.put("user_rating", new TableInfo.Column("user_rating", "REAL", true, 0));
        _columnsMovie.put("audience_rating", new TableInfo.Column("audience_rating", "REAL", true, 0));
        _columnsMovie.put("reviewer_rating", new TableInfo.Column("reviewer_rating", "REAL", true, 0));
        _columnsMovie.put("reservation_rate", new TableInfo.Column("reservation_rate", "REAL", true, 0));
        _columnsMovie.put("reservation_grade", new TableInfo.Column("reservation_grade", "INTEGER", true, 0));
        _columnsMovie.put("grade", new TableInfo.Column("grade", "INTEGER", true, 0));
        _columnsMovie.put("thumb", new TableInfo.Column("thumb", "TEXT", false, 0));
        _columnsMovie.put("image", new TableInfo.Column("image", "TEXT", false, 0));
        _columnsMovie.put("photos", new TableInfo.Column("photos", "TEXT", false, 0));
        _columnsMovie.put("videos", new TableInfo.Column("videos", "TEXT", false, 0));
        _columnsMovie.put("outlink", new TableInfo.Column("outlink", "TEXT", false, 0));
        _columnsMovie.put("genre", new TableInfo.Column("genre", "TEXT", false, 0));
        _columnsMovie.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0));
        _columnsMovie.put("audience", new TableInfo.Column("audience", "INTEGER", true, 0));
        _columnsMovie.put("synopsis", new TableInfo.Column("synopsis", "TEXT", false, 0));
        _columnsMovie.put("director", new TableInfo.Column("director", "TEXT", false, 0));
        _columnsMovie.put("actor", new TableInfo.Column("actor", "TEXT", false, 0));
        _columnsMovie.put("_like", new TableInfo.Column("_like", "INTEGER", true, 0));
        _columnsMovie.put("_dislike", new TableInfo.Column("_dislike", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovie = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovie = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovie = new TableInfo("movie", _columnsMovie, _foreignKeysMovie, _indicesMovie);
        final TableInfo _existingMovie = TableInfo.read(_db, "movie");
        if (! _infoMovie.equals(_existingMovie)) {
          throw new IllegalStateException("Migration didn't properly handle movie(com.example.leeyoungjae.my.DB.Entity.movie).\n"
                  + " Expected:\n" + _infoMovie + "\n"
                  + " Found:\n" + _existingMovie);
        }
      }
    }, "fca33c77d1259a639460e4c9f1e8cd58", "1f0842a8cb20d4d8ebc6c0606d84069a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "outline","movie");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `outline`");
      _db.execSQL("DELETE FROM `movie`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public outlineDAO getOutlineDAO() {
    if (_outlineDAO != null) {
      return _outlineDAO;
    } else {
      synchronized(this) {
        if(_outlineDAO == null) {
          _outlineDAO = new outlineDAO_Impl(this);
        }
        return _outlineDAO;
      }
    }
  }

  @Override
  public movieDAO getMovieDAO() {
    if (_movieDAO != null) {
      return _movieDAO;
    } else {
      synchronized(this) {
        if(_movieDAO == null) {
          _movieDAO = new movieDAO_Impl(this);
        }
        return _movieDAO;
      }
    }
  }
}
