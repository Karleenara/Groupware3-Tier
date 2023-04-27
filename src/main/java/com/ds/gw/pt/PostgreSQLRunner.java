package com.ds.gw.pt;

import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostgreSQLRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()){
            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

//            Statement statement = connection.createStatement();
            //String sql = "CREATE TABLE test(product_no INTEGER NOT NULL, product_name VARCHAR(255), PRIMARY KEY (product_no))";
            //statement.executeUpdate(sql);
        }
        //jdbcTemplate.execute("INSERT INTO test VALUES (1, 'Big shirt')");
    }
}