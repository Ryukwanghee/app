package com.sample.app.controller.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sample.app.dao.PostDao;
import com.sample.app.dto.PostListDto;
import com.sample.model2.Controller;
import com.sample.util.Pagination;
import com.sample.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 요청 URI
 * 		/post/list.hta
 * 		/post/list.hta?page=2
 * 요청 파라미터
 * 		page
 * 반환값
 * 		post/list.jsp
 * 요청처리내용
 * 		요청파라미터에서 페이지번호를 조회한다.
 * 		총 데이터개수를 조회한다.
 * 		페이징처리를 위한 Pagination객체 생성
 * 		게시글 목록 조회범위를 계산해서 Map객체에 저장한다.
 * 		PostDao의 getPosts(Map<String, Object> param) 메소드를 실행해서 게시글을 조회한다.
 * 
 * 		조회된 게시글 목록을 요청객체의 속성으로 저장한다.
 * 		생성된 Pagination 객체를 요청객체의 속성으로 저장한다.
 * 
 * 		게시글 목록정보와 페이징정보를 표시하는 jsp 페이지를 반환한다.
 */
public class ListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 요청 파라미터값을 조회한다.
		int currentPage = StringUtils.stringToInt(request.getParameter("page"), 1);
		
		PostDao postDao = PostDao.getInstance();
		// 총 게시글 개수를 조회하고, Pagination객체를 생성한다.
		int totalRows = postDao.getTotalRows();
		Pagination pagination = new Pagination(currentPage, totalRows);
		
		// 게시글 목록 조회에 필요한 정보를 저장하는 Map객체를 생성한다
		Map<String, Object> param = new HashMap<>();
		param.put("begin", pagination.getBegin());
		param.put("end", pagination.getEnd());
		
		//PostDao의 getPosts(Map<String, Object> param)메소드를 실행시켜서 게시글 목록을 조회한다.
		List<PostListDto> postListDto = postDao.getPosts(param);
		
		// 요청객체의 속성으로 게시글 목록정보를 저장한다.
		request.setAttribute("posts", postListDto);
		// 요청객체의 속성으로 페이징처리 정보를 저장한다.
		request.setAttribute("pagination", pagination);
		
		// 내부이동할 jsp 페이지를 반환한다.
		return "post/list.jsp";
	}
}
