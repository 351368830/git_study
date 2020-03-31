package com.zl.service;

import com.zl.vo.StudentVO;

import java.util.List;

public interface StudentService extends EasyExcelService {

    /**
     * 查询所有数据
     * @return List<StudentVO>
     */
    List<StudentVO> queryAll();
}
