package com.zl.controller;

import com.alibaba.excel.EasyExcel;
import com.zl.common.CommonConstant;
import com.zl.listener.CommonListener;
import com.zl.service.StudentService;
import com.zl.vo.StudentVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/student")
@ApiOperation("用户操作接口")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/import",method = RequestMethod.POST)
    @ApiOperation("导入用户数据")
    public String importUserInfo(@RequestParam("file") MultipartFile multipartFile) {
        try {
            EasyExcel.read(
                    multipartFile.getInputStream(), StudentVO.class,
                    new CommonListener<StudentVO>(
                            CommonConstant.BATCH_COUNT_THOUSAND, studentService))
                    .sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
        return "suceess";
    }
}
