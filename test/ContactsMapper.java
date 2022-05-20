import com.ojy.crm.workbench.pojo.Contacts;

import java.util.List;
import java.util.Map;

public interface ContactsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    Contacts selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);

    /**
     * 新增联系人信息
     * @param contacts
     * @return
     */
    int insertContacts(Contacts contacts);

    /**
     * 分页查询联系人信息
     * @param map
     * @return
     */
    List<Contacts> selectContactsByConditionForPage(Map<String, Object> map);

    /**
     * 分页查询的联系人信息记录总数
     * @param map
     * @return
     */
    int selectCountOfContactsByCondition(Map<String, Object> map);

    /**
     * 查询对应id的联系人信息
     * @param id
     * @return
     */
    Contacts selectContactsById(String id);

    /**
     * 修改对应id的联系人信息
     * @param contacts
     * @return
     */
    int updateContactsById(Contacts contacts);

    /**
     * 删除对应id的联系人信息
     * @param ids
     * @return
     */
    int deleteContactsByIds(String[] ids);
}