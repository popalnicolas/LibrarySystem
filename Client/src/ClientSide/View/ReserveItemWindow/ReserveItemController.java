package ClientSide.View.ReserveItemWindow;

import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;
import ClientSide.View.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

public class ReserveItemController implements ViewController
{
 private ViewHandler viewHandler;
 private ReserveItemViewModel reserveItemViewModel;
 @FXML private Label authorname;
 @FXML private Label authorLabel;
 @FXML private Label developerLabel;
 @FXML private Label productionCompanyLabel;
 @FXML private Label developer;
 @FXML private Label productioncomp;
 @FXML private Label name;
 @FXML private Label type;
 @FXML private Label id;
 @FXML private Label year;
 @FXML private Alert alert;
 @FXML ButtonType yes = new ButtonType("Yes");
 @FXML ButtonType no = new ButtonType("No");
 @FXML private Label genre;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
   viewHandler = vh;
   reserveItemViewModel = vmf.getReserveItemViewModel();
   authorname.textProperty().bind(reserveItemViewModel.authornameProperty());
   authorLabel.textProperty().bind(reserveItemViewModel.authorLabelProperty());
   developerLabel.textProperty().bind(reserveItemViewModel.developerLabelProperty());
   productionCompanyLabel.textProperty().bind(reserveItemViewModel.productionCompanyLabelProperty());
   developer.textProperty().bind(reserveItemViewModel.developerProperty());
   productioncomp.textProperty().bind(reserveItemViewModel.productioncompProperty());
   name.textProperty().bind(reserveItemViewModel.nameProperty());
   id.textProperty().bind(reserveItemViewModel.idProperty());
   year.textProperty().bind(reserveItemViewModel.yearProperty());
   type.textProperty().bind(reserveItemViewModel.typeProperty());
   genre.textProperty().bind(reserveItemViewModel.genreProperty());
   alert = new Alert(Alert.AlertType.NONE,"heyy",yes,no);
   DialogPane dialogPane = alert.getDialogPane();
   dialogPane.getStylesheets().add(getClass().getResource("../../Css/Style.css").toExternalForm());
   dialogPane.getStyleClass().add("myDialog");
   alert.contentTextProperty().bind(reserveItemViewModel.dialogtextProperty());
   alert.setHeaderText("This item is already borrowed");
   reserveItemViewModel.getItem();
  }

  public void reset()
  {
   reserveItemViewModel.getItem();
  }

  @FXML private void back()
  {
   viewHandler.openBorrow();
  }

  @FXML private void borrow()
  {
   String result = reserveItemViewModel.borrow();
   if(result.equals("Borrow"))
   {
    viewHandler.openBorrow();
   }
   else if(result.equals("Queue"))
    {
     alert.showAndWait().ifPresent(response->
     {
      if(response == yes)
      {
       reserveItemViewModel.borrowconfirm();
       viewHandler.openBorrow();
      }
      else
       {
        viewHandler.openBorrow();
       }
     });
    }
   else if(result.equals("Count"))
    {
     Alert alert = new Alert(Alert.AlertType.ERROR);
     alert.setContentText("You can borrow maximum 3 items");
     alert.showAndWait();
    }

  }
}
