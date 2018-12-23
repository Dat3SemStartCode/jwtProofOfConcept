package unittest;

import jwtdemo.entity.User;
import jwtdemo.entity.UserFacade;
import jwtdemo.exceptions.AuthenticationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class UnitTest {
  
  public UnitTest() {
  }
  
  UserFacade facade = UserFacade.getInstance();
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  @Test
   public void getUserValid() throws AuthenticationException {
     User u = facade.getVeryfiedUser("user", "test");
     assertEquals("user", u.getUserName());
     
   }
 
   @Test(expected = AuthenticationException.class)
   public void getUserInValid() throws AuthenticationException {
     User u = facade.getVeryfiedUser("user", "testxxxx");
     assertEquals("user", u.getUserName());
     
   }
}
