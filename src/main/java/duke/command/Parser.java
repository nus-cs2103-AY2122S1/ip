package duke.command;

import duke.io.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Parser {
    String input;
    TaskList taskList;
    Storage storage;

    public Parser(String input, TaskList taskList, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
    }

    public void newEvent(String taskName, TaskList tList) throws IOException {
        Event newEvent = new Event(taskName);
        tList.addTask(newEvent);

        String textToAppend = newEvent.showType() + " | "
                + ((newEvent.checkDone()).equals("[X]") ? "1" : "0") + " | "
                + newEvent.showTaskOnly() + " | "
                + newEvent.showWhen() + "\n";
        this.storage.save(textToAppend, true);

        String eventMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + "[" + newEvent.showType() + "]"
                + newEvent.checkDone() + " "
                + newEvent.showTaskOnly() + " at "
                + newEvent.showDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + newEvent.showTime() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(eventMessage);
    }

    public void newDeadline(String taskName, TaskList tList) throws IOException {
        Deadline newDeadline = new Deadline(taskName);
        tList.addTask(newDeadline);

        String textToAppend = newDeadline.showType() + " | "
                + ((newDeadline.checkDone()).equals("[X]") ? "1" : "0") + " | "
                + newDeadline.showTaskOnly() + " | "
                + newDeadline.showWhen() + "\n";
        this.storage.save(textToAppend, true);

        String deadlineMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + "[" + newDeadline.showType() + "]"
                + newDeadline.checkDone() + " "
                + newDeadline.showTaskOnly() + " by "
                + newDeadline.showDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + newDeadline.showTime() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(deadlineMessage);
    }

    public void newToDo(String taskName, TaskList tList) throws IOException {
        ToDo newToDo = new ToDo(taskName);
        tList.addTask(newToDo);

        String textToAppend = newToDo.showType() + " | "
                + ((newToDo.checkDone()).equals("[X]") ? "1" : "0") + " | "
                + newToDo.showTask() + "\n";
        this.storage.save(textToAppend, true);

        String toDoMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + "[" + newToDo.showType() + "]"
                + newToDo.checkDone() + " "
                + newToDo.showTask() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(toDoMessage);
    }

    public void compute(String input) {
        String[] instruction = input.split(" ", 2);

        switch (instruction[0]) {
            case "todo":
                try {
                    newToDo(instruction[1], this.taskList);
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The description of a todo cannot be empty.\n"
                            + "    ____________________________________________________________");
                } catch (IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The file that you requested cannot be found.\n"
                            + "    ____________________________________________________________");
                }
                break;
            case "deadline":
                try {
                    newDeadline(instruction[1], this.taskList);
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The description of a deadline cannot be empty.\n"
                            + "    ____________________________________________________________");
                } catch (IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The file that you requested cannot be found.\n"
                            + "    ____________________________________________________________");
                }
                break;
            case "event":
                try {
                    newEvent(instruction[1], this.taskList);
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The description of an event cannot be empty.\n"
                            + "    ____________________________________________________________");
                } catch (IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The file that you requested cannot be found.\n"
                            + "    ____________________________________________________________");
                }
                break;
            case "delete":
                try {
                    int deleteIndex = Integer.parseInt(instruction[1]);
                    this.taskList.deleteTask(deleteIndex);
                    this.storage.refresh(this.taskList);
                } catch (IndexOutOfBoundsException | NumberFormatException | IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! Please enter a valid item number.\n"
                            + "    ____________________________________________________________");
                }
                break;
            case "done":
                try {
                    int doneIndex = Integer.parseInt(instruction[1]);
                    this.taskList.markDone(doneIndex);
                    this.storage.refresh(this.taskList);
                } catch (IndexOutOfBoundsException | NumberFormatException | IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! Please enter a valid item number.\n"
                            + "    ____________________________________________________________");
                }
                break;
            case "list":
                this.taskList.showList();
                break;
            case "find":
                String keyword = instruction[1];
                this.taskList.searchList(keyword);
                break;
            default:
                try {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + "    ____________________________________________________________");

                    this.storage.refresh(this.taskList);
                } catch (IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The file you requested is unavailable.\n"
                            + "    ____________________________________________________________");
                }
        }
    }
}
