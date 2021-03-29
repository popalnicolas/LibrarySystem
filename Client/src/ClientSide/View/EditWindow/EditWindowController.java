package ClientSide.View.EditWindow;

import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;
import ClientSide.View.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditWindowController implements ViewController
{
  private ViewHandler viewHandler;
  private EditWindowViewModel editWindowViewModel;
  private @FXML TextField name;
  private @FXML TextField address;
  private @FXML PasswordField password;
  private @FXML TextField card;
  private @FXML Label error;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.viewHandler = vh;
    editWindowViewModel = vmf.getEditWindowViewModel();
    name.textProperty().bindBidirectional(editWindowViewModel.usernameProperty());
    address.textProperty().bindBidirectional(editWindowViewModel.addressProperty());
    password.textProperty().bindBidirectional(editWindowViewModel.passwordProperty());
    card.textProperty().bindBidirectional(editWindowViewModel.cardProperty());
    error.textProperty().bind(editWindowViewModel.errorProperty());
  }

  @FXML private void goBack()
  {
    viewHandler.openMain();
  }

  @FXML private void change()
  {
    if(editWindowViewModel.checkFields())
    {
      if(editWindowViewModel.change())
      {
        viewHandler.openMain();
      }
    }

  }
}
