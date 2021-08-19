package main.java;

public class Todo extends Task{
    public String type;
    public String date_time;
    public String task;


    public Todo (String message) {
        super(message);
        this.set_date_time();
        this.set_task();
        this.set_type();
    }

    @Override
    public String get_type() {
        return this.type;
    }

    @Override
    public void set_type() {
        this.type = "T";
    }

    @Override
    public void set_task() {
        int start_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("o")) {
                start_index = i + 4;
                break;
            }
        }
        this.task = message.substring(start_index,this.message.length());
    }

    @Override
    public String get_task() {
        return this.task;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void set_date_time() {
        this.date_time = "";
    }

    @Override
    public String getDate_time() {
        return date_time;
    }
}
