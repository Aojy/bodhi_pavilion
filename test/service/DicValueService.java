package service;

import com.ojy.crm.workbench.pojo.DicValue;

import java.util.List;

public interface DicValueService {

    /**
     * 根据typeCode查询相应的字典值
     * @param typeCode
     * @return
     */
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
