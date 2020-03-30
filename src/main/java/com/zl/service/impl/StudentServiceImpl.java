package com.zl.service.impl;

import com.zl.mapper.StudentMapper;
import com.zl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void batchInsert(List list) {
        studentMapper.batchInsert(list);
    }
}
