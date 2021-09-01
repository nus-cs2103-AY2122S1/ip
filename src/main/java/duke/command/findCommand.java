package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.Storage;

import duke.Ui;

public class findCommand extends Command {
    private String command;

    public findCommand(String command) {
        super(command);
        this.command = command;
    }

    public String toString() {
        return "This is a find command";
    }
    
    private boolean isWordPresent(String[] furtherBreakdownIntoParts, String wordToFind) {
        for (int j = 0; j < furtherBreakdownIntoParts.length; j++) {
            if (wordToFind.equals(furtherBreakdownIntoParts[j].trim())) {
                return true;
            }
        }
        return false;
    }

    public void execute(TaskList taskList, Storage storage) {
        Ui.printMessage("Here are the matching tasks in your list:");
        String[] parts = this.command.split(" ", 2);
        String wordToFind = parts[1];
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            String[] partsBeforeSlash = task.toString().split("\\(", 2);
            String[] furtherBreakdownIntoParts = partsBeforeSlash[0].split(" ");
            if (isWordPresent(furtherBreakdownIntoParts, wordToFind)) {
                Ui.printMessage(count + ". " + task.toString());
                count++;
            }
        }
        
        if (count == 1) {
            Ui.printMessage("Sorry, there are no matching tasks in your list :/");
        }
    }
}
