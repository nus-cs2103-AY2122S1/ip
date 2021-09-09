package duke;

/**
 * Command to find a task from existing list of tasks with a given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        String returnMessage = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task taskInstance = tasks.get(i);
            String taskDescription = taskInstance.getDescription();
            if (taskDescription.contains(keyword)) {
                returnMessage = returnMessage + taskInstance + "\n";
            }
        }
        if (returnMessage.equals("")) {
            return ui.printNoKeyword(keyword);
        }
        return ui.printFoundKeyword(returnMessage);
        
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
