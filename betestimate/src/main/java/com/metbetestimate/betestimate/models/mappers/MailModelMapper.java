package com.metbetestimate.betestimate.models.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.metbetestimate.betestimate.models.MailModel;

public class MailModelMapper implements RowMapper<MailModel>{

    @Override
    @Nullable
    public MailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        MailModel mailModel = new MailModel();
        mailModel.setId(rs.getInt("id"));
        mailModel.setEmail(rs.getString("email"));
        mailModel.setContent(rs.getString("content"));
        mailModel.setTimestamp(rs.getTimestamp("posted_at"));
        mailModel.setChecked(rs.getBoolean("ischecked"));
        return mailModel;
    }
    
}
