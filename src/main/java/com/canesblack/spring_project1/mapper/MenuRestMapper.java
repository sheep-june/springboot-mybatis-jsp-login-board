package com.canesblack.spring_project1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.canesblack.spring_project1.entity.Menu;

@Mapper  // 이 인터페이스를 MyBatis Mapper로 등록. 스프링이 자동으로 빈으로 관리함.
public interface MenuRestMapper {

    // 게시글 목록 전체를 가져오는 SQL
    // MyBatis는 @Select 안에 적힌 SQL을 실행하고, 결과를 List<Menu>에 자동 매핑
    @Select("SELECT idx,memID,title,content,writer,indate,count FROM backend_spring_project.menu ORDER BY idx DESC")
    public List<Menu> getLists();

    // 게시글 추가 (INSERT)
    // Menu 객체의 필드를 바탕으로 SQL의 #{변수}에 값이 자동으로 바인딩됨
    @Insert("INSERT INTO backend_spring_project.menu(memID,title,content,writer,indate) VALUES (#{memID},#{title},#{content},#{writer},#{indate})")
    public void boardInsert(Menu menu);

    // 특정 게시글 1개를 idx 기준으로 조회 (READ)
    // 반환 타입은 단일 Menu 객체
    @Select("SELECT idx,memID,title,content,writer,indate,count FROM backend_spring_project.menu WHERE idx=#{idx}")
    public Menu boardContent(int idx);

    // 특정 게시글을 idx 기준으로 삭제 (DELETE)
    @Delete("DELETE FROM backend_spring_project.menu WHERE idx=#{idx}")
    public void boardDelete(int idx);

    // 게시글 수정 (UPDATE)
    // Menu 객체의 값으로 title, content, writer를 수정함
    @Update("UPDATE backend_spring_project.menu SET title=#{title},content=#{content},writer=#{writer} WHERE idx=#{idx}")
    public void boardUpdate(Menu menu);

    // 게시글 조회수(count)를 1 증가시키는 SQL
    @Update("UPDATE backend_spring_project.menu SET count=count+1 WHERE idx=#{idx}")
    public void boardCount(int idx);
}
