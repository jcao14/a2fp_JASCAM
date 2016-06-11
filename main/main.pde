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

//========================SETUP==============================
void setup() {
  size(500, 500);  //DECIDE LATER, DO EVERYTHING RELATIVELY

  //init globals
  inputs = new LinkedList<String>();
}


//========================DRAW==============================
void draw() {
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