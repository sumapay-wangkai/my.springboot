package demo.wk.springboot4mybaits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("demo.wk.springboot4mybaits.dao")
public class Springboot4mybaitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot4mybaitsApplication.class, args);
    }

}

