//game.pde
//Core file for project
//implements Max's game engine/paradigm

import java.util.*;
import map.*;       //we need to package map class files
//add other packages here

//===============GLOBAL VARS====================
ArrayList<AbstractAnimatedMapObject> AMO;  //AnimatedMapObjects
int player_index;                          //index of Player in AMO, Player extends AMO

String[] floorFiles;         //array of names of floor files, based on James' design
int floorNum;                //floor number
Tile[][] floor;

LinkedList<Char> InputQ;     //enqueue keyboard input and dequeue as resolved

int t;                       //loop counter,

//Add more globals here!

//======================SETUP=========================
void setup() {

	floorFiles = new String[] { /*INSERT FILE NAMES HERE*/ };
	floor_i = 0;
	floor = Mapmaker.makeMap( floorFiles[floor_i] ); //sorry, idr exactly

	AMO = new ArrayList<AbstractAnimatedMapObject>();
	//intialize MapObjects Here IN PRIORITY, ie Player, Mobs

	//Adding tiles here; Tiles are AMOs; I'm giving them least priority
	for(int i=0; i<floor.length; i++)                //Tile[][] row iterator
		for(int j=0; j<floor[i].length; j++) {   //Tile[][] column iterator
			AMO.add( floor[i][j] );
		}

	InputQ = new LinkedList<Char>();

	t = -1;

}

//========================DRAW======================
void draw() {

	t++;
	MapMaker.drawMap(floor); //I'm sry, idr and i cant pull T-T
	

	for(int i=0; i<AMO.length; i++) {
		AbstractAnimatedMapObject a = AMO.get(i);
		a.drawme();  //we need to add this abstract method

		//RESOLVE ALL EFFECTS AND COLLISIONS HERE, IDR exactly but something like:
		a.handleTouch( a.getNearby(AMO) ); //MAKE SOMETHING LIKE THIS WORK IN 1 LINE		
	}

	//resolve InputQ and Player Here!
	if( !InputQ.isEmpty() ) {
		Char ch = InputQ.dequeue;
		//Have something corresponding to:
		(Player)(AMO.get(player_index)).resolveInput(ch.charValue);
	}

}

//===================KEYPRESSED=====================
void KeyPressed() {
	InputQ.enqueue( new Char(key) );

}

//==================FUNCTIONS=======================

