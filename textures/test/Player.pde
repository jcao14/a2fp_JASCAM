public class Player 
{
  PImage player;
  PImage front;
  PImage back;
  PImage left;
  PImage right;

  int xcor;
  int ycor;

  int speed;

  public Player() {
    player = loadImage("playerU.png");
    front = loadImage("playerD.png");
    back = loadImage("playerU.png");
    left =loadImage("playerL.png");
    right =loadImage("playerR.png");
    player.resize(45, 45);
    front.resize(45, 45);
    back.resize(45, 45);
    left.resize(45, 45);
    right.resize(45, 45);
    speed = 5;
    xcor = 100;
    ycor = 100;
  }

  public PImage getSprite() {
    return player;
  }

  public int getXCor() {
    return xcor;
  }

  public int getYCor() {
    return ycor;
  }
  

  public void create()
  {
    image(player, xcor, ycor);
  }

}