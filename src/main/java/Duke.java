import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    private static void getLstItems() {
        int counter = 1;
        for (Task task : tasks) {
            System.out.println(counter + "." + task.toString());
            counter += 1;
        }
    }
    private static void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.printf("Great, I've added this task:\n  %s%n", newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }
    private static void handleToDo(String userInput) throws EmptyTaskDescriptionException {
        try {
            ToDos newToDo = new ToDos(userInput.substring(5));
            addTask(newToDo);
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("todo");
        }
    }
    private static void handleDeadline(String userInput) throws EmptyTaskDescriptionException {
        try {
            int sep = userInput.indexOf('/', 9);
            String descPart = userInput.substring(9, sep);
            String byPart = userInput.substring(sep+1);
            Deadlines newDeadline = new Deadlines(descPart, byPart);
            addTask(newDeadline);
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("deadline");
        }
    }
    private static void handleEvent(String userInput) throws EmptyTaskDescriptionException {
        try {
            int sep = userInput.indexOf('/', 6);
            String descPart = userInput.substring(6, sep);
            String atPart = userInput.substring(sep+1);
            Events newEvent = new Events(descPart, atPart);
            addTask(newEvent);
        } catch (StringIndexOutOfBoundsException strE) {
            throw new EmptyTaskDescriptionException("event");
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLines = "-----------------------------------------";
        System.out.println("Hello! I'm Naruto\nWhat can I do for you?\n" + horizontalLines);
        Scanner in = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(horizontalLines);
                System.out.println("See ya! Hope to see you again!" + "\n" + horizontalLines);
                return;
            } else if (userInput.equals("list")) {
                System.out.println(horizontalLines);
                System.out.println("Here are the tasks in your list:");
                getLstItems();
                System.out.println(horizontalLines);
            } else if (userInput.startsWith("done")) {
                System.out.println(horizontalLines);
                int taskNum = Integer.parseInt(userInput.substring(5)) - 1;
                Task currTask = tasks.get(taskNum);
                currTask.markAsDone();
                System.out.println(horizontalLines);
            } else if (userInput.startsWith("todo")) {
                System.out.println(horizontalLines);
                try {
                    handleToDo(userInput);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(horizontalLines);
                }
            } else if (userInput.startsWith("deadline")) {
                System.out.println(horizontalLines);
                try {
                    handleDeadline(userInput);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(horizontalLines);
                }
            } else if (userInput.startsWith("event")) {
                System.out.println(horizontalLines);
                try {
                    handleEvent(userInput);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(horizontalLines);
                }
            } else {
                // All other cases means input error
                System.out.println(horizontalLines);
                UnknownInputException e = new UnknownInputException();
                try {
                    throw e;
                } catch (UnknownInputException e1) {
                    System.out.println(e1.getMessage());
                } finally {
                    System.out.println(horizontalLines);
                }
            }
        }
    }
}
