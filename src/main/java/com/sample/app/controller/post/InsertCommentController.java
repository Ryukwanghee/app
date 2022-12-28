package com.sample.app.controller.post;

import com.sample.app.dao.CommentDao;
import com.sample.app.dao.PostDao;
import com.sample.app.vo.Comment;
import com.sample.app.vo.Post;
import com.sample.model2.Controller;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * 요청 URI
 * 		/post/insertComment.hta
 * 요청 파라미터
 * 		postNo
 * 		content
 * 반환값
 * 		redirect:/app/user/loginform.hta?error=deny
 * 		redirect:detail.hta?postNo=100
 * 요청처리 내용
 * 		세션에서 로그인된 사용자 정보를 조회한다.
 * 		사용자 정보가 존재하지 않으면 로그인 폼을 요청하는 재요청 URL("redirect:/app/user/loginform.hta?error=deny")을 반환한다.
 * 		요청 파라미터 값(게시글 번호, 댓글 내용)을 조회한다.
 * 		Comment 객체를 생성해서 작성자 아이디, 내용, 게시글 번호를 저장한다.
 * 		CommentDao 객체의 insertComment(Comment comment)를 호출해서 댓글정보를 저장시킨다.
 * 		게시글 번호로 PostDao의 getPostByNo(int postNo)를 실행해서 게시글 정보를 조회한다.
 * 		게시글 정보의 댓글 개수를 1 증가 시킨다.
 * 		PostDao의 updatePost(post post)를 실행시켜서 변경된 정보를 반영시킨다.
 * 
 * 		게시글 상세정보를 요청하는 재요청 URL을 응답으로 보낸다.
 * 		
 */
public class InsertCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션에 저장되어 있는 사용자 정보를 불러올 때 세션을 생성 후 조회
		// 세션객체에서 로그인된 사용자 정보를 조회한다.
		// model1 방식에서는 HttpSession을 이용한 방법이므로 session.setAttribute()나 session.getAttribute를 사용하면 됐지만 model2 방식은 HttpServletRequest 방식이므로 아래와 같이 사용한다.
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			return "redirect:/app/user/loginform.hta?error=deny";
		}
		
		// 요청 파라미터값을 조회한다.
		int postNo = StringUtils.stringToInt(request.getParameter("postNo"));
		String content = request.getParameter("content");
		
		// Comment객체를 생성해서 작성자 아이디, 내용, 게시글 번호를 저장한다
		Comment comment = new Comment();
		comment.setUserId(loginUserId);
		comment.setContent(content);
		comment.setPostNo(postNo);
		
		CommentDao commentDao = CommentDao.getInstance();
		PostDao postDao = PostDao.getInstance();
		
		// CommentDao의 insertComment(Comment comment) 를 실행해서 댓글 정보를 테이블에 저장시킨다.
		commentDao.insertComment(comment);
		
		//게시글 번호로 게시글 정보를 조회한다.
		Post post = postDao.getPostByNo(postNo);
		// 게시글 정보의 댓글 수를 증가시킨다.
		post.setCommentCount(post.getCommentCount() + 1);
		
		// PostDao객체의 updatePost(Post post)를 실행해서 변경된 게시글 정보로 갱신시킨다.
		postDao.updatePost(post);
		
		return "redirect:detail.hta?postNo=" + postNo;
		// ****return "post/list.jsp"; 이렇게 쓰면 내부이동을 한 것이고 내부이동을 하면 url은 insert.hta 로 그대로 남아있다. 그래서 안돼
				// *****(추가, 변경, 삭제 작업을 하면 그 작업을 마친 후 그 작업에서 벗어나야 한다. 그래서 내부이동을 하면 안되고 clear를 해줘야한다.)
				// ******그렇기 때문에 insert작업을 clear 시켜야 한다. 그 clear(작업에서 벗어나는 것)는 내부이동을 해선 안되고 브라우저에게 redirect(재요청)를 요청한다.
		
		//model1 의 response.sendredirect()와 같은 결인데 model2는 여기서 내부이동과 재요청 두개가 있는 것이고 변경, 추가, 삭제 작업을 한 후는 재요청을 해야한다.
	}
}
