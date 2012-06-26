package org.example.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Gabriele Giovenco
 *
 */

@Entity
@Table(name="foresttable")
public class EntityForest implements Serializable{
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="userid")
    private String userId;
    /**
     * @return the userId
     */
    public String getUserId() {
            return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId ) {
            this.userId = userId;
    }
    
    @Id
    @Column(name="year")
    private int year;
    /**
     * @return the year
     */
    public int getYear() {
            return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
            this.year = year;
    }
    
    
    @Column(name="forest")
    private int forest;
    /**
     * @return the forest
     */
    public int getForest() {
            return forest;
    }

    /**
     * @param forest the forest to set
     */
    public void setForest(int forest) {
            this.forest = forest;
    }
    
    
    @Column(name="other_wooded_land")
    private int other_wooded_land;
    /**
     * @return the other_wooded_land
     */
    public int getOther_wooded_land() {
            return other_wooded_land;
    }

    /**
     * @param other_wooded_land the other_wooded_land to set
     */
    public void setOther_wooded_land(int other_wooded_land) {
            this.other_wooded_land = other_wooded_land;
    }
    
    
    @Column(name="other_land")
    private int other_land;
    /**
     * @return the other_land
     */
    public int getOther_land() {
            return other_land;
    }

    /**
     * @param other_land the other_land to set
     */
    public void setOther_land(int other_land) {
            this.other_land = other_land;
    }
    
    
    @Column(name="other_tree_cover")
    private int other_tree_cover;
    /**
     * @return the other_tree_cover
     */
    public int getOther_tree_cover() {
            return other_tree_cover;
    }

    /**
     * @param other_tree_cover the other_tree_cover
     */
    public void setOther_tree_cover(int other_tree_cover) {
            this.other_tree_cover = other_tree_cover;
    }
    
    @Column(name="inland_water_bodies")
    private int inland_water_bodies;
    /**
     * @return the inland_water_bodies
     */
    public int getInland_water_bodies() {
            return inland_water_bodies;
    }

    /**
     * @param inland_water_bodies the inland_water_bodies
     */
    public void setInland_water_bodies(int inland_water_bodies) {
            this.inland_water_bodies = inland_water_bodies;
    }
    
    

}
