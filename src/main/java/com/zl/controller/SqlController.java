package com.zl.controller;

import com.zl.service.impl.DBoperateServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.IOException;

@RestController
@RequestMapping("/sql")
@ApiOperation("sql脚本操作")
public class SqlController {

    @Autowired
    private DBoperateServiceImpl dBoperateService;

    @RequestMapping(value = "/run/sqlScript",method = RequestMethod.POST)
    @ApiOperation("执行sql脚本")
    public void runSqlScript() {
        try {
            dBoperateService.runScript();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
