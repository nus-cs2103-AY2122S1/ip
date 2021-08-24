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
        ArrayList<Task> found = lst.findItems(target);
        if (found.isEmpty()) {
            Ui.showInput("No task found!");
        } else {
            String temp = "The items found are: \n";
            for (int i = 0; i < found.size(); i++) {
                if (i + 1 < found.size()) {
                    temp += "     " + (i + 1) + "." + found.get(i).getType()
                            + found.get(i).getStatus() + " " + found.get(i).getTask() + "\n";
                } else {
                    temp += "     " + (i + 1) + "." + found.get(i).getType()
                            + found.get(i).getStatus() + " " + found.get(i).getTask();
                }
            }
            Ui.showInput(temp);
        }
    }
}
