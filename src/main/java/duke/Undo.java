package duke;

public class Undo {
    private TaskList items;

    public Undo(TaskList items) {
        this.items = items;
    }

    public String undo() throws DukeException {
        String output;
        if (Duke.state.size() == 0) {
            throw new DukeException("No more tasks left to undo");
        } else {
            Duke.state.pop();
            output = "Got it. I have undone the task.";
            output += items.printList();
            return output;
        }
    }

    /**
     * Undoes the last command
     *
     * @param command the command to be undone.
     * @return output after undoing the latest task.
     */
    /*
    private String undoLogic(String command) throws DukeException {
        String output;
        String fileTask;
        System.out.println("Reached undoLogic. Command: " + command);
        String[] undoCommand = command.split("\\s+");
        String inputCommand = undoCommand[0];
        System.out.println("input command is: " + inputCommand);
        switch (inputCommand) {
        case "delete":
            String deletedTask = DukeConstants.deleteTask;
            int index = Integer.parseInt(undoCommand[1]);
            output = undoDelete(index, deletedTask);
            break;
        case "done":
            int taskIndex = Integer.parseInt(undoCommand[1]);
            fileTask = undoDone(taskIndex);
            output = "The following task has been marked as 'Not Done':\n" + fileTask;
            break;
        case "deadline":
        case "event":
        case "todo":
            System.out.println("calling delete task");
            fileTask = deleteTask();
            output = "Following task has been removed:\n" + fileTask;
            break;
        default:
            output = "Only undo has been implemented";
        }
        DukeConstants.isUndoable = false;
        return output;
    }


    public String undoDelete(int index, String task) throws DukeException {
        String[] parseTask = task.split(" \\| ");
        String taskType = parseTask[0];
        String output = "";
        switch (taskType) {
        case "D":
            Deadline deadline = new Deadline(parseTask[2], parseTask[3]);
            output = items.addDeletedTask(index, deadline);
            break;
        case "E":
            Event event = new Event(parseTask[2], parseTask[3]);
            output = items.addDeletedTask(index, event);
            break;
        case "T":
            Todo todo = new Todo(parseTask[2]);
            output = items.addDeletedTask(index, todo);
            break;
        }
        if (parseTask[1].equals("1")) {
            items.markDone(index);
        }
        output += "\n" + task;
        return output;
    }

    public String undoDone(int index) throws DukeException {
        return items.markUndone(index);
    }

    public String deleteTask() throws DukeException {
        return items.deleteLatestTask();
    }
     */
}
