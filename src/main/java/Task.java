package main.java;

public class Task {
    public String type;
    public String date_time;
    public String message;


    public Task (String message) {
        this.message = message;
        }

    public String get_type() {
        return this.type;
    }

    public void set_type() {
        this.type = "";
    }

    public void set_task() {
    }

    public String get_task() {
        return "";
    }

    public String getType() {
        return type;
    }

    public void set_date_time() {
        this.date_time = "";
    }

    public String getDate_time() {
        return date_time;
    }

}

