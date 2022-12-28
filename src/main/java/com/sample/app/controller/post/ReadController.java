package com.sample.app.controller.post;

import com.sample.app.dao.PostDao;
import com.sample.app.vo.Post;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 요청 URI
 * 		/post/read.hta
 * 요청 파라미터
 * 		postNo
 * 반환값
 * 		redirect:detail.hta?postNo=100
 * 요청처리 내용
 * 		요청파라미터값(게시글 번호)을 조회한다.
 * 		PostDao객체의 getPostByNo(int postNo)를 실행해서 게시글 정보를 조회한다.
 * 		게시글 정보의 조회수를 1증가시킨다.
 * 		PostDao객체의 updatePost(Post post)를 실행시켜서 변경된 게시글 정보를 테이블에 반영시킨다.
 * 		게시글 상세정보를 요청하는 재요청 URL을 응답으로 보낸다.
 * 		
 * 		댓글 수정, 삭제 해도 조회수가 증가되니까 그걸 막기위해서 중간에 이 컨트롤러를 끼워둔다. 리스트와 디테일의 중간에 하나를 더 추가한것. 리스트에서 디테일로 갈 때만 1증가 
 * 		목록에서만 ReadController를 요청하게 하는 것. detail에서는 아무리 새로고침이나 다른 작업을 해도 조회수가 증가되지 않음.
 * 
 */
public class ReadController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청파라미터값(게시글 번호)를 조회한다.
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		
		PostDao postDao = PostDao.getInstance();
		// 게시글 번호에 해당하는 게시글 정보를 조회한다.
		Post post = postDao.getPostByNo(postNo);
		// 게시글 조회수를 1증가시킨다.
		post.setReadCount(post.getReadCount() + 1);
		// 변경된 게시글 정보를 테이블에 반영시킨다.
		postDao.updatePost(post);
		
		return "redirect:detail.hta?postNo=" + postNo;
	}

}
