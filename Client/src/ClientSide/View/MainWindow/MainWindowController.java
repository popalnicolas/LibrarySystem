package ClientSide.View.MainWindow;

import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;
import ClientSide.View.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController implements ViewController
{
  private ViewHandler viewHandler;
  private MainWindowViewModel mainWindowViewModel;
  @FXML private Label welcome;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    viewHandler = vh;
    mainWindowViewModel = vmf.getMainWindowViewModel();
    welcome.textProperty().bind(mainWindowViewModel.nameProperty());
    mainWindowViewModel.setName();
  }
  public void reset()
  {
    mainWindowViewModel.setName();
  }

  @FXML private void openEdit()
  {
    viewHandler.openEdit();
  }

  @FXML private void logout()
  {
    viewHandler.openLogin();
  }

  @FXML private void borrow()
  {
    mainWindowViewModel.passCpr();
    mainWindowViewModel.checkDate();
    viewHandler.openBorrow();
  }

  @FXML private void myItems()
  {
    mainWindowViewModel.passCpr();
    mainWindowViewModel.checkDate();
    viewHandler.openMyItems();
  }
}
