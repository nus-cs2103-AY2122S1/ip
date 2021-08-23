package com.iP.yiheng;

public class UserInterface {

    public UserInterface() {}

    protected void greet() {
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
    }

    protected void exit() {
        System.out.println("\nDuke: Bye. Hope to see you again soon!");
    }

    protected void taskListHeader() { System.out.println("\nHere are the tasks in your list:\n--------------------"); }

    protected void taskComplete(TaskList t) { System.out.println("\nDuke: Nice! I've marked this task as done:\n" + t); }

    protected void taskDeleted(TaskList t) { System.out.println("\nDuke: Noted. I've removed this task:\n"
            + t + "\nNow you have " + TaskList.listLength() + " tasks in the list."); }

    protected void taskAdded(TaskList t) { System.out.println("\nDuke: Got it. I have added this task:\n" +
            t + "\nNow you have " +
            TaskList.listLength() + " tasks in the list."); }

    protected void emptyListWarning() { System.out.println("Your list is empty!"); }

    protected void integerInputWarning() { System.out.println("Please enter a number as index!"); }

    protected void invalidIntegerWarning() { System.out.println("Please enter a proper index!"); }

    protected void fileNotFoundWarning() { System.out.println("File not found. Please add an item to automatically create your tasks.txt!" +
            " If no file is created,\nyou need to create a data folder in your root directory before " +
            "proceeding."); }

    protected void generalErrorWarning(String message) { System.out.println("Something went wrong: " + message); }
}
