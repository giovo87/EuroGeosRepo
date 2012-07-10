/*
 *  Copyright (C) 2007 - 2011 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 *  GPLv3 + Classpath exception
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.geosolutions.fao.fra.entryform.table1_4_a.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author Gabriele Giovenco
 *
 */

@Entity
@Table(name="foresttable")
public class EntityForest implements Serializable{
    
    public EntityForest(){}
    
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    @Id
    private PkForest pkForest;
    /**
     * @return the composite pk
     */
    public PkForest getPkForest(){
        return pkForest;
    }
    
    /**
     * @param pkForest the composite pk to set
     */
    public void setPkForest(PkForest pkForest){
        this.pkForest = pkForest;
    }
    
    
    @Column(name="forest")
    private Float forest;
    
    /**
     * @return the forest
     */
    public Float getForest() {
            return forest;
    }

    /**
     * @param forest the forest to set
     */
    public void setForest(Float forest) {
            this.forest = forest;
    }
    
    
    @Column(name="other_wooded_land")
    private Float other_wooded_land;
    
    /**
     * @return the other_wooded_land
     */
    public Float getOther_wooded_land() {
            return other_wooded_land;
    }

    /**
     * @param other_wooded_land the other_wooded_land to set
     */
    public void setOther_wooded_land(Float other_wooded_land) {
            this.other_wooded_land = other_wooded_land;
    }
    
    
    @Column(name="other_land")
    private Float other_land;
    
    /**
     * @return the other_land
     */
    public Float getOther_land() {
            return other_land;
    }

    /**
     * @param other_land the other_land to set
     */
    public void setOther_land(Float other_land) {
            this.other_land = other_land;
    }
    
    
    @Column(name="other_tree_cover")
    private Float other_tree_cover;
    
    /**
     * @return the other_tree_cover
     */
    public Float getOther_tree_cover() {
            return other_tree_cover;
    }

    /**
     * @param other_tree_cover the other_tree_cover
     */
    public void setOther_tree_cover(Float other_tree_cover) {
            this.other_tree_cover = other_tree_cover;
    }
    
    @Column(name="inland_water_bodies")
    private Float inland_water_bodies;
    
    /**
     * @return the inland_water_bodies
     */
    public Float getInland_water_bodies() {
            return inland_water_bodies;
    }

    /**
     * @param inland_water_bodies the inland_water_bodies
     */
    public void setInland_water_bodies(Float inland_water_bodies) {
            this.inland_water_bodies = inland_water_bodies;
    }
    
    

}
