package ServerSide.Adapter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Database
{
  private DatabaseInfo databaseInfo = new DatabaseInfo();
  private String driver = "org.postgresql.Driver";
  private String url = "jdbc:postgresql://localhost:5432/LibrarySystem?currentSchema=library";
  private String user = databaseInfo.getUsename();
  private String password = databaseInfo.getPassword();

  private static Database instance;
  private static Lock lock = new ReentrantLock();

  Connection connection = null;

  private Database()
  {
    connect();
  }

  public void connect()
  {
    try
    {
      Class.forName(driver);
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }

    try
    {
      connection = DriverManager.getConnection(url, user, password);
      JOptionPane.showMessageDialog(null, "Connected");
    } catch(SQLException e)
    {
      e.printStackTrace();
    }
  }

  public Connection getConnection()
  {
    return connection;
  }

  public static Database getInstance()
  {
    if (instance == null)
      synchronized (lock)
      {
        if (instance == null)
          instance = new Database();
      }
    return instance;
  }




}
