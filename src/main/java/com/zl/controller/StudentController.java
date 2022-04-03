package com.zl.controller;

import com.alibaba.excel.EasyExcel;
import com.zl.common.CommonConstant;
import com.zl.listener.CommonListener;
import com.zl.service.StudentService;
import com.zl.service.impl.HeartBeatUnit;
import com.zl.vo.StudentVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/student")
@ApiOperation("用户操作接口")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private HeartBeatUnit heartBeatUnit;

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

    @GetMapping(value = "/exportExcel")
    @ApiOperation("导出用户数据")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        List<StudentVO> datas = studentService.queryAll();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系 abc 为中文会乱码
        String fileName = URLEncoder.encode("abc", "UTF-8");
        response.setHeader("Content-disposition",
                "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), StudentVO.class).sheet(0, "模板").doWrite(datas);
    }

    @GetMapping(value = "/call/python")
    @ApiOperation("调用python")
    public void callPython() {
        String file_path = "";
        System.out.println(file_path);
        try {
            String path1 = StudentController.class.getResource("/").getPath();
            path1 = path1 + "python";
            System.out.println(path1);
            File file = new File(path1);
            System.out.println(file.exists());
            System.out.println(file.isDirectory());
            System.out.println();
            File[] files = file.listFiles();
            System.out.println(files.length);
            String path = files[0].getCanonicalPath();
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/execute/poll")
    @ApiOperation("poll")
    public void poll() {
        heartBeatUnit.run();
    }
}
