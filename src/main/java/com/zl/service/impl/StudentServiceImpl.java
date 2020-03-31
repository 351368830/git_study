package com.zl.service.impl;

import com.zl.mapper.StudentMapper;
import com.zl.service.StudentService;
import com.zl.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void batchInsert(List list) {
        studentMapper.batchInsert(list);
    }

    @Override
    public List<StudentVO> queryAll() {
        return filterVO(studentMapper.queryAll());
    }

    private List<StudentVO> filterVO(List<StudentVO> list) {
        Map<String, List<StudentVO>> mapNames =
                list.stream().collect(Collectors.groupingBy(StudentVO::getName));
        List<StudentVO> voList =
                mapNames.values().stream()
                        .filter(item -> item.size() >= 2)
                        .flatMap(Collection::stream).collect(Collectors.toList());
        return voList;
    }
}
