package main.java;
import java.util.ArrayList;

public class ListCommand extends Command {

    protected String input;

    protected ArrayList<Task> list;

    ListCommand(String input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

    public String reply() {

        String tasks = "";
        for (int i = 0; i < list.size(); i++) {
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
