






























































































































































































































































































































































class Queen
  extends ChessPiece
{
  public Queen(ChessPlayer paramChessPlayer, ChessGame paramChessGame, ChessPosition paramChessPosition)
  {
    super(paramChessPlayer, paramChessGame, paramChessPosition);
    if (paramChessPlayer == paramChessGame.getPlayer1()) {
      mark = '♕';
    } else {
      mark = '♛';
    }
  }
  
  public void moveTo(ChessPosition paramChessPosition) throws IllegalMove
  {
    double d;
    if (Math.abs(paramChessPosition.getY() - getPosition().getY()) == 0) {
      d = 0.0D;
    } else {
      d = Math.abs(paramChessPosition.getX() - getPosition().getX()) / Math.abs(paramChessPosition.getY() - getPosition().getY());
    }
    


    if ((d == 1.0D) || (paramChessPosition.getX() - getPosition().getX() == 0) || (paramChessPosition.getY() - getPosition().getY() == 0))
    {


      super.moveTo(paramChessPosition);
    }
    else
    {
      throw new IllegalMove(this, getPosition(), paramChessPosition);
    }
  }
}
