import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class Duke {

    public static boolean active;
    public static String startMessage = "Hello! I'm Duke \nFor " +
            "events and deadlines, be sure to state the due date or the event timing as such: " +
            "'XXX /by dd-MM-yyyy HH:mm'";
    public static String endMessage = "Bye! Hope to see you again soon!";
    //public static String[] list = new String[100];
    public static ArrayList<TaskItem> taskList = new ArrayList<>();
//    public static TaskItem[] taskList = new TaskItem[100];
    public static int listIndex = 0;


    /**
     * awaken() awakens Duke and allows one to input commands to Duke.
     */
    public static void awaken() {
        Duke.active = true;

        Duke.sendStartMessage();

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (active) {
            String input = scanner.nextLine();
            if (input.split(" ")[0].equals("done")) {
                try {
                    String[] splitString = input.split(" ");
                    if (splitString.length == 1) {
                        throw new DukeException(
                                "____________________________________________________________\n" +
                                    "☹ OOPS!!! Choose the task number to be considered done.\n" +
                                        "____________________________________________________________"
                        );
                    }
                    String taskItemNumber = splitString[1];
                    Duke.markAsFinished(taskItemNumber);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }   else if (input.split(" ")[0].equals("todo")) {
                //input = input.replace(input.split(" ")[0], "");
                try {
                    String actualToDo = input.replace(input.split(" ")[0], "");
                    if (actualToDo.equals("")) {
                        throw new DukeException(
                                "____________________________________________________________\n" +
                                        "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                        "____________________________________________________________"
                        );
                    }
                    ToDo newToDo = new ToDo(actualToDo);
                    Duke.addToList(newToDo);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }   else if (input.split(" ")[0].equals("deadline")) {
                try {
                    input = input.replace(input.split(" ")[0], "");
                    if (input.split("/").length == 1) {
                        throw new DukeException(
                                "____________________________________________________________\n" +
                                        "☹ OOPS!!! The description or by-date (or both) cannot be empty.\n" +
                                        "____________________________________________________________"
                        );
                    }
                    String by = input.split("/")[1].split(" ", 2)[1];
//                    System.out.println(by);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    try {
                        LocalDateTime byDateAndTime = LocalDateTime.parse(by, formatter);
                        String description = input.split("/")[0];
                        //Deadline dead = new Deadline(description, by);
                        Deadline dead = new Deadline(description, byDateAndTime);
                        Duke.addToList(dead);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please follow the specified format for entering the date and time of the deadline.");
                    }
//                    System.out.println(byDateAndTime.toString() + " for deadline");

                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }   else if (input.split(" ")[0].equals("event")) {
                try {
                    input = input.replace(input.split(" ")[0], "");

                    if (input.split("/").length == 1) {
                        throw new DukeException(
                                "____________________________________________________________\n" +
                                        "☹ OOPS!!! The description of an event, as well as its date and time, cannot be empty.\n" +
                                        "____________________________________________________________"
                        );
                    }
                    String date = input.split("/")[1].split(" ")[1];
                    String time = input.split("/")[1].split(" ")[2];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    System.out.println(date);
                    System.out.println(time);
                    System.out.println(date + time);
                    try {
                        LocalDateTime byDateAndTime = LocalDateTime.parse(date + " " + time, formatter);
//                    System.out.println(byDateAndTime.toString() + " for event");
                        String description = input.split("/")[0];
                        Event someEvent = new Event(description, byDateAndTime);
                        Duke.addToList(someEvent);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please follow the specified format for entering the date and time of the deadline.");
                    }

                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }   else if (input.equals("list")) {
                Duke.printList();
            } else if (input.equals("bye")) {
                Duke.active = false;
                Duke.sendEndMessage();
                break;
            } else if (input.split(" ")[0].equals("delete")) {

                try {
                    if (input.split(" ").length == 1) {
                        throw new DukeException(
                                "____________________________________________________________\n" +
                                        "☹ OOPS!!! Please state the task number that you want to delete.\n" +
                                        "____________________________________________________________"
                        );
                    }
                    String taskToBeDeleted = input.split(" ")[1];
                    Duke.deleteTask(taskToBeDeleted);
                } catch (DukeException e) {

                }

            } else {
                System.out.println("____________________________________________________________");
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }


        }
    }

    /**
     * deletes a task from the list that Duke creates.
     * @param taskToBeDeleted the index of the task to be deleted.
     * @throws DukeException An exception stemming from incorrect or awkward input to Duke.
     */
    public static void deleteTask(String taskToBeDeleted) throws DukeException {
        int taskNumber = Integer.parseInt(taskToBeDeleted);

        if (taskList.get(taskNumber - 1) == null) {
            throw new DukeException(
                    "____________________________________________________________\n" +
                            "☹ OOPS!!! The task you chose to delete does not exist. Use the 'list' command to check the items in your list.\n" +
                            "____________________________________________________________"
            );
        }
        TaskItem removedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        int taskListSize = taskList.size();
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        if (taskListSize == 1) System.out.println("Now you have 1 task in the list.");
        if (taskListSize > 1) System.out.println("Now you have " + taskListSize + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }


    /**
     * markAsFinished marks items on Duke's list as completed. Completed tasks will have a checkbox [X].
     * @param taskItemNumber the index of the task to be marked as finished.
     * @throws DukeException An exception stemming from incorrect or awkward input to Duke.
     */
    public static void markAsFinished(String taskItemNumber) throws DukeException {
        int taskNumber = Integer.parseInt(taskItemNumber);

        if (taskList.get(taskNumber - 1) == null) {
            throw new DukeException(
                    "____________________________________________________________\n" +
                            "☹ OOPS!!! The task you chose does not exist. Use the 'list' command to check the items in your list.\n" +
                            "____________________________________________________________"
            );
        }

        taskList.get(taskNumber - 1).completeTask();

        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(taskNumber - 1).toString());
        System.out.println("____________________________________________________________");

    }

    /**
     * addToList adds a taskItem of type TaskItem to the list.
     * @param taskItem a TaskItem that is to be added to Duke's list.
     */
    public static void addToList(TaskItem taskItem) {
        Duke.taskList.add(taskItem);
        Duke.listIndex++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskItem.toString());
        if (listIndex == 1) System.out.println("Now you have " + 1 + " task in the list.");
        if (listIndex > 1) System.out.println("Now you have " + (Duke.listIndex) + " tasks in your list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * prints the list upon inputting the 'list' command.
     */
    public static void printList() {
        int number = 1;
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) != null) {
                System.out.println(number + "." + taskList.get(i).toString());

                number++;
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * sends the greeting message for Duke.
     */
    public static void sendStartMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(startMessage);
        System.out.println("____________________________________________________________");
    }

    /**
     * sends the closing message for Duke.
     */
    public static void sendEndMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(endMessage);
        System.out.println("____________________________________________________________");
    }

    /**
     * main method.
     * @param args input from the user to control Duke.
     */
    public static void main(String[] args) {
        Duke.awaken();
    }
}
