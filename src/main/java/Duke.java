import java.util.Scanner;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int counter = 0;
    public Duke(){}

    public static String getFirstWord(Scanner scanner){
        return scanner.next();
    }

    public static void dukeAction(Scanner scanner) {
        String inp = getFirstWord(scanner);

        while(!inp.equals("bye")) {
            System.out.println("----------------------------------------------------");
            switch (inp) {
                case "list":
                    System.out.println( "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.length; i++) {
                        if (taskList[i] == null) break;
                        System.out.printf("\t%s." + taskList[i].toString() + "%n", i + 1);
                    }
                    System.out.println("----------------------------------------------------");
                    inp = getFirstWord(scanner);
                    break;

                case "done":
                    int doneNumber = Integer.parseInt(scanner.next()) - 1;
                    taskList[doneNumber].setDone();
                    System.out.println("Nice! I've marked this task as done:\n"
                                        + taskList[doneNumber].toString() +
                            "\n----------------------------------------------------");
                    inp = getFirstWord(scanner);
                    break;

                case "todo":
                    Todo todo = new Todo(scanner.nextLine().trim());
                    todo.addToList(taskList, counter);
                    counter++;
                    inp = getFirstWord(scanner);
                    break;

                case "deadline":
                    String[] taskTimeD = Deadline.getDeadlineTaskAndTime(scanner);
                    Deadline deadline = new Deadline(taskTimeD[0].trim(), taskTimeD[1]);
                    deadline.addToList(taskList, counter);
                    counter++;
                    inp = getFirstWord(scanner);
                    break;

                case "event":
                    String[] taskTimeE = Event.getEventTaskAndTime(scanner);
                    Event event = new Event(taskTimeE[0].trim(), taskTimeE[1]);
                    event.addToList(taskList, counter);
                    counter++;
                    inp = getFirstWord(scanner);
                    break;

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        dukeAction(scanner);
    }
}
