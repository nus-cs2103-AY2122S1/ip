package duke.task;

import java.time.LocalDate;

/**
 * A class that represent deadline task
 */
public class Deadline extends Task{
    private String name;
    private boolean isDone;
    private String time;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }




    public Deadline(String name, boolean isDone, String time) {
        super();
        this.name = name;
        this.isDone = isDone;
        this.time = time;
    }

    public String getTime() {
        String[] s = this.time.split(" ");

        LocalDate date = LocalDate.parse(s[1]);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s[0] + " ");
        stringBuilder.append(date.getMonth() + " ");
        stringBuilder.append(date.getDayOfMonth()  + " ");
        stringBuilder.append(date.getYear()  + " ");
        for (int i = 2; i < s.length; i++) {
            stringBuilder.append(s[i] + " ");
        }

        return stringBuilder.toString();
    }

    public boolean searchKeyword(String keyword) {
        String[] s = name.split(" ");
        for (String ss : s) {
            if (ss.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "D" + " " +  name  +" "  +"/"+ time + " "+  isDone+ '\n';
    }


}