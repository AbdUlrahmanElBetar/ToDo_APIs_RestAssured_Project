package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToDo {

        @JsonProperty("isCompleted")
        private Boolean isCompleted;
        @JsonProperty("_id")
        private String id;
        private String item;
        private String userID;
        private String createdAt;
         @JsonProperty("__v")
        private String v;

         public ToDo(Boolean isCompleted,String item){
             this.isCompleted = isCompleted;
             this.item = item;
         }
    public ToDo(String item){
        this.item = item;
    }

    public ToDo(){

    }
    @JsonProperty("__v")
    public String getV() {
        return v;
    }

    @JsonProperty("__v")
    public void setV(String v) {
        this.v = v;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }
    @JsonProperty("isCompleted")
    public void setCompleted(Boolean completed) {
        this.isCompleted = completed;
    }





}
