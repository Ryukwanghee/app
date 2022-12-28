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
 * 		/post/modifyform.hta
 * 요청 파라미터
 * 		postNo
 * 반환값
 * 		redirect:/app/user/loginform.hta?error=deny		로그인상태가 아닐 때(절대경로)
 * 		redirect:detail.hta?postNo=100&error=post		게시글작성자와 로그인한 사용자가 서로 다를 때
 * 		post/modifyform.jsp								게시글 정보를 조회해서 수정폼에 표시할 준비가 됐을 때
 * 요청처리 내용
 * 		세션에서 로그인 정보를 조회해서 로그인상태가 아니면 재요청URL을 반환한다.
 * 		요청파라미터값(게시글번호)를 조회한다.
 * 		PostDao의 getPostByNo(int postNo)를 실행해서 게시글번호에 해당하는 게시글 정보를 조회한다.
 * 		게시글 작성자와 로그인한 사용자의 아이디를 비교해서 서로 다르면 재요청 URL을 반환한다.
 * 		요청객체에 조회된 게시글 정보를 저장한다.
 * 		게시글 수정화면으로 내부이동할 수 있도록 post/modifyform.jsp를 반환한다.
 */
public class ModifyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션에서 로그인된 사용자 정보를 조회한다.
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		
		// 요청파라미터값(게시글 번호)를 조회한다.
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		
		PostDao postDao = PostDao.getInstance();
		// 게시글 번호에 해당하는 게시글 정보를 조회한다.
		Post post = postDao.getPostByNo(postNo);
		if (post == null) {
			return "redirect:list.hta";
		}
		// 게시글 작성자와 로그인한 사용자가 일치하지 않으면 재요청 URL을 응답으로 보낸다.
		if (!post.getUserId().equals(loginUserId)) {
			return "redirect:detail.hta?postNo=" + postNo + "&error=post";
		}
		
		// 요청객체의 속성으로 조회된 게시글 정보를 저장시킨다.
		request.setAttribute("post", post);
		
		return "post/modifyform.jsp";
	}
}
