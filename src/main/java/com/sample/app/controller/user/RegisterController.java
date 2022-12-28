package com.sample.app.controller.user;

import com.sample.app.dao.UserDao;
import com.sample.app.vo.User;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 요청 URI
 * 		/user/register.hta
 * 요청 파라미터
 * 		name
 * 		id
 * 		password
 * 		email
 * 반환값
 * 		redirect:registerSuccess.hta
 * 		redirect:registerform.hta?error=fail
 * 요청처리내용
 * 		요청 파라미터 값(회원가입 정보)을 조회한다.
 * 		동일한 아이디가 존재하면 회원가입폼을 요청하는 재요청 URL("redirect:registerform.hta?error=fail")을 반환한다.
 * 		동일한 이메일이 존재하면 회원가입폼을 요청하는 재요청 URL("redirect:registerform.hta?error=fail")을 반환한다.
 * 		요청 파라미터값을 User객체를 생성해서 대입한다.
 * 		UserDao의 void insertUser(User user) 메소드를 호출해서 회원정보를 저장시킨다.
 * 		
 * 		로그인 성공 페이지를 요청하는 재요청 URL("redirect:registerSuccess.hta")을 반환한다..
 */
public class RegisterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청 파라미터 값을 조회한다.
		String name =request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		// 동일한 아이디가 존재하는지 확인한다.
		UserDao userDao = UserDao.getInstance();
		User savedUser = userDao.getUserById(id);
		if (savedUser != null) {
			return "redirect:registerform.hta?error=fail";
		}
		savedUser = userDao.getUserByEmail(email);
		if (savedUser != null) {
			return "redirect:registerform.hta?error=fail";
		}
		
		// User를 객체를 생성해서 사용자 정보 저장
		User user = new User();
		user.setName(name);
		user.setId(id);
		user.setPassword(password);
		user.setEmail(email);
		// UserDao의 insertUser(User user)를 호출해서 테이블에 저장시킨다.
		userDao.insertUser(user);
		
		return "redirect:registerSuccess.hta?id=" + id;
	}
}
