package ClientSide.View.LoginWindow;

import ClientSide.Model.LoginModel;
import Shared.SharedObjects.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginWindowViewModel
{
  private LoginModel loginModel;
  private StringProperty cpr;
  private StringProperty password;
  private StringProperty error;

  public LoginWindowViewModel(LoginModel loginModel)
  {
    this.loginModel = loginModel;
    cpr = new SimpleStringProperty();
    password = new SimpleStringProperty();
    error = new SimpleStringProperty();
  }

  public StringProperty cprProperty()
  {
    return cpr;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public void reset()
  {
    password.set("");
    cpr.set("");
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public boolean setCpr()
  {
    if(cpr.getValue()==null||cpr.getValue().equals(""))
    {
      error.set("You must enter cpr");
      return false;
    }
    else if(password.getValue()==null||password.getValue().equals(""))
    {
    error.set("You must enter password");
    return false;
    }
    try
    {
      Long.parseLong(cpr.getValue());
      loginModel.setCpr(Long.parseLong(cpr.getValue()));
      return true;
    }
    catch (NumberFormatException e)
    {
      error.set("Wrong format of cpr");
      return false;
    }
  }

  public boolean login()
  {
    Customer customer = new Customer(Long.parseLong(cpr.getValue()),"a",password.getValue(),"a",-1);
    if(loginModel.login(customer))
    {
      error.set("");
      return true;
    }
    else
      {
        error.set("Invalid credentials");
        return false;
      }
  }
}
