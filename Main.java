import javax.swing.JFrame;








public class Main
{
  public Main() {}
  
  public static void main(String[] paramArrayOfString)
  {
    ChessPlayer localChessPlayer1 = new ChessPlayer("P1");
    ChessPlayer localChessPlayer2 = new ChessPlayer("P2");
    
    ChessGame localChessGame = new ChessGame(localChessPlayer1, localChessPlayer2);
    
    JFrame localJFrame = new JFrame();
    localJFrame.setTitle("Chess Game");
    localJFrame.setDefaultCloseOperation(3);
    ChessGameView localChessGameView = new ChessGameView(localChessGame);
    Controller localController = new Controller(localChessGame, localChessGameView);
    
    localController.addMoveListener(localChessGame);
    
    localJFrame.setContentPane(localChessGameView);
    localJFrame.pack();
    localJFrame.setVisible(true);
  }
}
