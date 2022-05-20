import com.ojy.crm.workbench.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    /**
     * 新增客户信息
     * @param customer
     * @return
     */
    int insertCustomer(Customer customer);

    /**
     * 分页查询客户信息
     * @param map
     * @return
     */
    List<Customer> selectCustomerByConditionForPage(Map<String, Object> map);

    /**
     * 分页查询的客户信息记录总数
     * @param map
     * @return
     */
    int selectCountOfCustomerByCondition(Map<String, Object> map);

    /**
     * 查询对应id的客户信息
     * @param id
     * @return
     */
    Customer selectCustomerById(String id);

    /**
     * 修改对应id的客户信息
     * @param customer
     * @return
     */
    int updateCustomerById(Customer customer);

    /**
     * 删除对应id的客户信息
     * @param ids
     * @return
     */
    int deleteCustomerByIds(String[] ids);
}