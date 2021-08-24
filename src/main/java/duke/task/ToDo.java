package duke.task;

import duke.io.TextColor;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public static ToDo load(String[] loadDatas) {
        boolean isDone = loadDatas[1].equals("o");
        String name = loadDatas[2];

        ToDo todo = new ToDo(name);
        if (isDone) {
            todo.doTask();
        }

        return todo;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public TextColor getListColor() {
        return TextColor.DEFAULT;
    }

    // prints in red if the deadline is expired, yellow if deadline is today
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getSaveString() {
        return "t," + super.getSaveString();
    }
}
