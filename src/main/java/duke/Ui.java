package duke;

import java.util.Scanner;

public class Ui {
    private Storage storage;
    private TaskList taskList;
    boolean isExit = false;
    private final Scanner scanner = new Scanner(System.in);
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }
    public void showLine() {
        System.out.println("______________________________________________");
    }
    public String greet() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        return "Hello! I'm Duke\n" +
                "What can I do for you?";
    }
    public String readLine() {
        return scanner.nextLine();
    }
    public String handleInput(String input) {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            isExit = true;
            return "Bye. Hope to see you again soon!";
        } else if(input.split(" ")[0].equals("delete")) {
            Task taskToBeDeleted = taskList.get(Parser.parseDelete(input) - 1);
            taskList.deleteTask(Parser.parseDelete(input) - 1);
            System.out.println("Noted. I've removed this task: \n"
                    + "  " + taskToBeDeleted.toString() +"\n" +
                    "Now you have " + taskList.length() + " tasks in the list.");
            storage.saveTask(taskList);
            return "Noted. I've removed this task: \n"
                    + "  " + taskToBeDeleted.toString() +"\n" +
                    "Now you have " + taskList.length() + " tasks in the list.";
        } else if (input.equals("list")) {
            String output = "Here are the tasks in your list:\n";
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.length(); i++) {
                output = output + (i + 1) + ". " + taskList.get(i).toString() + "\n";
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
            return output;
        } else if (input.split(" ")[0].equals("find")) {
            String wordToFind = input.split(" ")[1];
            System.out.println("Here are the matching tasks in your list:");
            String output = "Here are the matching tasks in your list:\n";
            for(int i = 0; i < taskList.length(); i++) {
                if(taskList.get(i).getTaskContent().contains(wordToFind)) {
                    System.out.println((i + 1) + ". " +taskList.get(i).toString());
                    output = output + (i + 1) + ". " + taskList.get(i).toString() + "\n";
                }
            }
            return output;
        } else if (input.split(" ")[0].equals("done")){
            int taskIndex = Parser.parseDone(input);
            taskList.get(taskIndex - 1).markCompleted();
            System.out.println("Nice! I've marked this task as done:\n" + taskList.get(taskIndex - 1).toString());
            storage.saveTask(taskList);
            return "Nice! I've marked this task as done:\n" + taskList.get(taskIndex - 1).toString();
        } else if (input.split("todo").length == 0) {
            try {
                throw new DukeException.emptyToDoDescriptionException();
            } catch (DukeException.emptyToDoDescriptionException e) {
                e.exceptionMessage();
                return e.stringExceptionMessage();
            }
        } else if(input.split("deadline").length == 0) {
            try {
                throw new DukeException.emptyDeadlineDescriptionException();
            } catch (DukeException.emptyDeadlineDescriptionException e) {
                e.exceptionMessage();
                return e.stringExceptionMessage();
            }
        } else if (input.split("event").length == 0) {
            try {
                throw new DukeException.emptyEventDescriptionException();
            } catch (DukeException.emptyEventDescriptionException e) {
                e.exceptionMessage();
                return e.stringExceptionMessage();
            }
        } else if (input.split("event")[0].equals("")) {
            String taskContent = Parser.parseEvent(input);
            Task newEvent = new Event(taskContent);
            taskList.addTask(newEvent);
            System.out.println("Got it. I've added this task: \n"
                    + "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.");
            storage.saveTask(taskList);
            return "Got it. I've added this task: \n"
                    + "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.";
        } else if (input.split("deadline")[0].equals("")) {
            String taskContent = Parser.parseDeadline(input);
            Task newEvent = new Deadline(taskContent);
            taskList.addTask(newEvent);
            System.out.println("Got it. I've added this task: \n"
                    + "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.");
            storage.saveTask(taskList);
            return "Got it. I've added this task: \n"
                    + "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.";
        } else if (input.split("todo")[0].equals("")) {
            String taskContent = Parser.parseTodo(input);
            Task newEvent = new ToDo(taskContent);
            taskList.addTask(newEvent);
            System.out.println("Got it. I've added this task: \n"
                    + "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.");
            storage.saveTask(taskList);
            return "Got it. I've added this task: \n"
                    + "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.";
        } else {
            try{
                throw new DukeException.invalidInputException();
            } catch (DukeException.invalidInputException e) {
                e.exceptionMessage();
                return e.stringExceptionMessage();
            }
        }
    }
    public boolean handleExit() {
        return isExit;
    }
}

