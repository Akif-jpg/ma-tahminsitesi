package com.metbetestimate.betestimate.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.metbetestimate.betestimate.constants.DbStaticStr;
import com.metbetestimate.betestimate.models.BetModel;
import com.metbetestimate.betestimate.models.mappers.BetModelMapper;

@Component
/*
 * Repository for the  current bet model database operations.
 */
public class CurrentBetJdbcRepository implements Repository<BetModel> {
    private static final Logger log = LoggerFactory.getLogger(BetJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    CurrentBetJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public java.util.List<BetModel> List() {
        log.info("sql query = "+DbStaticStr.GET_ALL_CPOSTS, jdbcTemplate);
        return jdbcTemplate.query(DbStaticStr.GET_ALL_CPOSTS, new BetModelMapper());
    }

    @Override
    public Object get(int index) {
        log.info("sql query = " + DbStaticStr.GET_CPOST_BY_ID.formatted(index));
        return jdbcTemplate.queryForObject(DbStaticStr.GET_CPOST_BY_ID.formatted(index),new BetModelMapper());

    }

    @Override
    public void create(BetModel t) {
        jdbcTemplate.execute(DbStaticStr.INSERT_CPOST.formatted(
            t.getTitle(),
            t.getContent(),
            t.getTeam1(),
            t.getTeam2(),
            t.getImage_url(),
            t.getTimestamp()
            ));    
    }

    @Override
    public void update(BetModel t, int index) {
        jdbcTemplate.update(DbStaticStr.UPDATE_CPOST,
        t.getTitle(),
        t.getContent(),
        t.getTeam1(),
        t.getTeam2(),
        t.getImage_url(),
        t.getId());
    }

    @Override
    public void delete(int index) {
        jdbcTemplate.update(DbStaticStr.DELETE_CPOST.formatted(index));
    }

    @Override
    public java.util.List<BetModel> listFromQuery(String query) {
        // TODO Auto-generated method stub
        return jdbcTemplate.query(query, new BetModelMapper());
    }
    
}
