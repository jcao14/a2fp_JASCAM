Player link;

Mapper map;

void setup()
{
  size(550, 550);
  map = new Mapper();
  map.getTextures();
  map.makeMap();
  link = new Player();
  link.create();
}


void draw()
{
  background(0);
  map.makeMap();
  link.create();
}
void keyPressed()
{
  if (key == 'w') {
    link.ycor -= link.speed;
    link.player = link.front;
  }
  if (key == 'a') {
    link.xcor -= link.speed;
    link.player = link.left;
  }
  if (key == 's') {
    link.ycor += link.speed;
    link.player = link.back;
  }
  if (key == 'd') {
    link.xcor += link.speed;
    link.player = link.right;
  }
}