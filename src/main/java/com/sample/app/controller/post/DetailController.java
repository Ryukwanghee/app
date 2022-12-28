package com.sample.app.controller.post;

import java.util.List;

import com.sample.app.dao.CommentDao;
import com.sample.app.dao.PostDao;
import com.sample.app.dto.CommentListDto;
import com.sample.app.dto.PostDetailDto;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 요청 URI
 * 		/post/detail.hta
 * 요청 파라미터
 * 		postNo
 * 반환값
 * 		post/detail.jsp
 * 요청처리 내용
 * 		요청파라미터로 전달된 게시글번호를 조회한다.
 * 		PostDao객체의 getPostByNo(int postNo)를 실행해서 게시글 번호에 해당하는 게시글 상세정보를 조회한다.
 * 		CommentDao객체의 getCommentsByPostNo(int postNo)를 실행해서 게시글 번호에 해당하는 댓글목록을 조회한다.
 * 		요청객체의 속성으로 조회된 게시글 정보를 저장한다.
 * 		요청객체의 속성으로 조회된 댓글목록 정보를 저장한다.
 * 
 * 		게시글 상세정보와 댓글목록을 표시하는 jsp 페이지를 반환한다.
 * 
 */
public class DetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//요청 파라미터 값을 조회한다.
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		
		PostDao postDao = PostDao.getInstance();
		CommentDao commentDao = CommentDao.getInstance();
		
		
		// 게시글 번호에 해당하는 게시글 상세정보를 조회한다.
		PostDetailDto dto = postDao.getPostDetailByNo(postNo);
		// 게시글 번호에 해당하는 댓글 목록 정보를 조회한다.
		List<CommentListDto> commentListDtos = commentDao.getCommentsByPostNo(postNo);
		
		// 요청객체의 속성으로 게시글정보와 댓글목록정보를 저장한다.
		request.setAttribute("post", dto);
		request.setAttribute("comments", commentListDtos);

		return "post/detail.jsp";
	}
}
