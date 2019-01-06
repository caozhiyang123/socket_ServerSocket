package com.baifenjy.utils;

import java.sql.ResultSet;

import com.mysql.jdbc.StringUtils;

public class pageQuery {/*
    public ResultSet getTcmCradList(TcmCardInfo u,int pageNum,int pageSize) {
    		
    		StringBuffer sb = new StringBuffer("select * from tcm_cardinfo where 1=1");
    		if(!StringUtils.isNullOrEmpty(u.getCardNo())) {
    			sb.append(" and cardNo like '%" + u.getCardNo() + "%'");
    		}
    		if(!StringUtils.isNullOrEmpty(u.getNetworkType())) {
    			sb.append(" and networkType like '%" + u.getNetworkType() + "%'");
    		}
    		if(!StringUtils.isNullOrEmpty(u.getMisdn())) {
    			sb.append(" and misdn like '%" + u.getMisdn() + "%'");
    		}
    		
    		sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize+"");
    		try {
    			conn = DBConnector.getConection();
    			ps = conn.prepareStatement(sb.toString());
    			return ps.executeQuery();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		return null;
    	}
*/}

