package com.example.litepaldemo.model;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

public class CategoryLitePal extends DataSupport
{
	private int id;
	private String name;
	
	private List<NewsLitePal> newsList = new ArrayList<NewsLitePal>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<NewsLitePal> getNewsList()
	{
		return newsList;
	}

	public void setNewsList(List<NewsLitePal> newsList)
	{
		this.newsList = newsList;
	}
	

}
