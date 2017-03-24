import java.util.Enumeration;
import java.util.Hashtable;

import model.Ville;

public class HastableTest {
	

  public static void main(String[] args) {
	  Ville v1 = new Ville("Bavay");
	  Ville v2 = new Ville("Bavay");

    Hashtable ht = new Hashtable();
    ht.put(v1, "printemps");
    ht.put(v1, "été");
    ht.put(v2, "automne");
    ht.put(v1, "hiver");
    ht.put(v1, "blbl");

    Enumeration e = ht.elements();

    while(e.hasMoreElements())
      System.out.println(e.nextElement());

  }
}
