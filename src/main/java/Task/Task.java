package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Task.TaskTypes;

//deals with loading tasks from the file and saving tasks in the file.
public class Task {
    private String done;
    private String[] dataOfAction;
    private String finalAction = "";
    private final TaskTypes type;
    private boolean isDone = false;
    private LocalDate localDate;

    /**
     * Constructor for the Task.
     * @param action is what to be done.
     * @param done is the tick box before the task.
     * @param type is what type of task to be created.
     */
    public Task(String action, String done, TaskTypes type) {
        this.done = done;
        this.type = type;
        dataOfAction = action.split("/")[0].split(" ");
        for (int i = 1; i < dataOfAction.length; i++) {
            finalAction = finalAction.concat(dataOfAction[i] + " ");
        }
        if (action.split("/").length > 1) {
            String day = setDate(action);
            finalAction = finalAction.concat(day);
        }
    }

    /**
     * Create a tail made up of the deadline.
     * @param inputs String containing the date with some other information.
     * @return String containing only the date with information correctly organised.
     */
    private String setDate(String inputs) {
        String[] data = inputs.split(" ");
        int pos = 0;
        String keywords = "";
        if (type.equals(TaskTypes.Deadline)) {
            keywords = "by";
        } else if (type.equals(TaskTypes.Events)) {
            keywords = "at";
        }
        for (String str : data) {
            if (str.equals("/" + keywords)) {
                break;
            }
            pos += 1;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = data[pos + 1];
        this.localDate = LocalDate.parse(date, formatter);

        String output = " (" + keywords + ": ";
        for (int i = pos + 1 ;i < data.length; i++) {
            output = output.concat(" " + data[i]);
        }
        return output + " )";
    }
    /**
     * modify the task message if done.
     */
    public void done() {
        done = done.substring(0, 3) + "[X] ";
        this.isDone = true;
    }
    /**
     * Override the original toString method to generate the correct value.
     * @return String representation of the Task, which is presentable at the terminal.
     */
    public String toString() {
        return done + finalAction;
    }

    /////////////////getters////////////////////////

    /**
     * get Task Type
     * @return Task Type from the enum
     */
    public TaskTypes getType() {
        return type;
    }

    /**
     * get isDone of this Task.
     * @return boolean value of isDone.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Getter to return the finalAction String of the Task.
     * @return finalAction.
     */
    public String getFinalAction() {
        return finalAction;
    }
}

