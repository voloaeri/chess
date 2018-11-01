









































































































































































































































































class Rook
  extends ChessPiece
{
  public Rook(ChessPlayer paramChessPlayer, ChessGame paramChessGame, ChessPosition paramChessPosition)
  {
    super(paramChessPlayer, paramChessGame, paramChessPosition);
    if (paramChessPlayer == paramChessGame.getPlayer1()) {
      mark = '♖';
    } else {
      mark = '♜';
    }
  }
  
  public void moveTo(ChessPosition paramChessPosition) throws IllegalMove {
    int i = paramChessPosition.getX() - getPosition().getX();
    int j = paramChessPosition.getY() - getPosition().getY();
    
    if ((i == 0) || (j == 0)) {
      super.moveTo(paramChessPosition);
    } else {
      throw new IllegalMove(this, getPosition(), paramChessPosition);
    }
  }
}
