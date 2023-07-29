/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppuserTest;

import cst8218.sinc0138.bouncer.entity.Appuser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
/**
 *
 * @author D
 */
public class AppuserTest {
    private Appuser appuser;
    
    @Before
    public void setUp(){
        appuser = new Appuser();
    }
    
    @Test
    public void testSetGroupName(){
        String expectedGroupname = "Admin";
        appuser.setGroupname(expectedGroupname);
        
        String actualGroupname = "Admin";
        
        assertEquals(expectedGroupname, actualGroupname);
    }
    
    
    @Test
    public void testSetUserID(){
        String expectedGroupname = "123";
        appuser.setGroupname(expectedGroupname);
        
        String actualGroupname = "123";
        
        assertEquals(expectedGroupname, actualGroupname);
    }
}
