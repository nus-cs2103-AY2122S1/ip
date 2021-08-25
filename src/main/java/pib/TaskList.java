package pib;

import pib.enums.TaskType;
import pib.tasks.Deadline;
import pib.tasks.Event;
import pib.tasks.Task;
import pib.tasks.Todo;
import pib.pibexception.PibException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void viewList() throws PibException {
        if (list.size() == 0) {
            throw new PibException("empty-list");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i).toString());
            }
        }
    }

    public void viewListSize() {
        System.out.println("There are " + list.size() + " task(s) in the list\n");
    }

    public void add(TaskType t, String taskDetails) throws PibException {
        Task newTask = null;
        switch (t) {
        case TODO:
            newTask = Todo.createTodo(taskDetails);
            break;
        case EVENT:
            newTask = Event.createEvent(taskDetails);
            break;
        case DEADLINE:
            newTask = Deadline.createDeadline(taskDetails);
            break;
        default:
            break;
        }
        if (newTask != null) {
            list.add(newTask);
            Storage.saveData(this, Pib.DATA_FILE_PATH);
            Ui.printListSize(this);
        }
    }

    public void addSavedData(Task t) {
        list.add(t);
    }

    public void delete(int taskNum) throws PibException {
        try {
            String taskDesc = list.get(taskNum - 1).getDescription();
            list.remove(taskNum - 1);
            Storage.saveData(this, Pib.DATA_FILE_PATH);
            Ui.printDeleteSuccess(taskDesc);
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("ioob-exception");
        }
    }

    public void markAsDone(int taskNum) throws PibException {
        try {
            list.get(taskNum - 1).markAsDone();
            Storage.saveData(this, Pib.DATA_FILE_PATH);
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("ioob-exception");
        }
    }

    public String convertListToSaveData() {
        StringBuilder data = new StringBuilder();
        for (Task t : list) {
            data.append(t.toDataString());
        }
        return data.toString();
    }
}
