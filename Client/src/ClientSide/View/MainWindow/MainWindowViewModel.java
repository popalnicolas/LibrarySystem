package ClientSide.View.MainWindow;

import ClientSide.Model.BorrowModel;
import ClientSide.Model.LoginModel;

import ClientSide.Model.MyItemsModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class MainWindowViewModel
{
  private LoginModel loginModel;
  private BorrowModel borrowModel;
  private MyItemsModel myItemsModel;
  private StringProperty cpr;
  private StringProperty name;

  public MainWindowViewModel(LoginModel loginModel,BorrowModel borrowModel,MyItemsModel myItemsModel)
  {
    this.borrowModel = borrowModel;
    this.loginModel = loginModel;
    this.myItemsModel = myItemsModel;
    cpr = new SimpleStringProperty();
    name = new SimpleStringProperty();
  }

  public void setName()
  {
    name.set("Welcome: "+loginModel.getName());
  }

  public void passCpr()
  {
    borrowModel.setCpr(loginModel.getCpr());
    myItemsModel.setCpr(loginModel.getCpr());
  }


  public StringProperty cprProperty()
  {
    return cpr;
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public void checkDate()
  {
    myItemsModel.checkDate();
  }
}
