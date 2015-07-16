/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.products;

import DatabaseConnection.databaseconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author c0654504
 */
@Path("product")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    Connection conn;
    
    products p = new products();
    ArrayList<products> products= new ArrayList<>();
    
    public GenericResource() {
             
    }

    /**
     * Retrieves representation of an instance of com.assignment4.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/products")
    @Produces("application/json")
    public ArrayList<products> getJson() throws SQLException {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();

         conn = databaseconnection.getConnection();
         String query ="select * from product";
         
         Statement  st = conn.createStatement();
         ResultSet  rs = st.executeQuery(query);
         
         while(rs.next()){
         
          products  pnew = new products(rs.getInt("ProductID"),rs.getString("name"),rs.getString("description"), rs.getInt("quantity"));
          products.add(pnew);        
                   
         }
//         
//        if (conn == null)
//        {
//        return "connection is not created";
//        }
//        else{
//        
//        return "connection is created";}
        
        return products;
    }
            
    
      @GET
      @Path("/products/{productid}")
      @Produces("application/json")
        public ArrayList<products> getJsonOne(@PathParam("productid") int productid) throws SQLException {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
    
         conn = databaseconnection.getConnection();
         String query ="";
         
         Statement  st = conn.createStatement();
         ResultSet  rs = st.executeQuery("select * from product where ProductID="+productid);
         
         while(rs.next()){
         
          products pnew = new products(rs.getInt("ProductID"),rs.getString("name"),rs.getString("description"), rs.getInt("quantity"));
          products.add(pnew);        
                   
         }
        return products;
    }
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Path("/products1")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public void postJson(String content) throws ParseException, SQLException {
        
        JSONParser jp = new JSONParser();
        JSONObject obj = (JSONObject) jp.parse(content);
        
        Object objid = obj.get("id");
        String ProductID = objid.toString();
        int id = Integer.parseInt(ProductID);
        
             Object objname = obj.get("name");
        String name = objname.toString();
        
          Object objdes = obj.get("description");
        String description = objdes.toString();
        
     
        
        Object objquantity = obj.get("quantity");
        String quantity_new = objquantity.toString();
        int quantity = Integer.parseInt(quantity_new);
        
        
         conn = databaseconnection.getConnection();
        // String query ="";
        String query="insert into product(ProductID, name, description, quantity) values('"+id+"','"+name+"','"+description+"','"+quantity+"')"; 
        Statement  st = conn.createStatement();
        st.executeUpdate(query);
        
    }
    
    
    
     @PUT
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
   
    public void putProduct(String content) throws SQLException, ParseException
    {
            
        JSONParser jp = new JSONParser();
        JSONObject obj = (JSONObject) jp.parse(content);
        
        Object objid = obj.get("id");
        String ProductID = objid.toString();
        int id = Integer.parseInt(ProductID);
        
             Object objname = obj.get("name");
        String name = objname.toString();
        
          Object objdes = obj.get("description");
        String description = objdes.toString();
        
     
        
        Object objquantity = obj.get("quantity");
        String quantity_new = objquantity.toString();
        int quantity = Integer.parseInt(quantity_new);
        
        
         conn = databaseconnection.getConnection();
        // String query ="";
        String query="insert into product(ProductID, name, description, quantity) values('"+id+"','"+name+"','"+description+"','"+quantity+"')"; 
        Statement  st = conn.createStatement();
        st.executeUpdate(query);
    
    
    }
    
    @DELETE
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
   
    public void deleteProduct(String content) throws SQLException, ParseException
    {
          
        JSONParser jp = new JSONParser();
        JSONObject obj = (JSONObject) jp.parse(content);
        
        Object objid = obj.get("id");
        String ProductID = objid.toString();
        int id = Integer.parseInt(ProductID);
        
             Object objname = obj.get("name");
        String name = objname.toString();
        
          Object objdes = obj.get("description");
        String description = objdes.toString();
        
     
        
        Object objquantity = obj.get("quantity");
        String quantity_new = objquantity.toString();
        int quantity = Integer.parseInt(quantity_new);
        
        
         conn = databaseconnection.getConnection();
        // String query ="";
        String query="insert into product(ProductID, name, description, quantity) values('"+id+"','"+name+"','"+description+"','"+quantity+"')"; 
        Statement  st = conn.createStatement();
        st.executeUpdate(query);
        }
}
    
