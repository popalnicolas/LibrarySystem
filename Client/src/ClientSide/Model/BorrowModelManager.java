package ClientSide.Model;

import ClientSide.Network.Client;
import Shared.SharedObjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.List;

public class BorrowModelManager implements BorrowModel
{
  private PropertyChangeSupport support;
  private Client client;
  private Long cpr;
  private int id;
  private String type;

  public BorrowModelManager(Client client)
  {
    this.support = new PropertyChangeSupport(this);
    this.client=client;
  }

  @Override public void setCpr(long cpr)
  {
    this.cpr = cpr;
  }

  @Override public long getCpr()
  {
    return cpr;
  }

  @Override public void remove(Item item)
  {
    client.remove(item);
  }

  @Override public List<Item> getitems()
  {
    return client.getlist();
  }

  @Override public void setItem(int id,String type)
  {
    this.id = id;
    this.type = type;
  }

  @Override public Item getItem()
  {
    return client.getItem(id);
  }

  @Override public String getDeveloper(int id)
  {
    return client.getDeveloper(id);
  }

  @Override public String getAuthor(int id)
  {
    return client.getAuthor(id);
  }

  @Override public String getProductionCompany(int id)
  {
    return client.getProductionCompany(id);
  }

  @Override public Info borrow(long cpr, int id, String type)
  {
   return client.borrow(cpr, id, type);
  }

  @Override public void borrowconfirm(long cpr, int id, String type, LocalDate date)
  {
    client.borrowconfirm(cpr, id, type,date);
  }

  @Override public boolean checkCount(long cpr)
  {
    return client.checkCount(cpr);
  }

  @Override public List<Item> searchItem(String category, String searchtext)
  {
    return client.searchItem(category,searchtext);
  }

  @Override public String getGenre(int id, String type)
  {
    return client.getGenre(id, type);
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
