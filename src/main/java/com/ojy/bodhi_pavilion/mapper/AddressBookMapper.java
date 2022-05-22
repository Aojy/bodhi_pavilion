package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    int deleteById(String id);

    int insert(AddressBook addressBook);

    AddressBook selectById(String id);

    int updateByIdSelective(AddressBook addressBook);

    List<AddressBook> selectAddressBookByUserId(@Param("id") String id, @Param("flag") String flag);

    int updateDefaultFalse(String id);
}