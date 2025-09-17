package com.example.GamingApp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recharges")
public class Recharge {
    @Id
    private String id;
    private String memberId;
    private float amount;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }
}
