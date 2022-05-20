package com.ojy.bodhi_pavilion.service;

import com.ojy.bodhi_pavilion.pojo.AddressBook;

import java.util.List;

public interface AddressBookService {
    AddressBook queryDefaultAddressBookByUserId(String id);

    List<AddressBook> queryAddressBookByUserId(String id);

    boolean saveAddressBook(AddressBook addressBook);

    boolean updateDefault(AddressBook addressBook, String id);

    AddressBook queryAddressBookById(String id);

    boolean updateAddressBook(AddressBook addressBook);

    boolean deleteAddressBook(String ids);
}
