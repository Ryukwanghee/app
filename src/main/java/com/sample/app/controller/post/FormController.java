package com.sample.app.controller.post;

import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * 요청 URI
 * 		/post/form.hta
 * 요청 파라미터
 * 		없음
 * 반환값
 * 		post/form.jsp
 * 		redirect:/app/user/loginform.hta?error=deny
 * 요청처리 내용
 * 		세션에서 로그인된 사용자 정보를 조회한다.
 * 		사용자정보가 존재하지 않으면 로그인폼을 요청하는 재요청 URL("redirect:/app/user/loginform.hta?error=deny")을 반환한다.
 * 		세션에 로그인된 사용자 정보가 존재하면 post/form.jsp를 반환한다.
 */
public class FormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션에서 로그인된 사용자정보를 조회한다.
		// 로그인된 사용자 정보가 존재하지 않으면 로그인폼을 요청하는 재요청 URL을 반환한다.
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUserId") == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		
		// 세션에 로그인된 사용자정보가 존재하면 post/form.jsp를 반환한다.
		return "post/form.jsp";
	}
}
