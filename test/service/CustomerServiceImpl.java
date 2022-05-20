package service;

import com.ojy.crm.workbench.mapper.CustomerMapper;
import com.ojy.crm.workbench.pojo.Customer;
import com.ojy.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public int saveCreateCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

    @Override
    public List<Customer> queryCustomerByConditionForPage(Map<String, Object> map) {
        return customerMapper.selectCustomerByConditionForPage(map);
    }

    @Override
    public int queryCountOfCustomerByCondition(Map<String, Object> map) {
        return customerMapper.selectCountOfCustomerByCondition(map);
    }

    @Override
    public Customer queryCustomerById(String id) {
        return customerMapper.selectCustomerById(id);
    }

    @Override
    public int saveEditCustomerById(Customer customer) {
        return customerMapper.updateCustomerById(customer);
    }

    @Override
    public int dropCustomerByIds(String[] ids) {
        return customerMapper.deleteCustomerByIds(ids);
    }
}
