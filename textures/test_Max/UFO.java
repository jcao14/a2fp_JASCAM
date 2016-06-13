import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
public class UFO extends Monster
{
  public UFO (LinkedList<Tile>p) {
    super (p);
    maxHp = 200;
    maxMp = 100;
    hp = 200;
    mp = 100;
    baseSpeed = 12;
    baseAttack = 5;
    baseDefense = 40;
    speed = baseSpeed/2;
    attack = baseAttack;
    defense = baseDefense;
    sizeX = 42;
    sizeY = 42;
  }
  public void loadSpawnAnimation()
  {
    String url = "";
    ArrayList<String> a = new ArrayList<String>();
    //add animation frames to list
    a.add("ufo (3).png");

    disableMovement();
    setAnimation(new Animation(a, AnimationType.SPAWN));
  }

  public void loadWalkingAnimation()
  {
    //System.out.println("walking");
    if (!disabled)
    {
      String url = "";
      ArrayList<String> a = new ArrayList<String>();
      a.add("ufo (3).png");
      setAnimation(new Animation(a, AnimationType.WALK));
    }
  }

  public void loadAttackAnimation()
  {
    //System.out.println("attacking");
    if (!disabled)
    {
      disableMovement();
      String url = "";
      ArrayList<String> a = new ArrayList<String>();
      //add animation frames to list
      a.add("ufo (3).png");
      setAnimation(new Animation(a, AnimationType.ATTACK));
    }
  }

  public void loadKillAnimation()
  {
    String url = "";
    ArrayList<String> a = new ArrayList<String>();
    //add animation frames to list
    for ( int i = 0; i <=4; i++)
    {
      String s = "ufo_boom";
      a.add(s + i + ".png");
      a.add(s + i + ".png");
    }
    disableMovement();
    setAnimation(new Animation(a, AnimationType.DIE));
  }
}