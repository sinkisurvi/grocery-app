
package com.grocery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Service {
  DBconnect dbcon;
  public Service(DBconnect _dbcon){
    this.dbcon = _dbcon;

  }
  public void createSchema() throws ClassNotFoundException {
    // load the sqlite-JDBC driver using the current class loader

    Connection connection = this.dbcon.getConnection();

    try {
      // create a database connection

      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.

      // statement.executeUpdate("drop table if exists grocery_detail");
      statement.executeUpdate("create table if not exists grocery_detail (id INTEGER PRIMARY KEY AUTOINCREMENT,gdate string, item string,quantity string)");
      
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    } 
    
  }
  public void sqlInsert(Grocerymodel gm) throws ClassNotFoundException {
    // load the sqlite-JDBC driver using the current class loader

    Connection connection = this.dbcon.getConnection();

    try {
      // create a database connection

      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.
      statement.executeUpdate("insert into grocery_detail values(null,'"+gm.getGdate()+"','"+gm.getItem()+"','"+gm.getQuantity()+"')");
      
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println("sqlInsert exception"+e.getMessage());
    } 
  }
  public void sqlDisplay() throws ClassNotFoundException {
    // load the sqlite-JDBC driver using the current class loader

    Connection connection = this.dbcon.getConnection();

    try {
      // create a database connection

      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.
       ResultSet rs = statement.executeQuery("select * from grocery_detail");
      while (rs.next()) {
      // read the result set
      //System.out.println("date = " + rs.getDate("gdate"));
      System.out.println("id = " + rs.getInt("id"));
      System.out.println("item = " + rs.getString("item"));
      System.out.println("qty = " + rs.getString("quantity"));
      System.out.println("======================");
      }
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println("sqldisplay exception "+e.getMessage());
    } 
  }

  public Grocerymodel getItem(int id) throws ClassNotFoundException {
    // load the sqlite-JDBC driver using the current class loader

    Connection connection = this.dbcon.getConnection();

    try {
      // create a database connection

      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.
       ResultSet rs = statement.executeQuery("select * from grocery_detail where id="+id);
      while (rs.next()) {
      // read the result set
      
      Grocerymodel gm = new Grocerymodel();

      gm.setId(rs.getInt("id"));
      gm.setItem(rs.getString("item"));
      gm.setQuantity(rs.getString("quantity"));
      return gm;
     }
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println("getItem exception "+e.getMessage());
    } 
    return null;
  }

  public void deleteItem(int id) throws ClassNotFoundException {
    // load the sqlite-JDBC driver using the current class loader

    Connection connection = this.dbcon.getConnection();

    try {
      // create a database connection

      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.
       statement.executeQuery("delete from grocery_detail where id="+id);
      
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println("sqldelete exception "+e.getMessage());
    } 
    
  }
  public void sqlUpdate(Grocerymodel gm) throws ClassNotFoundException {
    // load the sqlite-JDBC driver using the current class loader

    Connection connection = this.dbcon.getConnection();

    try {
      // create a database connection

      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.
      statement.executeUpdate("update grocery_detail set gdate='"+gm.getGdate()+"', item = '"+gm.getItem()+"',quantity='"+gm.getQuantity()+"' where id="+gm.getId());
      
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println("sqlUpdate exception "+e.getMessage());
    } 
  }
}

// insert into grocery_detail value('date','item','qnt')
