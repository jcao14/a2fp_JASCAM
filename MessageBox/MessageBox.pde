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
  mb = new MessageBox( 250, 250,
                       300, 100, 200/25 );

  mb.println("hello");
  mb.println("testing");
  mb.println("PLZWORK!!!!!!!!!!!!!!!!!");
  
  
}

void draw() {

  mb.display();

}

public class MessageBox {

  public static final int TEXTSIZE = 20;
  public static final int RED = 10;
  public static final int GREEN = 10;
  public static final int BLUE = 50;
  public static final int OPAC = 150;
  
  private double xcor, ycor;  //center coordinates;
  private double wid, hgt;
  private int num;

  private MBStack msgs;
  
  public MessageBox(double x, double y, double w, double h, int n) {
    this.xcor = x;
    this.ycor = y;
    this.wid = w;
    this.hgt = h;
    this.num = n;
    msgs = new MBStack();
  }
  
  public void print(String s) {
    msgs.push(s);
    System.out.print(s);
  }

  public void print(Object o) {
    msgs.push(o);
    System.out.print(o);
  }

  public void println(String s) {
    msgs.push( s + "\n" );
    System.out.println(s);
  }

  public void println(Object o) {
    msgs.push( o.toString() + "\n" );
    System.out.println(o);
  }

  public void display() {
    double ulx = this.xcor - this.wid/2;  //upper-left x
    double uly = this.ycor - this.hgt/2;  //upper-left y
    
    System.out.println("ulx:" + ulx +  " uly:" + uly);
    
    String[] sv = msgs.npeek(num);

    fill(MessageBox.RED, MessageBox.GREEN, MessageBox.BLUE, MessageBox.OPAC);
    rect((float)ulx, (float)uly, (float)wid, (float)hgt);
      
    for(int i=0; i<num && i<sv.length; i++) {

      textSize( MessageBox.TEXTSIZE );
      fill(255,255,255,255);
      text( sv[i], (float)ulx, (float)uly, (float)wid, (float)hgt );
      uly += (this.hgt/(num)+20);
      
    }
    
  }

}

class MBStack {  //custom made stack for MessageBox

  private ArrayList<String> _stack;

  public MBStack() {
    _stack = new ArrayList<String>(100);
  }

  public void push(String s) {
    _stack.add(s);
  }
  
  public void push(Object o) {
    _stack.add( o.toString() );
  }

  public String pop() {
    return( _stack.remove( _stack.size()-1 ) );
  }

  public String peek() {
    return( _stack.get( _stack.size()-1 ) );
  }

  public boolean isEmpty() {
    return( _stack.size()==0 );
  }

  //npeek
  //param n: positive int
  //return: array of top n strings on stack, in order pushed
  public String[] npeek(int n) {
    
    if( n > _stack.size() ) n = _stack.size(); //outofbounds check
    
    String[] ret = new String[n];
    
    for(int i=0; i<n; i++) {
      ret[i] = _stack.get( _stack.size()-n+i );
    }
    return(ret);
  }

  //clearBottom
  //param n: positive int less than size
  //clears the bottom-most element of the stack n-times, removing them
  public void clearBottom(int n) {
    for(int i=0; i<n && _stack.size()>0; i++) {
      _stack.remove(0);
    }
    _stack.trimToSize();
  }

}