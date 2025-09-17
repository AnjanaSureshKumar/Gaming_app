package com.example.GamingApp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "collections")
public class CollectionSummary {
    @Id
    private String id;
    private Date date;
    private float totalRecharges;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public float getTotalRecharges() { return totalRecharges; }
    public void setTotalRecharges(float totalRecharges) { this.totalRecharges = totalRecharges; }
}
