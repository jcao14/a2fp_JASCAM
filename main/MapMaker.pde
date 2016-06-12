public class MapMaker {
  //Final variable ints that will be used for text file reading for the map creation
  final int WALL = 0;
  final int FLOOR = 1;
  final int PLAYER = 2;



  Tile[][] world = new Tile[30][30]; //made the world bigger with a lot more walls on the side to make the world movements look nicer

  Player link;

  //Copypasta of Max's map object stuff. Currently only used to make all non player objects move relative to inputs. Use this for collision later on.
  private LinkedList<MapObject> allObjects = new LinkedList<MapObject>();
  // [noncollidables added in front] [collidables added in back] [last element is player]
  private LinkedList<MapObject> collidables = new LinkedList<MapObject>();
  private LinkedList<MapObject> noncollidables = new LinkedList<MapObject>();

  //Saves so much darn time lol. Make a new instance of MapMaker for each new map.
  public MapMaker(String map_file) {
    readMap (map_file);//READS THE TXT FILE, ONLY ONCE. TAKE THAT!!!
  }

//======Accessors============
  public Tile[][] getWorld() {
    return world; 
  }

  public LinkedList<MapObject> getAllObjects()
  {
    return allObjects;
  }

  public LinkedList<MapObject> getCollidables()
  {
    return collidables;
  }

  public LinkedList<MapObject> getNoncollidables()
  {
    return noncollidables;
  }

//Reads given txt file and produes a 2D array of tiles. Each tile carries a differnt number, which allows it to produce a different image
//Probably use this part to include collisions.
  public void readMap (String map_file) {
    String[] readMap = loadStrings (map_file);
    String[][] splitMap = new String[30][30];
    for (int i =0; i<30; i++) {
      String[]line = readMap[i].split(" ");
      for (int j=0; j<30; j++) {
        splitMap[i][j] = line[j];
      }
    }

    for (int i =0; i<30; i++) {
      for (int j=0; j<30; j++) {
        world[i][j] = new Tile(Integer.parseInt(splitMap[i][j]));
      }
    }
  }
  //used in setup ONLY. Will create a map. Tiles will store their x and y coordinates.
  //Player's initial coordinates are set.
  //Objects are added to their appropriate linked lists
  //Draws everything after all the data has been fed.
  public Player makeInitialMap () {

    for (int i =0; i<30; i++) {
      int ycor = i * 20 +10;

      for (int j=0; j<30; j++) {
        int xcor = j * 20 +10;

        world[i][j].setXY(xcor, ycor);
        switch (world[i][j].getType()) {
        case WALL:
          world[i][j].setWall();
          collidables.add(world[i][j]);
          if (allObjects.size() != 0) {
            allObjects.add(allObjects.size()-1, world[i][j]);
          } else {
            allObjects.add(0, world[i][j]);
          }
          break;
        case FLOOR:
          world[i][j].setFloor();
          noncollidables.add(world[i][j]);
          allObjects.add(0, world[i][j]);
          break;
        case PLAYER:
          link = new Player ("Hero", xcor, ycor);
          collidables.add(link);
          world[i][j].setFloor();
          noncollidables.add(world[i][j]);
          allObjects.addLast(world[i][j]);
          break;
        }

        world[i][j].animate();
      }
    }
    link.animate();
    return link;
  }

//Same thing as makeInitialMap except without the Tile and Player coordinates. Player won't move. Tile coordinates do not want to be reset.
//Moving with input will technically change tile coordinates, so let's not make them reset after each frame, lol. I'm being cynical here.
//We don't want it to reset. Yeah? o-o
  public void makeMap () {


    for (int i =0; i<30; i++) {


      for (int j=0; j<30; j++) {


        //world[i][j].setXY(xcor, ycor);
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

//Copypasta from Max. Moves all non Player objects based on player's x and y velocity.
  public void moveAll (double x, double y) {
    for (MapObject mo : allObjects)
    {
      if (!(mo instanceof Player))
      {
        mo.moveTo(x + mo.getX(), y + mo.getY());
      }
    }
  }
}