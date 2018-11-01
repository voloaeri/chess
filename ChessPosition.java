public class ChessPosition
{
  private int x;
  private int y;
  
  public ChessPosition(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 > 7)) {
      throw new IllegalArgumentException("x value of chess position out of range: " + paramInt1);
    }
    
    if ((paramInt2 < 0) || (paramInt2 > 7)) {
      throw new IllegalArgumentException("y value of chess position out of range: " + paramInt2);
    }
    
    x = paramInt1;
    y = paramInt2;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public String toString()
  {
    return "(" + x + ", " + y + ")";
  }
  
  public boolean equals(Object paramObject)
  {
    ChessPosition localChessPosition = (ChessPosition)paramObject;
    return (localChessPosition.getX() == x) && (localChessPosition.getY() == y);
  }
}
