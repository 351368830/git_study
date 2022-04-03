package com.zl.service.impl;

import com.zl.config.SystemConfig;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

@Service
public class DBoperateServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(DBoperateServiceImpl.class);
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SystemConfig systemConfig;

    public void runScript() throws IOException {
        String sqlFilePath = systemConfig.getSqlFilePath();
        Resources.setCharset(Charset.forName("UTF-8"));
        File file = Resources.getResourceAsFile(sqlFilePath);
        if (!file.exists()) {
            throw new FileNotFoundException("the sql file " + sqlFilePath + " not exist");
        }
        try {
            ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
            scriptRunner.runScript(new FileReader(file));
            scriptRunner.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.size() <= 0) {
            while (stack1.size() != 0) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public static void main(String[] args) {
        DBoperateServiceImpl service = new DBoperateServiceImpl();
        for (int i = 0; i < 100; i++) {
            service.push(i);
        };
//        System.out.println(service.stack1);
//        System.out.println(service.pop());
    }
}
