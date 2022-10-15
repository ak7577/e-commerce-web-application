package com.eWebsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.eWebsite.model.*;

public class AddProductDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	
	public AddProductDao(Connection con){
		this.con=con;
	}
	
	public boolean addProducts(Product model) {
		boolean result=false;
		
		try {
			query="INSERT INTO `products` (`id`, `name`, `category`, `price`, `image`) VALUES (?,?,?,?,?)";
			
			pst=this.con.prepareStatement(query);
			
			pst.setInt(1, model.getId());
			pst.setString(2, model.getName());
			pst.setString(3, model.getCategory());
			pst.setDouble(4, model.getPrice());
			pst.setString(5, model.getImage());
			pst.executeUpdate();
			result=true;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("e");
		}
		
		return result;
		
}}

