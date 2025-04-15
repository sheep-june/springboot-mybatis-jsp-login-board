package com.canesblack.spring_project1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.canesblack.spring_project1.entity.User;

@Mapper
public interface UserMapper {
	@Insert("INSERT INTO backend_spring_project.user(username,password,writer,role)"
			+ " VALUES(#{username},#{password},#{writer},#{role})")
	void insertUser(User user);
//	void=> 우리가 데이터베이스에서 백엔드영역(스프링프레임워크)으로 데이터를 
//	가져오는게 없기 떄문에 void로 가져오는게 없다고 작성한다.
//	CRUD의 READ에 해당하는 기능중하나
	@Select("SELECT username,password,writer,role FROM backend_spring_project.user WHERE username=#{username}")
	User findByUsername(String username);
	
	@Select("SELECT writer FROM backend_spring_project.user WHERE username=#{username}")
	String findWriter(String username);
	
}
