package ClientSide.Core;

import ClientSide.View.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Scene loginScene;
  private Scene regiterScene;
  private Scene mainScene;
  private Scene editScene;
  private Scene borrowScene;
  private Scene itemReserveScene;
  private Scene myItemsScene;
  private ViewModelFactory vmf;
  private Stage stage;

  public ViewHandler(ViewModelFactory vmf){this.vmf = vmf;}

  public void start()
  {
    stage=new Stage();
    stage.getIcons().add(new Image(getClass().getResourceAsStream("../Css/business.png")));
    openLogin();
  }

  public void openLogin() {
    if (loginScene == null) {
      try {
        Parent root = loadFXML("../View/LoginWindow/LoginWindow.fxml");

        stage.setTitle("Login");
        loginScene = new Scene(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else
      {
        vmf.getLoginWindowViewModel().reset();
      }
    stage.setScene(loginScene);
    stage.show();
  }

  public void openRegister() {
    if (regiterScene == null) {
      try {
        Parent root = loadFXML("../View/RegisterWindow/RegisterWindow.fxml");

        stage.setTitle("Register");
        regiterScene = new Scene(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    stage.setScene(regiterScene);
    stage.show();
  }

  public void openMain() {
    if (mainScene == null) {
      try {
        Parent root = loadFXML("../View/MainWindow/MainWindow.fxml");

        stage.setTitle("Main");
        mainScene = new Scene(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }else
      {
        vmf.getMainWindowViewModel().setName();
      }
    stage.setScene(mainScene);
    stage.show();
  }

  public void openEdit() {
    if (editScene == null) {
      try {
        Parent root = loadFXML("../View/EditWindow/EditWindow.fxml");

        stage.setTitle("Edit");
        editScene = new Scene(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    stage.setScene(editScene);
    stage.show();
  }

  public void openBorrow() {
    if (borrowScene == null) {
      try {
        Parent root = loadFXML("../View/BorrowWindow/BorrowWindow.fxml");

        stage.setTitle("Borrow");
        borrowScene = new Scene(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    stage.setScene(borrowScene);
    stage.show();
  }

  public void openReserverItem() {
    if (itemReserveScene == null) {
      try {
        Parent root = loadFXML("../View/ReserveItemWindow/ReserveItemWindow.fxml");

        stage.setTitle("Borrow");
        itemReserveScene = new Scene(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else
    {
      vmf.getReserveItemViewModel().getItem();
    }
    stage.setScene(itemReserveScene);
    stage.show();
  }

  public void openMyItems() {
    if (myItemsScene == null) {
      try {
        Parent root = loadFXML("../View/MyItemsWindow/MyItemsWindow.fxml");

        stage.setTitle("My Items");
        myItemsScene = new Scene(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else
      {
        vmf.getMyItemsWindowViewModel().loadItems();
      }
    stage.setScene(myItemsScene);
    stage.show();
  }

  private Parent loadFXML(String path) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(path));
    Parent root = loader.load();

    ViewController ctrl = loader.getController();
    ctrl.init(this, vmf);
    return root;
  }
}
