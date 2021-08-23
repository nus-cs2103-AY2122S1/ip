package com.duke.ui;

import com.duke.task.TaskList;

public class UserInterface {

    public UserInterface() {}

    public void greet() {
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("\nDuke: Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public void taskListHeader() { System.out.println("\nHere are the tasks in your list:\n--------------------"); }

    public void taskComplete(TaskList t) { System.out.println("\nDuke: Nice! I've marked this task as done:\n" + t); }

    public void taskDeleted(TaskList t) { System.out.println("\nDuke: Noted. I've removed this task:\n"
            + t + "\nNow you have " + TaskList.listLength() + " tasks in the list."); }

    public void taskAdded(TaskList t) { System.out.println("\nDuke: Got it. I have added this task:\n" +
            t + "\nNow you have " +
            TaskList.listLength() + " tasks in the list."); }

    public void emptyListWarning() { System.out.println("Your list is empty!"); }

    public void noTimelineHighlight() { System.out.println("Task has no deadline."); }

    public void integerInputWarning() { System.out.println("Please enter a number as index!"); }

    public void invalidIntegerWarning() { System.out.println("Please enter a proper index!"); }

    public void fileNotFoundWarning() { System.out.println("File not found. Please add an item to automatically create your tasks.txt!" +
            " If no file is created,\nyou need to create a data folder in your root directory before " +
            "proceeding."); }

    public void generalErrorWarning(String message) { System.out.println("Something went wrong: " + message); }

    public void nullFunction() {}
}
