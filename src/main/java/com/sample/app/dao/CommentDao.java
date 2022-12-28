package com.sample.app.dao;

import java.util.List;

import com.sample.app.dto.CommentListDto;
import com.sample.app.vo.Comment;
import com.sample.util.SqlMapper;

public class CommentDao {

	private static CommentDao instance = new CommentDao();
	private CommentDao() {}
	public static CommentDao getInstance() {
		return instance;
	}
	
	public void insertComment(Comment comment) {
		SqlMapper.insert("comments.insertComment", comment);
	}
	
	@SuppressWarnings("unchecked")
	public List<CommentListDto> getCommentsByPostNo(int postNo) {
		return (List<CommentListDto>) SqlMapper.selectList("comments.getCommentsByPostNo", postNo);
	}
	
	public void deleteComment(int commentNo) {
		SqlMapper.delete("comments.deleteComment", commentNo);
	}
	
	public Comment getCommentByNo(int commentNo) {
		return (Comment) SqlMapper.selectOne("comments.getCommentByNo", commentNo);
	}
}
