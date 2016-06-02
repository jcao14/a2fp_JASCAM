public class Tile{
  int type;
  int xcor;
  int ycor;
  boolean solid;
  
  public Tile(int num){
    type = num;
  }
  public int type(){
    return type;
  }
  
  public void makeSolid(){
    solid = true;
  }
  
  public void setXY(int x, int y){
    xcor = x;
    ycor = y;
  }
  
  public String getCoor(){
    String ret = "";
    ret += xcor;
    ret += " ";
    ret += ycor;
    return ret;
  }
}