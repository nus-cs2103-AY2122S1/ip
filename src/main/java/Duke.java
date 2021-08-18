import java.util.Scanner;

public class Duke {

    private static Task[] myList = new Task[100];
    private static int myListLength = 0;
    public static boolean done = false;

    private static void endBot() {
        done = true;
        System.out.println("Bye for now!");
    }

    private static void listTasks() {
        System.out.println("Here is your to-do list!");
        for (int i = 0; i < myListLength; i++) {
            System.out.println(i + 1 + ". " + myList[i].toString());
        }
    }

    private static void addTask(String input) {
        try {
            if (input.startsWith("todo")) {
                myList[myListLength] = new ToDo(input.substring(5));
            } else if (input.startsWith("deadline")) {
                String remaining = input.substring(9);
                String[] segments = remaining.split(" /");
                myList[myListLength] = new Deadline(segments[0], segments[1]);
            } else if (input.startsWith("event")) {
                String remaining = input.substring(6);
                String[] segments = remaining.split(" /");
                myList[myListLength] = new Event(segments[0], segments[1]);
            } else {
                System.out.println("Invalid task.");
                return;
            }
            System.out.println("I've added this task:");
            System.out.println(myList[myListLength]);
            myListLength++;
            System.out.println("Now you have " + myListLength + " tasks.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid format.");
        }
    }


    private static void markAsDone(String input) {
        String[] segments = input.split(" ");
        try {
            int index = Integer.parseInt(segments[segments.length - 1]);
            Task myTask = myList[index - 1];
            myTask.markAsDone();
            System.out.println("I've marked this task as done:\n[X] " + myTask.description);
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after the keyword: done");
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e ) {
            System.out.println("Please input a valid task index");
        }
    }

    public static void main(String[] args) {
        System.out.println("Sup! I'm Luka, your personal assistant.\n");
        Scanner myScanner = new Scanner(System.in);
        while (!done) {
            String input = myScanner.nextLine();
            if (input.equals("bye")) {
                endBot();
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("done")) {
                markAsDone(input);
            } else {
                addTask(input);
            }
        }
    }
}
