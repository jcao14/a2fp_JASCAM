public class MapMaker {
  final int WALL = 0;
  final int FLOOR = 1;
  final int PLAYER = 2;



  Tile[][] world = new Tile[20][20];

  Player link;
  
  //  private Player player = null;
    private LinkedList<MapObject> allObjects = new LinkedList<MapObject>();
    // [noncollidables added in front] [collidables added in back] [last element is player]
    private LinkedList<MapObject> collidables = new LinkedList<MapObject>();
    private LinkedList<MapObject> noncollidables = new LinkedList<MapObject>();

  public MapMaker(String map_file){
    readMap (map_file);
  }
  
  public Tile[][] getWorld(){
    return world;
  }
  public void readMap (String map_file){
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

  }
  public Player makeInitialMap () {

    for (int i =0; i<20; i++) {
      int ycor = i * 20 +10;

      for (int j=0; j<20; j++) {
        int xcor = j * 20 +10;

        world[i][j].setXY(xcor, ycor);
        switch (world[i][j].getType()) {
        case WALL:
          world[i][j].setWall();
          collidables.add(world[i][j]);
          allObjects.addLast(world[i][j]);
          break;
        case FLOOR:
          world[i][j].setFloor();
          noncollidables.add(world[i][j]);
          allObjects.add(0,world[i][j]);
          break;
        case PLAYER:
          link = new Player ("Hero", xcor, ycor);
          collidables.add(link);
          world[i][j].setFloor();
          noncollidables.add(world[i][j]);
          allObjects.add(0,world[i][j]);
          break;
        }

        world[i][j].animate();
      }
    }
    link.animate();
    return link;
  }

  public void makeMap () {
    

    for (int i =0; i<20; i++) {
      int ycor = i * 20 +10;

      for (int j=0; j<20; j++) {
        int xcor = j * 20 +10;

        world[i][j].setXY(xcor, ycor);
        switch (world[i][j].getType()) {
        case WALL:
          world[i][j].setWall();
          break;
        case FLOOR:
          world[i][j].setFloor();
          break;
        }

        world[i][j].animate();
      }
    }
  }
  
}