package com.sample.app.controller.todo;

import java.util.List;

import com.sample.app.dao.TodoCategoryDao;
import com.sample.app.vo.Category;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoRegisterFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TodoCategoryDao todoCategoryDao = TodoCategoryDao.getInstance();
		List<Category> categoryList = todoCategoryDao.getAllCategories();
		
		request.setAttribute("categories", categoryList);
		
		return "todo/form.jsp";
	}
}
