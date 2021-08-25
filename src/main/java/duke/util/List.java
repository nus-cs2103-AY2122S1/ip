package duke.util;

import duke.task.*;
import duke.util.Storage;
import duke.exception.DukeException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.*;

public class List {
    private ArrayList<Task> list;
    private Storage store;

    public List() {
        this.store = new Storage("list.ser");
        this.list = store.loadFromFile();
    }

    public void addToDo(String text) {
        Task newToDo = new ToDo(text);
        list.add(newToDo);
        System.out.println("Got it. I've added this duke.task: ");
        System.out.println(newToDo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        store.writeToFile(list);
    }

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

    public void addEvent(String text, String at) {
        Task newEvent = new Event(text, at);
        list.add(newEvent);
        System.out.println("Got it. I've added this duke.task: ");
        System.out.println(newEvent);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        store.writeToFile(list);
    }

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
