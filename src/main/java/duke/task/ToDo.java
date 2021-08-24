package duke.task;

import duke.io.TextColor;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * Loads a ToDo from data parsed from the save file
     *
     * @param loadData A line from the csv, split by commas
     * @return ToDo created from provided data
     */
    public static ToDo load(String[] loadData) {
        boolean done = loadData[1].equals("o");
        String name = loadData[2];

        ToDo todo = new ToDo(name);
        if (done) {
            todo.doTask();
        }

        return todo;
    }

    /**
     * Returns a string representation of the ToDo and its status
     *
     * @return string representation of the ToDo
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns a string representing the ToDo compliant to the saveFile format
     *
     * @return String to be saved as a line in save.csv
     */
    @Override
    public String getSaveString() {
        return "t," + super.getSaveString();
    }
}
