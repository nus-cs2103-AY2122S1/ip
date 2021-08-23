package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private Duke duke;

    public TaskList(Duke duke) {
        this.list = new ArrayList<>();
        this.duke = duke;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public String printList() {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            listString = listString + index + "." + list.get(i).toString() + "\n";
        }
        return listString;
    }

    public void createTask(String description, String time, Task.Category category, boolean isDone, boolean hasNotif) {
        switch (category) {
        case TODO:
            list.add(new ToDo(description, isDone, hasNotif));
            break;
        case DEADLINE:
            list.add(new Deadline(description, time, isDone, hasNotif));
            break;
        case EVENT:
            list.add(new Event(description, time, isDone, hasNotif));
            break;
        }
        if (hasNotif) {
            duke.getUi().showAddTask();
            try {
                duke.getStorage().saveListToFile();
            } catch (IOException e) {
                duke.getUi().showLoadingError();
            }
        }
    }

    public void createTaskDate(String description, LocalDate time, Task.Category category, boolean isDone, boolean hasNotif) {
        switch (category) {
        case DEADLINE:
            list.add(new Deadline(description, time, isDone, hasNotif));
            break;
        case EVENT:
            list.add(new Event(description, time, isDone, hasNotif));
            break;
        }
        if (hasNotif) {
            duke.getUi().showAddTask();
            try {
                duke.getStorage().saveListToFile();
            } catch (IOException e) {
                duke.getUi().showLoadingError();
            }
        }
    }
}
