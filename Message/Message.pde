//messageBox
/*
 * USAGE:
 *
 * instantiate: mb = new MessageBox(<center-x-cor>, <center-y-cor>, <box-width>, <box-height>, <number-strings-to-print>)
 * to print a msg: mb.println("Tat hype sprite");
 *
 * NOTES:
 * default box color: dark navy
 * default text color: white
 * default text size: 20
 * default font: POKEMON!! (TO BE IMPLEMENTED):
 *
 */
 
import java.util.ArrayList;
 
MessageBox mb;
 
void setup() {
  size(500,500);
  background(50,255,100);
  mb = new MessageBox( 250.0d, 250.0d,
                       300.0d, 100.0d, 200/25 );

  mb.println("hello");
  mb.println("testing");
  mb.println("PLZWORK!!!!!!!!!!!!!!!!!");
  
  
}

void draw() {

  mb.display();

}