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

    @Override
    public AddressBook queryDefaultAddressBookByUserId(String id) {
        List<AddressBook> list = addressBookMapper.selectAddressBookByUserId(id, "defualt");
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<AddressBook> queryAddressBookByUserId(String id) {
        return addressBookMapper.selectAddressBookByUserId(id, null);
    }

    @Override
    public boolean saveAddressBook(AddressBook addressBook) {
        return addressBookMapper.insert(addressBook) > 0;
    }

    @Override
    public boolean updateDefault(AddressBook addressBook, String id) {
        addressBookMapper.updateDefaultFalse(id);
        return addressBookMapper.updateByIdSelective(addressBook) > 0;
    }

    @Override
    public AddressBook queryAddressBookById(String id) {
        return addressBookMapper.selectById(id);
    }

    @Override
    public boolean updateAddressBook(AddressBook addressBook) {
        return addressBookMapper.updateByIdSelective(addressBook) > 0;
    }

    @Override
    public boolean deleteAddressBook(String ids) {
        return addressBookMapper.deleteById(ids) > 0;
    }
}
