package com.example.litepaldemo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;


public class NewsLitePal extends DataSupport
{
	//其中id这个字段可写可不写，因为即使不写这个字段，LitePal也会在表中自动生成一个id列，毕竟每张表都一定要有主键的嘛。
	private int id;
	private String title;
	private String content;
	private Date pulishDate;
	private int commentCount;
	
	//一对一加引用
	private IntroductionLitePal introduction;
	//一对多
	private List<CommentLitePal> commentList = new ArrayList<CommentLitePal>();
	//多对多
	private List<CategoryLitePal> categoryList = new ArrayList<CategoryLitePal>();
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getPulishDate()
	{
		return pulishDate;
	}
	public void setPulishDate(Date pulishDate)
	{
		this.pulishDate = pulishDate;
	}
	public int getCommentCount()
	{
		return commentCount;
	}
	public void setCommentCount(int commentCount)
	{
		this.commentCount = commentCount;
	}
	public IntroductionLitePal getIntroduction()
	{
		return introduction;
	}
	public void setIntroduction(IntroductionLitePal introduction)
	{
		this.introduction = introduction;
	}
	public List<CommentLitePal> getCommentList()
	{
		return commentList;
	}
	public void setCommentList(List<CommentLitePal> commentList)
	{
		this.commentList = commentList;
	}
	public List<CategoryLitePal> getCategoryList()
	{
		return categoryList;
	}
	public void setCategoryList(List<CategoryLitePal> categoryList)
	{
		this.categoryList = categoryList;
	}
	
	

}
