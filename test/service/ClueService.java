package service;

import com.ojy.crm.workbench.pojo.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {
    /**
     * 插入线索信息
     * @param clue
     * @return
     */
    int saveCreateClue(Clue clue);

    /**
     * 分页查询所有线索信息
     * @param map
     * @return
     */
    List<Clue> queryClueByConditionForPage(Map<String, Object> map);

    /**
     * 按照条件获取线索总数
     * @param map
     * @return
     */
    int queryCountOfClueByCondition(Map<String, Object> map);

    /**
     * 修改对应id的线索信息
     * @param clue
     * @return
     */
    int saveEditClueById(Clue clue);

    /**
     * 获取对应id的线索信息
     * @param id
     * @return
     */
    Clue queryClueById(String id);

    /**
     * 删除对应id的线索信息
     * @param ids
     * @return
     */
    int dropClueByIds(String[] ids);
}
