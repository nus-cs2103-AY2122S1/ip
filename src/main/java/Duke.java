import exceptions.*;
import task.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Duke {

    private ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        System.out.println(Constants.logo + Constants.HELLO + Constants.LINE);
        try {
            chatbot.start();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void start() throws IOException {
        Storage.readTasks(taskList);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            try {
                if (userInput.trim().equalsIgnoreCase("bye")) {
                    System.out.println(Constants.BYE + Constants.LINE);
                    System.exit(0);
                } else if (userInput.trim().equalsIgnoreCase("list")) {
                    printList(taskList);
                } else if (userInput.trim().split("\\s")[0].equalsIgnoreCase("done")) {
                    completeTask(userInput.trim());
                } else if (userInput.trim().split("\\s")[0].equalsIgnoreCase("delete")) {
                    deleteTask(userInput.trim());
                } else if (userInput.trim().split("\\s")[0].equalsIgnoreCase("onDate")) {
                    getTasksOnDate(userInput.trim());
                } else {
                    categoriseTask(userInput.trim());
                }
                Storage.saveTasks(taskList);

            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                System.out.println(Constants.LINE);
                start();
            }
        }
    }

    public static void printList(ArrayList<Task> list) {
        boolean allTasksDone = true;
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            int ind = i + 1;
            System.out.println(ind + ". " + list.get(i).toString());
            if (!list.get(i).getStatus()) {
                allTasksDone = false;
            }
        }
        if (allTasksDone) {
            System.out.println("All tasks are complete!!");
        }
        System.out.println(Constants.LINE);
    }

    public void categoriseTask(String input) {
            Task task;
            String[] splitTasks = input.split("\\s", 2);
            String taskType = splitTasks[0].toLowerCase();
            try {
                switch (taskType) {
                    case "todo":
                        if (hasEmptyDesc(splitTasks)) {
                            throw new EmptyDescriptionException(
                                "Sorry, the description of a todo cannot be empty" + Constants.TODO_FORMAT
                            );
                        } else {
                            String desc = splitTasks[1].trim();
                            task = new ToDo(desc);
                            addTaskToList(task);
                        }
                        break;
                     case "deadline":
                        if (hasEmptyDesc(splitTasks)) {
                            throw new EmptyDescriptionException(
                                "Sorry, the description of a deadline cannot be empty" + Constants.DEADLINE_FORMAT
                            );
                        } else {
                            String[] parsedDeadline = splitTasks[1].split("/by");
                                if (hasDateButEmptyDesc(parsedDeadline)) {
                                    throw new EmptyDescriptionException(
                                    "Sorry, the description of a deadline cannot be empty" + Constants.DEADLINE_FORMAT
                                    );
                                } else if (hasEmptyDesc(parsedDeadline)) {
                                    throw new IncorrectFormatException(
                                    "Please add a date for your deadline!" + Constants.DEADLINE_FORMAT);
                                } else {
                                    String desc = parsedDeadline[0].trim();
                                    String date = parsedDeadline[1].trim();
                                    task = new Deadline(desc, DateTimeParser.deadlineDateParse(date));
                                    addTaskToList(task);
                                }
                        }
                        break;
                    case "event":
                    if (hasEmptyDesc(splitTasks)) {
                        throw new EmptyDescriptionException(
                                "Sorry the description of an event cannot be empty" + Constants.EVENT_FORMAT
                        );
                    } else {
                        String[] parsedEvent = splitTasks[1].split("/at");
                        if (hasDateButEmptyDesc(parsedEvent)) {
                            throw new EmptyDescriptionException(
                                    "Sorry the description of an event cannot be empty" + Constants.EVENT_FORMAT
                            );
                        } else if (hasEmptyDesc(parsedEvent)) {
                            throw new IncorrectFormatException(
                                    "Please add a date and time for your event!" + Constants.EVENT_FORMAT);
                        } else {
                            String details = parsedEvent[0].trim();
                            String at = parsedEvent[1].trim();
                            task = new Event(details, DateTimeParser.eventDateTimeParse(at));
                            addTaskToList(task);
                        }
                    }
                    break;
                    default:
                        throw new InvalidKeywordException();
                }
            printAddedTasks(task);
        } catch (ArrayIndexOutOfBoundsException ex) {
            switch (taskType) {
                case "deadline": throw new IncorrectFormatException(
                        "Please specify a description and date for your deadline!" + Constants.DEADLINE_FORMAT);
                case "event": throw new IncorrectFormatException(
                        "Please specify a description, date and time for your event!" + Constants.EVENT_FORMAT);
                default: throw new IncorrectFormatException(
                        "Please specify a description and date/time for your task!");
            }
        }
    }

    public void completeTask(String input) {
        try {
            String[] parsedTask = input.split("\\s", 2);
            String indexOfTask = parsedTask[1].trim();
            int index = Integer.parseInt(indexOfTask);
            if (index > 0 && index > taskList.size()) {
                throw new NonExistentTaskException();
            } else {
                Task currTask = taskList.get((index - 1));
                if (currTask.getStatus()) {
                    throw new RepeatedDoneException();
                } else {
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currTask.toString() + "\n" + Constants.LINE);
                }
            }
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }

    public void deleteTask(String input) {
        try {
            String[] parsedTask = input.split("\\s", 2);
            String indexOfTask = parsedTask[1].trim();
            int index = Integer.parseInt(indexOfTask);
            if (index > 0 && index > taskList.size()) {
                throw new NonExistentTaskException();
            } else {
                Task currTask = taskList.remove(index - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(currTask.toString());
                printRemainingTasks();
            }
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }

    public void printRemainingTasks() {
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list." + "\n" + Constants.LINE);
        } else {
            System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()) + "\n" + Constants.LINE);
        }
    }

    public void printAddedTasks(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printRemainingTasks();
    }

    public boolean hasEmptyDesc(String[] taskArray) {
        return taskArray.length == 1 || taskArray[1].isBlank() || taskArray[1].isEmpty();
    }

    public boolean hasDateButEmptyDesc(String[] taskArray) {
        return (taskArray[0].isBlank() || taskArray[0].isEmpty()) && (!taskArray[1].isBlank());
    }

    public static boolean containsTask(Task task, ArrayList<Task> list) {
        boolean ans = false;
        for (Task value : list) {
            if (value.getDetails().trim().equals(task.getDetails().trim())){
                ans = true;
                break;
            }
        }
        return ans;
    }

    public void addTaskToList(Task task) {
        if (containsTask(task, taskList)) {
            throw new RepeatedTaskException();
        } else {
            taskList.add(task);
        }
    }

    public void getTasksOnDate(String str) {
        ArrayList<Task> list = new ArrayList<>();
        try {
            String[] parsedString = str.split("\\s", 2);
            LocalDate date = DateTimeParser.deadlineDateParse(parsedString[1].trim());

            for (Task task : taskList) {
                if (task instanceof Deadline) {
                    if (((Deadline) task).getDate().equals(date)) {
                        list.add(task);
                    }
                }
                if (task instanceof Event) {
                    if (((Event) task).getDate().equals(date)) {
                        list.add(task);
                    }
                }
            }

            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    int ind = i + 1;
                    System.out.println(ind + ". " + list.get(i).toString());
                }
            } else {
                System.out.println("No tasks are due on " + date + "!");
            }
            System.out.println(Constants.LINE);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new EmptyDescriptionException("Please enter a date to view all the tasks due");
        }
    }

}