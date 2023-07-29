/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BouncerTest;

import cst8218.sinc0138.bouncer.entity.Bouncer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author D
 */
public class BouncerTest {
    private Bouncer bouncer;
   
    @Before
    public void setUp() {
        bouncer = new Bouncer();
    }
    
    @Test
    public void testAdvanceOneFrame_PositiveYSpeed(){
        bouncer.setY(200);
        bouncer.setySpeed(10);
        bouncer.advanceOneFrame();
        
        int y = bouncer.getY();
        int ySpeed = bouncer.getySpeed();
        
        Assert.assertEquals(210, y);
        Assert.assertEquals(11, ySpeed);
    }
    
    @Test
    public void testAdvanceOneFrame_NegativeYSpeed(){
        bouncer.setY(200);
        bouncer.setySpeed(-5);
        bouncer.advanceOneFrame();
        
        int y = bouncer.getY();
        int ySpeed = bouncer.getySpeed();
        
        Assert.assertEquals(195, y);
        Assert.assertEquals(-4, ySpeed);
    }
}
