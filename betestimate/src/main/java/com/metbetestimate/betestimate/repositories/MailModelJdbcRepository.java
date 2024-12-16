package com.metbetestimate.betestimate.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.metbetestimate.betestimate.constants.DbStaticStr;
import com.metbetestimate.betestimate.models.MailModel;
import com.metbetestimate.betestimate.models.mappers.MailModelMapper;

@Component
/*
 * Repository for the  current mail model database operations.
 */
public class MailModelJdbcRepository implements Repository<MailModel> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public java.util.List<MailModel> List() {

        return jdbcTemplate.query(DbStaticStr.GET_ALL_MAILS,new MailModelMapper());
    }

    @Override
    public MailModel get(int index) {

        return jdbcTemplate.queryForObject(DbStaticStr.GET_MAIL_BY_ID.formatted(index), new MailModelMapper());
    }

    @Override
    public void create(MailModel t) {
        
        jdbcTemplate.execute(DbStaticStr.INSERT_MAIL.formatted(
            t.getEmail(),
            t.getContent(),
            t.isChecked()
        ));
    }

    @Override
    public void update(MailModel t, int index) {
        jdbcTemplate.update(DbStaticStr.UPDATE_MAIL, 
        t.getEmail(),
        t.getContent(),
        !t.isChecked(),
        t.getId()
        );
    }

    @Override
    public void delete(int index) {
        jdbcTemplate.execute(DbStaticStr.DELETE_MAIL.formatted(index));
    }

    @Override
    public java.util.List<MailModel> listFromQuery(String query) {
        return jdbcTemplate.query(query, new MailModelMapper());
    }
    
}
