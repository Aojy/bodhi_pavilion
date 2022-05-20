package com.ojy.bodhi_pavilion.service;

import com.ojy.bodhi_pavilion.pojo.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee queryEmployeeById(String id);

    Employee login(String username, String password);

    Map<String, Object> queryEmployeeList(Integer pageNo, Integer pageSize, String name);

    boolean saveEmployee(Employee employee);

    boolean updateEmployee(Employee employee);
}
