package com.anara.salon.Models;

import java.io.Serializable;

public class SubItemModel implements Serializable {
    String name;
    boolean isChecked = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
