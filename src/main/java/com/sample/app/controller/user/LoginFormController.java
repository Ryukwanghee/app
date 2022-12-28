package com.sample.app.controller.user;

import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 요청 URI
 * 		/user/loginform.hta
 * 		/user/loginform.hta?error=fail
 * 		/user/loginform.hta?error=deny
 * 요청 파라미터
 * 		error
 * 반환값
 * 		user/loginform.jsp
 * 요청처리 내용
 * 		내부이동시킬 jsp페이지 이름을 반환한다.
 */
public class LoginFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "user/loginform.jsp";
	}
}
