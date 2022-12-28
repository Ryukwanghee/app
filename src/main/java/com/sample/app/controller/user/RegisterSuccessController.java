package com.sample.app.controller.user;

import com.sample.app.dao.UserDao;
import com.sample.app.vo.User;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 요청 URI
 * 		/user/registerSuccess.hta
 * 요청 파라미터
 * 		id
 * 반환값
 * 		user/register-success.jsp
 * 요청처리 내용
 *  	요청파라미터로 전달받은 아이디로 사용자 정보를 조회해서 요청객체에 속성으로 저장한다.
 * 
 */
public class RegisterSuccessController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청파라미터값을 조회한다
		String userId = request.getParameter("id");
		
		//사용자 아이디로 사용자 정보를 조회한다.
		UserDao userDao = UserDao.getInstance();
		User user = userDao.getUserById(userId);
		
		//조회된 사용자 정보중에서 사용자이름을 요청객체에 저장한다
		request.setAttribute("username", user.getName());
		return "user/register-success.jsp";
	}
}
