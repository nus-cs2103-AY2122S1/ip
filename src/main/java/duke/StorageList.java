package duke;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageList {
    private final ArrayList<Task> storageList = new ArrayList<>();
    private File file;
    private Ui ui = new Ui();

    public StorageList() {};

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
                ToDos todo = new ToDos(taskDesc);
                if (doneState == 1) {
                    todo.markAsDone();
                }
                storageList.add(todo);
            } else {
                int thirdBarIdx = input.indexOf('|', 7);
                String taskDesc = input.substring(8, thirdBarIdx - 1);
                String taskTime = input.substring(thirdBarIdx + 2);
                if (type == 'D') {
                    Deadlines dl = new Deadlines(taskDesc, taskTime);
                    if (doneState == 1) {
                        dl.markAsDone();
                    }
                    storageList.add(dl);
                } else if (type == 'E') {
                    Events event = new Events(taskDesc, taskTime);
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
    public void add(Task task){
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
        String desc = storageList.get(idx).getDescription();
        storageList.remove(idx);
        ui.taskDeleteMsg(desc, storageList.size());
    }

}
