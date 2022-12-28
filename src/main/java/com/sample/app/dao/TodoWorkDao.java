package com.sample.app.dao;

import java.util.List;

import com.sample.app.dto.TodoWorkListDto;
import com.sample.app.vo.Work;
import com.sample.util.SqlMapper;

public class TodoWorkDao {

	private static TodoWorkDao instance = new TodoWorkDao();
	private TodoWorkDao() {}
	public static TodoWorkDao getInstance() {
		return instance;
	}
	
	public void insertWork(Work work) {
		SqlMapper.insert("works.insertWork", work);
	}
	
	@SuppressWarnings("unchecked")
	public List<TodoWorkListDto> getWorksByTodoNo(int todoNo) {
	      return (List<TodoWorkListDto>) SqlMapper.selectList("works.getWorksByTodoNo", todoNo);
	}
}
