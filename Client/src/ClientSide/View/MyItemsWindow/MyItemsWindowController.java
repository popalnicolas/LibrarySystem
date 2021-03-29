package ClientSide.View.MyItemsWindow;

import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;
import ClientSide.View.ViewController;
import Shared.SharedObjects.MyItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class MyItemsWindowController implements ViewController
{
  private ViewHandler viewHandler;
  private MyItemsWindowViewModel myItemsWindowViewModel;
  @FXML private ListView<MyItem> list;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    viewHandler = vh;
    myItemsWindowViewModel = vmf.getMyItemsWindowViewModel();
    list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    myItemsWindowViewModel.loadItems();
    list.itemsProperty().bind(myItemsWindowViewModel.getMyitems());
  }

  @FXML private void back()
  {
    viewHandler.openMain();
  }

  @FXML private void returnitem()
  {
    if(list.getSelectionModel().getSelectedItem()==null)
    {
      return;
    }
    else
      {
        myItemsWindowViewModel.returnItem(list.getSelectionModel().getSelectedItem());
      }

  }
}
