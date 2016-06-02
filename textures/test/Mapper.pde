public class Mapper {
  final int WALL = 0;
  final int FLOOR = 1;

  PImage wall;
  PImage floor;

  int maxCamX = 200;
  int maxCamY = 200;
  int minCamX = 0;
  int minCamY = 0;

  Tile[][] world = new Tile[20][20];


  public void getTextures()
  {
    wall = loadImage("wall.png");
    floor = loadImage("floor.png");
  }

  public void makeMap()
  {
    String[] readMap = loadStrings ("map.txt");
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

    PImage tile = loadImage("floor.png");
    for (int i =0; i<20; i++) {
      int ycor = i * 20;
      
      for (int j=0; j<20; j++) {
        int xcor = j * 20;
        
        world[i][j].setXY(xcor,ycor);
        //System.out.println (world[i][j].getCoor());
        switch (world[i][j].type()) {
        case WALL:
          tile = wall;
          world[i][j].makeSolid();
          break;
        case FLOOR:
          tile = floor;
          break;
        }
        
        image(tile, xcor, ycor);
      }
    }
  }
}