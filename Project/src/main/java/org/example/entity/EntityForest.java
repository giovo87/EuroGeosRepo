package org.example.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Gabriele Giovenco
 *
 */

@Entity
@Table(name="foresttable")
public class EntityForest {
    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name="id")
    private int id;
    /**
     * @return the id
     */
    public int getId() {
            return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
            this.id = id;
    }
    
    
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
    
    
    @Column(name="year")
    private String year;
    /**
     * @return the year
     */
    public String getYear() {
            return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
            this.year = year;
    }
    
    
    @Column(name="forest")
    private String forest;
    /**
     * @return the forest
     */
    public String getForest() {
            return forest;
    }

    /**
     * @param forest the forest to set
     */
    public void setForest(String forest) {
            this.forest = forest;
    }
    
    
    @Column(name="other_wooded_land")
    private String other_wooded_land;
    /**
     * @return the other_wooded_land
     */
    public String getOther_wooded_land() {
            return other_wooded_land;
    }

    /**
     * @param other_wooded_land the other_wooded_land to set
     */
    public void setOther_wooded_land(String other_wooded_land) {
            this.other_wooded_land = other_wooded_land;
    }
    
    
    @Column(name="other_land")
    private String other_land;
    /**
     * @return the other_land
     */
    public String getOther_land() {
            return other_land;
    }

    /**
     * @param other_land the other_land to set
     */
    public void setOther_land(String other_land) {
            this.other_land = other_land;
    }
    
    
    @Column(name="other_tree_cover")
    private String other_tree_cover;
    /**
     * @return the other_tree_cover
     */
    public String getOther_tree_cover() {
            return other_tree_cover;
    }

    /**
     * @param other_tree_cover the other_tree_cover
     */
    public void setOther_tree_cover(String other_tree_cover) {
            this.other_tree_cover = other_tree_cover;
    }
    
    @Column(name="inland_water_bodies")
    private String inland_water_bodies;
    /**
     * @return the inland_water_bodies
     */
    public String getInland_water_bodies() {
            return inland_water_bodies;
    }

    /**
     * @param inland_water_bodies the inland_water_bodies
     */
    public void setInland_water_bodies(String inland_water_bodies) {
            this.inland_water_bodies = inland_water_bodies;
    }
    
    

}
