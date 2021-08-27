package duke.data;

import duke.data.task.*;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskHandler {
    private ArrayList<Task> list;

    public TaskHandler(ArrayList<Task> list) {
        this.list = list;
    }

    public void printList() {
        if (list.size() != 0) {
            Ui.printList();
            for (int i = 0; i < list.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + list.get(i).toString());
            }
        } else {
            Ui.printEmptyList();
        }
    }

    public void printNoOfTasks() {
        Ui.printNoOfTasks(list.size());
    }

    public void markTaskAsDone(int taskNo) {
        Ui.markAsDone();
        Task task = list.get(taskNo - 1);
        task.markAsDone();
        System.out.println(Ui.indentation() + task);
    }

    public void deleteTask(int taskNo) {
        Ui.deleteTask();
        Task task = list.get(taskNo - 1);
        list.remove(taskNo - 1);
        System.out.println(Ui.indentation() + task);
    }

    public void addToDo(ToDo todo) {
        list.add(todo);
        Ui.addTask();
        System.out.println(todo);
    }

    public void addDeadline(Deadline deadline) {
        list.add(deadline);
        Ui.addTask();
        System.out.println(deadline);
    }

    public void addEvent(Event event) {
        list.add(event);
        Ui.addTask();
        System.out.println(event);
    }

    public String formatTaskToSave() {
        String[] tasksToSave = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tasksToSave[i] = list.get(i).toString();
        }
        return String.join("\n", tasksToSave);
    }

}
