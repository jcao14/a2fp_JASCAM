public class Tile {
  int type;
  int xcor;
  int ycor;
  boolean solid;

  public Tile(int num) {
    type = num;
  }
  public int type() {
    return type;
  }

  public void makeSolid() {
    solid = true;
  }

  public void setXY(int x, int y) {
    xcor = x;
    ycor = y;
  }

  public boolean onWall (Player link) {
    int playerX = link.getXCor();
    int playerY = link.getYCor();
    if ((this.solid) && 
      (playerX >= (this.xcor - 10
      )) && 
      (playerX <= (this.xcor + 10))&&
      (playerY >= (this.ycor - 10)) && 
      (playerY <= (this.ycor + 10))
      )
    {
      return true;
    } else {
      return false;
    }
  }

  public String getCoor() {
    String ret = "";
    ret += xcor;
    ret += " ";
    ret += ycor;
    return ret;
  }
}