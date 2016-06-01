package tile;

import map.TouchRegion;
import map.MapObject;
import map.MapObjectType;


public class Floor extends Tile
{
    public Floor(String img, double sx, double sy)
    {
	x = y = 0;
	frame = img;
	sizeX = sx;
	sizeY = sy;
	collidable = false;
	type = MapObjectType.FLOOR;
    }
}
