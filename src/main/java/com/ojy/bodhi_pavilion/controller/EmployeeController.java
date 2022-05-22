package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.pojo.Employee;
import com.ojy.bodhi_pavilion.service.EmployeeService;
import com.ojy.bodhi_pavilion.util.GetId;
import com.ojy.bodhi_pavilion.util.MD5;
import com.ojy.bodhi_pavilion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 验证登陆
     * @param emp
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Result toLogin(@RequestBody Employee emp, HttpSession session) {
        try {
            session.setAttribute("loginFlag","admin");
            Employee employee = employeeService.login(emp.getUsername(), MD5.getMD5(emp.getPassword()));
            if (employee != null) {
                if (employee.getStatus() == 1) {
                    session.setAttribute("emp", employee);
                    return Result.success(employee);
                }
                return Result.error("该账号已被禁用，请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("账号或密码错误");
    }

    @PostMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("emp");
        return Result.success(null);
    }

    /**
     * 获取所有员工账号数据
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result getEmployeeList(Integer page, Integer pageSize, String name) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start",(page - 1) * pageSize);
            map.put("size", pageSize);
            map.put("name", name);
            return Result.success(employeeService.queryEmployeeList(map));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 根据id获取员工账号数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getEmployee(@PathVariable String id) {
        try {
            return Result.success(employeeService.queryEmployeeById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 新增员工账号数据
     * @param employee
     * @param session
     * @return
     */
    @PostMapping
    public Result saveEmployee(@RequestBody Employee employee, HttpSession session) {
        try {
            Employee emp = (Employee) session.getAttribute("emp");
            employee.setId(GetId.getId());
            if (employee.getPassword() == null) {
                employee.setPassword(MD5.getMD5("123456"));
            }
            employee.setCreateUser(emp.getId());
            employee.setCreateTime(new Date());
            employee.setUpdateUser(emp.getId());
            employee.setUpdateTime(new Date());
            employee.setStatus(1);
            return Result.success(employeeService.saveEmployee(employee));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 修改员工账号数据
     * @param employee
     * @param session
     * @return
     */
    @PutMapping
    public Result updateEmployee(@RequestBody Employee employee, HttpSession session) {
        try {
            Employee emp = (Employee) session.getAttribute("emp");
            return Result.success(employeeService.updateEmployee(employee, emp));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

}
