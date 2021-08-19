package main.java;

public class ListCommand extends Command {

    protected String input;

    protected TaskList list;

    protected int index;

    ListCommand(String input, TaskList list, int index) {
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
                    + list[i].getTaskState()
                    + "\n";
        }

        return "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + tasks
                + "\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
    }
}
