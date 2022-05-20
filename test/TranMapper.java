import com.ojy.crm.workbench.pojo.Tran;

import java.util.List;
import java.util.Map;

public interface TranMapper {
    int deleteByPrimaryKey(String id);

    int insert(Tran record);

    int insertSelective(Tran record);

    Tran selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Tran record);

    int updateByPrimaryKey(Tran record);


    /**
     * 新增联系人信息
     * @param tran
     * @return
     */
    int insertTran(Tran tran);

    /**
     * 分页查询交易信息
     * @param map
     * @return
     */
    List<Tran> selectTranByConditionForPage(Map<String, Object> map);

    /**
     * 分页查询的交易信息记录总数
     * @param map
     * @return
     */
    int selectCountOfTranByCondition(Map<String, Object> map);

    /**
     * 查询对应id的交易信息
     * @param id
     * @return
     */
    Tran selectTranById(String id);

    /**
     * 修改对应id的交易信息
     * @param tran
     * @return
     */
    int updateTranById(Tran tran);

    /**
     * 删除对应id的交易信息
     * @param ids
     * @return
     */
    int deleteTranByIds(String[] ids);
}