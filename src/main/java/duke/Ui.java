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

    enum Type {
        BYEUSER,
        DELETETASK,
        VIEWLIST,
        FINDTASK,
        MARKDONE,
        ADDEVENT,
        ADDTODO,
        ADDDEADLINE,
        NONE
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
    public String byeUser(String input) {
        System.out.println("Bye. Hope to see you again soon!");
        isExit = true;
        return "Bye. Hope to see you again soon!";
    }

    public String deleteTask(String input) {
        Task taskToBeDeleted = taskList.get(Parser.parseDelete(input) - 1);
        taskList.deleteTask(Parser.parseDelete(input) - 1);
        System.out.println("Noted. I've removed this task: \n"
                + "  " + taskToBeDeleted.toString() +"\n" +
                "Now you have " + taskList.length() + " tasks in the list.");
        storage.saveTask(taskList);
        return "Noted. I've removed this task: \n"
                + "  " + taskToBeDeleted.toString() +"\n" +
                "Now you have " + taskList.length() + " tasks in the list.";
    }

    public String viewList() {
        String output = "Here are the tasks in your list:\n";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length(); i++) {
            output = output + (i + 1) + ". " + taskList.get(i).toString() + "\n";
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        return output;
    }

    public String findTask(String input) {
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
    }

    public String markTaskAsDone(String input) {
        int taskIndex = Parser.parseDone(input);
        taskList.get(taskIndex - 1).markCompleted();
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(taskIndex - 1).toString());
        storage.saveTask(taskList);
        return "Nice! I've marked this task as done:\n" + taskList.get(taskIndex - 1).toString();
    }

    public String addEvent(String input) {
        String error = Validators.validateEventDescription(input);
        if (error != null) {
            return error;
        }
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
    }

    public String addDeadline(String input) {
        String error = Validators.validateDeadlineDescription(input);
        if (error != null) {
            return error;
        }
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
    }

    public String addTodo(String input) {
        String error = Validators.validateTodoDescription(input);
        if (error != null) {
            return error;
        }
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
    }

    public String handleInvalidInput() {
        try{
            throw new DukeException.invalidInputException();
        } catch (DukeException.invalidInputException e) {
            e.exceptionMessage();
            return e.stringExceptionMessage();
        }
    }

    public String processInput(Type input, String actualInput) {
        switch (input) {
            case BYEUSER:
                return byeUser(actualInput);
            case DELETETASK:
                return deleteTask(actualInput);
            case VIEWLIST:
                return viewList();
            case FINDTASK:
                return findTask(actualInput);
            case MARKDONE:
                return markTaskAsDone(actualInput);
            case ADDEVENT:
                return addEvent(actualInput);
            case ADDTODO:
                return addTodo(actualInput);
            case ADDDEADLINE:
                return addDeadline(actualInput);
            default:
                return handleInvalidInput();
        }
    }
    public String readLine() {
        return scanner.nextLine();
    }

    public String handleInput(String input) {
        Type action;
        if (input.equals("bye")) {
            action = Type.BYEUSER;
        } else if(input.split(" ")[0].equals("delete")) {
            action = Type.DELETETASK;
        } else if (input.equals("list")) {
            action = Type.VIEWLIST;
        } else if (input.split(" ")[0].equals("find")) {
            action = Type.FINDTASK;
        } else if (input.split(" ")[0].equals("done")){
            action = Type.MARKDONE;
        } else if (input.split("event")[0].equals("")) {
            action = Type.ADDEVENT;
        } else if (input.split("deadline")[0].equals("")) {
            action = Type.ADDDEADLINE;
        } else if (input.split("todo")[0].equals("")) {
            action = Type.ADDTODO;
        } else {
            action = Type.NONE;
        }
        return processInput(action, input);
    }

    public boolean handleExit() {
        return isExit;
    }
}

