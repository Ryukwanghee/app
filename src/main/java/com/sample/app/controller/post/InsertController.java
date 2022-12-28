package com.sample.app.controller.post;

import com.sample.app.dao.PostDao;
import com.sample.app.vo.Post;
import com.sample.model2.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * 요청 URI
 * 		/post/insert.hta
 * 요청 파라미터
 * 		title
 * 		content
 * 반환값
 * 		redirect:/app/user/loginform.hta?error=deny
 * 		redirect:list.hta
 * 요청처리 내용
 * 		세션에서 로그인된 사용자 정보를 조회한다.
 * 		사용자 정보가 존재하지 않으면 로그인폼을 요청하는 재요청 URL("redirect:/app/user/loginform.hta?error=deny")을 반환한다.
 * 		요청파라미터값을 조회한다.
 * 		Post객체를 생성해서 제목, 로그인한 사용자 아이디, 내용을 저장한다.
 * 		PostDao의 insertPost(Post post)를 호출해서 게시글 정보를 저장시킨다.
 * 		게시글 목록을 요청하는 재요청 URL을 반환한다.
 */
public class InsertController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//세션에서 로그인된 사용자 정보를 조회한다.
		// 로그인된 사용자 정보가 존재하지 않으면 로그인폼을 요청하는 재요청 URL을 반환한다.
		HttpSession session= request.getSession();
		if (session.getAttribute("loginUserId") == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		String loginUserId= (String) session.getAttribute("loginUserId");
		
		// 요청파라미터값을 조회한다.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// Post객체를 생성해서 게시글 제목, 내용, 로그인한 사용자 아이디를 대입한다
		Post post = new Post();
		post.setTitle(title);
		post.setUserId(loginUserId);
		post.setContent(content);
		
		// PostDao객체의 insertPost(Post post) 메소드를 호출하여 게시글 정보를 저장한다.
		PostDao postDao = PostDao.getInstance();
		postDao.insertPost(post);
		
		// 게시글 목록을 요청하는 재요청 URL을 반환한다.
		return "redirect:list.hta";
		// ****return "post/list.jsp"; 이렇게 쓰면 내부이동을 한 것이고 내부이동을 하면 url은 insert.hta 로 남아있다. 그래서 안돼
		// *****(추가, 변경, 삭제 작업을 하면 그 작업을 마친 후 그 작업에서 벗어나야 한다. 그래서 내부이동을 하면 안되고 clear를 해줘야한다.)
		// ******그렇기 때문에 insert작업을 clear 시켜야 한다. 그 clear(작업에서 벗어나는 것)는 내부이동을 해선 안되고 브라우저에게 redirect(재요청)를 요청한다.
	}
}
