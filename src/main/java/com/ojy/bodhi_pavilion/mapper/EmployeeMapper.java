package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Mapper
@Transactional
public interface EmployeeMapper {
    int deleteById(String id);

    int insert(Employee record);

    Employee selectById(String id);

    int updateById(Employee record);

    Employee selectEmployeeByAccountAndPassword(@Param("username")String username, @Param("password")String password);

    List<Employee> selectEmployeeList(Map<String, Object> map);

    int selectTotalByCondition(String name);
}