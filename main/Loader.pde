//Loader class
//holds a Map of Strings to PImages

/* USAGE:
 * Loader.myLoadImage(URL, x, y);
 * PImage img = Loader.get(URL);
 */

public static class Loader {

  private static ArrayList<String> names = new ArrayList<String>();
  private static ArrayList<PImage> images = new ArrayList<PImage>();

  public static void myLoadImage(String s) {
    if( names.indexOf(s) < 0 ) {
      images.add( loadImage(s) );
      names.add( s );
    }
  }

  public static PImage get(String s) {
    return( images.get( names.indexOf(s) ) );
  }

}