package duke;

import java.util.ArrayList;

/**
 * The class for creating Duke' response when the user searches for a keyword.
 */
public class FindMessage extends DukeMessage {
    private String searchStr;

    public FindMessage(String searchStr) {
        this.searchStr = searchStr;
    }

    public void display() {
        ArrayList<Task> taskList = (ArrayList<Task>) TaskList.getTaskList().getTasks();
        ArrayList<Task> searchResults = new ArrayList<>();
        for(Task task: taskList) {
            String taskStr = task.getTaskString();
            if(taskStr.contains(searchStr)) {
                searchResults.add(task);
            }
        }
        int count = 1;
        System.out.println("Here are the matching tasks in your list:\n");
        for(Task task : searchResults) {
            System.out.println(count + "." + task.getTaskString());
            count = count + 1;
        }
    }
}




