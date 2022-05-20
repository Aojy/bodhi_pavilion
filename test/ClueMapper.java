import com.ojy.crm.workbench.pojo.Clue;

import java.util.List;
import java.util.Map;

public interface ClueMapper {
    int deleteByPrimaryKey(String id);


    int insertSelective(Clue record);

    Clue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);

    /**
     * 插入线索信息
     * @param clue
     * @return
     */
    int insertClue(Clue clue);

    /**
     * 分页查询所有线索信息
     * @param map
     * @return
     */
    List<Clue> selectClueByConditionForPage(Map<String, Object> map);

    /**
     * 按照条件获取线索总数
     * @param map
     * @return
     */
    int selectCountOfClueByCondition(Map<String, Object> map);

    /**
     * 修改对应id的线索信息
     * @param clue
     * @return
     */
    int updateClueById(Clue clue);

    /**
     * 根据id获取对应线索信息
     * @param id
     * @return
     */
    Clue selectClueById(String id);

    /**
     * 根据传来的ids参数删除线索
     * @param ids
     * @return
     */
    int deleteClueByIds(String[] ids);
}