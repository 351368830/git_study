package com.zl.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class SystemConfig {
    @Value("${zl.db.sql.path}")
    private String sqlFilePath;
}
