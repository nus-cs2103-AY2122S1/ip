package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    public FindCommand(ArrayList<String> s) {
        super(s);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) {
        String target = "";
        for (int i = 1; i < getInput().size(); i++) {
            if (i + 1 < getInput().size()) {
                target += getInput().get(i) + " ";
            } else {
                target += getInput().get(i);
            }
        }
        ArrayList<Task> tasksFound = lst.findTask(target);
        if (tasksFound.isEmpty()) {
            Ui.showInput("No task tasksFound!");
        } else {
            String temp = "The items tasksFound are: \n";
            for (int i = 0; i < tasksFound.size(); i++) {
                if (i + 1 < tasksFound.size()) {
                    temp += "     " + (i + 1) + "." + tasksFound.get(i).getType()
                            + tasksFound.get(i).getStatus() + " " + tasksFound.get(i).getTask() + "\n";
                } else {
                    temp += "     " + (i + 1) + "." + tasksFound.get(i).getType()
                            + tasksFound.get(i).getStatus() + " " + tasksFound.get(i).getTask();
                }
            }
            Ui.showInput(temp);
        }
    }
}
