package ClientSide.Model;

import ClientSide.Network.Client;
import Shared.SharedObjects.MyItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class MyItemsModelManager implements MyItemsModel
{
  private Client client;
  private long cpr;
  private PropertyChangeSupport support;

  public MyItemsModelManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    client.addListener("return",this::returned);
  }

  private void returned(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  @Override public void setCpr(long cpr)
  {
    this.cpr = cpr;
  }

  @Override public long getCpr()
  {
    return cpr;
  }

  @Override public List<MyItem> getitems(long cpr)
  {
    return client.getitems(cpr);
  }


  @Override public boolean returnItem(MyItem item)
  {
   return client.returnItem(item,cpr);
  }

  @Override public void checkDate()
  {
    client.checkDate();
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
support.addPropertyChangeListener(eventName, listener);
  }
}
