package ClientSide.View.BorrowWindow;

import ClientSide.Model.BorrowModel;
import ClientSide.Model.LoginModel;
import Shared.SharedObjects.Item;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class BorrowWindowViewModel
{
  private LoginModel loginModel;
  private BorrowModel borrowModel;
  private ListProperty<Item> items;
  private StringProperty searchtext;

  public BorrowWindowViewModel(LoginModel loginModel, BorrowModel borrowModel)
  {
    this.loginModel = loginModel;
    this.borrowModel = borrowModel;
    searchtext = new SimpleStringProperty();
    items = new SimpleListProperty<>();
  }

  public StringProperty searchtextProperty()
  {
    return searchtext;
  }

  public ListProperty<Item> itemsProperty()
  {
    return items;
  }

  public void loadItems()
  {
    List<Item> list = borrowModel.getitems();
    ObservableList<Item> list2 = FXCollections.observableArrayList(list);
    items.set(list2);
  }

  public void getSelectedItem(int id,String type)
  {
    borrowModel.setItem(id,type);
  }

  public ObservableList<Item> getItems()
  {
    return items;
  }

  public String search(String category)
  {
    List<Item> searcheditems = borrowModel.searchItem(category,searchtext.getValue());
    if(searcheditems.size()!=0)
    {
      Platform.runLater(()->
      {
        ObservableList something = FXCollections.observableArrayList(searcheditems);
        items.clear();
        items.set(something);
      });
      return "search";
    }
    else return "error";
  }

}
