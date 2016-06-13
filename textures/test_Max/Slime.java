import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
public class Slime extends Monster
{
  public Slime (LinkedList<Tile>p) {
    super (p);
    maxHp = 100;
    maxMp = 20;
    hp = 100;
    mp = 20;
    baseSpeed = 8;
    baseAttack = 10;
    baseDefense = 10;
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
    for ( int i = 18; i >= 0; i--)
    {
      String s = "melt_slime (";
      a.add(s + i + ").png");
      a.add(s + i + ").png");
    }
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
      //add animation frames to list
      a.add("slime (2).png");
      a.add("slime (2).png");
      a.add("slime (3).png");
      a.add("slime (4).png");
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
      for (int i = 0; i <= 12; i++)
      {
        String s = "jump_slime (";
        a.add(s + i + ").png");
        a.add(s + i + ").png");
      }
      setAnimation(new Animation(a, AnimationType.ATTACK));
    }
  }

  public void loadKillAnimation()
  {
    String url = "";
    ArrayList<String> a = new ArrayList<String>();
    //add animation frames to list
    for ( int i = 0; i <= 18; i++)
    {
      String s = "melt_slime (";
      a.add(s + i + ").png");
      a.add(s + i + ").png");
a.add(s + i + ").png");
      a.add(s + i + ").png");
a.add(s + i + ").png");
      a.add(s + i + ").png");
    }
    disableMovement();
    setAnimation(new Animation(a, AnimationType.DIE));
  }
}