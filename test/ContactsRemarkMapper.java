import com.ojy.crm.workbench.pojo.ContactsRemark;

public interface ContactsRemarkMapper {
    int deleteByPrimaryKey(String id);

    int insert(ContactsRemark record);

    int insertSelective(ContactsRemark record);

    ContactsRemark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ContactsRemark record);

    int updateByPrimaryKey(ContactsRemark record);
}