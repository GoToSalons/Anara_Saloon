package com.anara.salon.Models;

import java.util.ArrayList;

public class MainItemModel {
    String ItemName;
    ArrayList<SubItemModel> subItems;

    public MainItemModel(String itemName, ArrayList<SubItemModel> subItems) {
        ItemName = itemName;
        this.subItems = subItems;
    }

    public String getItemName() {
        return ItemName;
    }

    public ArrayList<SubItemModel> getSubItems() {
        return subItems;
    }
}
