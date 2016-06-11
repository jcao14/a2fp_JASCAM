import java.util.*;

/* USAGE:
 *
 * Movement.getMoves().get("U").move(link);
 */

public abstract class Movement {

  //public enum Direction { Up, Down, Left, Right };

  //public final static HashMap<String, Movement> directions = new HashMap <String, Movement>();
  public static final Movement MOVE_UP = new Up();
  
  public static final Movement[] moveFxn = new Movement[] { new Up(), new Down(), new Left(), new Right()};
  public static final ArrayList<String> playerInput = new ArrayList( Arrays.asList(new String[] {"W", "S", "A", "D"}) );


  public static Movement getMove(String s) {
    int index = playerInput.indexOf(s);
    return moveFxn[index];
  }


  public abstract void move(Player link);
}

class Up extends Movement {
  public Up() {
  }
  public void move(Player link) {
    link.ycor -= link.speed;
    link.player = link.front;
  }
}

class Down extends Movement {
  public Down() {
  }
  public void move(Player link) {
    link.ycor += link.speed;
    link.player = link.back;
  }
}


class Left extends Movement {
  public Left() {
  }
  public void move(Player link) {
    link.xcor -= link.speed;
    link.player = link.left;
  }
}


class Right extends Movement {
  public Right() {
  }
  public void move(Player link) {
    link.xcor += link.speed;
    link.player = link.right;
    ;
  }
}