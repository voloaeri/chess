import java.util.Observable;




public abstract class ChessPiece
  extends Observable
{
  private ChessPlayer owner;
  private ChessGame game;
  private ChessPosition position;
  protected char mark;
  
  protected ChessPiece(ChessPlayer paramChessPlayer, ChessGame paramChessGame, ChessPosition paramChessPosition)
  {
    owner = paramChessPlayer;
    game = paramChessGame;
    position = null;
    addObserver(paramChessGame);
    paramChessGame.getBoard().placePieceAt(this, paramChessPosition);
  }
  
  public ChessPlayer getOwner() {
    return owner;
  }
  
  public ChessGame getGame() {
    return game;
  }
  
  public ChessPosition getPosition() {
    return position;
  }
  
  public void setPosition(ChessPosition paramChessPosition) {
    position = paramChessPosition;
  }
  
  public void moveTo(ChessPosition paramChessPosition)
    throws IllegalMove
  {
    ChessPiece localChessPiece = null;
    if (getChessPieceAt(paramChessPosition) != null) {
      localChessPiece = getChessPieceAt(paramChessPosition);
    }
    
    if ((isPositionGood(paramChessPosition)) && (isLineofSightClear(getPosition(), paramChessPosition)))
    {


      ChessMove localChessMove = new ChessMove(this, getPosition(), paramChessPosition, localChessPiece);
      

      getGame().getBoard().removePieceFrom(getPosition());
      getGame().getBoard().placePieceAt(this, paramChessPosition);
      
      setChanged();
      notifyObservers(localChessMove);
    }
    else {
      throw new IllegalMove(this, position, paramChessPosition);
    }
  }
  
  public char getMark()
  {
    return mark;
  }
  


  private boolean isPositionGood(ChessPosition paramChessPosition)
  {
    if (getChessPieceAt(paramChessPosition) == null)
    {
      return true;
    }
    
    if (getChessPieceAt(paramChessPosition).getOwner() != getOwner())
    {
      return true;
    }
    

    return false;
  }
  


  public boolean isLineofSightClear(ChessPosition paramChessPosition1, ChessPosition paramChessPosition2)
    throws IllegalMove
  {
    double d1 = paramChessPosition1.getX() - paramChessPosition2.getX();
    double d2 = paramChessPosition1.getY() - paramChessPosition2.getY();
    double d3 = d1 / d2;
    





    if ((paramChessPosition1.getY() == paramChessPosition2.getY()) && 
      (isHorizontallyClear(paramChessPosition1, paramChessPosition2))) {
      return true;
    }
    
    if ((paramChessPosition1.getX() == paramChessPosition2.getX()) && 
      (isVerticallyClear(paramChessPosition1, paramChessPosition2))) {
      return true;
    }
    
    if (Math.abs(d3) == 1.0D)
    {
      if (isDiagonallyClear(paramChessPosition1, paramChessPosition2))
      {
        return true;
      }
    }
    if ((Math.abs(d3) == 0.5D) || (Math.abs(d3) == 2.0D)) {
      return true;
    }
    
    return false;
  }
  
  public boolean isVerticallyClear(ChessPosition paramChessPosition1, ChessPosition paramChessPosition2)
    throws IllegalMove
  {
    double d = paramChessPosition2.getY() - paramChessPosition1.getY();
    
    int i;
    ChessPosition localChessPosition;
    if (d < 0.0D) {
      for (i = 1; i < Math.abs(d); i++) {
        localChessPosition = new ChessPosition(paramChessPosition1.getX(), paramChessPosition1.getY() - i);
        
        if (getChessPieceAt(localChessPosition) != null) {
          return false;
        }
      }
    }
    if (d > 0.0D) {
      for (i = 1; i < Math.abs(d); i++) {
        localChessPosition = new ChessPosition(paramChessPosition1.getX(), paramChessPosition1.getY() + i);
        

        if (getChessPieceAt(localChessPosition) != null)
        {
          return false;
        }
      }
    }
    
    return true;
  }
  
  public boolean isHorizontallyClear(ChessPosition paramChessPosition1, ChessPosition paramChessPosition2)
    throws IllegalMove
  {
    double d = paramChessPosition2.getX() - paramChessPosition1.getX();
    
    int i;
    ChessPosition localChessPosition;
    if (d < 0.0D)
    {
      for (i = 1; i < Math.abs(d); i++) {
        localChessPosition = new ChessPosition(paramChessPosition1.getX() - i, paramChessPosition1.getY());
        
        if (getChessPieceAt(localChessPosition) != null) {
          return false;
        }
      }
    }
    
    if (d > 0.0D)
    {
      for (i = 1; i < Math.abs(d); i++) {
        localChessPosition = new ChessPosition(paramChessPosition1.getX() + i, paramChessPosition1.getY());
        

        if (getChessPieceAt(localChessPosition) != null)
        {
          return false;
        }
      }
    }
    
    return true;
  }
  
  public boolean isDiagonallyClear(ChessPosition paramChessPosition1, ChessPosition paramChessPosition2) {
    double d1 = paramChessPosition2.getX() - paramChessPosition1.getX();
    double d2 = paramChessPosition2.getY() - paramChessPosition1.getY();
    

    double d3 = Math.round(Math.sqrt(d1 * d1 + d2 * d2)) - 2L;
    
    int i;
    
    ChessPosition localChessPosition;
    if (d1 < 0.0D)
    {
      if (d2 < 0.0D)
      {
        for (i = 1; i < d3; i++) {
          localChessPosition = new ChessPosition(paramChessPosition2.getX() + i, paramChessPosition2.getY() + i);
          
          if (getChessPieceAt(localChessPosition) != null)
          {
            return false;
          }
        }
      }
      if (d2 > 0.0D)
      {
        for (i = 1; i < d3; i++) {
          localChessPosition = new ChessPosition(paramChessPosition2.getX() + i, paramChessPosition2.getY() - i);
          
          if (getChessPieceAt(localChessPosition) != null)
          {
            return false;
          }
        }
      }
    }
    


    if (d1 > 0.0D)
    {
      if (d2 > 0.0D)
      {
        for (i = 1; i < d3; i++) {
          localChessPosition = new ChessPosition(paramChessPosition2.getX() - i, paramChessPosition2.getY() - i);
          

          if (getChessPieceAt(localChessPosition) != null)
          {
            return false;
          }
        }
      }
      

      if (d2 < 0.0D)
      {
        for (i = 1; i < d3; i++) {
          localChessPosition = new ChessPosition(paramChessPosition2.getX() - i, paramChessPosition2.getY() + i);
          

          if (getChessPieceAt(localChessPosition) != null)
          {
            return false;
          }
        }
      }
    }
    

    return true;
  }
  
  public ChessPiece getChessPieceAt(ChessPosition paramChessPosition) {
    ChessPiece localChessPiece = getGame().getBoard().getPieceAt(paramChessPosition);
    return localChessPiece;
  }
}
