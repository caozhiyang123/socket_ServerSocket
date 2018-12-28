package com.baifenjy.config.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.baifenjy.config.vo.DataBaseVO;
import com.baifenjy.config.vo.DataBaseVO.MongoVO;
import com.baifenjy.config.vo.DataBaseVO.MongoVO.ServersVO;
import com.baifenjy.config.vo.DataBaseVO.SqlVO;
import com.baifenjy.utils.ConfigConnector;

import java.util.ArrayList;
import java.util.Iterator;


public class DatabaseDAO extends BaseDAO
{
	private ConfigConnector configConnector;
	private JSONObject jsonObject;
	private DataBaseVO dataBaseVO;
	
	public DataBaseVO getDataBaseVO(){
		configConnector = ConfigConnector.getInstance();
		jsonObject = configConnector.parseToJson("server.json");
		setDataBase();
		return getDataBase();
	}
	
	public DataBaseVO getDataBase(){
		return dataBaseVO;
	}
	
	private void setDataBase(){
		JSONObject databaseObj = (JSONObject)jsonObject.get("database");
		JSONObject sqlObj = (JSONObject)databaseObj.get("sql");
		JSONObject mongoObj = (JSONObject)databaseObj.get("mongo");
		
		dataBaseVO = new DataBaseVO();
		SqlVO sqlVO = new DataBaseVO.SqlVO();
		MongoVO mongoVO = new DataBaseVO.MongoVO();
		
		sqlVO.setUrl(sqlObj.get("url").toString());
		sqlVO.setUser(sqlObj.get("user").toString());
		sqlVO.setPass(sqlObj.get("pass").toString());
		dataBaseVO.setSql(sqlVO);
		
		mongoVO.setConnectionsPerHost(Integer.parseInt(mongoObj.get("connectionsPerHost").toString()));
		mongoVO.setNumberOfWrites(Integer.parseInt(mongoObj.get("numberOfWrites").toString()));
		JSONArray serversArray = (JSONArray)mongoObj.get("servers");
		Iterator iterator = serversArray.iterator();
		ServersVO serversVO = new ServersVO();
		ArrayList<ServersVO> serversVOs = new ArrayList<ServersVO>();
		while(iterator.hasNext()){
			JSONObject object = (JSONObject) iterator.next();
			serversVO.setIp(object.get("ip").toString());
			serversVO.setPort(Integer.parseInt(object.get("port").toString()));
			serversVOs.add(serversVO);
		}
		mongoVO.setServers(serversVOs);
		dataBaseVO.setMongo(mongoVO);
		
	}
}