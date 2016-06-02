public class Player 
{
  PImage player;

  int xcor;
  int ycor;

  int speed;

  public Player() {
    player = loadImage("playerU.png");
    player.resize(45, 45);
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