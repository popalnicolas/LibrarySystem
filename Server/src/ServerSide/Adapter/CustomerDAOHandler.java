package ServerSide.Adapter;

import Shared.SharedObjects.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDAOHandler implements CustomerDAO
{
  private Connection connection;
  PreparedStatement preparedStatement;
  private Customer customer;

  public CustomerDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  @Override public String addUser(Customer customer)
  {
    ArrayList<Long> cprs;
    cprs = findCpr();
    try
    {

      if (cprs.size() == 0)
      {
        preparedStatement = connection
            .prepareStatement("INSERT INTO CUSTOMER VALUES(?, ?, ?, ?, ?)");
        preparedStatement.setLong(1, customer.getCpr());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setString(4, customer.getAddress());
        preparedStatement.setLong(5, customer.getCard());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        return "ok";
      }
      else if (cprs.size() > 0)
      {
        for (int i = 0; i < cprs.size(); i++)
        {
          preparedStatement = connection
              .prepareStatement("INSERT INTO CUSTOMER VALUES(?, ?, ?, ?, ?)");
          preparedStatement.setLong(1, customer.getCpr());
          preparedStatement.setString(2, customer.getPassword());
          preparedStatement.setString(3, customer.getName());
          preparedStatement.setString(4, customer.getAddress());
          preparedStatement.setLong(5, customer.getCard());

          preparedStatement.executeUpdate();
          preparedStatement.close();
          return "ok";
        }
      }
    }
    catch (SQLException e)
    {
      return "AlreadyThere";
    }
    return "Error";
  }

  protected List<Long> convertCpr(ResultSet resultSet)
  {
    ArrayList<Long> results = new ArrayList<Long>();
    try
    {
      while (resultSet.next())
      {
        Long cpr = resultSet.getLong("cpr");
        results.add(cpr);
      }
      return results;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<Long> findCpr()
  {
    try
    {
      String querysql = "SELECT CPR FROM CUSTOMER";
      PreparedStatement querySelectCPR = connection.prepareStatement(querysql);
      ResultSet resultSet = querySelectCPR.executeQuery();

      ArrayList<Long> results = (ArrayList<Long>) convertCpr(resultSet);
      resultSet.close();
      querySelectCPR.close();
      return results;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  protected List<String> convertPassword(ResultSet resultSet)
  {
    ArrayList<String> results = new ArrayList<String>();
    try
    {
      while (resultSet.next())
      {
        String password = resultSet.getString("password");
        results.add(password);
      }
      return results;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<String> findPassword()
  {
    ArrayList<String> passwordList = new ArrayList<>();
    try
    {
      String querysql = "SELECT PASSWORD FROM CUSTOMER";
      PreparedStatement querySelectCPR = connection.prepareStatement(querysql);
      ResultSet resultSet = querySelectCPR.executeQuery();

      ArrayList<String> results = (ArrayList<String>) convertPassword(
          resultSet);
      resultSet.close();
      querySelectCPR.close();
      return results;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return passwordList;
  }

    protected List<Customer> convertCustomer(ResultSet resultSet)
    {
      ArrayList<Customer> customerList = new ArrayList<>();
      try
      {
        while (resultSet.next())
        {
          Long cpr = resultSet.getLong("cpr");
          String name = resultSet.getString("name");
          String password = resultSet.getString("password");
          String address = resultSet.getString("address");
          Long creditCard = resultSet.getLong("creditCard");
          Customer customer = new Customer(cpr, name, password, address,
              creditCard);
          customerList.add(customer);
        }
        return customerList;
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return Collections.emptyList();
    }

  @Override public ArrayList<Customer> getCustomer()
  {
    try
    {
      String querysql = "SELECT CUSTOMER.* FROM CUSTOMER";
      PreparedStatement querySelectCustomer = connection
          .prepareStatement(querysql);
      ResultSet resultSet = querySelectCustomer.executeQuery();
           ArrayList<Customer> customerList = (ArrayList<Customer>) convertCustomer(
                resultSet);
      resultSet.close();
      querySelectCustomer.close();
      return customerList;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public boolean setName(Customer customer)
  {
    try
    {
      String updateName = "UPDATE CUSTOMER SET NAME = ? WHERE CPR = ?";
      PreparedStatement updateNameStatement = connection
          .prepareStatement(updateName);
      updateNameStatement.setString(1, customer.getName());
      updateNameStatement.setLong(2, customer.getCpr());

      updateNameStatement.executeUpdate();
      updateNameStatement.close();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  @Override public boolean setaddress(Customer customer)
  {
    try
    {
      String updateAddress = "UPDATE CUSTOMER SET ADDRESS = ? WHERE CPR = ?";
      PreparedStatement updateAddressStatement = connection
          .prepareStatement(updateAddress);
      updateAddressStatement.setString(1, customer.getAddress());
      updateAddressStatement.setLong(2, customer.getCpr());

      updateAddressStatement.executeUpdate();
      updateAddressStatement.close();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  @Override public boolean setpassword(Customer customer)
  {
    try
    {
      String updatePassword = "UPDATE CUSTOMER SET PASSWORD = ? WHERE CPR = ?";
      PreparedStatement updatePasswordStatement = connection
          .prepareStatement(updatePassword);
      updatePasswordStatement.setString(1, customer.getPassword());
      updatePasswordStatement.setLong(2, customer.getCpr());

      updatePasswordStatement.executeUpdate();
      updatePasswordStatement.close();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  @Override public boolean setCreditcard(Customer customer)
  {
    try
    {
      String updateCreditCard = "UPDATE CUSTOMER SET CREDITCARD = ? WHERE CPR = ?";
      PreparedStatement updateCreditCardStatement = connection
          .prepareStatement(updateCreditCard);
      updateCreditCardStatement.setLong(1, customer.getCard());
      updateCreditCardStatement.setLong(2, customer.getCpr());

      updateCreditCardStatement.executeUpdate();
      updateCreditCardStatement.close();
      return true;
    }
    catch (SQLException e)
    {
      return false;
    }
  }

  @Override public String findName(Customer customer)
  {
    try
    {
      String querysql = "SELECT NAME FROM CUSTOMER WHERE CPR = ?";
      PreparedStatement selectName = connection.prepareStatement(querysql);
      selectName.setLong(1, customer.getCpr());
      ResultSet resultSet = selectName.executeQuery();

      while (resultSet.next())
      {
        Object[] row = new Object[resultSet.getMetaData().getColumnCount()];

        for (int i = 0; i < row.length; i++)
        {
          row[i] = resultSet.getObject(i + 1);
        }
        return row[0].toString();
      }
      resultSet.close();
      selectName.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

}
