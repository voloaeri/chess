
public class ChessMove
{
  private ChessPiece piece;
  private ChessPosition from;
  private ChessPosition to;
  private ChessPiece captured;
  
  public ChessMove(ChessPiece paramChessPiece1, ChessPosition paramChessPosition1, ChessPosition paramChessPosition2, ChessPiece paramChessPiece2)
  {
    piece = paramChessPiece1;
    from = paramChessPosition1;
    to = paramChessPosition2;
    captured = paramChessPiece2;
  }
  
  public ChessPiece getPiece() {
    return piece;
  }
  
  public ChessPosition getFrom() {
    return from;
  }
  
  public ChessPosition getTo() {
    return to;
  }
  
  public ChessPiece getCaptured() {
    return captured;
  }
  
  public boolean pieceWasCaptured() {
    return captured != null;
  }
  
  public String toString()
  {
    String str = piece.getOwner().getName() + "'s " + piece.toString() + " moved from " + from.toString() + " to " + to.toString();
    
    if (pieceWasCaptured()) {
      str = str + " capturing " + captured.getOwner().getName() + "'s " + captured.toString();
    }
    return str;
  }
}
