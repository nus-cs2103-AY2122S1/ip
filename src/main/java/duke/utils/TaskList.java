package duke.utils;

import java.util.*;

import duke.tasks.*;

import java.io.*;
import java.time.LocalDate;

public class TaskList {
    ArrayList<Task> userInputs;

    public TaskList() {
        this.userInputs = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> userInputs) {
        this.userInputs = userInputs;
    }

    public Task getTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input);
            return userInputs.get(taskIndex - 1);
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Task> getTasks() {
        return userInputs;
    }

    public void addTask(Task task) throws DukeException {
        userInputs.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + userInputs.size() + " tasks in the list.");
    }
    
    public void deleteTask(String input) {
        try {
            System.out.println("Noted. I've removed this task: ");
            int taskIndex = Integer.parseInt(input);
            Task task = userInputs.get(taskIndex - 1);
            userInputs.remove(taskIndex - 1);
            System.out.println(" " + task);
            task = null;
            System.out.println("Now you have " + userInputs.size() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
