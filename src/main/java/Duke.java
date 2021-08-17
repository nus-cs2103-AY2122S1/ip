import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    private static void getLstItems() {
        int counter = 1;
        for (Task task : tasks) {
            System.out.println(String.valueOf(counter) + "." + task.toString());
            counter += 1;
        }
    }

    private static void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println(String.format("Great, I've added this task:\n  %s", newTask.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
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
            } else if (userInput.substring(0, 5).equals("done ")) {
                System.out.println(horizontalLines);
                int taskNum = Integer.valueOf(userInput.substring(5)) - 1;
                Task currTask = tasks.get(taskNum);
                currTask.markAsDone();
                System.out.println(horizontalLines);
            } else if (userInput.substring(0, 5).equals("todo ")) {
                System.out.println(horizontalLines);
                ToDos newToDo = new ToDos(userInput.substring(5));
                addTask(newToDo);
                System.out.println(horizontalLines);
            } else if (userInput.substring(0, 9).equals("deadline ")) {
                System.out.println(horizontalLines);
                int sep = userInput.indexOf('/', 9);
                String descPart = userInput.substring(9, sep);
                String byPart = userInput.substring(sep+1);
                Deadlines newDeadline = new Deadlines(descPart, byPart);
                addTask(newDeadline);
                System.out.println(horizontalLines);
            } else if (userInput.substring(0, 6).equals("event ")) {
                System.out.println(horizontalLines);
                int sep = userInput.indexOf('/', 6);
                String descPart = userInput.substring(6, sep);
                String atPart = userInput.substring(sep+1);
                Events newEvent = new Events(descPart, atPart);
                addTask(newEvent);
                System.out.println(horizontalLines);
            } else {
                // All other cases add it to tasks
                System.out.println(horizontalLines);
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                System.out.println("added: " + userInput + "\n" + horizontalLines);
            }
        }

    }

}