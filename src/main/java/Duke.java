import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected ArrayList<Task> toDoList;
    protected FileIO fio;

    public Duke() {
        this.toDoList = new ArrayList<>(100);
        this.fio = new FileIO();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        loadToDoList();
        greeting();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.equals("bye")) {
                scanner.close();
                bye();
                break;
            }

            try {
                runCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    public void runCommand(String command) throws DukeException {
        if (command.equals("list")) {
            list();
        } else if (command.startsWith("done")) {
            try {
                done(command.substring(5));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please provide a number after the done command that is within the total number of tasks: " + toDoList.size());
            }

        } else if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            try {
                add(command.substring(5), TaskType.TODO);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please write your date and time in the following format: " +
                        "D/MM/YYYY HHMM");
            }
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            try {
                add(command.substring(9), TaskType.DEADLINE);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please write your date and time in the following format: " +
                        "D/MM/YYYY HHMM");

            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please write your deadline command in the following format: " +
                        "deadline task /by datetime");
            }
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            try {
                add(command.substring(6), TaskType.EVENT);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please write your date and time in the following format: " +
                        "D/MM/YYYY HHMM");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please write your event command in the following format: " +
                        "event task /at datetime");
            }
        } else if (command.startsWith("delete")) {
            try {
                delete(command.substring(7));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please provide a number after the delete command that is within the total number of tasks: " + toDoList.size());
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void loadToDoList() {
        ArrayList<String> taskStrings = fio.loadTaskStrings();
        for (String ts: taskStrings) {
            String[] tsData = ts.split(" \\| ");
            String taskType = tsData[0];

            switch (taskType) {
                case "T":
                    toDoList.add(new Todo(tsData[1], tsData[2]));
                    break;
                case "D":
                    toDoList.add(new Deadline(tsData[1], tsData[2], tsData[3]));
                    break;
                case "E":
                    toDoList.add(new Event(tsData[1], tsData[2], tsData[3]));
                    break;
            }
        }

    }

    public void list() {
        if (toDoList.size() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            int num = 1;
            for (Task task: toDoList) {
                System.out.printf("%d.%s%n", num, task);
                num++;
            }
        }

    }


    public void add(String description, TaskType taskType) throws DateTimeParseException, StringIndexOutOfBoundsException {
        if (toDoList.size() < 100) {

            Task task;

            if (taskType == TaskType.TODO) {
                task = new Todo(description);
            } else if (taskType == TaskType.DEADLINE) {
                String newDescription = description.substring(0, description.indexOf(" /by "));
                String by = description.substring(description.indexOf("/by ") + 4);
                task = new Deadline(newDescription, by);
            } else {
                String newDescription = description.substring(0, description.indexOf(" /at "));
                String at = description.substring(description.indexOf("/at ") + 4);
                task = new Event(newDescription, at);
            }

            toDoList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            fio.appendToFile(task.getFileString());
            printNowSize();
        }
    }

    public void delete(String num) {
        int listNum = Integer.parseInt(num);
        Task deletedTask = toDoList.remove(listNum - 1);
        fio.deleteFileLine(listNum - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        printNowSize();

    }

    public void done(String num){
        int listNum = Integer.parseInt(num);
        toDoList.get(listNum - 1).setIsDone(true);
        Task task = toDoList.get(listNum - 1);
        fio.replaceFileLine(task.getFileString(), listNum - 1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(toDoList.get(listNum - 1));

    }

    public void printNowSize() {
        if (toDoList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", toDoList.size());
        }
    }
}
