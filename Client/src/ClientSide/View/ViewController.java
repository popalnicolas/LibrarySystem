package ClientSide.View;

import ClientSide.Core.ViewHandler;
import ClientSide.Core.ViewModelFactory;

public interface ViewController
{
  void init(ViewHandler vh, ViewModelFactory vmf);
}
