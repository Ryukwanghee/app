package com.sample.app.dao;

import java.util.List;
import java.util.Map;

import com.sample.app.dto.PostDetailDto;
import com.sample.app.dto.PostListDto;
import com.sample.app.vo.Post;
import com.sample.util.SqlMapper;

public class PostDao {

	private static PostDao instance = new PostDao();
	private PostDao() {}
	public static PostDao getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<PostListDto> getPosts(Map<String, Object> param) {
		return (List<PostListDto>) SqlMapper.selectList("posts.getPosts", param);
	}
	
	public int getTotalRows() {
		return (Integer) SqlMapper.selectOne("posts.getTotalRows");
	}
	
	public void insertPost(Post post) {
		SqlMapper.insert("posts.insertPost", post);
	}
	
	public Post getPostByNo(int postNo) {
		return (Post) SqlMapper.selectOne("posts.getPostByNo", postNo);
	}
	public PostDetailDto getPostDetailByNo(int postNo) {
		return (PostDetailDto) SqlMapper.selectOne("posts.getPostDetailByNo", postNo);
	}
	
	public void updatePost(Post post) {
		SqlMapper.update("posts.updatePost", post);
	}
}
