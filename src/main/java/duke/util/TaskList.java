package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    //List that holds all the Tasks.
    private final ArrayList<Task> list;
    //Storage to load the list and write to list,
    private final Storage store;

    /**
     * Constructs List.
     */
    public TaskList() {
        this.store = new Storage("list.ser");
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
        return "Got it. I've added this duke.task: \n" +
                newToDo +
                "Now you have " + list.size() + " tasks in the list. \n";
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
            return  "Got it. I've added this duke.task: \n" +
                    newDl +
                    "Now you have " + list.size() + " tasks in the list. \n";
        } catch (DukeException | DateTimeParseException e) {
            return "Please input your date and time in dd/mm/yyyy hhmm format, eg. 28/08/2021 2359";
        }

    }

    /**
     * Adds Event Task to list.
     *
     * @param text Description of Event Task.
     * @param at Date time of Event.
     */
    public String addEvent(String text, String at) {
        try {
            Task newEvent = new Event(text, at);
            list.add(newEvent);
            store.writeToFile(list);
            return "Got it. I've added this duke.task: \n" +
                    newEvent +
                    "Now you have " + list.size() + " tasks in the list. \n";
        } catch (DukeException | DateTimeParseException e) {
            return "Please input your date and time in dd/mm/yyyy hhmm format, eg. 28/08/2021 2359";
        }
    }

    /**
     * Sets Task to be done at list index.
     *
     * @param index Position of Task to be set to done.
     * @return
     */
    public String setIndexDone(int index) {// starts from 1
        if (index > list.size() || index < 1) {//check for invalid index number
            return "There is no duke.task " + index;
        }
        list.get(index - 1).setDone();
        store.writeToFile(list);
        return  "Nice! I've marked this duke.task as done: \n" +
                list.get(index - 1).toString();
    }

    /**
     * Deletes Task from list.
     *
     * @param index Index of task to be deleted in list.
     */
    public String deleteTask(int index) {//starts from 1
        if (index > list.size() || index < 1) {
            return "There is no duke.task " + index;
        }
        String taskDesc = list.get(index - 1).toString();
        list.remove(index - 1);
        store.writeToFile(list);
        return "Noted. I've removed this duke.task: \n" +
                taskDesc +
                "Now you have " + list.size() + " tasks in the list. \n";

    }

    /**
     * Finds Tasks with the input String in it.
     *
     * @param input Keyword to search Tasks with.
     */
    public String find(String input) {
        int length = list.size();
        ArrayList<Integer> listOfHits = new ArrayList<>();
        for (int i = 0; i < length; i++) {// finding the index of task with keyword input
            if (list.get(i).getDescription().contains(input)) {
                listOfHits.add(i);
            }
        }
        if (listOfHits.size() == 0) {//no matches
            return "There are no matching tasks in your list";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list: ");
            for (int i = 0; i < listOfHits.size(); i++) {//print out matches
                stringBuilder.append((i + 1) + "." + list.get(i).toString());
            }
            return stringBuilder.toString();
        }

    }

    /**
     * Prints out the entire list using the Tasks toString method.
     */
    public String show() {
        int length = list.size();
        if (length == 0) {
            return  "YAY! You have no more tasks left :)";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= length; i++) {
                stringBuilder.append(i + "." + list.get(i - 1).toString());
            }
            return stringBuilder.toString();
        }
    }

}
