package duke;
import java.io.IOException;
import java.time.LocalDateTime;

public class Parser {
    public static String parseCommand(String command) {
        String keyWord = "";
        if (command.equals("list")) {
            keyWord = "list";
        } else if (command.startsWith("done")) {
            keyWord = "done";
        } else if (command.startsWith("delete")) {
            keyWord = "delete";
        } else if (command.startsWith("todo")) {
            keyWord = "todo";
        } else if (command.startsWith("deadline")) {
            keyWord = "deadline";
        } else if (command.startsWith("event")) {
            keyWord = "event";
        } else if (command.startsWith("today")) {
            keyWord = "today";
        } else if (command.startsWith("bye")) {
            keyWord = "bye";
        }
        return keyWord;
    }

    public static void parseDone(String instruction) throws DukeException, IOException {
        int taskNum = Integer.parseInt(instruction.substring(5)) - 1;
        if (taskNum >= TaskList.getCounter()) {
            throw new DukeException("\tHmm, I don't have task " + (taskNum + 1) +
                    " in my list. Please key in 'list' if you'd like to " +
                    "view your list of tasks again!");
        } else {
            TaskList.getTaskList().get(taskNum).markAsDoneAndUpdate();
            System.out.println("\tGood job! I've marked this task as done:");
            System.out.println("\t\t" + TaskList.getTaskList().get(taskNum).toString());
        }
    }

    public static void parseDelete(String instruction) throws DukeException, IOException {
        int taskNum = Integer.parseInt(instruction.substring(7)) - 1;
        if (taskNum >= TaskList.getCounter()) {
            throw new DukeException("\tHmm, I don't have task " + (taskNum + 1) +
                    " in my list. Please key in 'list' if you'd like to " +
                    "view your list of tasks again!");
        } else {
            System.out.println("\tNoted. I've removed this task from your main list:");
            System.out.println("\t\t" + TaskList.getTaskList().get(taskNum).toString());
            TaskList.deleteTaskAndUpdate(TaskList.getTaskList().get(taskNum));
            System.out.println("\tNow you have " + TaskList.getCounter() +
                    " task(s) in the list.");
        }
    }

    public static void parseTodo(String instruction) throws DukeException, IOException {
        if (instruction.length() < 5) {
            throw new DukeException("\tOOPS!!! The description of a todo " +
                    "cannot be empty.");
        } else {
            String taskDescription = instruction.substring(4);
            Todo newTodo = new Todo(taskDescription);
            TaskList.addTaskAndUpdate(newTodo);
            System.out.println("\tGot it! I've added this task:");
            System.out.println("\t\t" + newTodo.toString());
            System.out.println("\tNow you have " + TaskList.getCounter() +
                    " task(s) in the list.");
        }
    }

    public static void parseDeadline(String instruction) throws DukeException, IOException {
        if (instruction.length() < 10) {
            throw new DukeException("\tOOPS!!! The description of a deadline " +
                    "cannot be empty.");
        } else {
            String taskDescription = "";
            int currIndex = 8;
            while (currIndex < instruction.length() &&
                    !instruction.substring(currIndex).startsWith(" /")) {
                taskDescription += instruction.substring(currIndex, currIndex + 1);
                currIndex++;
            }
            if (currIndex == instruction.length() ||
                    currIndex + 5 >= instruction.length()) {
                throw new DukeException("I think you forgot to key in your deadline! Please key it" +
                        " in as dd/mm/yyyy hh:mm (in 24 hours format)");
            } else if (instruction.charAt(currIndex + 7) != '/' &&
                    instruction.charAt(currIndex + 10) != '/') {
                throw new DukeException("Please format the date as dd/mm/yyy");
            } else if (instruction.substring(currIndex).length() < 20){
                throw new DukeException("Please include the time in the 24 hour " +
                        "format (e.g. 15:00)");
            } else {
                String by = instruction.substring(currIndex + 5);
                Task newDeadline = new Deadline(taskDescription, by);
                TaskList.addTaskAndUpdate(newDeadline);
                System.out.println("\tGot it! I've added this task:");
                System.out.println("\t\t" + newDeadline.toString());
                System.out.println("\tNow you have " + TaskList.getCounter() +
                        " task(s) in the list.");
            }
        }
    }

    public static void parseEvent(String instruction) throws DukeException, IOException {
        if (instruction.length() < 7) {
            throw new DukeException("\tOOPS!!! The description of a event " +
                    "cannot be empty.");
        } else {
            String taskDescription = "";
            int currIndex = 5;
            while (currIndex < instruction.length() &&
                    !instruction.substring(currIndex).startsWith(" /")) {
                taskDescription += instruction.substring(currIndex, currIndex + 1);
                currIndex++;
            }
            if (currIndex == instruction.length() ||
                    currIndex + 5 >= instruction.length()) {
                throw new DukeException("\tI think you forgot to key in your event timing!");
            } else if (instruction.charAt(currIndex + 7) != '/' &&
                    instruction.charAt(currIndex + 10) != '/') {
                throw new DukeException("Please format the date as dd/mm/yyy");
            } else if (instruction.substring(currIndex).length() < 20){
                throw new DukeException("Please include the time in the 24 hour " +
                        "format (e.g. 15:00)");
            } else {
                String by = instruction.substring(currIndex + 5);
                Task newEvent = new Event(taskDescription, by);
                TaskList.addTaskAndUpdate(newEvent);
                System.out.println("\tGot it! I've added this task:");
                System.out.println("\t\t" + newEvent.toString());
                System.out.println("\tNow you have " + TaskList.getCounter() +
                        " task(s) in the list.");
            }
        }
    }

    public static void parseList() throws DukeException, IOException {
        int num = 1;
        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            System.out.println("\t" + num + "." +
                    TaskList.getTaskList().get(i).toString());
            num++;
        }
    }

    public static void parseToday() throws DukeException, IOException {
        System.out.println("\tTasks scheduled for today are: ");
        int num = 1;
        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            if (TaskList.getTaskList().get(i) instanceof Todo) {
                System.out.println("\t\t" + num + "." +
                        TaskList.getTaskList().get(i).toString());
                num++;
            } else {
                int currYear = LocalDateTime.now().getYear();
                int currMonth = LocalDateTime.now().getMonthValue();
                int currDate = LocalDateTime.now().getDayOfMonth();
                LocalDateTime start = LocalDateTime.of(currYear, currMonth, currDate, 0, 0);
                LocalDateTime end = LocalDateTime.of(currYear, currMonth, currDate, 23, 59);

                if (TaskList.getTaskList().get(i) instanceof Deadline &&
                        (((Deadline) TaskList.getTaskList().get(i)).getDeadline()).isAfter(start) &&
                        (((Deadline) TaskList.getTaskList().get(i)).getDeadline()).isBefore(end)) {
                    System.out.println("\t\t" + num + "." +
                            TaskList.getTaskList().get(i).toString());
                    num++;
                } else if (TaskList.getTaskList().get(i) instanceof Event &&
                        (((Event) TaskList.getTaskList().get(i)).getEventTime()).isAfter(start) &&
                        (((Event) TaskList.getTaskList().get(i)).getEventTime()).isBefore(end)) {
                    System.out.println("\t\t" + num + "." +
                            TaskList.getTaskList().get(i).toString());
                    num++;
                }
            }
        }
        if (num == 1) {
            System.out.println("\t\tLooks like there is nothing due today!");
        }
    }
}
