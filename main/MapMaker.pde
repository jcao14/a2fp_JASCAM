public class MapMaker {
  //Final variable ints that will be used for text file reading for the map creation
  final int WALL = 0;
  final int FLOOR = 1;
  final int PLAYER = 2;
  final int HOLE = 3;
  final int WONDER = 4;
  final int TREASURE = 5;
  final int STAIRS = 6;


  Tile[][] world = new Tile[30][30]; //made the world bigger with a lot more walls on the side to make the world movements look nicer
  PImage img_wall;
  PImage img_floor;
  PImage img_hole;
  PImage img_wonder;
  PImage img_treasure;
  PImage img_stairs;

  Player link;

  //Copypasta of Max's map object stuff. Currently only used to make all non player objects move relative to inputs. Use this for collision later on.
  private LinkedList<MapObject> allObjects = new LinkedList<MapObject>();
  // [noncollidables added in front] [collidables added in back] [last element is player]
  private LinkedList<MapObject> collidables = new LinkedList<MapObject>();
  private LinkedList<MapObject> noncollidables = new LinkedList<MapObject>();

  //Saves so much darn time lol. Make a new instance of MapMaker for each new map.
  public MapMaker(String map_file) {
    readMap (map_file);//READS THE TXT FILE, ONLY ONCE. TAKE THAT!!!
    img_wall = loadImage("wall.png");
    img_floor = loadImage("floor.png");
    img_hole = loadImage("hole.png");
    img_wonder = loadImage("wonder.png");
    img_treasure = loadImage("treasure_closed.png");
    img_stairs = loadImage("stairs.png");
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

    for (int i =0; i<26; i++) {
      String[] line = readMap[i].split(" ");
      for (int j=0; j<26; j++) {
        splitMap[i][j] = line[j];
      }
    }

    for (int i =0; i<26; i++) {
      for (int j=0; j<26; j++) {
        world[i][j] = new Tile(Integer.parseInt(splitMap[i][j]), null);
      }
    }
  }
  //used in setup ONLY. Will create a map. Tiles will store their x and y coordinates.
  //Player's initial coordinates are set.
  //Objects are added to their appropriate linked lists
  //Draws everything after all the data has been fed.
  public Player makeInitialMap () {

    for (int i =0; i<26; i++) {
      int ycor = i * 50 +25;

      for (int j=0; j<26; j++) {
        int xcor = j * 50 +25;

        world[i][j].setXY(xcor, ycor);
        switch (world[i][j].getType()) {
        case WALL:
          world[i][j].setType(img_wall, 0);
          collidables.add(world[i][j]);
          if (allObjects.size() != 0) {
            allObjects.add(allObjects.size()-1, world[i][j]);
          } else {
            allObjects.add(0, world[i][j]);
          }
          break;
        case FLOOR:
          world[i][j].setType(img_floor, 1);
          noncollidables.add(world[i][j]);
          allObjects.add(0, world[i][j]);
/*
          if (Math.random() > .5) {
            Monster slime = new Monster(xcor, ycor);
            collidables.add(slime);
            world[i][j].setType(img_floor, 1);
            noncollidables.add(world[i][j]);
            allObjects.add(world[i][j]);
            slime.animate();
          }*/
          break;
        case PLAYER:
          link = new Player ("Hero", xcor, ycor);
          collidables.add(link);
          world[i][j].setType(img_floor, 1);
          noncollidables.add(world[i][j]);
          allObjects.addLast(world[i][j]);
          break;

        case HOLE:
          world[i][j] = new Trap(TrapType.values()[0], world[i][j].getX(), world[i][j].getY() );
          collidables.add(world[i][j]);
          if (allObjects.size() != 0) {
            allObjects.add(allObjects.size()-1, world[i][j]);
          } else {
            allObjects.add(0, world[i][j]);
          }
          break;

        case WONDER:
          world[i][j] = new Trap(TrapType.values()[2], world[i][j].getX(), world[i][j].getY() );
          collidables.add(world[i][j]);
          if (allObjects.size() != 0) {
            allObjects.add(allObjects.size()-1, world[i][j]);
          } else {
            allObjects.add(0, world[i][j]);
          }
          break;

        case TREASURE:
          world[i][j] = new Trap(TrapType.values()[1], world[i][j].getX(), world[i][j].getY() );
          collidables.add(world[i][j]);
          if (allObjects.size() != 0) {
            allObjects.add(allObjects.size()-1, world[i][j]);
          } else {
            allObjects.add(0, world[i][j]);
          }
          break;

        case STAIRS:
          world[i][j] = new Trap(TrapType.values()[3], world[i][j].getX(), world[i][j].getY() );
          collidables.add(world[i][j]);
          if (allObjects.size() != 0) {
            allObjects.add(allObjects.size()-1, world[i][j]);
          } else {
            allObjects.add(0, world[i][j]);
          }
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


    for (int i =0; i<26; i++) {


      for (int j=0; j<26; j++) {


        //world[i][j].setXY(xcor, ycor);
        switch (world[i][j].getType()) {
        case WALL:
          world[i][j].setType(img_wall, 0);
          break;
        case FLOOR:
          world[i][j].setType(img_floor, 1);
          break;

          //these should appear as their default cases until they are triggered. So use some kinda of local boolean to track that? 
          //local boolean assigned to the trap tile. boolean triggered?
        case HOLE:
          world[i][j].setType(img_hole, 2);
          break;

        case WONDER:
          world[i][j].setType(img_hole, 3);
          break;

        case TREASURE:
          world[i][j].setType(img_hole, 4);
          break;

        case STAIRS:
          world[i][j].setType(img_stairs, 5);
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
        mo.setXY(x + mo.getX(), y + mo.getY());
      }
    }
  }
}