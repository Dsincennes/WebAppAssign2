/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.sinc0138.bouncer.game;

import cst8218.sinc0138.bouncer.business.BouncerFacade;
import cst8218.sinc0138.bouncer.entity.Bouncer;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.Transient;

/**
 * Class that holds the actual Bounce game.
 * @author Donald
 */
@Startup 
@Singleton
public class BoucerGame {

    private List<Bouncer> bouncers;
    @EJB
    private BouncerFacade bouncerFacade;
    
    @Transient
    final static int CHANGE_RATE = 1;

    @PostConstruct
    public void go() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // the game runs indefinitely
                while (true) {
                    //update all the bouncers and save changes to the database
                    bouncers = bouncerFacade.findAll();
                    for (Bouncer bouncer : bouncers) {
                        bouncer.advanceOneFrame();
                        bouncerFacade.edit(bouncer);
                    }
                    //sleep while waiting to process the next frame of the animation
                    try {
                        // wake up roughly CHANGE_RATE times per second
                        Thread.sleep((long)(1.0/CHANGE_RATE*1000));                               
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
