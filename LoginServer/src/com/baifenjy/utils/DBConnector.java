package com.baifenjy.utils;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.baifenjy.config.dao.DAOFactory;
import com.baifenjy.config.dao.DatabaseDAO;
import com.baifenjy.config.vo.DataBaseVO;
import com.baifenjy.config.vo.DataBaseVO.MongoVO.ServersVO;
import com.baifenjy.config.vo.DataBaseVO.SqlVO;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;

public class DBConnector
{
	private final Logger logger = Logger.getLogger(DBConnector.class);
    private static DBConnector dbConnector;

    private DBConnector(){}

    public static DBConnector getInstance()
    {
        if (dbConnector == null)
        {
            dbConnector = new DBConnector();
        }
        return dbConnector;
    }

    public Connection getConnection()
    {
    	try
		{
    		return JdbcUtilsDBCP.getConnection();
		} catch (SQLException e)
		{
			logger.error("",e);
		}
    	return null;
    }
    
    public void release(Connection conn,PreparedStatement ps,ResultSet rs){
    	JdbcUtilsDBCP.release(conn, ps, rs);
    }
    
    public Mongo getMongClient(){
    	return MongoUtil.getMongo();
    }
    

    static class JdbcUtilsDBCP{

    	private static Logger logger = Logger.getLogger(JdbcUtilsDBCP.class);
        /**
         * In Java, the database connection pool is written to implement java.sql.DataSource interface. 
         * Every database connection pool is implemented by DataSource interface.
         * The DBCP connection pool is a concrete implementation of the java.sql.DataSource interface.
         */
        private static BasicDataSource dataSource = null;
        private static SqlVO sqlVO;
    	 private static DataBaseVO dataBaseVO;
    	 private static String driver;
    	 private static String url;
    	 private static String username;
    	 private static String password;
    	 private static int initSize = 100;//initialSize connection num
    	 private static int maxActive = 1000;//max connection num
    	 private static int maxIdle = 20 ;//Maximum idle connection 
    	 private static int minIdle = 5;//Minimum idle connection 
    	 private static int maxWait = 60000;//timeout waiting time in milliseconds of 6000 milliseconds /1000 equal to 60 seconds
    			 
        //Create a database connection pool in a static code block
        static{
        	DatabaseDAO databaseDAO = DAOFactory.getDataBaseDAO();
    		dataBaseVO = databaseDAO.getDataBaseVO();
    		sqlVO = dataBaseVO.getSql();
    		driver = "com.mysql.jdbc.Driver";
            url = sqlVO.getUrl();
            username = sqlVO.getUser();
            password = sqlVO.getPass();
            try{
                //Create a dataSource
            	dataSource = new BasicDataSource();
            	dataSource.setDriverClassName(driver);
            	dataSource.setUrl(url);
                dataSource.setUsername(username);
                dataSource.setPassword(password);
                dataSource.setInitialSize(initSize);
                dataSource.setMaxActive(maxActive);
                dataSource.setMaxIdle(maxIdle);
                dataSource.setMinIdle(minIdle);
                dataSource.setMaxWait(maxWait);
                //for test fix time_out bug
                dataSource.setTestWhileIdle(true);
                dataSource.setTimeBetweenEvictionRunsMillis(3600000);
                dataSource.setValidationQuery("select 1");
                dataSource.setNumTestsPerEvictionRun(maxActive);
                dataSource.setValidationQueryTimeout(60000);
            }catch (Exception e) {
            	logger.error("", e);
            }
        }

        /**
        * @Method: getConnection
        * @Description: Obtaining a database connection from a data source
        * @Anthor:michael
        * @return Connection
        * @throws SQLException
        */ 
        public static Connection getConnection() throws SQLException{
            //Obtaining a database connection from a data source
            return dataSource.getConnection();
        }

        /**
        * @Method: release
        * @Description: release data source��
        * The resources released include the Connection database connection object, 
        * the Statement object responsible for executing the SQL command, 
        * and the ResultSet object that stores the query results.
        * @Anthor:michael
        *
        * @param conn
        * @param ps
        * @param rs
        */ 
        public static void release(Connection conn,PreparedStatement ps,ResultSet rs){
            if(rs!=null){
                try{
                	 rs.close();
                }catch (Exception e) {
                	logger.error("", e);
                }
            }
            if(ps!=null){
                try{
                	ps.close();
                }catch (Exception e) {
                    logger.error("", e);
                }
            }

            if(conn!=null){
                try{
                    conn.close();
                }catch (Exception e) {
                    logger.error("", e);
                }
            }
        }

    }
    
    static class MongoUtil{
    	private static Logger logger = Logger.getLogger(MongoUtil.class);
    	private static Mongo mongo;
    	private static int connectionsPerHost;
    	private static int numberOfWrites;
    	private static ServerAddress serverAddress;
    	private static List<ServerAddress> serverAddresss = new ArrayList<ServerAddress>();
    	
    	static{
    		try
    		{
    			String IP = null;
    			int port = 0;
    			DatabaseDAO databaseDAO = DAOFactory.getDataBaseDAO();
    			DataBaseVO dataBaseVO = databaseDAO.getDataBase();
    			connectionsPerHost = dataBaseVO.getMongo().getConnectionsPerHost();
    			numberOfWrites = dataBaseVO.getMongo().getNumberOfWrites();
    			List<ServersVO> servers = dataBaseVO.getMongo().getServers();
    			for (Iterator<ServersVO> iterator = servers.iterator(); iterator.hasNext();)
    			{
    				ServersVO serversVO = iterator.next();
    				IP = serversVO.getIp();
    				port = serversVO.getPort();
    				serverAddress= new ServerAddress(IP,port);
    				serverAddresss.add(serverAddress);
    			}
    			Mongo mongoClient = new Mongo(serverAddresss);
    			mongoClient.getMongoOptions().setConnectionsPerHost(connectionsPerHost);
//    			mongoClient.getMongoOptions().setW(numberOfWrites);
    			mongo =  mongoClient;
    		} catch (UnknownHostException e)
    		{
    			logger.error("mongo exception ",e);
    		}
    	}
    	public static Mongo getMongo(){
    		return mongo;
    	}
}

    public static void setDataSource(BasicDataSource basicDataSource)
    {
        JdbcUtilsDBCP.dataSource = basicDataSource;
    }

}
