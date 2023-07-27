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
        y += ySpeed; // add speed to coordinate so it moves

        if (y > 0 && y < FRAME_HEIGHT) // checks if its at the floor
        {
            ySpeed += GRAVITY_ACCEL; // keep going until it hits the floor
        }
        if (y <= 0 && ySpeed < 0) // checks if its gone too high, start going down
        {
            ySpeed = -ySpeed - DECAY_RATE; // go lower each time
        } else if (y >= FRAME_HEIGHT && ySpeed > 0) // go other way
        {
            ySpeed = -ySpeed + DECAY_RATE; // reverse direction, add decay
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
