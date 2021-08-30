package duke;

/**
 * 
 * Command to find a task from existing list of tasks with a given keyword.
 */
public class FindCommand extends Command{

    private String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        String returnMessage = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task taskInstance = tasks.get(i);
            String taskDescription = taskInstance.getDescription();
            if (taskDescription.contains(keyword)) {
                returnMessage = returnMessage + taskInstance.toString() + "\n";
            }
        }
        if (returnMessage.equals("")) {
            System.out.println("There were no tasks found with keyword: " + keyword);
        } else {
            System.out.println("Here are the tasks in your list that match the keyword:\n" + returnMessage);
        }
    }
}
