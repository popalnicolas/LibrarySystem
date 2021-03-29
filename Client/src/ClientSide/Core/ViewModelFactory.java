package ClientSide.Core;


import ClientSide.View.BorrowWindow.BorrowWindowViewModel;
import ClientSide.View.EditWindow.EditWindowViewModel;
import ClientSide.View.LoginWindow.LoginWindowViewModel;
import ClientSide.View.MainWindow.MainWindowViewModel;
import ClientSide.View.MyItemsWindow.MyItemsWindowViewModel;
import ClientSide.View.RegisterWindow.RegisterWindowViewModel;
import ClientSide.View.ReserveItemWindow.ReserveItemViewModel;

public class ViewModelFactory
{
  private final ModelFactory mf;
  private LoginWindowViewModel loginWindowViewModel;
  private RegisterWindowViewModel registerWindowViewModel;
  private MainWindowViewModel mainWindowViewModel;
  private EditWindowViewModel editWindowViewModel;
  private BorrowWindowViewModel borrowWindowViewModel;
  private ReserveItemViewModel reserveItemViewModel;
  private MyItemsWindowViewModel myItemsWindowViewModel;


  public ViewModelFactory(ModelFactory mf){this.mf=mf;}

  public EditWindowViewModel getEditWindowViewModel()
  {
    if(editWindowViewModel==null)
    {
      editWindowViewModel = new EditWindowViewModel(mf.getLoginModel());
    }
    return editWindowViewModel;
  }

  public LoginWindowViewModel getLoginWindowViewModel()
  {
    if(loginWindowViewModel==null)
    {
      loginWindowViewModel = new LoginWindowViewModel(mf.getLoginModel());
    }
    return loginWindowViewModel;
  }

  public RegisterWindowViewModel getRegisterWindowViewModel()
  {
    if(registerWindowViewModel==null)
    {
      registerWindowViewModel = new RegisterWindowViewModel(mf.getLoginModel());
    }
    return registerWindowViewModel;
  }

  public MainWindowViewModel getMainWindowViewModel()
  {
    if(mainWindowViewModel==null)
    {
      mainWindowViewModel = new MainWindowViewModel(mf.getLoginModel(),mf.getBorrowModel(),mf.getMyItemsModel());
    }
    return mainWindowViewModel;
  }

  public BorrowWindowViewModel getBorrowWindowViewModel()
  {
    if(borrowWindowViewModel==null)
    {
      borrowWindowViewModel = new BorrowWindowViewModel(mf.getLoginModel(),mf.getBorrowModel());
    }
    return borrowWindowViewModel;
  }

  public ReserveItemViewModel getReserveItemViewModel()
  {
    if(reserveItemViewModel==null)
    {
      reserveItemViewModel = new ReserveItemViewModel(mf.getBorrowModel());
    }
    return reserveItemViewModel;
  }

  public MyItemsWindowViewModel getMyItemsWindowViewModel()
  {
    if(myItemsWindowViewModel==null)
    {
      myItemsWindowViewModel = new MyItemsWindowViewModel(mf.getMyItemsModel());
    }
    return myItemsWindowViewModel;
  }
}
