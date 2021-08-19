package main.java;
import java.util.ArrayList;

public class ListCommand extends Command {

    protected String input;

    protected ArrayList<Task> list;

    protected int index;

    ListCommand(String input, int index, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
        this.index = index;
    }

    public String reply() {

        String tasks = "";
        for (int i = 0; i < index; i++) {
            tasks = tasks
                    + String.valueOf(i + 1)
                    + ". "
                    + list.get(i).getTaskState()
                    + "\n";
        }

        return "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + tasks
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
    }
}
