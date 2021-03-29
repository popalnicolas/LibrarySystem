package ClientSide.View.MyItemsWindow;

import ClientSide.Model.MyItemsModel;
import Shared.SharedObjects.MyItem;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class MyItemsWindowViewModel
{
  private MyItemsModel myItemsModel;
  private ListProperty<MyItem> myitems;

  public MyItemsWindowViewModel(MyItemsModel myItemsModel)
  {
    this.myItemsModel = myItemsModel;
    this.myitems = new SimpleListProperty<>();
    myItemsModel.addListener("return",this::update);
  }

  private void update(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(()->
        {
          List<MyItem> list = myItemsModel.getitems(myItemsModel.getCpr());
          ObservableList<MyItem> aaa = FXCollections.observableArrayList(list);
          myitems.set(aaa);
        });
  }

  public ListProperty<MyItem> getMyitems()
  {
    return myitems;
  }

  public void loadItems()
  {
    List<MyItem> list = myItemsModel.getitems(myItemsModel.getCpr());
    ObservableList<MyItem> aaa = FXCollections.observableArrayList(list);
    myitems.set(aaa);
  }

  public boolean returnItem(MyItem item)
  {
    if(myItemsModel.returnItem(item))
    {
      return true;
    }
    else return false;
  }
}
