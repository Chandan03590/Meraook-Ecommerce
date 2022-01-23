package com.meraook.Model;


public class CategoryItemModel {

    private String categoryIcon;
    private String categoryName;


    public CategoryItemModel(String categoryIcon, String categoryName) {
        this.categoryIcon = categoryIcon;
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
