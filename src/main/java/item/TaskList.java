package item;

import exception.BotException;
import exception.InvalidCommandException;
import exception.OutOfBoundException;

import java.util.*;

public class TaskList {
    public List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String add(Task item) {
        this.taskList.add(item);
        String front = "Got it meow! I've added this task:";
        String back = checkSize();
        return String.format("%s\n      %s\n    %s", front, item, back);
    }

    public String display() {
        if (taskList.size() == 0) {
            return "Meow currently no tasks!";
        }

        return String.format("Here are the tasks in your list:%s", this);
    }

    public String completeTask(int index) throws BotException {
        checkRange(index);
        Task t = taskList.get(index - 1);
        t.markAsDone();
        return String.format("Nice! I've marked this task as done:\n      %s", t);
    }

    public String deleteTask(int index) throws BotException {
        checkRange(index);
        Task temp = taskList.get(index - 1);
        taskList.remove(temp);
        return String.format(
                "Noted. I've removed this task:\n      %s\n      %s", temp, checkSize());
    }

    private void checkRange(int index) throws BotException {
        if (taskList.size() == 0) {
            throw new InvalidCommandException("There is no tasks currently! Try to add one!");
        }
        if (index < 1 || index > taskList.size()) {
            String expected = String.format("%d - %d", 1, taskList.size());
            String actual = String.format("%d", index);
            throw new OutOfBoundException(expected, actual);
        }
    }

    private String checkSize() {
        return String.format("Now you have %d tasks in the list.", taskList.size());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < taskList.size(); i++) {
            s.append("\n    ");
            s.append(String.format("%d. %s", i + 1, this.taskList.get(i)));
        }
        return s.toString();
    }
}
