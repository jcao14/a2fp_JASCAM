
final int WALL = 0;
final int FLOOR = 1;

PImage wall;
PImage floor;

int[][] world = new int [20][20];

void getTextures()
{
  wall = loadImage("wall.png");
  floor = loadImage("floor.png");
}

void makeMap()
{
  String[] readMap = loadStrings ("map.txt");
  String[][] splitMap = new String[20][20];
  for (int i =0; i<20; i++){
     String[]line = readMap[i].split(" ");
     for (int j=0;j<20;j++){
       splitMap[i][j] = line[j];
     }
  }
 
       
  for (int i =0; i<20; i++){
    for (int j=0; j<20; j++){
      world[i][j] = Integer.parseInt(splitMap[i][j]);
    }
  }
  
  PImage tile = loadImage("floor.png");
  for (int i =0; i<20; i++){
    int ycor = i * 20;
    for (int j=0; j<20; j++){
      int xcor = j * 20;
      switch (world[i][j]){
        case WALL:
          tile = wall;
          break;
        case FLOOR:
          tile = floor;
          break;
      }
      image(tile,xcor,ycor);
    }
  }
}
void setup()
{
  size(400,400);
  getTextures();
}

void draw()
{
  makeMap();
}
