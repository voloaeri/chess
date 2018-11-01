import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Controller implements MouseListener, ActionListener
{
  private ChessGame game;
  private ChessGameView view;
  private BoardSpot firstmove;
  private Color originalColor;
  private ArrayList<MoveListener> listeners;
  private BoardSpot undoSpot;
  
  public Controller(ChessGame paramChessGame, ChessGameView paramChessGameView)
  {
    view = paramChessGameView;
    game = paramChessGame;
    listeners = new ArrayList();
    paramChessGameView.setControllerandListeners(this);
  }
  





  public void mousePressed(MouseEvent paramMouseEvent) {}
  




  public void mouseReleased(MouseEvent paramMouseEvent) {}
  




  public void actionPerformed(ActionEvent paramActionEvent)
  {
    if (game.getCurrentPlayer() == game.getPlayer1()) {
      game.setCurrentPlayer(game.getPlayer2());
    } else {
      game.setCurrentPlayer(game.getPlayer1());
    }
    


    if (firstmove != null) {
      undoSpot.setBackground(originalColor);
    }
    

    if (game.getLogSize() != 0) {
      if (game.getCurrentPlayer() == game.getPlayer1()) {
        view.getP2().setForeground(Color.red);
        view.getP1().setForeground(Color.black);
        view.setNextPlayer("Next: Player One");
      } else {
        view.getP1().setForeground(Color.red);
        view.getP2().setForeground(Color.black);
        view.setNextPlayer("Next:Player Two");
      }
    }
    
    game.undo();
  }
  

  public void mouseClicked(MouseEvent paramMouseEvent)
  {
    BoardSpot localBoardSpot = (BoardSpot)paramMouseEvent.getComponent();
    undoSpot = ((BoardSpot)paramMouseEvent.getComponent());
    


    ChessPiece localChessPiece = game.getBoard().getPieceAt(localBoardSpot.getPosition());
    

    if (firstmove == null) {
      originalColor = localBoardSpot.getBackground();
      
      if (game.getBoard().getPieceAt(localBoardSpot.getPosition()) != null) {
        firstmove = localBoardSpot;
        

        localBoardSpot.setBackground(Color.orange);
      }
      

    }
    else
    {

      if (game.getBoard().getPieceAt(firstmove.getPosition()).getOwner() == game.getPlayer1()) {
        view.getP2().setForeground(Color.red);
        view.getP1().setForeground(Color.black);
        view.setNextPlayer("Next: Player One");
      } else {
        view.getP1().setForeground(Color.red);
        view.getP2().setForeground(Color.black);
        view.setNextPlayer("Next:Player Two");
      }
      

      firstmove.setBackground(originalColor);
      
      ChessMove localChessMove = new ChessMove(game.getBoard().getPieceAt(firstmove.getPosition()), firstmove.getPosition(), localBoardSpot.getPosition(), localChessPiece);
      


      for (int i = 0; i < listeners.size(); i++)
      {

        ((MoveListener)listeners.get(i)).moveAttempted(localChessMove);
      }
      

      firstmove = null;
    }
  }
  
  public void mouseEntered(MouseEvent paramMouseEvent)
  {
    BoardSpot localBoardSpot = (BoardSpot)paramMouseEvent.getComponent();
    localBoardSpot.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
  }
  
  public void mouseExited(MouseEvent paramMouseEvent)
  {
    BoardSpot localBoardSpot = (BoardSpot)paramMouseEvent.getComponent();
    localBoardSpot.setBorder(null);
  }
  
  public void addMoveListener(MoveListener paramMoveListener)
  {
    listeners.add(paramMoveListener);
  }
  
  public void removeMoveListener(MoveListener paramMoveListener)
  {
    listeners.remove(paramMoveListener);
  }
}
