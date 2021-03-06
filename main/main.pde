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
Queue<String> inputs;//handles player inputs
Player link; 
MapMaker world;
Tile[][] screen; //Basically is a 2D array of the map tiles
LinkedList<MapObject> gameObjects; // a linkedList containing all game objects
Loader loader;
int bullet_cooldown; //keep track of cooldown

LinkedList<Bullet> bullets;
Bullet bang;
boolean fired;

//double radianCounter; //for testing
//========================SETUP==============================
void setup() {
  size(700, 700);  //DECIDE LATER, DO EVERYTHING RELATIVELY

  //init globals
  inputs = new LinkedList<String>();
  bullets = new LinkedList<Bullet>();
  world = new MapMaker("map.txt");//change txt file to change map
  link = world.makeInitialMap(); // will create a player with their default coordinates based on the map. Will also initialize the game screen
  screen = world.getWorld(); // gets the 2D array of the map that was just created
  gameObjects = world.getAllObjects();
  loader = new Loader();
  
  bullet_cooldown = 1;
}


//========================DRAW==============================
void draw() {
  
  background(0);
  world.makeMap(); //different from the initial map creation. This one doesn't store tile coordinates as they've already been created.
  // link.move();
  if (inputs.size() != 0) { //.catches the idle player behaviors. ;)
    readInput(); //pops off next input in stack and does something
  }
  link.animate(); //prints the W,A,S or D sprite for player depending on what readInput did

  //bullet movement code
  for (int i=0; i<bullets.size(); i++) {
    Bullet b = bullets.get(i);
    b.animate();
    b.move();
    b.increase();
    if ( b.getCounter() > Bullet.BULLET_TIME_LIMIT ) {
      bullets.remove(i);
    }
  }

  //player bullet creation code
  if(mousePressed && bullet_cooldown>0) {
    Bullet b = new Bullet(link.getX(), link.getY(), mouseX, mouseY);
    bullets.addLast(b);
    bullet_cooldown = -9;
    //System.out.println("BOOM");
  }
  bullet_cooldown++;
  
}


/*void mousePressed(){
 Bullet b = new Bullet(link.getX(), link.getY());
 bullets.addLast(b);
 System.out.println("BOOM");
 }can't hold down the mouse */
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

void readInput() {
  String input = inputs.remove(); //deque

  //Each input changes the player sprite for animate
  //World moves relative to player's velocity so that player is always at the center of the screen
  //Not sure if we really wanna use velocity for that, but yeah as of now, it works fine.

  if (input == "W") {
    link.player = link.back;
    world.moveAll (0, link.getVelocity()[1]);
  } else if (input == "A") {
    link.player = link.left;
    world.moveAll (link.getVelocity()[0], 0);
  } else if (input == "S") {
    link.player = link.front;
    world.moveAll (0, -link.getVelocity()[1]);
  } else if (input == "D") {
    link.player = link.right;
    world.moveAll (-link.getVelocity()[0], 0);
  }
}




//========================MISC==============================