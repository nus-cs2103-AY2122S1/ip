package duke;

import java.util.Scanner;

public class Ui {
    Storage storage;
    TaskList taskList;
    boolean isExit = false;
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }
    private final Scanner scanner = new Scanner(System.in);
    public void showLine() {
        System.out.println("______________________________________________");
    }
    public void greet() {
        System.out.println("Hello! I'm duke.Duke\n" +
                "What can I do for you?");
        System.out.println("Here are your tasks: ");
    }
    public String readLine() {
        return scanner.nextLine();
    }
    public void handleInput(String input) {
        if(input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            isExit = true;
        } else if(input.split(" ")[0].equals("delete")) {
            Task taskToBeDeleted = taskList.get(Parser.parseDelete(input) - 1);
            taskList.deleteTask(Parser.parseDelete(input) - 1);
            System.out.println("Noted. I've removed this task: \n" +
                    "  " + taskToBeDeleted.toString() +"\n" +
                    "Now you have " + taskList.length() + " tasks in the list.");
            storage.saveTask(taskList);
        } else if (input.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < taskList.length(); i++){
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
        }else if(input.split(" ")[0].equals("done")){
            int taskIndex = Parser.parseDone(input);
            taskList.get(taskIndex - 1).markCompleted();
            System.out.println("Nice! I've marked this task as done:\n" + taskList.get(taskIndex - 1).toString());
            storage.saveTask(taskList);
        } else if(input.split("todo").length == 0) {
            try {
                throw new DukeException.emptyToDoDescriptionException();
            } catch (DukeException.emptyToDoDescriptionException e) {
                e.exceptionMessage();
            }
        }else if(input.split("deadline").length == 0) {
            try {
                throw new DukeException.emptyDeadlineDescriptionException();
            } catch (DukeException.emptyDeadlineDescriptionException e) {
                e.exceptionMessage();
            }
        }else if(input.split("event").length == 0) {
            try {
                throw new DukeException.emptyEventDescriptionException();
            } catch (DukeException.emptyEventDescriptionException e) {
                e.exceptionMessage();
            }
        } else if(input.split("event")[0].equals("")) {
            String taskContent = Parser.parseEvent(input);
            Task newEvent = new Event(taskContent);
            taskList.addTask(newEvent);
            System.out.println("Got it. I've added this task: \n" +
                    "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.");
            storage.saveTask(taskList);
        } else if(input.split("deadline")[0].equals("")) {
            String taskContent = Parser.parseDeadline(input);
            Task newEvent = new Deadline(taskContent);
            taskList.addTask(newEvent);
            System.out.println(" Got it. I've added this task: \n" +
                    "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.");
            storage.saveTask(taskList);
        } else if(input.split("todo")[0].equals("")) {
            String taskContent = Parser.parseTodo(input);
            Task newEvent = new ToDo(taskContent);
            taskList.addTask(newEvent);
            System.out.println("Got it. I've added this task: \n" +
                    "  " + newEvent.toString() + "\n" +
                    "Now you have " + taskList.length() + " tasks in the list.");
            storage.saveTask(taskList);
        }
        else {
            try{
                throw new DukeException.invalidInputException();
            } catch (DukeException.invalidInputException e) {
                e.exceptionMessage();
            }
        }
    }
    public boolean handleExit() {
        return isExit;
    }
}

