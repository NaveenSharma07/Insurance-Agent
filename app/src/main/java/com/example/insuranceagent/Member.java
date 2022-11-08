package com.example.insuranceagent;

public class Member {
    private String Category;
    public Member(String Category){

        this.Category = Category;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }
}
