package Shared.SharedObjects;

import java.io.Serializable;
import java.time.LocalDate;

public class Info implements Serializable
{
  private int position;
  private LocalDate dateDue;

  public Info(int position, LocalDate dateDue)
  {
    this.position = position;
    this.dateDue = dateDue;
  }

  public int getPosition()
  {
    return position;
  }

  public LocalDate getDateDue()
  {
    return dateDue;
  }
}
