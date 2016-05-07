package com.arun.rajora.movies.buzz.moviesbuzz.movie_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.arun.rajora.movies.buzz.moviesbuzz.movie_data.db_contract_favourite.favourite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajor on 06-May-16.
 */
public class db_favourite_movies extends SQLiteOpenHelper{

	public static final int DATABASE_VERSION=1;
	public static final String DATABASE_NAME="moviesFav.db";
	public db_favourite_movies(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		final String SQL_CREATE_FAV_TABLE="CREATE TABLE "+favourite.TABLE_NAME+"("
				+favourite.COLUMN_ID+" STRING PRIMARY KEY );";
		db.execSQL(SQL_CREATE_FAV_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+db_contract_favourite.favourite.TABLE_NAME);
		onCreate(db);
	}
	public boolean isMovieFavourite(SQLiteDatabase db,String id){
		String cols[]={favourite.COLUMN_ID};
		Cursor result=db.query(favourite.TABLE_NAME,cols,favourite.COLUMN_ID+"=?",new String[]{id},null,null,null);
		if(result.getCount()<=0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public void addMovieAsFavourite(SQLiteDatabase db,String id)
	{
		ContentValues values = new ContentValues();
		values.put(favourite.COLUMN_ID,id);
		db.insert(favourite.TABLE_NAME,null,values);
		db.close();
	}
	public void removeMovieAsFavourite(SQLiteDatabase db,String id)
	{
		db.delete(favourite.TABLE_NAME,favourite.COLUMN_ID+"=?",new String[]{id});
		db.close();
	}
	public List<String> getFavouriteMovies(SQLiteDatabase db) {
		List<String> list = new ArrayList<String>();
		Cursor result = db.query(favourite.TABLE_NAME, new String[]{favourite.COLUMN_ID}, null, null, null, null, null);
		result.moveToFirst();
		for (int i = 0; i < result.getCount(); i++) {
			list.add(result.getString(result.getColumnIndex(favourite.COLUMN_ID)));
			result.moveToNext();
		}
		db.close();
		return list;
	}
}
