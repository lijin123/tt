package com.example.litepaldemo;

import java.util.Date;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import com.example.litepaldemo.model.CommentLitePal;
import com.example.litepaldemo.model.NewsLitePal;
import com.example.litepaldemo.nomal.MySQLiteHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	private Button mButton_LitePal, mButton_Nomal, mButton_NomalSave, mButton_LitePalSave, mButton_Comments,
			mButton_UpdataNomal, mButton_DeleteNomal, mButton_LitePalUpdate, mButton_LitePalUpdateAll;
	private MySQLiteHelper mDbHelper;
	private SQLiteDatabase mNomalDb;
	private SQLiteDatabase mLitePalDb;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mButton_LitePal = (Button) this.findViewById(R.id.btn_litepal);
		mButton_LitePal.setOnClickListener(this);
		mButton_Nomal = (Button) this.findViewById(R.id.btn_nomal);
		mButton_Nomal.setOnClickListener(this);
		mButton_NomalSave = (Button) this.findViewById(R.id.btn_savenomal);
		mButton_NomalSave.setOnClickListener(this);
		mButton_LitePalSave = (Button) this.findViewById(R.id.btn_savelitepal);
		mButton_LitePalSave.setOnClickListener(this);
		mButton_Comments = (Button) this.findViewById(R.id.btn_commentlitepal);
		mButton_Comments.setOnClickListener(this);
		mButton_UpdataNomal = (Button) this.findViewById(R.id.btn_updatanomal);
		mButton_UpdataNomal.setOnClickListener(this);
		mButton_DeleteNomal = (Button) this.findViewById(R.id.btn_deletenomal);
		mButton_DeleteNomal.setOnClickListener(this);
		mButton_LitePalUpdate = (Button) this.findViewById(R.id.btn_updatelitepal);
		mButton_LitePalUpdate.setOnClickListener(this);
		mButton_LitePalUpdateAll = (Button) this.findViewById(R.id.btn_updatealllitepal);
		mButton_LitePalUpdateAll.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_litepal:
			mLitePalDb = Connector.getDatabase();
			Toast.makeText(MainActivity.this, "使用LitePal创建成功", 1).show();
			break;
		case R.id.btn_nomal:
			mDbHelper = new MySQLiteHelper(MainActivity.this, "nomalDemo.db", null, 3);
			mNomalDb = mDbHelper.getWritableDatabase();
			Toast.makeText(MainActivity.this, "使用SQLiteOpenHelper创建成功", 1).show();
			break;
		case R.id.btn_savenomal:
			ContentValues valuesnomal = new ContentValues();
			valuesnomal.put("title", "这是一条新闻标题");
			valuesnomal.put("content", "只是一条新闻内容");
			valuesnomal.put("publishdate", System.currentTimeMillis());
			long id = mNomalDb.insert("newsnomal", null, valuesnomal);
			Toast.makeText(MainActivity.this, "id=" + id + "使用SQLiteOpenHelper创建并且插入成功", 1).show();
			break;
		case R.id.btn_savelitepal:
			NewsLitePal news = new NewsLitePal();
			news.setTitle("这是一条新闻标题");
			news.setContent("这是一条新闻内容");
			news.setPulishDate(new Date());
			news.save();
			if (news.save())
			{
				Toast.makeText(MainActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
			} else
			{
				Toast.makeText(MainActivity.this, "存储失败", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btn_commentlitepal:
			CommentLitePal comment1 = new CommentLitePal();
			comment1.setContent("good");
			comment1.setPublishDate(new Date());
			comment1.save();
			CommentLitePal comment2 = new CommentLitePal();
			comment2.setContent("nice");
			comment2.setPublishDate(new Date());
			comment2.save();
			NewsLitePal newsLitePal = new NewsLitePal();
			newsLitePal.getCommentList().add(comment1);
			newsLitePal.getCommentList().add(comment2);
			newsLitePal.setTitle("第二条新闻标题");
			newsLitePal.setContent("第二条新闻内容");
			newsLitePal.setPulishDate(new Date());
			newsLitePal.setCommentCount(newsLitePal.getCommentList().size());
			newsLitePal.save();
			break;
		case R.id.btn_updatanomal:
			ContentValues nomalUpdateValues = new ContentValues();
			nomalUpdateValues.put("title", "吹牛逼");
			mNomalDb.update("newsnomal", nomalUpdateValues, "id=?", new String[]
			{ "1" });
			Toast.makeText(MainActivity.this, "传统的Updata成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_deletenomal:
			mNomalDb.delete("newsnomal", "id=?", new String[]
			{ "1" });
			Toast.makeText(MainActivity.this, "传统的Delete成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_updatelitepal:
			ContentValues litePalUpdateValues = new ContentValues();
			litePalUpdateValues.put("title", "HTC M9发布");
			DataSupport.update(NewsLitePal.class, litePalUpdateValues, 1);
			Toast.makeText(MainActivity.this, "LitePal的updata成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_updatealllitepal:
			ContentValues litePalUpdateAllValues = new ContentValues();
			litePalUpdateAllValues.put("title", "三星S6发布");
			DataSupport.updateAll(NewsLitePal.class, litePalUpdateAllValues, "title=?", "这是一条新闻标题");
			Toast.makeText(MainActivity.this, "LitePal的updataAll成功", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
