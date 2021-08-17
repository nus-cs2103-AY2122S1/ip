import java.util.*;

public class Duke {
    private static final String HELLO = "\nHello! I'm Duke\nWhat can I do for you?\n";
    private static final String LINE = "===============================================";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String BYE = "Bye. Hope to see you again soon!\n";

    private static final String TODO_FORMAT = "\nPlease use the following format: todo [todo_description]";
    private static final String DEADLINE_FORMAT = "\nPlease use the following format: deadline [deadline_description] /by [deadline_date]";
    private static final String EVENT_FORMAT = "\nPlease use the following format: event [event_description] /at [event_date_and_time]";

    private final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        System.out.println(logo + HELLO + LINE);
        chatbot.start();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        userInput = userInput.trim();
        try {
            while (!userInput.equalsIgnoreCase("bye")) {
                if (userInput.equalsIgnoreCase("list")) {
                    printList(taskList);
                } else if (userInput.split("\\s")[0].equalsIgnoreCase("done")) {
                    completeTask(userInput);
                } else {
                    categoriseTask(userInput);
                }
                userInput = sc.nextLine();
            }
            System.out.println(BYE + LINE);
            sc.close();
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
            System.out.println(LINE);
            start();
        }
    }

    public void printList(ArrayList<Task> list) {
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
        System.out.println(LINE);
    }

    public void categoriseTask(String inp) {
            Task task;
            String[] splitTasks = inp.split("\\s", 2);
            String taskType = splitTasks[0].toLowerCase();
            try {
                switch (taskType) {
                    case "todo":
                        if (hasEmptyDesc(splitTasks)) {
                            throw new EmptyDescriptionException(
                                    "Sorry, the description of a todo cannot be empty" + TODO_FORMAT
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
                                    "Sorry, the description of a deadline cannot be empty" + DEADLINE_FORMAT
                            );
                        } else {
                            String[] parsedDeadline = splitTasks[1].split("/by");
                            if (hasDateButEmptyDesc(parsedDeadline)) {
                                throw new EmptyDescriptionException(
                                        "Sorry, the description of a deadline cannot be empty" + DEADLINE_FORMAT
                                );
                            } else if (hasEmptyDesc(parsedDeadline)) {
                                throw new IncorrectFormatException(
                                        "Please add a date for your deadline!" + DEADLINE_FORMAT);
                            } else {
                                String desc = parsedDeadline[0].trim();
                                String date = parsedDeadline[1].trim();
                                task = new Deadline(desc, date);
                                addTaskToList(task);
                            }
                        }
                        break;
                    case "event":
                        if (hasEmptyDesc(splitTasks)) {
                            throw new EmptyDescriptionException(
                                    "Sorry the description of an event cannot be empty" + EVENT_FORMAT
                            );
                        } else {
                            String[] parsedEvent = splitTasks[1].split("/at");
                            if (hasDateButEmptyDesc(parsedEvent)) {
                                throw new EmptyDescriptionException(
                                        "Sorry the description of an event cannot be empty" + EVENT_FORMAT
                                );
                            } else if (hasEmptyDesc(parsedEvent)) {
                                throw new IncorrectFormatException(
                                        "Please add a date and time for your event!" + EVENT_FORMAT);
                            } else {
                                String details = parsedEvent[0].trim();
                                String at = parsedEvent[1].trim();
                                task = new Event(details, at);
                                addTaskToList(task);
                            }
                        }
                        break;
                    default:
                        throw new InvalidKeywordException();
                }
                printAddedTasks(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                switch (taskType) {
                    case "deadline": throw new IncorrectFormatException(
                            "Please specify a description and date for your deadline!" + DEADLINE_FORMAT);
                    case "event": throw new IncorrectFormatException(
                            "Please specify a description, date and time for your event!" + EVENT_FORMAT);
                    default: throw new IncorrectFormatException(
                            "Please specify a description and date/time for your task!");
                    }
                }
    }

    public void completeTask(String input) {
        try {
            String indexOfTask = input.substring(5).trim();
            int index = Integer.parseInt(indexOfTask);
            Task currTask = taskList.get((index - 1));
                if (currTask.getStatus()) {
                    throw new RepeatedDoneException();
                } else {
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currTask.toString() + "\n" + LINE);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                throw new InvalidDoneException();
            }
    }

    public void printRemainingTasks() {
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list." + "\n" + LINE);
        } else {
            System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()) + "\n" + LINE);
        }
    }

    public void printAddedTasks(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printRemainingTasks();
    }

    public boolean hasEmptyDesc(String[] taskArray) {
        return taskArray.length == 1 || taskArray[1].isEmpty() || taskArray[1].isBlank();
    }

    public boolean hasDateButEmptyDesc(String[] taskArray) {
        return taskArray[0].isBlank() || taskArray[0].isEmpty();
    }

    public boolean containsTask(Task task, ArrayList<Task> list) {
       boolean ans = false;
        for (Task value : list) {
            if (value.getDescription().equals(task.getDescription())) {
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

}