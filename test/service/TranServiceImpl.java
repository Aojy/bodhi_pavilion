package service;

import com.ojy.crm.workbench.mapper.TranMapper;
import com.ojy.crm.workbench.pojo.Tran;
import com.ojy.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TranServiceImpl implements TranService {

    @Autowired
    TranMapper tranMapper;


    @Override
    public int saveCreateTran(Tran tran) {
        return tranMapper.insertTran(tran);
    }

    @Override
    public List<Tran> queryTranByConditionForPage(Map<String, Object> map) {
        return tranMapper.selectTranByConditionForPage(map);
    }

    @Override
    public int queryCountOfTranByCondition(Map<String, Object> map) {
        return tranMapper.selectCountOfTranByCondition(map);
    }

    @Override
    public Tran queryTranById(String id) {
        return tranMapper.selectTranById(id);
    }

    @Override
    public int saveEditTranById(Tran tran) {
        return tranMapper.updateTranById(tran);
    }

    @Override
    public int dropTranByIds(String[] ids) {
        return tranMapper.deleteTranByIds(ids);
    }
}
