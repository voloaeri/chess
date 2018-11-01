import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class ChessGameView
  extends JPanel
  implements Observer
{
  private ChessGame game;
  private JLabel p1;
  private JLabel p2;
  private BoardSpot[][] boardspot = new BoardSpot[8][8];
  private JButton undo;
  private Controller controller;
  JTextField nextplayer;
  
  public ChessGameView(ChessGame paramChessGame) {
    game = paramChessGame;
    paramChessGame.addObserver(this);
    
    JPanel localJPanel1 = new JPanel();
    localJPanel1.setLayout(new GridBagLayout());
    
    GridBagConstraints localGridBagConstraints = new GridBagConstraints();
    

    p2 = new JLabel("P1");
    fill = 1;
    gridx = 4;
    gridy = 0;
    gridwidth = 1;
    gridheight = 1;
    ipadx = 10;
    ipady = 10;
    weightx = 0.5D;
    weighty = 0.5D;
    localJPanel1.add(p2, localGridBagConstraints);
    

    for (int i = 7; i >= 0; i--) {
      for (int j = 0; j < 8; j++) {
        boardspot[i][j] = new BoardSpot(getName(), new ChessPosition(j, 7 - i));
        

        fill = 1;
        gridx = j;
        gridy = (i + 1);
        ipady = 30;
        ipadx = 30;
        weightx = 0.5D;
        weighty = 0.5D;
        
        if (paramChessGame.getBoard().getPieceAt(new ChessPosition(j, 7 - i)) != null) {
          String str = Character.toString(paramChessGame.getBoard().getPieceAt(new ChessPosition(j, 7 - i)).getMark());
          


          boardspot[i][j].setText(str);
          boardspot[i][j].setFont(new Font(null, 1, 40));
        }
        else {
          boardspot[i][j].setText(" ");
        }
        boardspot[i][j].setOpaque(true);
        boardspot[i][j].setPreferredSize(new Dimension(20, 20));
        

        if ((i + j) % 2 != 0) {
          boardspot[i][j].setBackground(Color.gray);
        } else {
          boardspot[i][j].setBackground(Color.white);
        }
        
        localJPanel1.add(boardspot[i][j], localGridBagConstraints);
        boardspot[i][j].setHorizontalAlignment(0);
      }
    }
    


    setLayout(new BorderLayout());
    add(localJPanel1, "Center");
    

    p1 = new JLabel("P2");
    
    fill = 1;
    gridx = 4;
    gridy = 15;
    gridwidth = 1;
    gridheight = 1;
    ipadx = 10;
    ipady = 10;
    weightx = 0.5D;
    weighty = 0.5D;
    localJPanel1.add(p1, localGridBagConstraints);
    


    JPanel localJPanel2 = new JPanel(new GridLayout(1, 2));
    undo = new JButton("Undo Move");
    localJPanel2.add(undo);
    undo.setActionCommand("Undo");
    

    nextplayer = new JTextField("Next Player");
    nextplayer.setEditable(false);
    nextplayer.setHorizontalAlignment(0);
    localJPanel2.add(nextplayer);
    add(localJPanel2, "South");
  }
  


  public void update(Observable paramObservable, Object paramObject)
  {
    printBoard();
  }
  
  private void printBoard()
  {
    for (int i = 7; i >= 0; i--)
    {
      for (int j = 0; j < 8; j++)
      {
        if (game.getBoard().getPieceAt(new ChessPosition(j, 7 - i)) != null) {
          String str = Character.toString(game.getBoard().getPieceAt(new ChessPosition(j, 7 - i)).getMark());
          

          boardspot[i][j].setText(str);
          boardspot[i][j].setFont(new Font(null, 1, 40));
        }
        else {
          boardspot[i][j].setText(" ");
        }
      }
    }
    

    repaintUI();
  }
  
  public void repaintUI() {
    repaint();
  }
  
  public void setControllerandListeners(Controller paramController) {
    controller = paramController;
    setMouseListeners();
    undo.addActionListener(paramController);
  }
  

  public void setMouseListeners()
  {
    for (int i = 7; i >= 0; i--) {
      for (int j = 0; j < 8; j++) {
        boardspot[i][j].addMouseListener(controller);
      }
    }
  }
  


  public JLabel getP1()
  {
    return p1;
  }
  
  public JLabel getP2() {
    return p2;
  }
  
  public void setNextPlayer(String paramString) {
    nextplayer.setText(paramString);
  }
}
