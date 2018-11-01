



























































































































































































































































































































































































































class Pawn
  extends ChessPiece
{
  public Pawn(ChessPlayer paramChessPlayer, ChessGame paramChessGame, ChessPosition paramChessPosition)
  {
    super(paramChessPlayer, paramChessGame, paramChessPosition);
    if (paramChessPlayer == paramChessGame.getPlayer1()) {
      mark = '♙';
    } else {
      mark = '♟';
    }
  }
  
  public void moveTo(ChessPosition paramChessPosition)
    throws IllegalMove
  {
    ChessPosition localChessPosition;
    if ((getPosition().getY() == 1) && (getGame().getBoard().getPieceAt(getPosition()).getOwner() == getGame().getPlayer1()))
    {


      if ((paramChessPosition.getY() <= 3) && (paramChessPosition.getX() == getPosition().getX()))
      {

        localChessPosition = new ChessPosition(getPosition().getX(), getPosition().getY() + 1);
        
        if (getGame().getBoard().getPieceAt(localChessPosition) == null) {
          super.moveTo(paramChessPosition);
        } else {
          throw new IllegalMove(this, getPosition(), paramChessPosition);
        }
      }
      else if (getGame().getBoard().getPieceAt(paramChessPosition) != null) {
        super.moveTo(paramChessPosition);
      }
      else {
        throw new IllegalMove(this, getPosition(), paramChessPosition);
      }
      
    }
    else if ((getPosition().getY() == 6) && (getGame().getBoard().getPieceAt(getPosition()).getOwner() == getGame().getPlayer2()))
    {


      if ((paramChessPosition.getY() >= 4) && (paramChessPosition.getX() == getPosition().getX()))
      {

        localChessPosition = new ChessPosition(getPosition().getX(), getPosition().getY() - 1);
        
        if (getGame().getBoard().getPieceAt(localChessPosition) == null) {
          super.moveTo(paramChessPosition);
        } else {
          throw new IllegalMove(this, getPosition(), paramChessPosition);
        }
      } else if (getGame().getBoard().getPieceAt(paramChessPosition) != null)
      {
        super.moveTo(paramChessPosition);
      }
      else
      {
        throw new IllegalMove(this, getPosition(), paramChessPosition);
      }
      

    }
    else
    {
      if (getOwner() == getGame().getPlayer1())
      {

        if (paramChessPosition.getY() - getPosition().getY() == 1)
        {
          localChessPosition = null;
          
          if (getPosition().getY() != 7) {
            localChessPosition = new ChessPosition(getPosition().getX(), getPosition().getY() + 1);
          }
          else
          {
            throw new IllegalMove(this, getPosition(), paramChessPosition);
          }
          
          if (getGame().getBoard().getPieceAt(localChessPosition) == null)
          {
            if ((paramChessPosition.getX() == localChessPosition.getX()) && (paramChessPosition.getY() == localChessPosition.getY()))
            {
              super.moveTo(paramChessPosition);
            } else if (getGame().getBoard().getPieceAt(paramChessPosition) != null)
            {
              super.moveTo(paramChessPosition);
            }
            else {
              throw new IllegalMove(this, getPosition(), paramChessPosition);
            }
          }
          else if (getGame().getBoard().getPieceAt(localChessPosition) != null) {
            if ((paramChessPosition.getX() != localChessPosition.getX()) && (paramChessPosition.getY() == localChessPosition.getY()))
            {

              super.moveTo(paramChessPosition);
            }
            else {
              throw new IllegalMove(this, getPosition(), paramChessPosition);
            }
            
          }
          else {
            throw new IllegalMove(this, getPosition(), paramChessPosition);
          }
        }
        else
        {
          throw new IllegalMove(this, getPosition(), paramChessPosition);
        }
      }
      
      if (getOwner() == getGame().getPlayer2())
      {

        if (paramChessPosition.getY() - getPosition().getY() == -1)
        {
          localChessPosition = null;
          


          if (getPosition().getY() != 0) {
            localChessPosition = new ChessPosition(getPosition().getX(), getPosition().getY() - 1);
          }
          else
          {
            throw new IllegalMove(this, getPosition(), paramChessPosition);
          }
          
          if (getGame().getBoard().getPieceAt(localChessPosition) == null)
          {
            if ((paramChessPosition.getX() == localChessPosition.getX()) && (paramChessPosition.getY() == localChessPosition.getY()))
            {
              super.moveTo(paramChessPosition);
            } else if (getGame().getBoard().getPieceAt(paramChessPosition) != null)
            {
              super.moveTo(paramChessPosition);
            } else {
              throw new IllegalMove(this, getPosition(), paramChessPosition);

            }
            

          }
          else if (getGame().getBoard().getPieceAt(localChessPosition) != null)
          {
            if ((paramChessPosition.getX() != localChessPosition.getX()) && (paramChessPosition.getY() == localChessPosition.getY()))
            {

              super.moveTo(paramChessPosition);

            }
            else
            {
              throw new IllegalMove(this, getPosition(), paramChessPosition);
            }
            
          }
          else {
            throw new IllegalMove(this, getPosition(), paramChessPosition);
          }
        }
        else {
          throw new IllegalMove(this, getPosition(), paramChessPosition);
        }
      }
    }
  }
}
