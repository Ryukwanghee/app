package com.sample.app.controller.user;

import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 요청 URI
 * 		user/registerform.hta
 * 요청 파라미터
 * 		없음
 * 반환값
 * 		user/registerform.jsp
 * 요청 처리 내용
 * 		내부이동시킬 jsp 페이지 이름을 반환한다.
 * 
 */
public class RegisterFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "user/registerform.jsp";
	}
}
