public class Deadline extends Task{
    public String type;
    public String date_time;
    public String task = "";


    public Deadline (String message) {
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
        this.type = "D";
    }

    @Override
    public void set_task() {
        int start_index = 0;
        int end_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("e")) {
                start_index = i + 8;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("/")) {
                end_index = i - 1;
                break;
            }
        }
        this.task = message.substring(start_index,end_index) + this.date_time;
    }

    @Override
    public String get_task() {
        return this.task;
    }

    @Override
    public void set_date_time() {
        int start_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("/")) {
                start_index = i + 4;
                break;
            }
        }
        this.date_time = " (by: " + message.substring(start_index) + ")";
    }

    public String get_date_time() {
        return this.date_time;
    }

}
