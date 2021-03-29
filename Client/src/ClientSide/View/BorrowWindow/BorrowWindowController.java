package ClientSide.View.BorrowWindow;

import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;
import ClientSide.View.ViewController;
import Shared.SharedObjects.Item;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BorrowWindowController implements ViewController
{
  private ViewHandler viewHandler;
  private BorrowWindowViewModel borrowWindowViewModel;
  @FXML private ListView<Item> items;
  @FXML private ChoiceBox<String> category;
  @FXML private TextField searchtext;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.viewHandler = vh;
    borrowWindowViewModel = vmf.getBorrowWindowViewModel();
    items.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    category.getItems().add("Books");
    category.getItems().add("Movies");
    category.getItems().add("VideoGames");
    category.getItems().add("All");
    category.setValue("All");
    searchtext.textProperty().bindBidirectional(borrowWindowViewModel.searchtextProperty());
    borrowWindowViewModel.loadItems();
    items.itemsProperty().bind(borrowWindowViewModel.itemsProperty());
  }

  @FXML private void back()
  {
   viewHandler.openMain();
  }

  @FXML private void borrow()
  {

    if(items.getSelectionModel().getSelectedItem()==null)
    {
      return;
    }
    else
      {
        borrowWindowViewModel.getSelectedItem(items.getSelectionModel().getSelectedItem().getId(),items.getSelectionModel().getSelectedItem().getType());
        viewHandler.openReserverItem();
      }

  }

  @FXML private void search()
  {
    String a = borrowWindowViewModel.search(category.getValue());
    if(a.equals("search"))
    {
      borrowWindowViewModel.loadItems();
    }
    else if(a.equals("error"))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Could not find item with that name");
        alert.showAndWait();
      }
  }

}
