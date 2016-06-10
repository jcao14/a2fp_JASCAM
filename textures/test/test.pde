Player link;

Mapper map;

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
  map.makeMap(link);
  //System.out.println (link.getXCor());
  // System.out.println(link.getYCor());
}
void keyPressed()
{
  collision = map.makeMap(link);
  if (key == 'w') {

    if (!collision) {
      link.ycor -= link.speed;
      link.player = link.front;
    } 
    if (collision) {
      link.ycor += link.speed;
      link.player = link.front;
    }
  }
  if (key == 'a') {
    //collision = map.makeMap(link);
    if (!collision) {
      link.xcor -= link.speed;
      link.player = link.left;
    } 
    if (collision) {
      link.xcor += link.speed;
      link.player = link.left;
    }
  }
  if (key == 's') {
    //collision = map.makeMap(link);
    if (!collision) {
      link.ycor += link.speed;
      link.player = link.back;
    } 
    if (collision) {
      link.ycor -= link.speed;
      link.player = link.back;
    }
  }
  if (key == 'd') {
    //collision = map.makeMap(link);
    if (!collision) {
      link.xcor += link.speed;
      link.player = link.right;
    } 
    if (collision) {
      link.xcor -= link.speed;
      link.player = link.right;
    }
  }
}