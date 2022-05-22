package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.mapper.AddressBookMapper;
import com.ojy.bodhi_pavilion.pojo.AddressBook;
import com.ojy.bodhi_pavilion.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    /**
     * 查询对应userId的默认address数据
     * @param id
     * @return
     */
    @Override
    public AddressBook queryDefaultAddressBookByUserId(String id) {
        List<AddressBook> list = addressBookMapper.selectAddressBookByUserId(id, "defualt");
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 查询对应userId的所有address数据
     * @param id
     * @return
     */
    @Override
    public List<AddressBook> queryAddressBookByUserId(String id) {
        return addressBookMapper.selectAddressBookByUserId(id, null);
    }

    /**
     * 保存新增的address数据
     * @param addressBook
     * @return
     */
    @Override
    public boolean saveAddressBook(AddressBook addressBook) {
        return addressBookMapper.insert(addressBook) > 0;
    }

    /**
     * 根据userId修改对应address为默认address
     * @param addressBook
     * @param id
     * @return
     */
    @Override
    public boolean updateDefault(AddressBook addressBook, String id) {
        // 将对应id的所有address改为非默认的
        addressBookMapper.updateDefaultFalse(id);
        // 修改对应id的address为默认的
        return addressBookMapper.updateByIdSelective(addressBook) > 0;
    }

    /**
     * 查询对应id的address数据
     * @param id
     * @return
     */
    @Override
    public AddressBook queryAddressBookById(String id) {
        return addressBookMapper.selectById(id);
    }

    /**
     * 修改对应id的address数据
     * @param addressBook
     * @return
     */
    @Override
    public boolean updateAddressBook(AddressBook addressBook) {
        return addressBookMapper.updateByIdSelective(addressBook) > 0;
    }

    /**
     * 删除对应id的address数据
     * @param ids
     * @return
     */
    @Override
    public boolean deleteAddressBook(String ids) {
        return addressBookMapper.deleteById(ids) > 0;
    }
}
