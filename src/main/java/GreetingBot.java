import java.util.Scanner;
import java.util.LinkedList;
public class GreetingBot {

    public GreetingBot() {

    }

    public void startBot() {
        greet();
        store();
        exit();
    }


    private void greet() {
        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMessage);

    }

    private void store() {
        Scanner inputScanner = new Scanner(System.in);
        LinkedList<Task> myList = new LinkedList<>();
        int counter = 1;
        while (true) {
            String nextLine = inputScanner.nextLine();
            if (nextLine.equals("list")) {
                list(myList);
            } else if  (nextLine.equals("bye")) {
                break;
            } else if (nextLine.startsWith("done")) {
                String[] splitWords = nextLine.split(" ");
                int taskIndex = Integer.parseInt(splitWords[1]);
                myList.get(taskIndex - 1).setDone(true);
            } else {
                System.out.println("Got it. I've added this task:");
                if (nextLine.startsWith("todo")) {
                    String[] splitLine = nextLine.split("todo");
                    String title = splitLine[1];
                    Task nextTask = new Todo(title, counter);
                    myList.add(nextTask);
                    counter += 1;
                    System.out.println(nextTask.getInfo());
                } else if (nextLine.startsWith("deadline")) {
                    String[] splitLine = nextLine.split("/by ");
                    String date = splitLine[1];
                    String title = splitLine[0].split("deadline")[1];
                    Task nextTask = new Deadline(title, counter, date);
                    myList.add(nextTask);
                    counter += 1;
                    System.out.println(nextTask.getInfo());
                } else if (nextLine.startsWith("event")) {
                    String[] splitLine = nextLine.split("/at ");
                    String date = splitLine[1];
                    String title = splitLine[0].split("event")[1];
                    Task nextTask = new Event(title, counter, date);
                    myList.add(nextTask);
                    counter += 1;
                    System.out.println(nextTask.getInfo());
                }
                int totalTasks = myList.size();
                System.out.println("Now you have " + totalTasks + " tasks in the list.");
            }
        }
    }

    private void list(LinkedList<Task> myList) {
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        while(counter < myList.size()) {
            System.out.println(myList.get(counter).toString());
            counter += 1;
        }
    }




    private void echo() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            String nextLine = inputScanner.nextLine();
            if (nextLine.equals("bye")) {
                break;
            } else {
                System.out.println(nextLine);
            }
        }
    }

    private void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
}



