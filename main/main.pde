//main

/* This will be the main file, launching and running programs
 *
 */

/*
 *
 *
 */

import java.util.*;

//========================GLOBALS==============================
Queue<String> inputs;
Player link;
MapMaker world;
Tile[][] screen;



//double radianCounter; //for testing
//========================SETUP==============================
void setup() {
  size(500, 500);  //DECIDE LATER, DO EVERYTHING RELATIVELY

  //init globals
  inputs = new LinkedList<String>();
  world = new MapMaker("map.txt");
  link = world.makeInitialMap();
  screen = world.getWorld();
  
  //camera ((float)link.getX(),(float)link.getY(),(float)0,(float)link.getX(),(float)link.getY(),(float)0,(float)0,(float)0,(float)0);
}


//========================DRAW==============================
void draw() {
 // pushMatrix();
  background(0);
  world.makeMap();
  link.move();
  link.animate();
 // popMatrix();
    //radianCounter += 0.01;
// link.setDirection (radianCounter);

}


//========================KEYPRESSED==============================
void keyPressed() {
  if (key == 'w') {
    inputs.add ("W");
  } else if (key == 'a') {
    inputs.add ("A");
  } else if (key == 's') {
    inputs.add ("S");
  } else if (key == 'd') {
    inputs.add ("D");
  }
}




//========================MISC==============================