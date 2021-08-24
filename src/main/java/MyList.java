import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class MyList {

    private final List<Task> tasks;
    HashSet<String> listedItems;

    MyList() {
        this.tasks = new ArrayList<Task>(100);
        this.listedItems = new HashSet<String>();
    }

    protected String addItem(String command) throws DukeException.InvalidCommandException, 
            DukeException.InvalidTaskDescriptionException, DukeException.DuplicateTaskException, DateTimeParseException {
        String[] commandTokens = generateTokens(command, " ");
        String taskName;
        Task task;
        if (commandTokens[0].equals("todo")) {
            taskName = command.substring(5).trim();
            if (this.listedItems.contains(taskName)) {
                throw new DukeException.DuplicateTaskException("Task already in list!");
            } else if (taskName.length() == 0) {
               throw new DukeException.InvalidTaskDescriptionException("Missing task description!");
            } else {
                task = new ToDo(taskName);
                this.listedItems.add(taskName);
                this.tasks.add(task);
            }
        } else if (commandTokens[0].equals("event") || commandTokens[0].equals("deadline")) { //either event ot deadline
            String details = command.substring(commandTokens[0].length() + 1).trim();
            String[] detailTokens = generateTokens(details, "/");
            if (detailTokens.length < 2) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: missing date/time!");
            } else if (detailTokens.length > 2) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: "
                        + "invalid date/time\nPlease use [command type] [task name] / [dd-mm-yyyy] [time (in 24hr " +
                        "format)" +
                        "]\ne.g. event lecture / 21-02-2021 1500");
            } else if (this.listedItems.contains(detailTokens[0].trim())){ // item already in list
                throw new DukeException.DuplicateTaskException("Task already in list!");
            } else { // valid
                taskName = detailTokens[0].trim();
                String[] dateTimeString = detailTokens[1].trim().split(" "); 
                LocalDate date = LocalDate.parse(dateTimeString[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalTime time = LocalTime.parse(dateTimeString[1], DateTimeFormatter.ofPattern("HHmm"));
                LocalDateTime dateTime = LocalDateTime.of(date, time);
                if (commandTokens[0].trim().equals("event")) {
                    task = new Event(taskName, dateTime);
                } else { //deadline
                    task = new Deadline(taskName, dateTime);
                }
                this.listedItems.add(taskName);
                this.tasks.add(task);
            }
        } else { // invalid input
            throw new DukeException.InvalidCommandException("Invalid command!");
        }
        return String.format("New task added to list:\n%s", task);
    }

    private String[] generateTokens(String taskDetails, String delimiter) {
        return taskDetails.split(delimiter);
    }

    protected String markAsCompleted(String taskName) {
        //find the task to delete
        int i = 0;
        while (i < this.tasks.size()) {
            if (this.tasks.get(i).getTaskName().equals(taskName)) {
                Task completedTask = this.tasks.get(i);
                if (completedTask.getIsCompleted()) {
                    return "Task is already completed!";
                }
                this.tasks.remove(i);
                this.tasks.add(i, completedTask.markAsCompleted());
                return "Task marked as completed:\n" + this.tasks.get(i).toString();
            } else {
                i++;
            }
        }
        return "task is not in list!!";
    }
    
    protected String deleteItem(String itemNum) throws DukeException.InvalidTaskNumException {
        int val = Integer.parseInt(itemNum.trim());
        if (val > this.tasks.size() || val < 1) {
            throw new DukeException.InvalidTaskNumException("Task number " + val + " does not exist!");
        } else {
            Task toRemove = this.tasks.get(val - 1);
            this.tasks.remove(val - 1);
            this.listedItems.remove(toRemove.getTaskName());
            return "Successfully deleted:\n" + toRemove;
        }
    }

    protected String getAllItems() {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.size() == 0) {
            sb.append("There are no items in your list!");
            return sb.toString();
        }
        sb.append("Your list contains:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            String itemNum = Integer.toString(i + 1) + ". ";
            sb.append(itemNum);
            sb.append(this.tasks.get(i).toString());
            if (i < this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
