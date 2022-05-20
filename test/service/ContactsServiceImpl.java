package service;

import com.ojy.crm.workbench.mapper.ContactsMapper;
import com.ojy.crm.workbench.pojo.Contacts;
import com.ojy.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    ContactsMapper contactsMapper;

    @Override
    public int saveCreateContacts(Contacts contacts) {
        return contactsMapper.insertContacts(contacts);
    }

    @Override
    public List<Contacts> queryContactsByConditionForPage(Map<String, Object> map) {
        return contactsMapper.selectContactsByConditionForPage(map);
    }

    @Override
    public int queryCountOfContactsByCondition(Map<String, Object> map) {
        return contactsMapper.selectCountOfContactsByCondition(map);
    }

    @Override
    public Contacts queryContactsById(String id) {
        return contactsMapper.selectContactsById(id);
    }

    @Override
    public int saveEditContactsById(Contacts contacts) {
        return contactsMapper.updateContactsById(contacts);
    }

    @Override
    public int dropContactsByIds(String[] ids) {
        return contactsMapper.deleteContactsByIds(ids);
    }


}