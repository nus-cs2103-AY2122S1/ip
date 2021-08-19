package main.java;

public class ListCommand extends Command {

    protected String input;

    protected Task[] list;

    protected int index;

    ListCommand(String input, int index, Task[] list) {
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
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
    }
}
