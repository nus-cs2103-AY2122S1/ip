import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    public String type;
    public String task = "";
    public LocalDate date_time;
    public String date_time2;


    public Deadline (String message) {
        super(message);
        this.set_date_time();
        this.set_task();
        this.set_type();
    }

    public Deadline (String message, boolean b) {
        super(message);
        this.set_date_time2();
        this.set_task2();
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
    public void set_task2() {
        int start_index = 0;
        int end_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("d")) {
                start_index = i + 9;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("(")) {
                end_index = i - 1;
                break;
            }
        }
        this.task = message.substring(start_index,end_index) + " " + this.get_date_time2();
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
        String date_timing = message.substring(start_index);
        this.date_time = LocalDate.parse(date_timing);
    }

    public void set_date_time2() {
        int length = this.message.length();
        this.date_time2 = "(" + message.substring(length - 15);
    }

    public String get_date_time() {
        return this.date_time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String get_date_time2() {
        return this.date_time2;
    }

}
