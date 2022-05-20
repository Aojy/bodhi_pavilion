package service;

import com.ojy.crm.workbench.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    /**
     * 保存创建的客户信息
     * @param customer
     * @return
     */
    int saveCreateCustomer(Customer customer);

    /**
     * 分页查询客户信息
     * @param map
     * @return
     */
    List<Customer> queryCustomerByConditionForPage(Map<String, Object> map);

    /**
     * 分页查询的客户信息记录总数
     * @param map
     * @return
     */
    int queryCountOfCustomerByCondition(Map<String, Object> map);

    /**
     * 查询对应id的客户信息
     * @param id
     * @return
     */
    Customer queryCustomerById(String id);

    /**
     * 保存修改的客户信息
     * @param customer
     * @return
     */
    int saveEditCustomerById(Customer customer);

    /**
     * 删除对应id的客户信息
     * @param ids
     * @return
     */
    int dropCustomerByIds(String[] ids);
}
