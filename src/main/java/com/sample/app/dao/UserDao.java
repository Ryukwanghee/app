package com.sample.app.dao;

import com.sample.app.vo.User;
import com.sample.util.SqlMapper;

public class UserDao {

	private static UserDao instance = new UserDao();
	private UserDao() {}
	public static UserDao getInstance() {
		return instance;
	}
	
	public User getUserById(String userId) {
		return (User) SqlMapper.selectOne("users.getUserById", userId);
	}
	
	public User getUserByEmail(String email) {
		return (User) SqlMapper.selectOne("users.getUserByEmail", email);
	}
	
	public void insertUser(User user) {
		SqlMapper.insert("users.insertUser", user);
	}
	
	public void updateUser(User user) {
		SqlMapper.update("users.updateUser", user);
	}
}
