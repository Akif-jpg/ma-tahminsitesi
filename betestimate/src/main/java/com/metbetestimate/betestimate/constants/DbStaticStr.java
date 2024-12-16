package com.metbetestimate.betestimate.constants;

/**
 * DatabaseNames
 */
public class DbStaticStr {

    public static final String BET_TABLE_NAME = "posts";
    public static final String C_BET_TABLE_NAME = "current_posts";
    private static final String MAIL_TABLE_NAME = "mails";

    public static final String GET_ALL_POSTS = "SELECT * FROM "+BET_TABLE_NAME+" ORDER BY posted_at DESC LIMIT 1000";
    public static final String GET_POST_BY_ID = "SELECT * FROM "+BET_TABLE_NAME+" WHERE id = %d";
    public static final String INSERT_POST = "INSERT INTO "+BET_TABLE_NAME+" (title,content,team1,team2,image_url,posted_at) VALUES ('%s','%s','%s','%s','%s','%s')";
    public static final String UPDATE_POST = "UPDATE "+BET_TABLE_NAME + " SET title = ?, content = ?, image_url = ? WHERE id = ?";
    public static final String DELETE_POST = "DELETE FROM "+BET_TABLE_NAME+" WHERE id = '%d'";

    public static final String GET_ALL_CPOSTS = "SELECT * FROM "+C_BET_TABLE_NAME;
    public static final String GET_CPOST_BY_ID = "SELECT * FROM "+C_BET_TABLE_NAME+" WHERE id = %d";
    public static final String INSERT_CPOST = "INSERT INTO "+C_BET_TABLE_NAME+" (title,content,team1,team2,image_url,posted_at) VALUES ('%s','%s','%s','%s','%s','%s')";
    public static final String UPDATE_CPOST = "UPDATE "+C_BET_TABLE_NAME + " SET title = ?, content = ?, image_url = ? WHERE id = ?";
    public static final String DELETE_CPOST = "DELETE FROM "+C_BET_TABLE_NAME+" WHERE id = '%d'";

    public static final String GET_ALL_MAILS = "SELECT * FROM "+MAIL_TABLE_NAME;
    public static final String GET_MAIL_BY_ID = "SELECT * FROM "+MAIL_TABLE_NAME+" WHERE id = %d";
    public static final String INSERT_MAIL = "INSERT INTO "+MAIL_TABLE_NAME+" (email,content,ischecked) VALUES ('%s','%s','%b')";
    public static final String UPDATE_MAIL = "UPDATE "+MAIL_TABLE_NAME + " SET email = ?, content = ?, unchecked = ? WHERE id = ?";
    public static final String DELETE_MAIL = "DELETE FROM "+MAIL_TABLE_NAME+" WHERE id = '%d'";

    
}