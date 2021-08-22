package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(List<String> initial) {
        Scanner scanner;
        for (int i = 0; i < initial.size(); i++) {
            scanner = new Scanner(initial.get(i));

            String task = scanner.next();

            String done = scanner.next();

            String details = scanner.next();

            switch (task) {
            case "T":
                taskList.add(new ToDo(details));
                break;
            case "D":

                String by =  scanner.next();

                if (by != null) {

                    String date = scanner.next();

                    int time = Integer.parseInt(scanner.next());

                    taskList.add(new Deadline(details, null, date, time));
                } else {
                    taskList.add(new Deadline(details, by, null, -1));
                }
                break;
            case "E":

                String at = scanner.next();

                if (at != null) {

                    String date = scanner.next();

                    int start = Integer.parseInt(scanner.next());
                    int end = Integer.parseInt(scanner.next());

                    taskList.add(new Event(details, null, date, start, end));
                } else {
                    taskList.add(new Event(details, at, null, -1, -1));
                }
                break;
            }
            if (Objects.equals(done, "done")) {
                taskList.get(taskList.size() - 1).complete();
            }
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public String getList() {
        if (this.taskList.isEmpty()) {
            return "There are no items in the task list.";
        }
        else {
            String list = "Tasks in task list:\n";
            for (int i = 0; i < this.taskList.size(); i++) {
                list += "\t\t" + (i + 1) + ". " + this.taskList.get(i).toString();
                if (i != this.taskList.size() - 1) {
                    list += "\n";
                }
            }
            return list;
        }
    }

    public void completeTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).complete();
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        this.taskList.remove(index);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        return this.taskList.get(index);
    }

    public int getSize() {
        return this.taskList.size();
    }
}
