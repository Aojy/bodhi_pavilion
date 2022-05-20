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

    @Override
    public Map<String, Object> queryEmployeeList(Integer pageNo, Integer pageSize, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("start",(pageNo - 1) * pageSize);
        map.put("size", pageSize);
        map.put("name", name);
        List<Employee> data = employeeMapper.selectEmployeeList(map);
        int total = employeeMapper.selectTotalByCondition(name);
        map.clear();
        map.put("records", data);
        map.put("total", total);

        return map;
    }

    @Override
    public boolean saveEmployee(Employee employee) {
        return employeeMapper.insert(employee) > 0;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        if (employee != null) {
            if (employee.getName() == null) {
                int status = employee.getStatus();
                employee = employeeMapper.selectById(employee.getId());
                employee.setStatus(status);
            }
            return employeeMapper.updateById(employee) > 0;
        }
        return false;
    }

    @Override
    public Employee queryEmployeeById(String id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public Employee login(String username, String password) {
        return employeeMapper.selectEmployeeByAccountAndPassword(username, password);
    }
}
