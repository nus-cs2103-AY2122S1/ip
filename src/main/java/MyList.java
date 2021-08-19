import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class MyList {

    private final List<Task> items;
    HashSet<String> listedItems;

    MyList() {
        this.items = new ArrayList<Task>(100);
        this.listedItems = new HashSet<String>();
    }

    protected String addItem(String command) throws DukeException.InvalidCommandException, DukeException.InvalidTaskDescriptionException, DukeException.DuplicateTaskException {
        String[] commandTokens = generateTokens(command, " ");
        String taskName;
        Task task = null;
        if (commandTokens[0].equals("todo")) {
            taskName = command.substring(5).trim();
            if (this.listedItems.contains(taskName)) {
                throw new DukeException.DuplicateTaskException("Task already in list!");
            } else if (taskName.length() == 0) {
               throw new DukeException.InvalidTaskDescriptionException("Missing task description!");
            } else {
                task = new ToDo(taskName);
                this.listedItems.add(taskName);
                this.items.add(task);
            }
        } else if (commandTokens[0].equals("event") || commandTokens[0].equals("deadline")) { // either event ot deadline
            String details = command.substring(commandTokens[0].length() + 1).trim();
            String[] detailTokens = generateTokens(details, "/");
            if (detailTokens.length < 2) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: missing date/time!");
            } else if (detailTokens.length > 2) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: multiple dates/times!");
            } else if (this.listedItems.contains(detailTokens[0].trim())){ // item alr in list
                throw new DukeException.DuplicateTaskException("Task already in list!");
            } else { // valid
                taskName = detailTokens[0].trim();
                String dateTime = detailTokens[1].trim();
                if (commandTokens[0].trim().equals("event")) {
                    task = new Event(taskName, dateTime);
                } else { //deadline
                    task = new Deadline(taskName, dateTime);
                }
                this.listedItems.add(taskName);
                this.items.add(task);
            }
        } else { // invalid input
            throw new DukeException.InvalidCommandException("Invalid command!");
        }
        return String.format("New task added to list:\n%s", task.toString()); //TODO
    }

    private String[] generateTokens(String taskDetails, String delimiter) {
        return taskDetails.split(delimiter);
    }

    protected String markAsCompleted(String taskName) {
        //find the task to delete
        int i = 0;
        boolean found = false;
        while (i < this.items.size()) {
            if (this.items.get(i).getTaskName().equals(taskName)) {
                Task completedTask = this.items.get(i);
                if (completedTask.getIsCompleted()) {
                    return "Task is already completed!";
                }
                this.items.remove(i);
                this.items.add(i, completedTask.markAsCompleted());
                return "Task marked as completed:\n" + this.items.get(i).toString();
            } else {
                i++;
            }
        }
        return "task is not in list!!";
    }

    protected String getAllItems() {
        StringBuilder sb = new StringBuilder();
        if (this.items.size() == 0) {
            sb.append("There are no items in your list!");
            return sb.toString();
        }
        sb.append("Your list contains:\n");
        for (int i = 0; i < this.items.size(); i++) {
            String itemNum = Integer.toString(i + 1) + ". ";
            sb.append(itemNum);
            sb.append(this.items.get(i).toString());
            if (i < this.items.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
