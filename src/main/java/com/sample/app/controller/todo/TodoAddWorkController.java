package com.sample.app.controller.todo;

import com.sample.app.dao.TodoWorkDao;
import com.sample.app.vo.Work;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TodoAddWorkController implements Controller {


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에서 로그인한 사용자 아이디를 조회한다.
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		
		// 요청파라미터값을 조회한다.
		int todoNo = StringUtils.stringToInt(request.getParameter("todoNo"));
		String content = request.getParameter("content");
		
		// Work객체를 생성해서 작업내용, 사용자 아이디, 할일번호를 대입한다.
		Work work = new Work();
		work.setUserId(loginUserId);
		work.setContent(content);
		work.setTodoNo(todoNo);
		
		TodoWorkDao todoWorkDao = TodoWorkDao.getInstance();
		// 작업내용정보를 테이블에 저장시킨다.
		todoWorkDao.insertWork(work);
		
		return "redirect:detail.hta?todoNo=" + todoNo;
	}
}
