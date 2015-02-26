package com.example.litepaldemo.nomal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper
{
	public static final String CREATE_NEWS = "create table newsnomal (" + "id integer primary key autoincrement, "
			+ "title text, " + "content text, " + "publishdate integer," + "commentcount integer)";

	// 新增一张表
	public static final String CREATE_COMMENT = "create table commentnomal(" + "id integer primary key autoincrement,"
			+ "content text," + "publishdate integer)";

	public MySQLiteHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_NEWS);
		db.execSQL(CREATE_COMMENT);
	}

	// 版本号增加后会调用该方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		switch (oldVersion)
		{
		case 1:
			db.execSQL(CREATE_COMMENT);
			break;
		case 2:
			db.execSQL("alter table commentnomal add column publishdate integer");
			break;
		default:
			break;
		}
	}

}
