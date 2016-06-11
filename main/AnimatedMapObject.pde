/* AnimatedMapObject
 *
 * This class is the direct subclass of CollidableMapObject
 * implements Animation (only basic walking animation for now)
 *
 * we can insert Max's better animation stuff here latar
 *
 *
 *
 */

public abstract class AnimatedMapObject extends MapObject {

  //========NESTED CLASS=====================
  public class Animation {
    public PImage[] frames;
    public int ctr;

    public Animation( String[] URLs, int sizeX, int sizeY ) {
      frames = new PImage[ URLs.length ];
      ctr=0;
      for (int i =0; i <URLs.length; i++) {
        frames[i] = loadImage(URLs[i]);
        frames[i].resize((int)sizeX, (int)sizeY);
      }
    }
    
    public void animate(int x, int y) {
      image(frames[ctr],x,y);
      ctr = (ctr+1)%(frames.length);
    }
  }

  //========STATIC FIELD=====================
  public final static int WALK_UP = 0;
  public final static int WALK_RIGHT = 1;
  public final static int WALK_LEFT = 2;
  public final static int WALK_DOWN = 3;
  
  //========FIELD=====================
  public ArrayList<Animation> anims;   //treat as protected
  public int current_animation_index;  //treat as protected

  public AnimatedMapObject( double x_arg, double y_arg, 
    double size_x, double size_y, 
    MapObjectType type ) 
  { //to be used by super only!
    super(x_arg, y_arg, size_x, size_y, type);
    anims = new ArrayList<Animation>();
  }
  
  public void addAnimation( String[] URLs ) {
    anims.add( new Animation(URLs, (int)sizeX, (int)sizeY) );
  }
  
  public abstract void animate();
  
}