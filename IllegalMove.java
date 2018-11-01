







class IllegalMove
  extends ChessException
{
  public IllegalMove(ChessPiece paramChessPiece, ChessPosition paramChessPosition1, ChessPosition paramChessPosition2)
  {
    super("Illegal move: piece " + paramChessPiece.toString() + " can not move from " + paramChessPosition1.toString() + " to " + paramChessPosition2.toString());
  }
}
