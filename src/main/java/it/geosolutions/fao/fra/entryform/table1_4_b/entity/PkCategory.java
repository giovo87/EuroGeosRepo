package it.geosolutions.fao.fra.entryform.table1_4_b.entity;


import java.io.Serializable;
import javax.persistence.Embeddable;
@Embeddable
public class PkCategory implements Serializable{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String userId;
    private String category;
    public PkCategory(){}
    public PkCategory(String userId, String category) {
        this.userId = userId;
        this.category = category;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCategory() {
        return category;
    }
    public void setYear(String category) {
        this.category = category;
    }
}