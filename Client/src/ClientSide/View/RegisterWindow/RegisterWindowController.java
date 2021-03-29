package ClientSide.View.RegisterWindow;

import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;
import ClientSide.View.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterWindowController implements ViewController
{
  private RegisterWindowViewModel registerWindowViewModel;
  private ViewHandler viewHandler;
  @FXML private TextField cpr;
  @FXML private TextField name;
  @FXML private TextField address;
  @FXML private PasswordField password;
  @FXML private TextField card;
  @FXML private Label error;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
  this.viewHandler= vh;
  registerWindowViewModel = vmf.getRegisterWindowViewModel();
  cpr.textProperty().bindBidirectional(registerWindowViewModel.cprProperty());
  name.textProperty().bindBidirectional(registerWindowViewModel.nameProperty());
  address.textProperty().bindBidirectional(registerWindowViewModel.addressProperty());
  password.textProperty().bindBidirectional(registerWindowViewModel.passwordProperty());
  card.textProperty().bindBidirectional(registerWindowViewModel.cardProperty());
  error.textProperty().bind(registerWindowViewModel.getError());
  }

  @FXML public void reset()
  {
    viewHandler.openRegister();
  }

  @FXML private void Register()
  {
    if(registerWindowViewModel.checkInput())
    {
      if(registerWindowViewModel.register())
      {
        viewHandler.openLogin();
      }
    }

  }

  @FXML private void back()
  {
    viewHandler.openLogin();
  }

}
