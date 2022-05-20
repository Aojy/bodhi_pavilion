package service;

import com.ojy.crm.workbench.pojo.Tran;

import java.util.List;
import java.util.Map;

public interface TranService {
    /**
     * 保存创建的联系人信息
     * @param tran
     * @return
     */
    int saveCreateTran(Tran tran);

    /**
     * 分页查询交易信息
     * @param map
     * @return
     */
    List<Tran> queryTranByConditionForPage(Map<String, Object> map);

    /**
     * 分页查询的交易信息记录总数
     * @param map
     * @return
     */
    int queryCountOfTranByCondition(Map<String, Object> map);

    /**
     * 查询对应id的交易信息
     * @param id
     * @return
     */
    Tran queryTranById(String id);

    /**
     * 保存修改的交易信息
     * @param tran
     * @return
     */
    int saveEditTranById(Tran tran);

    /**
     * 删除对应id的交易信息
     * @param ids
     * @return
     */
    int dropTranByIds(String[] ids);
}