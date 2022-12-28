package com.sample.util;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * ibatis 프레임워크를 사용한 데이터베이스 엑세스 작업을 지원하는 헬프 클래스다.
 * @author lee_e
 *
 */
public class SqlMapper {

	private static SqlMapClient sqlMapClient;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("META-INF/ibatis/ibatis-config.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private static SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
	
	/**
	 * 지정된 아이디에 해당하는 매핑된 SQL 구문을 실행, INSERT 작업을 수행시킨다.
	 * @param id 매핑된 SQL 구문의 아이디
	 */
	public static void insert(String id) {
		try {
			getSqlMapClient().insert(id);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	} 
	
	/**
	 * 아이디와 파라미터객체를 전달받아서 매핑된 SQL 구문을 실행, INSERT 작업을 수행시킨다.
	 * @param id 매핑된 SQL 구문의 아이디
	 * @param param SQL 구문 실행에 필요한 값을 가지고 있는 파라미터 객체
	 */
	public static void insert(String id, Object param) {
		try {
			getSqlMapClient().insert(id, param);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 지정된 아이디에 해당하는 매핑된 SQL 구문을 실행, UPDATE 작업을 수행시킨다.
	 * @param id 매핑된 SQL 구문의 아이디
	 */
	public static void update(String id) {
		try {
			getSqlMapClient().update(id);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	} 
	
	/**
	 * 아이디와 파라미터객체를 전달받아서 매핑된 SQL 구문을 실행, UPDATE 작업을 수행시킨다.
	 * @param id 매핑된 SQL 구문의 아이디
	 * @param param SQL 구문 실행에 필요한 값을 가지고 있는 파라미터 객체
	 */
	public static void update(String id, Object param) {
		try {
			getSqlMapClient().update(id, param);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 지정된 아이디에 해당하는 매핑된 SQL 구문을 실행, DELETE 작업을 수행시킨다.
	 * @param id 매핑된 SQL 구문의 아이디
	 */
	public static void delete(String id) {
		try {
			getSqlMapClient().delete(id);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	} 
	
	/**
	 * 아이디와 파라미터객체를 전달받아서 매핑된 SQL 구문을 실행, DELETE 작업을 수행시킨다.
	 * @param id 매핑된 SQL 구문의 아이디
	 * @param param SQL 구문 실행에 필요한 값을 가지고 있는 파라미터 객체
	 */
	public static void delete(String id, Object param) {
		try {
			getSqlMapClient().delete(id, param);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 지정된 아이디에 해당하는 매핑된 SQL 구문을 실행, SELECT 작업을 수행시킨다.
	 * <P>조회 결과를 Object 객체로 반환한다.
	 * <P>조회 결과가 하나도 없으면 null을 반환한다.
	 * @param id 매핑된 SQL 구문의 아이디
	 * @return 조회결과가 저장된 객체
	 */
	public static Object selectOne(String id) {
		try {
			return getSqlMapClient().queryForObject(id);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	} 
	
	/**
	 * 아이디와 파라미터객체를 전달받아서 매핑된 SQL 구문을 실행, SELECT 작업을 수행시킨다.
	 * <P>조회 결과를 Object 객체로 반환한다.
	 * <P>조회 결과가 하나도 없으면 null을 반환한다.
	 * @param id 매핑된 SQL 구문의 아이디
	 * @param param SQL 구문 실행에 필요한 값을 가지고 있는 파라미터 객체
	 * @return 조회결과가 저장된 객체
	 */
	public static Object selectOne(String id, Object param) {
		try {
			return getSqlMapClient().queryForObject(id, param);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 지정된 아이디에 해당하는 매핑된 SQL 구문을 실행, SELECT 작업을 수행시킨다.
	 * <P>조회 결과를 List 객체로 반환한다.
	 * <P>조회 결과가 하나도 없으면 빈 List 객체를 반환한다.
	 * @param id 매핑된 SQL 구문의 아이디
	 * @return List 객체
	 */
	public static List<?> selectList(String id) {
		try {
			return getSqlMapClient().queryForList(id);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	} 
	
	/**
	 * 아이디와 파라미터객체를 전달받아서 매핑된 SQL 구문을 실행, SELECT 작업을 수행시킨다.
	 * <P>조회 결과를 List 객체로 반환한다.
	 * <P>조회 결과가 하나도 없으면 빈 List 객체를 반환한다.
	 * @param id 매핑된 SQL 구문의 아이디
	 * @param param SQL 구문 실행에 필요한 값을 가지고 있는 파라미터 객체
	 * @return List 객체
	 */
	public static List<?> selectList(String id, Object param) {
		try {
			return getSqlMapClient().queryForList(id, param);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	
	
}
