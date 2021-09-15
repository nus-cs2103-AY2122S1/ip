package duke;

import javafx.application.Platform;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        Timer timer = new Timer();
        System.out.println("hello");
        TimerTask task = new TimerTask(){
            public void run(){
                System.out.println("complete todo");
                Platform.runLater(() -> {

                });
            }
        };

        Calendar date = Calendar.getInstance();
        System.out.println("hello");
        date.set(Calendar.YEAR,2021);
        date.set(Calendar.MONTH,Calendar.SEPTEMBER);
        date.set(Calendar.DAY_OF_MONTH,15);
        date.set(Calendar.HOUR_OF_DAY,14);
        date.set(Calendar.MINUTE,7);
        date.set(Calendar.SECOND,20);
        date.set(Calendar.MILLISECOND,0);
        timer.schedule(task, date.getTime());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getIcon() {
        return "T";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;
            return toDo.toString().equals(this.toString());
        } else {
            return false;
        }
    }
}
