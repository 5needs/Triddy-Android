package edu.eci.ieti.triddy.android.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "Rent")
public class Rent {
    @NonNull
    @PrimaryKey
    private String id;
    private String userOwner;
    private String userLessee;
    private String productId;
    @TypeConverters(DateConverter.class)
    private Date initialDate;
    @TypeConverters(DateConverter.class)
    private Date finalDate;
    private String status;
    public Rent(String id, String productId, String userOwner, String userLessee  ,Date initialDate, Date finalDate, String status) {
        this.id = id;
        this.productId = productId;
        this.userOwner = userOwner;
        this.userLessee = userLessee;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.status = status;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getInitialDate() {
        return initialDate;
    }
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }
    public Date getFinalDate() {
        return finalDate;
    }
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUserOwner() {
        return userOwner;
    }
    public void setUserOwner(String userOwner) {
        this.userOwner = userOwner;
    }
    public String getUserLessee() {
        return userLessee;
    }
    public void setUserLessee(String userLessee) {
        this.userLessee = userLessee;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
}
