//trap
//will be compatable with loader class

public class Trap extends AnimatedMapObject {

	TrapType type;
	
	public Trap(TrapType type, double x, double y) {
		super(x, y, 100, 100, MapObjectType.Trap);
		this.type = type;

    switch(type) {
      case TrapType.TrapHole:
        break;
      case TrapType.CHEST:
        break;
      case TrapType.WONDERTILE:
        break;
      default:
        break;
    }
    
	}

	
	
}

public enum TrapType {
	TRAPHOLE, CHEST, WONDERTILE
}