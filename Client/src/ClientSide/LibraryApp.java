package ClientSide;

import ClientSide.Core.ClientFactory;
import ClientSide.Core.ModelFactory;
import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class LibraryApp extends Application
{
  private ClientFactory cf = new ClientFactory();
  private ModelFactory mf = new ModelFactory(cf);
  private ViewModelFactory vmf=new ViewModelFactory(mf);
  private ViewHandler viewHandler=new ViewHandler(vmf);
  public void start(Stage stage) throws Exception
  {
    viewHandler.start();
  }
  public void stop()
  {
    cf=null;
    System.exit(0);
  }
}

