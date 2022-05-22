package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.mapper.EmployeeMapper;
import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有员工信息
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> queryEmployeeList(Map<String, Object> map) {
        List<Employee> data = employeeMapper.selectEmployeeList(map);
        int total = employeeMapper.selectTotalByCondition((String) map.get("name"));
        map.clear();
        map.put("records", data);
        map.put("total", total);
        return map;
    }

    /**
     * 保存新增的员工账号
     * @param employee
     * @return
     */
    @Override
    public boolean saveEmployee(Employee employee) {
        return employeeMapper.insert(employee) > 0;
    }

    /**
     * 修改员工账号信息
     * @param updateEmployee 要修改的员工账号信息
     * @param emp 当前登录的账号
     * @return
     */
    @Override
    public boolean updateEmployee(Employee updateEmployee, Employee emp) {
        // 判断要修改的员工账号是否是管理员账号，是管理员账号的则不允许修改账号，其他信息可以修改
        if (updateEmployee != null && emp != null) {
            // 判断当前登录的账号是否是管理员账号, 非管理员账号不能修改账号的启停
            if (emp.getId().equals("1653104060277599936") && updateEmployee.getUsername() == null) {
                int status = updateEmployee.getStatus();
                updateEmployee = employeeMapper.selectById(updateEmployee.getId());
                updateEmployee.setStatus(status);
            }
            return employeeMapper.updateById(updateEmployee) > 0;
        }
        return false;
    }

    /**
     * 根据员工id查询对应员工账号信息
     * @param id
     * @return
     */
    @Override
    public Employee queryEmployeeById(String id) {
        return employeeMapper.selectById(id);
    }

    /**
     * 根据账号和密码查询对应账号信息
     * @param username
     * @param password
     * @return
     */
    @Override
    public Employee login(String username, String password) {
        return employeeMapper.selectEmployeeByAccountAndPassword(username, password);
    }
}
