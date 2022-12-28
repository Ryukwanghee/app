package com.sample.app.controller.user;

import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * 얘가 로그아웃 실행
 * 요청 URI
 * 		/user/logout.hta
 * 요청 파라미터
 * 		없음
 * 반환값 , 어디서 로그아웃 할 지 모르니까 절대경로로
 * 		redirect:/app/home.hta
 * 요청처리 내용
 * 		세션객체를 무효화 시킨다.
 * 		홈화면을 요청하는 재요청 URL("redirect:/app/home.hta")을 반환한다.
 */
public class LogoutController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션객체를 무효화 시킨다.
		/*
		 * request.getSession() 과 request.getSession(true)
		 * 	=>  HttpSession객체를 반환한다.
		 * 		요청객체에 세션아이디가 없으면 새로운 HttpSession객체를 생성해서 반환한다.
		 * 		요청객체에 세션아이디가 있지만, 세션아이디와 일치하는 세션객체가 존재하지 않으면 새로운 HttpSession객체를 생성해서 반환한다.
		 * 		요청객체에 세션아이디가 있고, 세션아이디와 일치하는 세션객체가 존재하면 그 세션을 반환한다.
		 * request.getSession(false)
		 * =>	요청객체에 세션아이디가 없으면 null을 반환한다.
		 * 		요청객체에 세션아이디가 있지만, 세션아이디와 일치하는 세션객체가 존재하지 않으면 null을 반환한다.
		 * 		요청객체에 세션아이디가 있고, 세션아이디와 일치하는 세션객체가 존재하면 그 세션을 반환한다.
		 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		return "redirect:/app/home.hta";	//앞에 redirect 는 그냥 정한것
	}
}
