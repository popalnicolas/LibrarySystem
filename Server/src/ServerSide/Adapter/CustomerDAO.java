package ServerSide.Adapter;

import Shared.SharedObjects.Customer;

import java.util.ArrayList;

public interface CustomerDAO
{
  String addUser(Customer customer);
  ArrayList<Long> findCpr();
  ArrayList<String> findPassword();
  ArrayList<Customer> getCustomer();
  boolean setName(Customer customer);
  boolean setaddress(Customer customer);
  boolean setpassword(Customer customer);
  boolean setCreditcard(Customer customer);
  String findName(Customer customer);
}
