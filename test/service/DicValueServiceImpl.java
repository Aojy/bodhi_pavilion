package service;

import com.ojy.crm.workbench.mapper.DicValueMapper;
import com.ojy.crm.workbench.pojo.DicValue;
import com.ojy.crm.workbench.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
