//Loader class
//holds a Map of Strings to PImages

/* USAGE:
 * Loader.myLoadImage(URL, x, y);
 * PImage img = Loader.get(URL);
 */

public class Loader {

  private ArrayList<String> names;
  private ArrayList<PImage> images;

  public Loader() {
    names = new ArrayList<String>();
    images = new ArrayList<PImage>();
  }

  public void myLoadImage(String s) {
    if ( names.indexOf(s) < 0 ) {
      images.add( loadImage(s) );
      names.add( s );
    }
  }

  public PImage get(String s) {
    return( images.get( names.indexOf(s) ) );
  }
}