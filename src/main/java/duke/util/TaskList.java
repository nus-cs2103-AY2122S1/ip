package duke.util;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents an ArrayList of Task.
 */
public class TaskList {
    //List that holds all the Tasks.
    private ArrayList<Task> list;
    //Storage to load the list and write to list.
    private Storage store;

    /**
     * Constructs List that contains Tasks.
     *
     * @param fileName File to save and load existing TaskList from.
     */
    public TaskList(String fileName) {
        this.store = new Storage(fileName);
        this.list = store.loadFromFile();
    }

    /**
     * Adds ToDo Task to list.
     *
     * @param text Description of ToDo Task.
     */
    public String addToDo(String text) {
        Task newToDo = new ToDo(text);
        list.add(newToDo);
        store.writeToFile(list);
        return "Got it. I've added this task: \n"
                + newToDo
                + "Now you have " + list.size() + " tasks in the list. \n";
    }

    /**
     * Adds Deadline Task to list.
     *
     * @param text Description of Deadline.
     * @param by Deadline of Deadline Task.
     */
    public String addDeadline(String text, String by) {
        try {
            Task newDl = new Deadline(text, by);
            list.add(newDl);
            store.writeToFile(list);
            return "Got it. I've added this task: \n"
                    + newDl
                    + "Now you have " + list.size() + " tasks in the list. \n";
        } catch (DukeException | DateTimeParseException e) {
            return "OOPS!!! Please input your date and time in dd/mm/yyyy hhmm format, eg. 28/08/2021 2359";
        }

    }

    /**
     * Adds Event Task to list.
     *
     * @param text Description of Event Task.
     * @param at Date time of Event.
     * @return Notify user that the command is done/not of standard format.
     */
    public String addEvent(String text, String at) {
        try {
            Task newEvent = new Event(text, at);
            list.add(newEvent);
            store.writeToFile(list);
            return "Got it. I've added this task: \n"
                    + newEvent
                    + "Now you have " + list.size() + " tasks in the list. \n";
        } catch (DukeException | DateTimeParseException e) {
            return "OOPS!!! Please input your date and time in dd/mm/yyyy hhmm format, eg. 28/08/2021 2359";
        }
    }

    /**
     * Sets Task at list index to done .
     *
     * @param index Position of Task to be set to done.
     * @return String message notifying user that the task is done/no task to be done.
     */
    public String setIndexDone(int index) { // starts from 1
        if (index > list.size() || index < 1) { //check for invalid index number
            return "There is no task " + index;
        }
        list.get(index - 1).setDone();
        store.writeToFile(list);
        return "Nice! I've marked this task as done: \n"
                + list.get(index - 1).toString();
    }

    /**
     * Deletes Task from list.
     *
     * @param index Index of task to be deleted in list.
     * @return Notify user that the command is done/nothing to be done.
     */
    public String deleteTask(int index) { //starts from 1
        if (index > list.size() || index < 1) {
            return "There is no task " + index;
        }
        String taskDesc = list.get(index - 1).toString();
        list.remove(index - 1);
        store.writeToFile(list);
        return "Noted. I've removed this task: \n"
                + taskDesc
                + "Now you have " + list.size() + " tasks in the list. \n";

    }

    /**
     * Edits the task at index in TaskList.
     *
     * @param index Index of task to be edited.
     * @param input Include variable to be edited and the replacement.
     * @return Notify user that the command is done/nothing to be done
     * @throws DukeException if variable type to be edited is not of standard format.
     */
    public String editTask(int index, String input) throws DukeException {
        if (index > list.size() || index < 1) {
            return "There is no task " + index;
        }
        Task task = list.get(index - 1);
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        String replacement = parts[1];
        switch (taskType.toLowerCase()) {
        case "date":
            task.setDate(replacement);
            break;
        case "time":
            task.setTime(replacement);
            break;
        case "description":
            task.setDescription(replacement);
            break;
        default:
            throw new DukeException("OOPS!!! Edit type unclear, please specify either /date, /time, /description");
        }
        store.writeToFile(list);
        return "Noted. I've edited this task: \n"
                + task.toString();
    }

    /**
     * Finds Tasks with the input String in it.
     *
     * @param input Keyword to search Tasks with.
     */
    public String findTask(String input) {
        int length = list.size();
        ArrayList<Integer> listOfHits = new ArrayList<>();
        for (int i = 0; i < length; i++) { // finding the index of task with keyword input
            if (list.get(i).getDescription().contains(input)) {
                listOfHits.add(i);
            }
        }
        if (listOfHits.size() == 0) { //no matches
            return "There are no matching tasks in your list";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list: \n");
            for (int i = 0; i < listOfHits.size(); i++) { //print out matches
                stringBuilder.append((i + 1) + "." + list.get(i).toString());
            }
            return stringBuilder.toString();
        }

    }

    /**
     * Prints out the entire list using the Tasks toString method.
     *
     * @return Shows user the tasks in the TaskList.
     */
    public String show() {
        int length = list.size();
        if (length == 0) {
            return "YAY! You have no more tasks left :)";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= length; i++) {
                stringBuilder.append(i + "." + list.get(i - 1).toString());
            }
            return stringBuilder.toString();
        }
    }

}
