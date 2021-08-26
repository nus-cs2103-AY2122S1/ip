package duke.util;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.task.Event;
import duke.exception.DukeException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class List {
    //List that holds all the Tasks.
    private ArrayList<Task> list;
    //Storage to load the list and write to list,
    private Storage store;

    /**
     * Constructor for List.
     */
    public List() {
        this.store = new Storage("list.ser");
        this.list = store.loadFromFile();
    }

    /**
     * Add ToDo Task to list.
     *
     * @param text Description of ToDo Task.
     */
    public void addToDo(String text) {
        Task newToDo = new ToDo(text);
        list.add(newToDo);
        System.out.println("Got it. I've added this duke.task: ");
        System.out.println(newToDo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        store.writeToFile(list);
    }

    /**
     * Add Deadline Task to list.
     *
     * @param text Description of Deadline.
     * @param by Deadline of Deadline Task.
     */
    public void addDeadline(String text, String by) {
        try {
            Task newDl = new Deadline(text, by);
            list.add(newDl);
            System.out.println("Got it. I've added this duke.task: ");
            System.out.println(newDl);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            store.writeToFile(list);
        } catch (DukeException | DateTimeParseException e) {
            System.out.println("Please input your date and time in dd/mm/yyyy hhmm format, eg. 28/08/2021 2359");
        }

    }

    /**
     * Add Event Task to list.
     *
     * @param text Description of Event Task.
     * @param at Date time of Event.
     */
    public void addEvent(String text, String at) {
        try {
            Task newEvent = new Event(text, at);
            list.add(newEvent);
            System.out.println("Got it. I've added this duke.task: ");
            System.out.println(newEvent);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            store.writeToFile(list);
        } catch (DukeException | DateTimeParseException e) {
            System.out.println("Please input your date and time in dd/mm/yyyy hhmm format, eg. 28/08/2021 2359");
        }
    }

    /**
     * Set Task to be done at list index.
     *
     * @param index Position of Task to be set to done.
     */
    public void setIndexDone(int index) {// starts from 1
        if(index > list.size() || index < 1){//check for invalid index number
            System.out.println("There is no duke.task " + index);
            return;
        }
        list.get(index - 1).setDone();
        System.out.println("Nice! I've marked this duke.task as done: ");
        System.out.println(list.get(index - 1).toString());
        store.writeToFile(list);
    }

    /**
     * Delete Task from list.
     *
     * @param index Index of task to be deleted in list.
     */
    public void deleteTask(int index) {//starts from 1
        if(index > list.size() || index < 1){
            System.out.println("There is no duke.task " + index);
            return;
        }
        System.out.println("Noted. I've removed this duke.task: ");
        System.out.println(list.get(index - 1).toString());
        list.remove(index - 1);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        store.writeToFile(list);
    }

    /**
     * Print out the entire list using the Tasks toString method.
     */
    public void show() {
        int length = list.size();
        if (length == 0) {
            System.out.println("YAY! You have no more tasks left :)");
        }
        for(int i = 1; i <= length; i++) {
            System.out.println(i + "." + list.get(i - 1).toString());
        }
    }

}
