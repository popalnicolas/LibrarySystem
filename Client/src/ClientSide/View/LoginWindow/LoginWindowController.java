package ClientSide.View.LoginWindow;

import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;
import ClientSide.View.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindowController implements ViewController
{
  private @FXML TextField cpr;
  private @FXML PasswordField password;
  private @FXML Label error;
  private LoginWindowViewModel loginWindowViewModel;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.viewHandler = vh;
    loginWindowViewModel = vmf.getLoginWindowViewModel();
    cpr.textProperty().bindBidirectional(loginWindowViewModel.cprProperty());
    password.textProperty()
        .bindBidirectional(loginWindowViewModel.passwordProperty());
    error.textProperty().bind(loginWindowViewModel.errorProperty());
  }

  @FXML private void openRegister()
  {
    viewHandler.openRegister();
  }

  @FXML private void login()
  {
    if(loginWindowViewModel.setCpr())
    {
      if(loginWindowViewModel.login())
      {
        viewHandler.openMain();
      }
    }
  }

}
