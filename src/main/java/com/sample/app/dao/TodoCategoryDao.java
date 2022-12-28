package com.sample.app.dao;

import java.util.List;

import com.sample.app.vo.Category;
import com.sample.util.SqlMapper;

public class TodoCategoryDao {

	private static TodoCategoryDao instance = new TodoCategoryDao();
	private TodoCategoryDao() {}
	public static TodoCategoryDao getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		return (List<Category>) SqlMapper.selectList("categories.getAllCategories");
	}
}
