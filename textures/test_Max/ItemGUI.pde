//itemgui class
//class with interface for user to use items
//outside of GMap map

public class ItemGUI {

  private float x, y;  //center cordinates
  private int sizeX, sizeY;
  private Inventory inv;   //a reference to players inventory

  //constructors
  public ItemGUI(int x_, int y_, int size_x, int size_y, Inventory i) {
    this.x = (float)x_;
    this.y = (float)y_;
    this.sizeX = size_x;
    this.sizeY = size_y;
    this.inv = i;
  }

  public ItemGUI(int x_, int y_, Inventory i) {
    this(x_, y_, 60, 70, i);
  }

  //returns true if points or on box
  public boolean isOn(int ax, int ay) {
    return( (x-sizeX/2 < ax) && (ax < x+sizeX/2) && (y-sizeY/2< ay) && (ay < y+sizeY/2) );
  }

  public void display() {
    float ulx = x+sizeX/2;
    float uly = y-sizeY/2;
    fill( 0, 0, 50 );
    rect( ulx, uly, sizeX, sizeY, 7);
    for (int i=0; i<sizeY/70; i++) {
      for (int j=0; j<sizeX/70; j++) {
        fill( 70, 70, 70 );
        rect( ulx+i*30+5, uly+(j*30+5), 30, 30, 4 );
        //draw image....
      }
    }
  }
  
  public void update(Inventory i) {
    this.inv = i;
  }
  
}