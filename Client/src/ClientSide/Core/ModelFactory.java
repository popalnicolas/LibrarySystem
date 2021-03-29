package ClientSide.Core;

import ClientSide.Model.*;

public class ModelFactory
{
  private final ClientFactory cf;
  private LoginModel loginModel;
  private BorrowModel borrowModel;
  private MyItemsModel myItemsModel;

  public ModelFactory(ClientFactory cf){this.cf=cf;}

  public LoginModel getLoginModel()
  {
    if(loginModel ==null)
    {
      loginModel =new LoginModelManager(cf.getClient());
    }
    return loginModel;
  }

  public BorrowModel getBorrowModel()
  {
    if(borrowModel == null)
    {
      borrowModel = new BorrowModelManager(cf.getClient());
    }
    return borrowModel;
  }

  public MyItemsModel getMyItemsModel()
  {
    if(myItemsModel == null)
    {
      myItemsModel = new MyItemsModelManager(cf.getClient());
    }
    return myItemsModel;
  }
}
