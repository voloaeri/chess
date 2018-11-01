import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ChessGame extends Observable implements Observer, MoveListener
{
  private ChessBoard board;
  private ChessPlayer player1;
  private ChessPlayer player2;
  private ArrayList<ChessMove> log = new ArrayList();
  private ChessPlayer currentPlayer;
  
  public ChessGame(ChessPlayer paramChessPlayer1, ChessPlayer paramChessPlayer2) {
    player1 = paramChessPlayer1;
    player2 = paramChessPlayer2;
    board = new ChessBoard();
    
    new Rook(paramChessPlayer1, this, new ChessPosition(0, 0));
    new Knight(paramChessPlayer1, this, new ChessPosition(1, 0));
    new Bishop(paramChessPlayer1, this, new ChessPosition(2, 0));
    new Queen(paramChessPlayer1, this, new ChessPosition(3, 0));
    new King(paramChessPlayer1, this, new ChessPosition(4, 0));
    new Bishop(paramChessPlayer1, this, new ChessPosition(5, 0));
    new Knight(paramChessPlayer1, this, new ChessPosition(6, 0));
    new Rook(paramChessPlayer1, this, new ChessPosition(7, 0));
    
    for (int i = 0; i < 8; i++) {
      new Pawn(paramChessPlayer1, this, new ChessPosition(i, 1));
    }
    
    new Rook(paramChessPlayer2, this, new ChessPosition(0, 7));
    new Knight(paramChessPlayer2, this, new ChessPosition(1, 7));
    new Bishop(paramChessPlayer2, this, new ChessPosition(2, 7));
    new Queen(paramChessPlayer2, this, new ChessPosition(3, 7));
    
    new King(paramChessPlayer2, this, new ChessPosition(4, 7));
    new Bishop(paramChessPlayer2, this, new ChessPosition(5, 7));
    new Knight(paramChessPlayer2, this, new ChessPosition(6, 7));
    new Rook(paramChessPlayer2, this, new ChessPosition(7, 7));
    
    for (i = 0; i < 8; i++) {
      new Pawn(paramChessPlayer2, this, new ChessPosition(i, 6));
    }
  }
  
  public ChessPlayer getPlayer1() {
    return player1;
  }
  
  public ChessPlayer getPlayer2() {
    return player2;
  }
  
  public ChessBoard getBoard() {
    return board;
  }
  

  public void update(Observable paramObservable, Object paramObject)
  {
    ChessMove localChessMove = (ChessMove)paramObject;
    log.add(localChessMove);
    setChanged();
    notifyObservers();
  }
  


  public int getLogSize()
  {
    int i = log.size();
    return i;
  }
  
  public ChessMove[] getMoves(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    ChessMove[] arrayOfChessMove = null;
    int i = getLogSize();
    

    if (i == 0) {
      arrayOfChessMove = new ChessMove[i];
      localArrayList = log;


    }
    else if (paramInt == 0)
    {
      arrayOfChessMove = new ChessMove[i];
      localArrayList = log; } else { int j;
      ChessMove localChessMove;
      if (paramInt < 0) {
        if (i != 0)
        {

          arrayOfChessMove = new ChessMove[Math.abs(paramInt)];
          for (j = 0; j < Math.abs(paramInt); j++) {
            localChessMove = (ChessMove)log.get(i - Math.abs(paramInt) + j);
            localArrayList.add(j, localChessMove);
          }
        }
      }
      else
      {
        arrayOfChessMove = new ChessMove[paramInt];
        for (j = 0; j < Math.abs(paramInt); j++) {
          localChessMove = (ChessMove)log.get(j);
          localArrayList.add(localChessMove);
        }
      }
    }
    
    return arrayOfChessMove = (ChessMove[])localArrayList.toArray(arrayOfChessMove);
  }
  


  public void undo()
  {
    if (getLogSize() == 0) {
      System.out.println("No moves to undo!");
    }
    else
    {
      ChessMove localChessMove = (ChessMove)log.get(getLogSize() - 1);
      
      getBoard().removePieceFrom(localChessMove.getTo());
      getBoard().placePieceAt(localChessMove.getPiece(), localChessMove.getFrom());
      
      if (localChessMove.pieceWasCaptured()) {
        getBoard().placePieceAt(localChessMove.getCaptured(), localChessMove.getTo());
      }
      
      log.remove(getLogSize() - 1);
      log.trimToSize();
      
      setChanged();
      notifyObservers();
    }
  }
  
  public void moveAttempted(ChessMove paramChessMove)
  {
    try {
      ChessPiece localChessPiece = getBoard().getPieceAt(paramChessMove.getFrom());
      
      if (localChessPiece != null)
      {

        if (isCorrectPlayersTurn(paramChessMove, localChessPiece)) {
          localChessPiece.moveTo(paramChessMove.getTo());
        }
        else {
          System.out.println("Other Player's Turn");
        }
      }
    } catch (IllegalMove localIllegalMove) {
      System.out.println(localIllegalMove.toString());
    }
  }
  

  public boolean isCorrectPlayersTurn(ChessMove paramChessMove, ChessPiece paramChessPiece)
  {
    if (currentPlayer == null) {
      currentPlayer = paramChessPiece.getOwner();
      return true; }
    if (currentPlayer == paramChessPiece.getOwner()) {
      return false;
    }
    currentPlayer = paramChessPiece.getOwner();
    return true;
  }
  

  public ChessPlayer getCurrentPlayer()
  {
    return currentPlayer;
  }
  
  public void setCurrentPlayer(ChessPlayer paramChessPlayer) {
    currentPlayer = paramChessPlayer;
  }
}
