package duke.task;

import duke.Items;

public class Undo {
    // event, deadline, todo: just delete the last task
    // done: mark as undone
    // delete: add task at same index.
    // find, list: no undo available.

    private Items items;

    public Undo(Items items) {
        this.items = items;
    }

    /**
     *
     * @param inputCommand actual command entered by user
     * @return
     */
    public String undoTask(String[] inputCommand) {
        // done 5
        // command[0] = done, command[1] = 5
        String typeOfTask = inputCommand[0];
        String output;
        switch (typeOfTask) {
        case "done":
            int taskIndex = Integer.parseInt(inputCommand[1]);
            String task = undoDone(taskIndex);
            output = "Alright. The following task has been marked as 'Not Done'"
                    + task;
            break;
        default:
            output = "Only undo has been implemented";
        }
        return output;
    }

    public String undoDone(int index) {
        return items.markUndone(index);
    }
}
