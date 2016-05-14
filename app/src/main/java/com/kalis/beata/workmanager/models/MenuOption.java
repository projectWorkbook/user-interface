package com.kalis.beata.workmanager.models;

import java.io.Serializable;

/**
 * Created by Beata on 2016-05-13.
 */
public class MenuOption implements Serializable {
    private String name;
    private String startDate;
    private String endDate;

    public MenuOption() {

    }
    public MenuOption(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
