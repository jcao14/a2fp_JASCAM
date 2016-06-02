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
  }
  if (key == 'a') {
    link.xcor -= link.speed;
  }
  if (key == 's') {
    link.ycor += link.speed;
  }
  if (key == 'd') {
    link.xcor += link.speed;
  }
}