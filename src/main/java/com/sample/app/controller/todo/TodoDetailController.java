package com.sample.app.controller.todo;

import java.util.List;

import com.sample.app.dao.TodoDao;
import com.sample.app.dao.TodoWorkDao;
import com.sample.app.dto.TodoDetailDto;
import com.sample.app.dto.TodoWorkListDto;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TodoDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int todoNo = StringUtils.stringToInt(request.getParameter("todoNo"));
		
		TodoDao todoDao = TodoDao.getInstance();
		TodoWorkDao todoWorkDao = TodoWorkDao.getInstance();
		
		TodoDetailDto dto = todoDao.getTodoDetailByNo(todoNo);
		List<TodoWorkListDto> works = todoWorkDao.getWorksByTodoNo(todoNo);
		
		request.setAttribute("todo", dto);
		request.setAttribute("works", works);
		
		
		return "todo/detail.jsp";
	}
}
