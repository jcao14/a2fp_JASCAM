

public class Wall extends Tile
{
    public Wall(String img, double sx, double sy)
    {
	x = y = 0;
	frame = img;
	sizeX = sx;
	sizeY = sy;
	collidable = true;
	type = MapObjectType.WALL;
    }	
}
