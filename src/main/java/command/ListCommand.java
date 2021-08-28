package main.java.command;

import main.java.bot.TaskList;
import main.java.bot.UserInterface;

public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    public void execute(TaskList list, UserInterface ui) {

        String tasks = "";
        for (int i = 0; i < list.getSize(); i++) {
            tasks = tasks
                    + String.valueOf(i + 1)
                    + ". "
                    + list.getTask(i).getTaskState()
                    + "\n";
        }

        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + tasks
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
    }
}
