package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Optional;

public class DukeTaskList {
    private final ArrayList<Task> tasklist;

    public DukeTaskList() {
        this.tasklist = new ArrayList<>();
    }

    public DukeTaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public Optional<Task> addTask(Task toAdd) {
        this.tasklist.add(toAdd);
        return Optional.of(toAdd);
    }

    public Optional<Task> setDone(int toComplete) {

        if (toComplete >= this.tasklist.size() || toComplete < 0) {
            return Optional.empty();
        } else {
            Task completed = tasklist.get(toComplete);
            completed.markAsDone();
            return Optional.of(completed);
        }
    }

    public Optional<Task> toRemove(int indexToRemove) {
        if (indexToRemove >= this.tasklist.size() || indexToRemove < 0) {
            return Optional.empty();
        } else {
            Task toRemove = tasklist.get(indexToRemove);
            tasklist.remove(indexToRemove);
            return Optional.of(toRemove);
        }
    }

    public int getSize() {
        return this.tasklist.size();
    }

    public ArrayList<Task> getList() {
        return this.tasklist;
    }

    public String printList() {

        StringBuilder output =
                new StringBuilder("Here are the tasks in your list:\n");
        for (Task i : tasklist) {
            output.append(String.format("%d. %s\n",
                    tasklist.indexOf(i) + 1,
                    i));
        }

        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
