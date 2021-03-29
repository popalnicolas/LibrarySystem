package ServerSide.Network;

import ServerSide.Model.ServerModel;
import Shared.Network.ClientCallback;
import Shared.Network.RMIServer;
import Shared.SharedObjects.*;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class Server implements RMIServer
{
  private ServerModel serverModel;

  public Server(ServerModel serverModel) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this,0);
    this.serverModel = serverModel;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("LibraryServer",this);
  }

  @Override public boolean registrer(Customer customer)
  {
    return serverModel.register(customer);
  }

  @Override public boolean login(Customer customer)
  {
    return serverModel.login(customer);
  }

  @Override public boolean change(Customer customer)
  {
    return serverModel.change(customer);
  }

  @Override public String getname(Customer customer)
  {
    return serverModel.getname(customer);
  }

  @Override public List<Item> getlist()
  {
    return serverModel.getList();
  }

  @Override public void remove(Item item)
  {
    serverModel.delete(item);
  }

  @Override public void registerClientItems(ClientCallback clientCallback)
  {
    PropertyChangeListener listener = null;
    PropertyChangeListener finalListener = listener;

    listener = evt-> {
      try
      {
        clientCallback.updateMyItems();
      }
      catch (RemoteException e)
      {
        serverModel.removeListener("return", finalListener);
      }
    };
    serverModel.addListener("return", listener);
  }

  @Override public String getAuthor(int id)
  {
    return serverModel.getAuthor(id);
  }

  @Override public Item getItem(int id)
  {
    return serverModel.getItem(id);
  }

  @Override public String getDeveloper(int id)
  {
    return serverModel.getDeveloper(id);
  }

  @Override public String getProductionCompany(int id)
  {
    return serverModel.getProductionCompany(id);
  }

  @Override public Info borrow(long cpr, int id, String type)
  {
    return serverModel.borrow(cpr, id, type);
  }

  @Override public void borrowconfirm(long cpr, int id, String type, LocalDate date)
  {
    serverModel.borrowconfirm(cpr, id, type,date);
  }

  @Override public List<MyItem> getitems(long cpr)
  {
    return serverModel.getitems(cpr);
  }

  @Override public boolean returnItem(MyItem item,long cpr)
  {
   return serverModel.returnItem(item,cpr) ;
  }

  @Override public boolean checkCount(long cpr)
  {
    return serverModel.checkCount(cpr);
  }

  @Override public List<Item> searchItem(String category, String searchtext)
  {
    return serverModel.searchItem(category,searchtext);
  }

  @Override public String getGenre(int id, String type)
  {
    return serverModel.getGenre(id, type);
  }

  @Override public void checkDate()
  {
    serverModel.checkDate();
  }

}
