package duke;

import java.util.ArrayList;

public class Schedule {

    public ArrayList<Task> sortTask(ArrayList<Task> input, String date) {
        ArrayList<Task> output = new ArrayList<>();

        for (Task task: input) {
            if (task instanceof Deadline) {


                Deadline deadline = (Deadline) task;
                if (deadline.isDate(date)) {
                    output.add(task);
                }
            } else if (task instanceof Event) {

                Event event = (Event) task;
                if (event.isDate(date)) {
                    output.add(task);
                }
            }
        }

        output.sort(new TimeComparator());
        return output;
    }
}
