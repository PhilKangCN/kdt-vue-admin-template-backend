package com.kdt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "com.kdt.mapper")
public class KdtSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(KdtSpringbootApplication.class, args);
    }

}
