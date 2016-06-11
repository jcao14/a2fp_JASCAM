import java.util.LinkedList;
import java.util.Queue;
Player link;

Mapper map;

Queue<String> inputs = new LinkedList<String>();
boolean collision;

void setup()
{
  size(550, 550);
  link = new Player();
  map = new Mapper();
  map.getTextures();
  map.makeMap(link);
  
}


void draw()
{
  background(0);
  if (inputs.size() != 0) {
    String move = inputs.remove();
    Movement.getMove(move).move(link);
  }
  collision = map.makeMap(link);// may cause errors atm
  //System.out.println (link.getXCor());
  // System.out.println(link.getYCor());
}
void keyPressed()
{
  synchronized(link)
  {
    //collision = map.makeMap(link);
    if (key == 'w') {
      inputs.add ("W");
      // if (collision) {
      //     link.ycor += link.speed;
      //     link.player = link.front;
      //    }
      //    if (!collision) {
      // link.ycor -= link.speed;
      // link.player = link.front;
      //   }
    } else if (key == 'a') {
      inputs.add ("A");
      //collision = map.makeMap(link);
      //if (collision) {
      //      link.xcor += link.speed;
      //      link.player = link.left;
      //    }
      //   if (!collision) {
      //link.xcor -= link.speed;
      //link.player = link.left;
      //   }
    } else if (key == 's') {
      inputs.add ("S");
      //collision = map.makeMap(link);

      //  if (collision) {
      //    link.ycor -= link.speed;
      //    link.player = link.back;
      //  }
      //   if (!collision) {
      //link.ycor += link.speed;
      //link.player = link.back;
      //  }
    } else if (key == 'd') {
      inputs.add ("D");
      //collision = map.makeMap(link);
      //  if (collision) {
      //    link.xcor -= link.speed;
      //     link.player = link.right;
      //  }
      //  if (!collision) {
      //link.xcor += link.speed;
      //link.player = link.right;
      //    }
    }
  }
}