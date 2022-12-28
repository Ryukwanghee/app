package com.sample.app.controller.todo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sample.app.dao.TodoDao;
import com.sample.app.dto.TodoListDto;
import com.sample.model2.Controller;
import com.sample.util.Pagination;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int currentPage = StringUtils.stringToInt(request.getParameter("page"), 1);
		
		TodoDao todoDao = TodoDao.getInstance();
		
		int totalRows = todoDao.getTotalRows();
		
		Pagination pagination = new Pagination(currentPage, totalRows);
		
		Map<String, Object> param = new HashMap<>();
		param.put("begin", pagination.getBegin());
		param.put("end", pagination.getEnd());
		
		List<TodoListDto> todoListDto = todoDao.getTodos(param);
		
		request.setAttribute("todos", todoListDto);
		request.setAttribute("pagination", pagination);
		
		return "todo/list.jsp";
	}
}
