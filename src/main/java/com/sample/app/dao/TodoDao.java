package com.sample.app.dao;

import java.util.List;
import java.util.Map;

import com.sample.app.dto.TodoDetailDto;
import com.sample.app.dto.TodoListDto;
import com.sample.app.vo.Todo;
import com.sample.util.SqlMapper;

public class TodoDao {

	private static TodoDao instance = new TodoDao();
	private TodoDao() {}
	public static TodoDao getInstance() {
		return instance;
	}
	
	public void insertTodo(Todo todo) {
		SqlMapper.insert("todos.insertTodo", todo);
	}
	
	public int getTotalRows() {
		return (Integer) SqlMapper.selectOne("todos.getTotalRows");
	}
	
	@SuppressWarnings("unchecked")
	public List<TodoListDto> getTodos(Map<String, Object> param) {
		return (List<TodoListDto>) SqlMapper.selectList("todos.getTodos", param);
	}
	
	public TodoDetailDto getTodoDetailByNo(int todoNo) {
		return (TodoDetailDto) SqlMapper.selectOne("todos.getTodoDetailByNo", todoNo);
	}
	
	public Todo getTodoByNo(int todoNo) {
		return (Todo) SqlMapper.selectOne("todos.getTodoByNo", todoNo);
	}
	
	public void updateTodo(Todo todo) {
		SqlMapper.update("todos.updateTodo", todo);
	}
}
