package com.anara.salon.Models;

public class SubItemModel {
    String Name;
    boolean isChecked = false;

    public SubItemModel(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
