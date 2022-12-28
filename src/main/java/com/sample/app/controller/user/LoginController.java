package com.sample.app.controller.user;

import com.sample.app.dao.UserDao;
import com.sample.app.vo.User;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * 요청 URI
 * 		/user/login.hta
 * 요청 파라미터
 * 		id
 * 		password
 * 반환값
 * 		redirect:../home.hta
 * 		redirect:loginform.hta?error=fail
 * 요청처리 내용
 * 		요청파라미터로 전달받은 id, 비밀번호에 대한 인증절차를 수행한다.
 * 		인증을 통과하지 못하면 재요청 URL(loginform.hta?error=fail)을 반환한다.
 * 		인증을 통과하면 세션에 인증된 사용자 정보를 저장한다.
 * 		재요청 URL을 반환한다.
 */
public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터값을 조회한다.
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 아이디와 비밀번호로 사용자 인증작업 수행
		UserDao userDao = UserDao.getInstance();
		User savedUser = userDao.getUserById(id);
		if (savedUser == null) {
			return "redirect:loginform.hta?error=fail";
		}
		if (!savedUser.getPassword().equals(password)) {
			return "redirect:loginform.hta?error=fail";
		}
		
		// 사용자 인증이 완료되면 사용자정보를 세션객체에 저장한다.
		// 현재 jsp에 세션객체가 없어서 세션객체에 바로 저장을 못한다. 그래서 생성(세션객체를 획득) 후 저장 => 12-26일 12시 쯤 설명하는 거 다시듣고 적기, jsp인지 뭔지 못들음
		// 로그인 성공시 세션에 담아뒀다.
		HttpSession session = request.getSession();
		session.setAttribute("loginUserId", savedUser.getId());
		
		return "redirect:../home.hta";
		
	}
}
