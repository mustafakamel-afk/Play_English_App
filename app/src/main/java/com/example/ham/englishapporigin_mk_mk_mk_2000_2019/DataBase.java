package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by H.A.M on 7/31/2018.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "data.db";

    public DataBase(Context context) {

        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table save_data (levelNum INTEGER PRIMARY KEY , score INTEGER , rating INTEGER DEFAULT 0 )");

        ContentValues contentValues = new ContentValues();
        contentValues.put("score", 0);
        contentValues.put("levelNum", 1);
        sqLiteDatabase.insert("save_data", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS save_data");
        onCreate(sqLiteDatabase);
    }

    public int getScore() {
        int score;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select score from save_data", null);
        cursor.moveToFirst();
        score = cursor.getInt(cursor.getColumnIndex("score"));
        return score;

    }

    public void updateScore(int score) {

        this.getWritableDatabase().execSQL("UPDATE save_data SET score='" + score + "'");
    }

    public int getRating(int levelNum) {

        SQLiteDatabase write = this.getWritableDatabase();
        SQLiteDatabase read = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        Cursor cursor1 = read.rawQuery("select levelNum from save_data where levelNum='" + levelNum + "'", null);

        //Log.d("DataBase", String.valueOf(cursor1.moveToFirst()));

        if (!cursor1.moveToFirst()) {
            contentValues.put("levelNum", levelNum);
            write.insert("save_data", null, contentValues);
        }

        int rating;
        Cursor cursor2 = read.rawQuery("select rating from save_data where levelNum='" + levelNum + "'", null);
        cursor2.moveToFirst();
        rating = cursor2.getInt(cursor2.getColumnIndex("rating"));

       // Log.d("DataBase", String.valueOf(cursor2.getInt(cursor2.getColumnIndex("rating"))));
      //  Log.d("DataBase", String.valueOf(cursor1.getInt(cursor1.getColumnIndex("levelNum"))));

        return rating;

    }

    public void updateRating(int rating, int levelNum) {

        this.getWritableDatabase().execSQL("UPDATE save_data SET rating='" + rating + "' WHERE levelNum='" + levelNum + "'");

    }

}
