package com.formation.tp14;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSQLite extends SQLiteOpenHelper {
	
	private static final String TABLE_LIVRES = "table_livres";
	private static final String COL_ID = "ID";
	private static final String COL_ISBN = "ISBN";
	private static final String COL_TITRE = "Titre";
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_LIVRES + " ("
	+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ISBN + " TEXT NOT NULL, "
	+ COL_TITRE + " TEXT NOT NULL);";

	public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_BDD);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE " + TABLE_LIVRES + ";");
		onCreate(db);
	}

}
