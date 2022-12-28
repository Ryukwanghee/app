package com.sample.app.controller.todo;

import java.util.Date;

import com.sample.app.dao.TodoDao;
import com.sample.app.vo.Todo;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TodoRegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		
		
		int categoryNo = StringUtils.stringToInt(request.getParameter("categoryNo"));
		String title = request.getParameter("title");
		Date beginDate = StringUtils.textToDate(request.getParameter("beginDate"));
		Date endDate = StringUtils.textToDate(request.getParameter("endDate"));
		String description = request.getParameter("description");
		
		Todo todo = new Todo();
		todo.setCategoryNo(categoryNo);
		todo.setTitle(title);
		todo.setUserId(loginUserId);
		todo.setBeginDate(beginDate);
		todo.setEndDate(endDate);
		todo.setDescription(description);
		
		TodoDao todoDao = TodoDao.getInstance();
		todoDao.insertTodo(todo);
		
		
		return "redirect:list.hta";
	}
}
