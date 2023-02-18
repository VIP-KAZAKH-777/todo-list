package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task{
    private int id;
    private String description;
    private Date date;
    private boolean status;

    public Task() {

    }

    public Task(String description, Date date, boolean status) {
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDate() {
        return date.getTime();
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getStatus() {
        return status? "Done":"Not Done";
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public String getFormattedDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    @Override
    public String toString() {
        return "|ID: " + id + " || task: " + description + " || last modified: " + getFormattedDate(date) + " || status: " + getStatus() + " |";
    }
}