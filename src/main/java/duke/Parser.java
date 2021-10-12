package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class which converts user command to functions
 */
public class Parser {

    public Parser() {
    }

    /**
     * Parses input given by user into a Task
     *
     * @param type        of the task T, D or E
     * @param description description of task
     * @param dateTime    for D,E to track time
     * @return Task of the specific specifications
     */
    public static Task taskParse(String type, String description, String dateTime) {
        if (type.equalsIgnoreCase("T")) {
            return new Todo(description);
        } else if (type.equalsIgnoreCase("D")) {
            return new Deadline(description, dateTime);
        } else if (type.equalsIgnoreCase("E")) {
            return new Event(description, dateTime);
        } else {
            return null;
        }
    }

    /**
     * Parses input given by storage during initialization of duke
     *
     * @param storageData string passed in after reading from storage file
     * @return Arraylist of task to be passed into a task list during initialization of duke
     */
    public static ArrayList<Task> storageParse(String storageData) {
        ArrayList<Task> tasks = new ArrayList<>();
        if (storageData.equals("")) {
            return tasks;
        }
        for (String i : storageData.split("\n")) {
            Task newTask;
            String[] inputs = i.split("\\|");

            if (inputs.length < 4) {
                newTask = taskParse(inputs[1], inputs[2], null);
            } else {
                newTask = taskParse(inputs[1], inputs[2], inputs[3]);
            }

            if (inputs[0].equals("true")) {
                assert newTask != null;
                newTask.markAsDone();
            }
            tasks.add(newTask);
        }
        return tasks;
    }

    /**
     * Parses date related inputs and produce LocalDate object for duke
     *
     * @param dateString input to be parsed in
     * @return LocalDate object requested
     */
    public static LocalDate dateParse(String dateString) {
        LocalDate date;
        if (dateString.contains("/")) {
            String[] inputs = dateString.split("/");
            date = LocalDate.parse(inputs[2] + "-" + inputs[1] + "-" +
                    (Integer.parseInt(inputs[0]) < 10 ? "0" + inputs[0] :
                            inputs[0]));
        } else {
            date = LocalDate.parse(dateString);
        }
        return date;
    }
}
