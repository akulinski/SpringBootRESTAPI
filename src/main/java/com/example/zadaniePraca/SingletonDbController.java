package com.example.zadaniePraca;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SingletonDbController {

    private static SingletonDbController instance=null;
    private MysqlDataSource dataSource;
    private java.sql.Connection conn;
    private PreparedStatement preparedStatement=null;
    private final Logger logger = Logger.getLogger(SingletonDbController.class.getName());
    private FileHandler fileHandler=null;

    public boolean isConntected() {
        return conntected;
    }

    private boolean conntected=false;

    SingletonDbController(){
        setUpLogger();
        dataSource = new MysqlDataSource ();

        dataSource.setUser(Keys.user);
        dataSource.setPassword(Keys.password);
        dataSource.setServerName(Keys.name);
        dataSource.setDatabaseName(Keys.databaseName);

        try {
            conn = dataSource.getConnection ();
            conntected=true;
            logger.log(Level.INFO,"Connected");
        } catch (SQLException e) {

            logger.log(Level.SEVERE,"SQL exception Message: "+e.getMessage()+" SQLState  "+e.getSQLState()+" Error code: "+e.getErrorCode());
            e.printStackTrace();
        }
    }

    public static synchronized SingletonDbController getInstance(){
        if(instance==null){
            instance=new SingletonDbController();
        }

        return instance;
    }

    private void setUpLogger()
    {
        try {
            fileHandler=new FileHandler("DB.log");
            logger.addHandler(fileHandler);

            SimpleFormatter formatter = new SimpleFormatter();

            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
