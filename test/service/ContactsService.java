package service;

import com.ojy.crm.workbench.pojo.Contacts;

import java.util.List;
import java.util.Map;

public interface ContactsService {
    /**
     * 保存创建的联系人信息
     * @param contacts
     * @return
     */
    int saveCreateContacts(Contacts contacts);

    /**
     * 分页查询联系人信息
     * @param map
     * @return
     */
    List<Contacts> queryContactsByConditionForPage(Map<String, Object> map);

    /**
     * 分页查询的联系人信息记录总数
     * @param map
     * @return
     */
    int queryCountOfContactsByCondition(Map<String, Object> map);

    /**
     * 查询对应id的联系人信息
     * @param id
     * @return
     */
    Contacts queryContactsById(String id);

    /**
     * 保存修改的联系人信息
     * @param contacts
     * @return
     */
    int saveEditContactsById(Contacts contacts);

    /**
     * 删除对应id的联系人信息
     * @param ids
     * @return
     */
    int dropContactsByIds(String[] ids);
}