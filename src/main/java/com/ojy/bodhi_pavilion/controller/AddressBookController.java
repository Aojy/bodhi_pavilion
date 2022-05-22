package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.pojo.AddressBook;
import com.ojy.bodhi_pavilion.pojo.User;
import com.ojy.bodhi_pavilion.service.AddressBookService;
import com.ojy.bodhi_pavilion.util.GetId;
import com.ojy.bodhi_pavilion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 查询对应用户的默认地址信息
     * @param session
     * @return
     */
    @GetMapping("/default")
    public Result getDefault(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            return Result.success(addressBookService.queryDefaultAddressBookByUserId(user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("网络繁忙，请稍后重试...");
        }
    }

    /**
     * 查询对应用户的所有地址信息
     * @param session
     * @return
     */
    @GetMapping("/list")
    public Result getAddressBooks(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            return Result.success(addressBookService.queryAddressBookByUserId(user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("网络繁忙，请稍后重试...");
        }
    }

    /**
     * 保存对应用户的地址信息
     * @param addressBook
     * @param session
     * @return
     */
    @PostMapping
    public Result saveAddressBook(@RequestBody AddressBook addressBook, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            Date date = new Date();
            addressBook.setId(GetId.getId());
            addressBook.setCreateTime(date);
            addressBook.setUserId(user.getId());
            addressBook.setCreateUser(user.getId());
            addressBook.setUpdateTime(date);
            addressBook.setUpdateUser(user.getId());
            addressBook.setIsDefault(false);
            addressBook.setIsDeleted(0);
            return Result.success(addressBookService.saveAddressBook(addressBook));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("网络繁忙，请稍后重试...");
        }
    }

    /**
     * 修改对应用户的默认地址
     * @param addressBook
     * @param session
     * @return
     */
    @PutMapping("/default")
    public Result updateDefault(@RequestBody AddressBook addressBook, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            addressBook.setIsDefault(true);
            return Result.success(addressBookService.updateDefault(addressBook, user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 查询对应addressId的地址信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getAddressBook(@PathVariable String id) {
        try {
            return Result.success(addressBookService.queryAddressBookById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 修改对应的地址信息
     * @param addressBook
     * @param session
     * @return
     */
    @PutMapping
    public Result updateAddressBook(@RequestBody AddressBook addressBook, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            Date date = new Date();
            addressBook.setUpdateUser(user.getId());
            addressBook.setUpdateTime(date);
            return Result.success(addressBookService.updateAddressBook(addressBook));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }

    /**
     * 删除对应的地址信息
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result deleteAddressBook(String ids) {
        try {
            return Result.success(addressBookService.deleteAddressBook(ids));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统繁忙，请稍后重试...");
        }
    }
}
