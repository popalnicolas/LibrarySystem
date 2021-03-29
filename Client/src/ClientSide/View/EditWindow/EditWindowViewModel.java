package ClientSide.View.EditWindow;

import ClientSide.Model.LoginModel;
import Shared.SharedObjects.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditWindowViewModel
{
  private LoginModel loginModel;
  private StringProperty username;
  private StringProperty address;
  private StringProperty password;
  private StringProperty card;
  private StringProperty error;

  public EditWindowViewModel(LoginModel loginModel)
  {
    this.loginModel = loginModel;
    username = new SimpleStringProperty();
    address = new SimpleStringProperty();
    password = new SimpleStringProperty();
    card = new SimpleStringProperty();
    error = new SimpleStringProperty();
  }

  public StringProperty usernameProperty()
  {
    return username;
  }

  public StringProperty addressProperty()
  {
    return address;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public StringProperty cardProperty()
  {
    return card;
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public boolean checkFields()
  {
    if(username.getValue()==null||username.getValue().equals(""))
    {
      error.set("Enter username");
      return false;
    }
    else if(address.getValue()==null||address.getValue().equals(""))
    {
      error.set("Enter address");
      return false;
    }
    else if(password.getValue()==null||password.getValue().equals(""))
    {
      error.set("Enter password");
      return false;
    }
    else if(card.getValue()==null||card.getValue().equals(""))
    {
      error.set("Enter card");
      return false;
    }

    try
    {
      Long.parseLong(card.getValue());
      return true;
    }
    catch (NumberFormatException e)
    {
      error.set("Wrong format of card number");
      return false;
    }
  }

  public boolean change()
  {
    Customer customer = new Customer(-1,username.getValue(),password.getValue(),address.getValue(),Long.parseLong(card.getValue()));
    if(loginModel.change(customer))
    {
      return true;
    }
    else
      {
       error.set("Could not change information");
       return false;
      }
  }
}
