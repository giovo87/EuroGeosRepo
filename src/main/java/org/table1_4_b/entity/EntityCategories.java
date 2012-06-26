package org.table1_4_b.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author Gabriele Giovenco
 *
 */

@Entity
@Table(name="categorytable")
public class EntityCategories implements Serializable{
    
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
    @Column(name="category")
    private String category;
    /**
     * @return the category
     */
    public String getCategory() {
            return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
            this.category = category;
    }
    
    
    @Column(name="tier_for_reported_trend")
    private String tier_for_reported_trend;
    /**
     * @return the tier_for_reported_trend
     */
    public String getTier_for_reported_trend() {
            return tier_for_reported_trend;
    }

    /**
     * @param tier_for_reported_trend the tier_for_reported_trend to set
     */
    public void setTier_for_reported_trend(String tier_for_reported_trend) {
            this.tier_for_reported_trend = tier_for_reported_trend;
    }
    
    
    @Column(name="tier_for_status")
    private String tier_for_status;
    /**
     * @return the Tier for status
     */
    public String getTier_for_status() {
            return tier_for_status;
    }

    /**
     * @param tier_for_status the tier_for_status to set
     */
    public void setTier_for_status(String tier_for_status) {
            this.tier_for_status = tier_for_status;
    }
    
}
