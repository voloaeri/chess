import javax.swing.JLabel;

public class BoardSpot
  extends JLabel
{
  ChessPosition position;
  
  BoardSpot(String paramString, ChessPosition paramChessPosition)
  {
    super(paramString);
    position = paramChessPosition;
  }
  
  public ChessPosition getPosition()
  {
    return position;
  }
}
