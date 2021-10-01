package duke.message;

import duke.task.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import duke.task.Task;

/**
 * Represents Duke' response when the user searches for a keyword.
 */
public class FindTasksMessage extends DukeMessage {
    private String searchStr;
    private ArrayList<Task> searchResults = new ArrayList<>();

    public FindTasksMessage(String searchStr) {
        this.searchStr = searchStr;
    }

    /**
     * Stores a list of tasks that match the given key-word or date
     * in searchResults.
     */
    private void findTasks() {
        ArrayList<Task> taskList =
                (ArrayList<Task>) TaskList.getTaskList().getTasks();

        if (searchStr.matches("\\d{4}-\\d{2}-\\d{2}")) {    //regex for date
            String dateQuery =
                    LocalDate.parse(searchStr).format(DateTimeFormatter
                            .ofPattern("MMM dd yyyy"));
            int count = 1;
            for (Task task : taskList) {
                String taskStr = task.getTaskString();
                if (taskStr.contains(dateQuery)) {
                    this.searchResults.add(task);
                    task.setRefId(count);
                    count = count + 1;
                }
            }
        } else {
            int count = 1;
            for (Task task : taskList) {
                String taskStr = task.getTaskString();
                if (taskStr.contains(searchStr)) {
                    this.searchResults.add(task);
                    task.setRefId(count);
                    count = count + 1;
                }
            }
        }
    }

    @Override
    public String createMessageString() {
        findTasks();
        if(searchResults.isEmpty()) {
            return "Bhai sahi se toh lik raha h na? (No matching " +
                    "tasks!)";
        }
        int count = 1;
        String reply = "Here are the matching tasks in your list:\n";
        for(Task task : searchResults) {
            System.out.println(count + "." + task.getTaskString());
            reply = reply + count + "." + task.getTaskString() + "\n";
            count = count + 1;
        }
        return reply;
    }
}




