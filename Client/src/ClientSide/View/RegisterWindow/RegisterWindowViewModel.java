package ClientSide.View.RegisterWindow;

import ClientSide.Model.LoginModel;
import Shared.SharedObjects.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigInteger;

public class RegisterWindowViewModel
{
  private LoginModel loginModel;
  private StringProperty cpr;
  private StringProperty name;
  private StringProperty address;
  private StringProperty password;
  private StringProperty card;
  private StringProperty error;

  public RegisterWindowViewModel(LoginModel loginModel)
  {
    this.loginModel = loginModel;
    cpr = new SimpleStringProperty();
    name = new SimpleStringProperty();
    address = new SimpleStringProperty();
    password = new SimpleStringProperty();
    card = new SimpleStringProperty();
    error = new SimpleStringProperty();
  }

  public StringProperty cprProperty()
  {
    return cpr;
  }

  public StringProperty nameProperty()
  {
    return name;
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

  public StringProperty getError(){return error;}

  public boolean checkInput()
  {
    if(name.getValue()==null||name.getValue().equals(""))
    {
      error.set("Enter name");
      return false;
    }
    else if(cpr.getValue()==null||cpr.getValue().equals(""))
    {
      error.set("Enter cpr");
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
      error.set("Enter credit card number");
      return false;
    }
    else if(cpr.getValue().length()!= 10)
    {
      error.set("Cpr must have 10 digits");
      return false;
    }

    try
    {
      Long.parseLong(cpr.getValue());
    }
    catch (NumberFormatException e)
    {
      error.set("Wrong cpr format");
      return false;
    }

    try
    {
      Long.parseLong(card.getValue());
    }
    catch (NumberFormatException e)
    {
      error.set("Wront card number");
      return false;
    }
    return true;
  }

  public boolean register()
  {
      Customer customer = new Customer(Long.parseLong(cpr.getValue()),name.getValue(),password.getValue(),address.getValue(),Long.parseLong(card.getValue()));
    if(loginModel.register(customer))
    {
      return true;
    }
    else
      {
        error.set("User already exists");
        return false;
      }
  }
}
