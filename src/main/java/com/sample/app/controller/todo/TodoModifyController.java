package com.sample.app.controller.todo;

import java.util.Date;

import com.sample.app.dao.TodoDao;
import com.sample.app.vo.Todo;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TodoModifyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에서 로그인아이디를 조회한다.
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		
		// 요청파라미터값을 조회한다.
		int todoNo = StringUtils.stringToInt(request.getParameter("todoNo"));
		int categoryNo = StringUtils.stringToInt(request.getParameter("categoryNo"));
		String title = request.getParameter("title");
		Date beginDate = StringUtils.textToDate(request.getParameter("beginDate"));
		Date endDate = StringUtils.textToDate(request.getParameter("endDate"));
		String status = request.getParameter("status");
		String description = request.getParameter("description");
		
		TodoDao todoDao = TodoDao.getInstance();
		
		// 할일번호에 해당하는 할일 정보 조회하기
		Todo todo = todoDao.getTodoByNo(todoNo);
		// 조회된 할일정보의 작성자 아이디와 로그인한 사용자 아이가 서로 다르면 재요청 URL을 반환한다.
		if (!todo.getUserId().equals(loginUserId)) {
			return "redirect:detail.hta?todoNo=" + todoNo + "&error=todo";
		}
		// 조회된 할일정보를 수정폼의 입력값으로 변경한다.
		todo.setCategoryNo(categoryNo);
		todo.setTitle(title);
		todo.setBeginDate(beginDate);
		todo.setEndDate(endDate);
		todo.setStatus(status);
		// 할일상태가 "완료"인 경우 현재날짜를 완료날짜로 입력한다.
		if ("완료".equals(status) && todo.getCompleteDate() == null) {
			todo.setCompleteDate(new Date());
		} 
		todo.setDescription(description);
		
		// 변경된 할일정보를 테이블에 반영시킨다.
		todoDao.updateTodo(todo);
		
		return "redirect:detail.hta?todoNo=" + todoNo;
	}
}