package duke.task;

/**
 * Class representing a ToDo task that can be marked as done.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo, setting its name, and marking it not done.
     *
     * @param name Name of the ToDo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Loads a ToDo from data parsed from the save file.
     *
     * @param loadDatas A line from the csv, split by commas.
     * @return ToDo created from provided data.
     */
    public static ToDo load(String[] loadDatas) {
        boolean isDone = loadDatas[1].equals("o");
        String name = loadDatas[2];

        ToDo todo = new ToDo(name);
        if (isDone) {
            todo.doTask();
        }

        return todo;
    }

    /**
     * Returns a string representation of the ToDo and its status.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns a string representing the ToDo compliant to the saveFile format.
     *
     * @return String to be saved as a line in save.csv.
     */
    @Override
    public String getSaveString() {
        return "t," + super.getSaveString();
    }
}
