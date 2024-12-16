package com.metbetestimate.betestimate.models.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.metbetestimate.betestimate.models.BetModel;

public class BetModelMapper implements RowMapper<BetModel> {

    @Override
    @Nullable
    public BetModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            BetModel bModel = new BetModel();
            bModel.setId(rs.getInt("id"));
            bModel.setTitle(rs.getString("title"));
            bModel.setContent(rs.getString("content"));
            bModel.setTeam1(rs.getString("team1"));
            bModel.setTeam2(rs.getString("team2"));
            bModel.setImage_url(rs.getString("image_url"));
            bModel.setTimestamp(rs.getTimestamp("posted_at"));
            return bModel;
        
    }
    
}
