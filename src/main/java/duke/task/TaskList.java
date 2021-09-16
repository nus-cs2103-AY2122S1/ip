package duke.task;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public final class TaskList {
    private final ArrayList<Task> TASKS;
    private final Storage STORAGE_FILE;

    /**
     * Factory method for TaskList.
     * Parses in Files containing Strings (line) of duke.task representation.
     *
     * @param  storageFile of Storage class which contains initialized storage file
     * @return TaskList of tasks that was passed in through storageFile
     */
    public static TaskList of(Storage storageFile) {
        File tasksStorageFile = storageFile.getFile();

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(tasksStorageFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                //can't use polymorphism because parseLine has to be static method
                Task task = Task.parseLine(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new TaskList(tasks, storageFile);
    }

    /**
     * Constructor for TaskList class
     * @param tasks ArrayList of Tasks
     * @param storageFile Storage object that abstracts out where storage file is
     */
    private TaskList(ArrayList<Task> tasks, Storage storageFile) {
        this.STORAGE_FILE = storageFile;
        this.TASKS = tasks;
    }

    public void addTask(Task task) {
        this.TASKS.add(task);
        this.updateStore();
    }

    public int length() {
        return TASKS.size();
    }

    public String executeDoneCommand(int idx) {
        if (!isValidIndex(idx, TASKS.size())) {
            throw DukeException.of(
                    "user index: " + idx + "\n",
                    "task index passed in out of range");
        }

        this.toggleDoneAndUpdateStore(idx);
        String reply = "Nice! I've marked this task as done:\n" +
                Ui.INDENT + TASKS.get(idx).toString();
        return reply;
    }

    /**
     * Toggles a certain task as done and updates the storage file
     * @param idx index of task that is to be toggled
     */
    private void toggleDoneAndUpdateStore(int idx) {
        assert isValidIndex(idx, TASKS.size()) : "has to be valid index to toggle done";
        TASKS.get(idx).markCompleted();
        this.updateStore();
    }

    /**
     * Deletes a certain task as done and updates the storage file
     * @param idx index of task that is to be deleted
     */
    public String executeDeleteCommand(int idx) {
        if (!isValidIndex(idx, TASKS.size())) {
            throw DukeException.of(
                    "user index: " + idx + "\n",
                    "task index passed in out of range");
        }
        String reply = String.format(
                "Noted. I've removed this task:\n    %s\nNow you have %d tasks in the list.",
                TASKS.get(idx).toString(),
                TASKS.size() - 1
        );
        this.removeTaskAndUpdateStore(idx);
        return reply;
    }

     private void removeTaskAndUpdateStore(int idx) {
        assert isValidIndex(idx, TASKS.size()) : "has to be valid index to remove task";
        TASKS.remove(idx);
        this.updateStore();
    }

    /**
     * @return List of tasks of which task description contains the passed in keyword
     */
    public String executeFindCommand(String keyword) {
        Integer[] indexesFrom0 = this.filter(task -> task.isInTaskSummary(keyword));
        String tasksAsString = this.selectTasksAsString(indexesFrom0);
        String reply;
        if (tasksAsString.equals("")) {
            reply = String.format("No tasks matched the keyword: '%s'", keyword);
        } else {
            reply = "Here are the matching tasks in your list:\n" + tasksAsString;
        }
        return reply;
    }

    private void updateStore() {
        this.STORAGE_FILE.write(this.TASKS);
    }

    /**
     * Helper function which checks if index is valid given a list length
     * @param idxFrom0 index which is being checked if valid
     * @param numOfTasks length of list
     * @return
     */
    public static boolean isValidIndex(int idxFrom0, int numOfTasks) {
        if (idxFrom0 < 0 || idxFrom0 >= numOfTasks) {
            return false;
        }
        return true;
    }


    /**
     * Filters the TaskList and returns all indexes of task which fulfil condition.
     * Frequent use with selectedTasks() to print tasks.
     *
     * @param predicate Function condition which must be fulfilled by task for its index to be returned
     * @return Array of integers (idx from 0) of tasks which fulfil predicate
     */
    public Integer[] filter(Predicate<Task> predicate) {
        List<Integer> result = new ArrayList<>();
        int numOfTasks = this.TASKS.size();
        for (int i = 0; i < numOfTasks; i++) {
            Task currTask = this.TASKS.get(i);
            if (predicate.test(currTask)) {
                result.add(i);
            }
        }
        Integer[] res = result.toArray(new Integer[0]);
        return res;
    }

    //internal use only, hence assert statement
    private String selectTasksAsString(Integer[] indexesFrom0) {
        for (Integer i : indexesFrom0) {
            assert isValidIndex(i, TASKS.size());
        }

        return Arrays.stream(indexesFrom0)
                .map(i -> (i+1) + ". " + TASKS.get(i).toString() + "\n")
                .reduce("",(a,b) -> (a + b));
    }

    @Override
    public String toString() {
        int numOfTasks = this.TASKS.size();
        return IntStream.range(0,numOfTasks)
                .mapToObj(i -> (i+1) + ". " + TASKS.get(i).toString() + "\n")
                .reduce("",(a,b) -> (a + b));
    }
}
