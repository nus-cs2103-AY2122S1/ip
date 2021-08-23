package parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import duke.Duke;
import ui.Ui;
import task.TaskList;
import task.Deadline;
import task.Event;
import task.Todo;
import storage.Storage;

public class Parser {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }


    public boolean parse(String userInput) {
        if (userInput.equals("bye")) {
            ui.goodbyeMessage();
            return true;
        } else if (userInput.equals("list")) {
            ui.listMessage(tasks);
        } else if (isDoneCall(userInput)) {
            int index = Integer.parseInt(userInput.substring(5));

            if (tasks.getTask(index - 1) != null) {
                tasks.getTask(index - 1).markAsDone();
                storage.markAsDoneData(index - 1);
                ui.doneMessage(tasks.getTask(index - 1));
            } else {
                ui.noSuchTaskMessage();
            }

        } else if (isRemoveCall(userInput)) {
            int index = Integer.parseInt(userInput.substring(7));
            if (tasks.getTask(index - 1) != null) {
                ui.removeMessage(tasks.getTask(index - 1), tasks.size() - 1);
                storage.removeData(index - 1);
                tasks.removeTask(index - 1);
            } else {
                ui.noSuchTaskMessage();
            }
        } else {
            System.out.println("____________________________________________________________");
            try {
                if (userInput.startsWith("todo")) {
                    parseAddTask(userInput, tasks, Duke.Type.TODO);
                } else if (userInput.startsWith("deadline")) {
                    parseAddTask(userInput, tasks, Duke.Type.DEADLINE);
                } else if (userInput.startsWith("event")) {
                    parseAddTask(userInput, tasks, Duke.Type.EVENT);
                } else {
                    throw new IllegalArgumentException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
        return false;
    }

    public boolean isDoneCall (String strNum) {
        if (strNum == null) {
            return false;
        }
        if (strNum.length() < 6) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum.substring(5));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!strNum.startsWith("done ")) {
            return false;
        }
        return true;
    }

    public boolean isRemoveCall (String str) {
        if (str == null) {
            return false;
        }
        if (str.length() < 8) {
            return false;
        }
        try {
            int d = Integer.parseInt(str.substring(7));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!str.startsWith("remove ")) {
            return false;
        }
        return true;
    }

    public void parseAddTask(String userInput, TaskList tasks, Duke.Type type) throws IllegalArgumentException {
        if (type == Duke.Type.TODO) {
            if (userInput.substring(4).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.addTask(new Todo(userInput.substring(5)));
            storage.newTaskToData(userInput.substring(5), Duke.Type.TODO, "");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.getTask(tasks.size() - 1).toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else if (type == Duke.Type.DEADLINE) {
            if (userInput.substring(8).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            int timeIndex = userInput.indexOf("/by");
            if (timeIndex == -1) {
                throw new IllegalArgumentException(" Please set a deadline by adding /by");
            }
            try {
                tasks.addTask(new Deadline(userInput.substring(9, timeIndex - 1),
                        LocalDate.parse(userInput.substring(timeIndex + 4, timeIndex + 14)),
                        LocalTime.parse(userInput.substring(timeIndex + 15))));
            } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(" Date and Time must be specified by YYYY-MM-DD HH:MM");
            }
            storage.newTaskToData(userInput.substring(9, timeIndex - 1), Duke.Type.DEADLINE, userInput.substring(timeIndex + 4));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.getTask(tasks.size() - 1).toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            if (userInput.substring(5).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of an event cannot be empty.");
            }
            int timeIndex = userInput.indexOf("/at");
            if (timeIndex == -1) {
                throw new IllegalArgumentException(" Please set a deadline by adding /at");
            }
            try {
                tasks.addTask(new Event(userInput.substring(6, timeIndex - 1),
                        LocalDate.parse(userInput.substring(timeIndex + 4, timeIndex + 14)),
                        LocalTime.parse(userInput.substring(timeIndex + 15))));
            } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(" Date and Time must be specified by YYYY-MM-DD HH:MM");
            }

            storage.newTaskToData(userInput.substring(6, timeIndex - 1), Duke.Type.EVENT, userInput.substring(timeIndex + 4));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.getTask(tasks.size() - 1).toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
