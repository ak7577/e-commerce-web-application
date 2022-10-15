package com.eWebsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.*;
import com.eWebsite.model.*;
 
public class ProductDao 
{
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) 
	{
		this.con = con;
	}
	
	
	public List<Product> getAllProducts()
	{
		List<Product> products =new ArrayList<Product>();
		
		try {
			query ="SELECT * FROM `products`";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Product row=new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
		
	}
	
	//new method to show all the products which are exist in cart  aka [show data in cart]
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList)
	{
		List<Cart> products=new ArrayList<Cart>();
		
		try 
		{
			if(cartList.size()>0) 
			{
				for(Cart item:cartList) 
				{
					query="select *from products where id=?";
					pst= this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					while(rs.next()) 
					{
						Cart row=new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantiy(item.getQuantity());
						products.add(row);
					}
				}
			}
			
		}catch(Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return products;
	}
	
	
	public Product getSingleProduct(int id) {
		
		Product row=null;
		
		try {
			query="SELECT * FROM products WHERE id=?"; 
			pst=this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				row= new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
			}  
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		
		return row;
	}
	
	//to calculate and show total price in the cart section

	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum=0;
		
		try{
			if(cartList.size()>0) {
				for(Cart item:cartList) 
				{
					query="SELECT price FROM `products` WHERE id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
					}
					
				}
			}
				
		}catch (Exception e){
			e.printStackTrace();
		}
		return sum;
	}
}
