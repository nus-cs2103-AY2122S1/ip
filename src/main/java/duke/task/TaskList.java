package duke.task;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public final class TaskList {
    private final ArrayList<Task> TASKS;
    private final Storage STORAGE_FILE;
    public static final String LIST_COMMAND_REGEX = "list";
    public static final String BYE_COMMAND_REGEX = "bye";
    public static final String DONE_COMMAND_REGEX = "done [0-9]{1,2}"; //done <num> //limits to 99
    public static final String DELETE_COMMAND_REGEX = "delete [0-9]{1,2}"; //delete <num
    public static final String FIND_COMMAND_REGEX = "find \\w+"; //find <word>
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

    private TaskList(ArrayList<Task> tasks, Storage storageFile) {
        this.STORAGE_FILE = storageFile;
        this.TASKS = tasks;
    }

    public void addTask(Task task) {
        this.TASKS.add(task);
        this.updateStore();
    }

    public Task get(int idx) {
        return this.TASKS.get(idx);
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

    //helper function
    private void toggleDoneAndUpdateStore(int idx) {
        assert isValidIndex(idx, TASKS.size()) : "has to be valid index to toggle done";
        TASKS.get(idx).markCompleted();
        this.updateStore();
    }

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

    //helper function
     private void removeTaskAndUpdateStore(int idx) {
        assert isValidIndex(idx, TASKS.size()) : "has to be valid index to remove task";
        TASKS.remove(idx);
        this.updateStore();
    }

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
    public String selectTasksAsString(Integer[] indexesFrom0) {
        StringBuilder result = new StringBuilder();
        int numOfTasks = this.TASKS.size();
        for (Integer i : indexesFrom0) {
            assert true;
            result.append(String.format("%d: %s\n", i + 1, this.TASKS.get(i).toString()));

        }
//        for (int i = 0; i < numOfTasks; i++) {
//            List<Integer> listOfIndexesFrom0 = Arrays.asList(indexesFrom0);
//            if (listOfIndexesFrom0.contains(i)) {
//                result.append(String.format("%d: %s\n", i + 1, this.TASKS.get(i).toString()));
//            }
//        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int numOfTasks = this.TASKS.size();
        for (int i = 0; i < numOfTasks; i++) {
            int idxFrom1 = i + 1;
            result.append(String.format("%d: %s\n", idxFrom1, this.TASKS.get(i).toString()));
        }
        return result.toString();
    }
}
