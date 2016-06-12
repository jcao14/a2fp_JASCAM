public class MapMaker {
  final int WALL = 0;
  final int FLOOR = 1;
  final int PLAYER = 2;

  PImage wall;
  PImage floor;

  Tile[][] world = new Tile[20][20];
  
  Player link;



  public Player makeMap (String map_file, MapObject player) {
    String[] readMap = loadStrings (map_file);
    String[][] splitMap = new String[20][20];
    for (int i =0; i<20; i++) {
      String[]line = readMap[i].split(" ");
      for (int j=0; j<20; j++) {
        splitMap[i][j] = line[j];
      }
    }

    for (int i =0; i<20; i++) {
      for (int j=0; j<20; j++) {
        world[i][j] = new Tile(Integer.parseInt(splitMap[i][j]));
      }
    }

    PImage tile = floor;
    for (int i =0; i<20; i++) {
      int ycor = i * 20 +10;

      for (int j=0; j<20; j++) {
        int xcor = j * 20 +10;
        
        world[i][j].setXY(xcor, ycor);
        switch (world[i][j].getType()) {
        case WALL:
          tile = wall;
          break;
        case FLOOR:
          tile = floor;
          break;
        case PLAYER:
          link = new Player ("Hero",xcor,ycor);
          link.animate();
          tile = floor;
          break;
        }
        
        world[i][j].animate();
      }
    }
    return link;
  }
}