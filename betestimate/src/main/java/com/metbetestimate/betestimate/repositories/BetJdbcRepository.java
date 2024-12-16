package com.metbetestimate.betestimate.repositories;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.metbetestimate.betestimate.constants.DbStaticStr;
import com.metbetestimate.betestimate.models.BetModel;
import com.metbetestimate.betestimate.models.mappers.BetModelMapper;


@Component
/*
 * Repository for the bet model database operations.
 */
public class BetJdbcRepository implements Repository<BetModel>{
    private static final Logger log = LoggerFactory.getLogger(BetJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    BetJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BetModel> List() {
        
        log.info("sql query = "+DbStaticStr.GET_ALL_POSTS, jdbcTemplate);
        return jdbcTemplate.query(DbStaticStr.GET_ALL_POSTS, new BetModelMapper());
       
    }

    @Override
    public BetModel get(int index) {
        log.info("sql query = " + DbStaticStr.GET_POST_BY_ID.formatted(index));
        return jdbcTemplate.queryForObject(DbStaticStr.GET_POST_BY_ID.formatted(index),new BetModelMapper());
    }

    @Override
    public void create(BetModel t) {
        jdbcTemplate.execute(DbStaticStr.INSERT_POST.formatted(
            t.getTitle(),
            t.getContent(),
            t.getTeam1(),
            t.getTeam2(),
            t.getImage_url(),
            t.getTimestamp()));
        
    }

    @Override
    public void update(BetModel t, int index){
        jdbcTemplate.update(DbStaticStr.UPDATE_POST,
        t.getTitle(),
        t.getContent(),
        t.getImage_url(),
        t.getId());

    }

    @Override
    public void delete(int index) {
        jdbcTemplate.update(DbStaticStr.DELETE_POST.formatted(index));
       
    }

    @Override
    public java.util.List<BetModel> listFromQuery(String query) {
       log.info("sql query= "+query);
        return jdbcTemplate.query(query, new BetModelMapper());
    }
    
}
