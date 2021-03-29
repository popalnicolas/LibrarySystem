package ServerSide.Adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductionCompanyDAOHandler implements ProductionCompanyDAO
{
  private Connection connection;

  public ProductionCompanyDAOHandler()
  {
    this.connection = Database.getInstance().getConnection();
  }

  protected List<String> convertProductionCompanies(ResultSet resultset)
  {
    ArrayList<String> result = new ArrayList<>();
    try
    {
      while (resultset.next())
      {
        String name = resultset.getString("productioncompanyname");
        result.add(name);
      }
      return result;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override public ArrayList<String> getProductionCompanies(int id)
  {
    try
    {
      String querysql = "SELECT PRODUCTIONCOMPANYNAME FROM PRODUCTIONCOMPANY WHERE PRODUCTIONCOMPANY_ID IN(SELECT PRODUCTIONCOMPANY_ID FROM MOVIEPRODUCTIONCOMPANY WHERE ITEM_ID = ?)";
      PreparedStatement getProductioinCompanyName = connection.prepareStatement(querysql);
      getProductioinCompanyName.setInt(1, id);
      ResultSet resultset = getProductioinCompanyName.executeQuery();

      ArrayList<String> result = (ArrayList<String>) convertProductionCompanies(resultset);
      resultset.close();
      getProductioinCompanyName.close();
      return result;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
