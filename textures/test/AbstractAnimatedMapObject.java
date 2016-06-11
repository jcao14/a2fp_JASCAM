
/* AbstractAnimatedMapObject
 *
 *
 */

public abstract class AbstractAnimatedMapObject extends MapObject;{

	public class Animation {
		//TBD;
	}

	Animation[] walkAnimation;
	Animation[] idleAnimation;
	
	public abstract void move();
	
}