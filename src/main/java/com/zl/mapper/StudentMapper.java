package com.zl.mapper;

import com.zl.common.MarkerInterface;
import org.apache.ibatis.annotations.Param;
import com.zl.vo.StudentVO;

import java.util.List;

public interface StudentMapper extends MarkerInterface {
    /**
     * 批量插入
     * @param list 学生集合
     * @return 成功条数
     */
    int batchInsert(@Param("list") List<StudentVO> list);

    /**
     *
     * @param studentVO 学生vo
     * @return 成功条数
     */
    int insertOne(StudentVO studentVO);

    /**
     * 查询所有数据
     * @return List<StudentVO>
     */
    List<StudentVO> queryAll();
}
