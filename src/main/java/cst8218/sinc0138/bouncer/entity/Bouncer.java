/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.sinc0138.bouncer.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class, DB values are gathered from here.
 * @author Donald
 */
@Entity
@XmlRootElement
public class Bouncer implements Serializable {

    final static int GRAVITY_ACCEL = 1;
    final static int FRAME_HEIGHT = 500;
    final static int DECAY_RATE = 1;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Integer x;
    
    private Integer y;
    
    private Integer ySpeed;
    
    /**
     * every second, we advance our x/y ball. We use acceleration and decay to
     * create a bouncing ball effect.
     *
     */
    public void advanceOneFrame() { 
        
        int currentLocation = y; 
        
        // all these if statements are hella gross but =)
        
            if (ySpeed > 0){                
                // positive calculation
                // find next y Postion bases on ySpeed. If Beyond box bounds, it bounces.                
                if ((currentLocation + ySpeed) >= FRAME_HEIGHT){
                    // do bounce                      
                    y = FRAME_HEIGHT;
                    ySpeed *= -1;
                    ySpeed = ySpeed + 1;    
                    // couldn't decide if i wanted the bounce to also include a move? or this frame only assigns a "bounce"
                } 
                else {                      
                    y = y + ySpeed; // adjust location. 
                    ySpeed = ySpeed + GRAVITY_ACCEL; // adjust speed
                }                
            }
            else if (ySpeed < 0 ) {                
                if ((currentLocation + ySpeed) <= 0){ // adding due to it being a negative number
                    // do bounce                    
                     y = 0;
                     ySpeed *= -1;
                     ySpeed = ySpeed - 1;                     
                }
                else {                     
                    y = y + ySpeed; // adjust location. 
                    ySpeed = ySpeed + DECAY_RATE; // adjust speed
                }                 
            } 
            else { // implies speed is 0. Have to account for moments the 
                
                if (y != FRAME_HEIGHT){ // means it's not stationary   
                    
                       ySpeed = ySpeed + 1;
                    }             
                                
            }       
    }
    
    /**
     * Checks if body of request has any null objects and sets them to 
     * original values before the response call.
     * @param entity 
     */
    public void updateBouncer(Bouncer entity){
        if(x == null)
            setX(entity.x);
        if(y == null)
            setY(entity.y);
        if(ySpeed == null)
            setySpeed(entity.ySpeed);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
        public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getySpeed() {
        return ySpeed;
    }

    public void setySpeed(Integer ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bouncer)) {
            return false;
        }
        Bouncer other = (Bouncer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.sinc0138.bouncer.entity.Bouncer[ id=" + id + " ]";
    }
    
}
