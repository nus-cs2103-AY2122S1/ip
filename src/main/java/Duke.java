import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dash = "__________________________________";

        System.out.println("Hello from\n" + logo);
        System.out.println(dash);
        System.out.println("Howdy! I'm Duke" + '\n'+ "How may I assist you?");
        System.out.println(dash);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // ARRAY FORM
        // Task tasks[] = new Task[100];
        ArrayList<Task> tasks = new ArrayList<Task>();
        int counter = 0;



        while (true) {
            userInput = scanner.nextLine();

            if (userInput.matches("bye")) {
                System.out.println(dash);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(dash);
                break;

            } else if (userInput.matches("list")){
                System.out.println(dash);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    Task currTask = tasks.get(i);
                    System.out.println(i+1 + "." + currTask.toString());
                }
                System.out.println(dash);

            } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("done")){

                if (userInput.substring(4).length() == 0) {
                    System.out.println(dash + '\n' + "Sorry, which task do you wish to mark as completed?" + '\n'+ dash);
                    // throw new DukeException("User has not indicated task to mark as complete.");

                } else {
                    Integer index = Integer.parseInt(userInput.substring(5));
                    Task currTask = tasks.get(index - 1);
                    currTask.completeTask();

                    System.out.println(dash);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currTask.toString());
                    System.out.println(dash);
                }

            } else if (userInput.length() > 5 && userInput.substring(0, 6).matches("delete")){

                if (userInput.substring(6).length() == 0) {
                    System.out.println(dash + '\n' + "Sorry, which task do you wish to delete?" + '\n'+ dash);
                    // throw new DukeException("User has not indicated task to delete.");

                } else {
                    Integer index = Integer.parseInt(userInput.substring(7));
                    Task currTask = tasks.get(index - 1);
                    tasks.remove(currTask);

                    System.out.println(dash);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(currTask.toString());

                    counter -= 1;
                    System.out.println("You now have " + counter + " tasks in your list!");
                    System.out.println(dash);
                }

            } else if (userInput.length() > 3 && userInput.substring(0, 4).matches("todo")) {

                if (userInput.substring(4).length() == 0) {
                    System.out.println(dash + '\n' + "YIKES!! The description of a todo cannot be empty!" + '\n'+ dash);
                    // throw new DukeException("YIKES!! The description of a todo cannot be empty!");
                } else {
                    tasks.add(new ToDo(userInput.substring(5)));

                    System.out.println(dash);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(counter).toString());
                    counter += 1;

                    System.out.println("You now have " + counter + " task(s) in the list.");
                    System.out.println(dash);
                }


            } else if (userInput.length() > 4 && userInput.substring(0, 5).matches("event")) {

                if (userInput.substring(5).length() == 0) {
                    System.out.println(dash + '\n' + "YIKES!! The description of an Event cannot be empty!" + '\n'+ dash);
                    // throw new DukeException("YIKES!! The description of an Event cannot be empty!");
                } else {
                    String output = userInput.substring(6);
                    String[] info = output.split("/");
                    tasks.add(new Event(info[0], info[1].substring(3)));

                    System.out.println(dash);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(counter).toString());
                    counter += 1;

                    System.out.println("You now have " + counter + " task(s) in the list.");
                    System.out.println(dash);
                }

            } else if (userInput.length() > 7 && userInput.substring(0, 8).matches("deadline")) {

                if (userInput.substring(8).length() == 0) {
                    System.out.println(dash + '\n' + "YIKES!! The description of a Deadline cannot be empty!" + '\n'+ dash);
                    // throw new DukeException("YIKES!! The description of a Deadline cannot be empty!");
                } else {
                    String output = userInput.substring(9);
                    String[] info = output.split("/");
                    tasks.add(new Deadline(info[0], info[1].substring(3)));

                    System.out.println(dash);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(counter).toString());
                    counter += 1;

                    System.out.println("You now have " + counter + " task(s) in the list.");
                    System.out.println(dash);
                }

            } else {
                System.out.println(dash + '\n' + "OOPS!! I don't know how to respond to this command! :-(" + '\n'+ dash);
                // throw new DukeException("OOPS!! I don't know how to respond to this command! :-(");
            }
        }
    }
}
