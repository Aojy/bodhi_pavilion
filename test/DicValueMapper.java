import com.ojy.crm.workbench.pojo.DicValue;

import java.util.List;

public interface DicValueMapper {
    int deleteByPrimaryKey(String id);

    int insert(DicValue record);

    int insertSelective(DicValue record);

    DicValue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DicValue record);

    int updateByPrimaryKey(DicValue record);

    /**
     * 根据typeCode查询相应的字典值
     * @param typeCode
     * @return
     */
    List<DicValue> selectDicValueByTypeCode(String typeCode);
}