package com.zl.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentVO {

    @ExcelProperty(value = "姓名", index = 0)
    private String name;
    @ExcelProperty(value = "年龄", index = 1)
    private String age;
    @ExcelProperty(value = "地址", index = 2)
    private String address;
    @ExcelProperty(value = "邮箱", index = 3)
    private String email;
}
