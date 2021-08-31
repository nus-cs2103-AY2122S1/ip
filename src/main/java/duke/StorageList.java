package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageList {
    private final ArrayList<Task> storageList = new ArrayList<>();
    private File file;

    public StorageList(File file) throws FileNotFoundException {
        this.file = file;
        readFile(new Scanner(file));
    }

    private void readFile(Scanner sc) {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            char type = input.charAt(0);
            int doneState = Integer.parseInt(input.substring(4, 5));

            if (type == 'T') {
                String taskDesc = input.substring(8);
                ToDo todo = new ToDo(taskDesc);

                if (doneState == 1) {
                    todo.markAsDone();
                }
                storageList.add(todo);

            } else {
                int thirdBarIdx = input.indexOf('|', 7);
                String taskDesc = input.substring(8, thirdBarIdx - 1);
                String taskTime = input.substring(thirdBarIdx + 2);

                if (type == 'D') {
                    Deadline dl = new Deadline(taskDesc, taskTime);
                    if (doneState == 1) {
                        dl.markAsDone();
                    }
                    storageList.add(dl);

                } else if (type == 'E') {
                    Event event = new Event(taskDesc, taskTime);
                    if(doneState == 1){
                        event.markAsDone();
                    }
                    storageList.add(event);
                }
            }
        }
    }

    /**
     * Adds a task to the storage list/task list.
     *
     * @param task
     */
    public void addTask(Task task){
        storageList.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param idx Index of the task.
     * @return The task at index i.
     */
    public Task get(int idx){
        return storageList.get(idx);
    }

    /**
     * Returns the size of the storage list.
     *
     * @return The size of the list.
     */
    public int size(){
        return storageList.size();
    }

    /**
     * Deletes a task from the specified index in the list.
     *
     * @param idx The index of the task to be deleted.
     */
    public void delete(int idx) {
        storageList.remove(idx);
    }

    public String findAndReturn(String keyword) {
        String output = "    Here are the matching tasks in your list:";
        int size = storageList.size();
        int itemNumber = 1;
        for (int i = 0; i < size; i++) {
            Task task = storageList.get(i);
            if (task.getDescription().contains(keyword)) {
                output += "\n        " + itemNumber + "." + task.toString();
                itemNumber++;
            }
        }
        return output;
    }

}
