package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageList {
    private final ArrayList<Task> storageList = new ArrayList<>();

    public StorageList(File file) throws FileNotFoundException {
        readFile(new Scanner(file));
    }

    private void readFile(Scanner sc) {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            char type = input.charAt(0);
            int doneState = Integer.parseInt(input.substring(4, 5));

            if (type == 'T') { // T for todo
                String taskDesc = input.substring(8);
                ToDo todo = new ToDo(taskDesc);
                checkIfDoneAndAdd(todo, doneState);

            } else { // D for deadline

                int thirdBarIdx = input.indexOf('|', 7);
                String taskDesc = input.substring(8, thirdBarIdx - 1);
                String taskTime = input.substring(thirdBarIdx + 2);

                if (type == 'D') { // D for deadline
                    Deadline dl = new Deadline(taskDesc, taskTime);
                    checkIfDoneAndAdd(dl, doneState);

                } else if (type == 'E') { // E for event

                    Event event = new Event(taskDesc, taskTime);
                    checkIfDoneAndAdd(event, doneState);
                }
            }
        }
    }

    private void checkIfDoneAndAdd(Task task, int doneState) {
        if(doneState == 1){
            task.markAsDone();
        }
        storageList.add(task);
    }

    /**
     * Adds a task to the storage list/task list.
     *
     * @param task The task to be added to the list.
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
        assert itemNumber <= size : "Index out of bounds";
        return output;
    }

    public String viewSchedule(String dateQuery) {
        Ui ui = new Ui();
        try {
            DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

            String output = "    Here is your schedule for this day:";
            String parsedDate = LocalDate.parse(dateQuery, stringFormat)
                    .format(stringFormat);

            int itemNumber = 1;
            for (Task task : storageList) {
                String dateOfTask = "";
                if (task instanceof Event) {
                    dateOfTask = ((Event) task).getAt();
                } else if (task instanceof Deadline) {
                    dateOfTask = ((Deadline) task).getBy();
                } else {
                    continue;
                }

                if (dateOfTask.contains(parsedDate)) {
                    output += "\n        " + itemNumber + "." + task.toString();
                    itemNumber++;
                }
            }
            return output;

        } catch (DateTimeParseException e) {
            return ui.parsingFormatErrorMsg();
        }
    }

}
