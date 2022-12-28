package com.sample.app.controller;

import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 요청 URI
 * 		/home.hta
 * 요청 파라미터
 * 		없음
 * 요청 처리 내용
 *  	내부이동시킬 jsp 페이지 이름을 반환한다.
 *  반환값
 *  	"home.jsp"
 */
public class HomeController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "home.jsp";
	}
}
