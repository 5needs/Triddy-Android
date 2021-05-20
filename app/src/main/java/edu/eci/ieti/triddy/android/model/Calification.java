package edu.eci.ieti.triddy.android.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "Calification", primaryKeys = {"qualifier", "product"})
public class Calification {
    @NonNull
    private String qualifier;
    @NonNull
    private String product;
    private Double productStatus;
    private Double productCharacteristics;
    private String productComment;
    private String user;
    private Double userCalification;
    private String userComment;

    public Calification(String qualifier, String product, Double productStatus, Double productCharacteristics,
                        String productComment, String user, Double userCalification, String userComment){

        this.product = product;
        this.qualifier = qualifier;
        this.productStatus = productStatus;
        this.productCharacteristics = productCharacteristics;
        this.productComment = productComment;
        this.user = user;
        this.userCalification = userCalification;
        this.userComment = userComment;
    }

    public Calification(){}

    public String getQualifier(){
        return this.qualifier;
    }

    public void setQualifier(String qualifier){
        this.qualifier = qualifier;
    }

    public String getProduct(){
        return this.product;
    }

    public void setProduct(String product){
        this.product = product;
    }

    public Double getProductStatus() {
        return this.productStatus;
    }

    public void setProductStatus(Double productStatus) {
        this.productStatus = productStatus;
    }

    public Double getProductCharacteristics() {
        return this.productCharacteristics;
    }

    public void setProductCharacteristics(Double productCharacteristics) {
        this.productCharacteristics = productCharacteristics;
    }

    public String getProductComment() {
        return this.productComment;
    }

    public void setProductComment(String productComment) {
        this.productComment = productComment;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Double getUserCalification() {
        return this.userCalification;
    }

    public void setUserCalification(Double userCalification) {
        this.userCalification = userCalification;
    }

    public String getUserComment() {
        return this.userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    @Override
    public String toString(){
        return String.format("Calification[ qualifier= '%s', product= '%s', productStatus= '%s', productCharacteristics= '%s', productComment= '%s', user= '%s', userCalification= '%s', userComment= '%s' ]"
                ,this.qualifier,this.product,this.productStatus,this.productCharacteristics, this.productComment, this.user, this.userCalification, this.userComment);
    }
}
