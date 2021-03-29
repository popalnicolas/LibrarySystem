package ClientSide.Model;

import ClientSide.Network.Client;
import Shared.SharedObjects.Customer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelManager implements LoginModel
{
  private PropertyChangeSupport support;
  private Client client;
  private long cpr;


  public LoginModelManager(Client client)
  {
    support=new PropertyChangeSupport(this);
    this.client=client;
    client.startClient();
  }

  @Override public void setCpr(long cpr)
  {
   this.cpr = cpr;
  }

  @Override public boolean register(Customer customer)
  {
    return client.register(customer);
  }

  @Override public boolean login(Customer customer)
  {
    return client.login(customer);
  }

  @Override public boolean change(Customer customer)
  {
    customer.setCpr(cpr);
    return client.change(customer);
  }

  @Override public String getName()
  {
    Customer customer1 = new Customer(cpr,"bah","nothing","stillnothing",-1);
    return client.getname(customer1);
  }

  @Override public long getCpr()
  {
    return cpr;
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName,listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
