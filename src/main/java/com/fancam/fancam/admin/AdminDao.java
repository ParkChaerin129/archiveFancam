package com.fancam.fancam.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;

@Repository
public class AdminDao {

/*
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Integer createNewFancamToDB(String name, String date,String member,String url){
        String createUserQuery = "insert into Fancam (name, date, member, fancamUrl VALUES (?,?,?,?)";
        Object[] createUserParams = new Object[]{name, date,member, url};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }*/

}
