import com.ojy.crm.workbench.pojo.ContactsActivityRelation;

public interface ContactsActivityRelationMapper {
    int deleteByPrimaryKey(String id);

    int insert(ContactsActivityRelation record);

    int insertSelective(ContactsActivityRelation record);

    ContactsActivityRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ContactsActivityRelation record);

    int updateByPrimaryKey(ContactsActivityRelation record);
}