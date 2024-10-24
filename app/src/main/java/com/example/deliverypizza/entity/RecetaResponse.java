package com.example.deliverypizza.entity;

import java.util.List;

public class RecetaResponse {
    private List<Pizza> recipes;

    public List<Pizza> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Pizza> recipes) {
        this.recipes = recipes;
    }
}
