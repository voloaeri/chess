
public class ChessBoard
{
  private ChessPiece[][] spaces;
  
  public ChessBoard()
  {
    spaces = new ChessPiece[8][8];
  }
  
  public void placePieceAt(ChessPiece paramChessPiece, ChessPosition paramChessPosition)
  {
    spaces[paramChessPosition.getX()][paramChessPosition.getY()] = paramChessPiece;
    paramChessPiece.setPosition(paramChessPosition);
  }
  
  public ChessPiece getPieceAt(ChessPosition paramChessPosition) {
    return spaces[paramChessPosition.getX()][paramChessPosition.getY()];
  }
  
  public void removePieceFrom(ChessPosition paramChessPosition)
  {
    ChessPiece localChessPiece = getPieceAt(paramChessPosition);
    if (localChessPiece != null) {
      localChessPiece.setPosition(null);
      spaces[paramChessPosition.getX()][paramChessPosition.getY()] = null;
    }
  }
}
