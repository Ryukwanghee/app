package com.sample.app.controller.post;

import com.sample.app.dao.PostDao;
import com.sample.app.vo.Post;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * 요청 URI
 * 		/post/modify.hta
 * 요청 파라미터
 * 		postNo
 * 		title
 * 		content
 * 반환값
 * 		redirect:/app/user/loginform.hta?error=deny
 * 		redirect:detail.hta?postNo=100&error=post
 * 		redirect:detail.hta?postNo=100
 * 요청처리 내용
 * 		로그인 상태가 아니면 재요청 URL을 반환한다.
 * 		요청파라미터값(게시글번호, 제목, 내용)을 조회한다.
 * 		PostDao객체의 getPostByNo(int postNo)를 실행해서 게시글 정보를 조회한다.
 * 		게시글 작성자와 로그인한 사용자가 서로 다르면 재요청 URL을 반환한다.
 * 		게시글의 제목과 내용을 요청파라미터로 조회한 값으로 변경한다.
 * 		PostDao객체의 updatePost(Post post)를 실행해서 변경된 게시글 정보를 테이블에 반영시킨다.
 * 
 * 		게시글 상세정보를 요청하는 재요청 URL을 반환한다.
 */
public class ModifyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에서 로그인정보를 조회한다.
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("loginUserId"); //로그인시 loginUserId라는 이름으로 객체를 저장. 모든 jsp,컨트롤에서 다 꺼내올 수 있다.
		if (loginUserId == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		
		// 요청파라미터 값을 조회한다.
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 게시글 번호에 해당하는 게시글 정보를 조회한다.
		PostDao postDao = PostDao.getInstance();
		Post post = postDao.getPostByNo(postNo);
		
		// 게시글 사용자와 로그인한 사용자가 일치하지 않으면 재요청 URL을 반환한다.
		if (!post.getUserId().equals(loginUserId)) {
			return "redirect:detail.hta?postNo=" + postNo + "&error=post";
		}
		
		// 조회된 게시글 정보의 제목과 내용을 변경한다.
		post.setTitle(title);
		post.setContent(content);
		
		// 변경된 게시글 정보를 테이블에 반영시킨다.
		postDao.updatePost(post);
		
		
		return "redirect:detail.hta?postNo=" + postNo;
	}
}
