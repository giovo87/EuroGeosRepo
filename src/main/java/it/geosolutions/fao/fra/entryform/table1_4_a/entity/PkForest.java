package it.geosolutions.fao.fra.entryform.table1_4_a.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
@Embeddable
public class PkForest implements Serializable{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String userId;
    private int year;
    public PkForest(){}
    public PkForest(String userId, int year) {
        this.userId = userId;
        this.year = year;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}