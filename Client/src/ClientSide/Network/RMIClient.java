package ClientSide.Network;


import Shared.Network.ClientCallback;
import Shared.Network.RMIServer;
import Shared.SharedObjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class RMIClient implements Client, ClientCallback
{
  private RMIServer server;
  private PropertyChangeSupport support;
  public RMIClient() {support=new PropertyChangeSupport(this);}
  @Override public void startClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this,0);
      Registry registry=LocateRegistry.getRegistry("localhost",1099);
      server = (RMIServer) registry.lookup("LibraryServer");
      server.registerClientItems(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public boolean register(Customer customer)
  {
    try
    {
      return server.registrer(customer);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public boolean login(Customer customer)
  {
    try
    {
      return server.login(customer);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public boolean change(Customer customer)
  {
    try
    {
      return server.change(customer);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public String getname(Customer customer)
  {
    try
    {
      return server.getname(customer);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public List<Item> getlist()
  {
    try
    {
      return server.getlist();
    }
    catch (Exception e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public void remove(Item item)
  {
    try
    {
      server.remove(item);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public String getAuthor(int id)
  {
    try
    {
      return server.getAuthor(id);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public Item getItem(int id)
  {
    try
    {
      return server.getItem(id);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public String getDeveloper(int id)
  {
    try
    {
      return server.getDeveloper(id);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public String getProductionCompany(int id)
  {
    try
    {
      return server.getProductionCompany(id);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public Info borrow(long cpr, int id, String type)
  {
    try
    {
      return server.borrow(cpr, id, type);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public void borrowconfirm(long cpr, int id, String type, LocalDate date)
  {
    try
    {
      server.borrowconfirm(cpr, id, type,date);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public List<MyItem> getitems(long cpr)
  {
    try
    {
      return server.getitems(cpr);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public boolean returnItem(MyItem item,long cpr)
{
  try
  {
    return server.returnItem(item,cpr);
  }
  catch (Exception e)
  {
    throw new RuntimeException("Could not contact server");
  }
}

  @Override public boolean checkCount(long cpr)
  {
    try
    {
      return server.checkCount(cpr);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public List<Item> searchItem(String category, String searchtext)
  {
    try
    {
      return server.searchItem(category, searchtext);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public String getGenre(int id, String type)
  {
    try
    {
      return server.getGenre(id, type);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public void checkDate()
  {
    try
    {
      server.checkDate();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override public void updateMyItems()
  {
    support.firePropertyChange("return",null,null);
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
  support.removePropertyChangeListener(eventName, listener);
  }
}
