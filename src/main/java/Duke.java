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
            done(command.substring(5));
        } else if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            add(command.substring(5), TaskType.TODO);
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            add(command.substring(9), TaskType.DEADLINE);
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            add(command.substring(6), TaskType.EVENT);
        } else if (command.startsWith("delete")) {
            delete(command.substring(7));
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
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task: toDoList) {
            System.out.printf("%d.%s%n", num, task);
            num++;
        }
    }



    public void add(String description, TaskType taskType) {
        if (toDoList.size() < 100) {

            System.out.println("Got it. I've added this task:");

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
            fio.appendToFile(task.getFileString());
            System.out.println(task);
            printNowSize();
        }
    }

    public void delete(String num) {
        int listNum = Integer.parseInt(num);
        if (listNum <= toDoList.size()) {
            Task deletedTask = toDoList.remove(listNum - 1);
            fio.deleteFileLine(listNum - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask);
            printNowSize();
        }
    }

    public void done(String num) {
        int listNum = Integer.parseInt(num);
        if (listNum <= toDoList.size()) {
            toDoList.get(listNum - 1).setIsDone(true);
            Task task = toDoList.get(listNum - 1);
            fio.replaceFileLine(task.getFileString(), listNum - 1);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(toDoList.get(listNum - 1));
        }
    }

    public void printNowSize() {
        if (toDoList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", toDoList.size());
        }
    }
}
