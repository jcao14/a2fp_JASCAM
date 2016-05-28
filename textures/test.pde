
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
// Just replaced the text( myText.substring(0,currentIndex), xpos+3, ypos+12); with text( myText.substring(0,currentIndex), xpos+3, ypos, width - 40, 60);
/*
class TFextbox {
  int xpos;
  int ypos;
  String myText;
  int delayTime;
  int creationTime;
  int currentIndex;
  TFextbox( String _myText, int _xpos, int _ypos, int _delayTime ) {
    myText = _myText;
    xpos = _xpos;
    ypos = _ypos;
    delayTime = _delayTime;
      creationTime = millis();
    currentIndex = 0;
  }
  void draw() {
    while( millis() - creationTime > delayTime ){
      creationTime += delayTime;
      currentIndex+=1;
    }

    fill( 0,0,255);
    stroke(255);
    rect( xpos, ypos, width - 40, 15 );
    fill(255);
    noStroke();
    currentIndex = constrain( currentIndex, 0, myText.length());
    text( myText.substring(0,currentIndex), xpos+3, ypos+12 );
  }
    
  }

TFextbox box1;

void setup(){
  size( 420, 100);
  box1 = new TFextbox( "ZELDA: Link, you must hurry to the castle to save me. I'm in trouble!", 20, 20, 100);
}

void draw(){
  background(0);
  box1.draw();
}*/