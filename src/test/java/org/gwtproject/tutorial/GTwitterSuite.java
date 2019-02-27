package org.gwtproject.tutorial;

import org.gwtproject.tutorial.client.GTwitterTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class GTwitterSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for GTwitter");
    suite.addTestSuite(GTwitterTest.class);
    return suite;
  }
}
