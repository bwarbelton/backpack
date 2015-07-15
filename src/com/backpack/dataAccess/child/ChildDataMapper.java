package com.backpack.dataAccess.child;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.backpack.model.Child;

public interface ChildDataMapper {
	@Insert("{CALL insertChild(#{child.childId, mode=INOUT, jdbcType=INTEGER}, "
			+ "#{child.firstName, mode=IN, jdbcType=VARCHAR}, "
			+ "#{child.lastName, mode=IN, jdbcType=VARCHAR}, "
			+ "#{child.backpack, mode=IN, jdbcType=TINYINT},"
			+ "#{child.healthCheck, mode=IN, jdbcType=TINYINT},"
			+ "#{child.haircut, mode=IN, jdbcType=TINYINT}"
			+ ") }")
	@Options(statementType = StatementType.CALLABLE, flushCache = true)
	public int insertChild(@Param("child") Child child);
	
	@Update("{CALL updateChild(#{child.childId, mode=IN, jdbcType=INTEGER}, "
			+ "#{child.firstName, mode=IN, jdbcType=VARCHAR}, "
			+ "#{child.lastName, mode=IN, jdbcType=VARCHAR},"
			+ "#{child.backpack, mode=IN, jdbcType=TINYINT},"
			+ "#{child.healthCheck, mode=IN, jdbcType=TINYINT},"
			+ "#{child.haircut, mode=IN, jdbcType=TINYINT}"
			+ ") }")
	@Options(statementType = StatementType.CALLABLE, flushCache = true)
	public void updateChild(@Param("child") Child child);
	
	@Select("{CALL getAllChildren()}")
	@Options(statementType = StatementType.CALLABLE, useCache = false)
	@Results(value = {
			@Result(property = "childId", column = "child_id", javaType = int.class),
			@Result(property = "firstName", column = "first_name", javaType = String.class),
			@Result(property = "lastName", column = "last_name", javaType = String.class),
			@Result(property = "backpack", column = "backpack", javaType = int.class),
			@Result(property = "healthCheck", column = "healthCheck", javaType = int.class),
			@Result(property = "haircut", column = "haircut", javaType = int.class) })
	public List<Child> fetchAllChildren();

	
	@Select("{CALL getChild(#{childId, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE, useCache = false)
	@Results(value = {
			@Result(property = "childId", column = "child_id", javaType = int.class),
			@Result(property = "firstName", column = "first_name", javaType = String.class),
			@Result(property = "lastName", column = "last_name", javaType = String.class),
			@Result(property = "backpack", column = "backpack", javaType = int.class),
			@Result(property = "healthCheck", column = "healthCheck", javaType = int.class),
			@Result(property = "haircut", column = "haircut", javaType = int.class) })
	public Child fetchChild( @Param("childId") int childId);	
	
}
