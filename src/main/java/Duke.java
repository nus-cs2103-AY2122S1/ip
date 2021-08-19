import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //print a list
    public void printList(ArrayList<Task> l) {
        System.out.println("Here are the tasks on your list: ");
        for (int i = 1; i <= l.size(); i++) {
            System.out.println(i + ". " + l.get(i-1).printTask());
        }
    }

    //mark a task as done
    public void markAsDone(String str, ArrayList<Task> l) throws OutOfBoundException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > l.size()) {
            throw new OutOfBoundException();
        } else {
            Task task = l.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I have marked this task as done!");
            System.out.println(task.printTask());
        }
    }

    //method to add a task to a list
    public void addTask(String str, ArrayList<Task> l) throws EmptyDescriptionException, InvalidTaskException{
        //first check if the task only contain 1 word
        if (str.split(" ").length == 1) {
            //check if task if valid
            if (str.startsWith("todo") || str.startsWith("deadline")) {
                throw new EmptyDescriptionException();
            }
            else if (str.startsWith("event")) {
                throw new EmptyDescriptionException();
            }
            //check for invalid task
            else {
                throw new InvalidTaskException();
            }
        }

        //task that contain more than 1 word.
        //check for invalid task. otherwise, add the correct task to the list.
        else {
            //check for invalid task
            if (!str.startsWith("todo") && !str.startsWith("deadline") && !str.startsWith("event")) {
                throw new InvalidTaskException();
            }

            //add correct task to the list
            else {
                System.out.println("Got it. I've added this task.");
                if (str.startsWith("todo")) {
                    l.add(new ToDos(str));
                } else if (str.startsWith("deadline")) {
                    String[] message = str.split("/by");
                    l.add(new Deadline(message[0], message[1]));
                } else {
                    String[] message = str.split("/at");
                    l.add(new Events(message[0], message[1]));
                }
                System.out.println(l.get(l.size() - 1).printTask());
                System.out.println("Now you have " + l.size()
                        + (l.size() == 1 ? " task in the list" : " tasks in the list."));
            }
        }
    }

    //delete a task
    public void deleteTask(String str, ArrayList<Task> l) throws OutOfBoundException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > l.size()) {
            throw new OutOfBoundException();
        } else {
            Task task = l.get(index - 1);
            l.remove(index - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task.printTask());
            System.out.println("Now you have " + l.size()
                    + (l.size() == 1 ? " task in the list" : " tasks in the list."));
        }
    }

    public static void main(String[] args) throws InvalidTaskException, EmptyDescriptionException, OutOfBoundException {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! This is Duke :)" + "\n" + "What can I do for you?");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            //print out list of task
            if (input.equals("list")) {
                duke.printList(list);
            }

            //mark a task as done
            else if (input.length() >= 4 && input.startsWith("done")) {
                try {
                    duke.markAsDone(input, list);
                } catch (OutOfBoundException e) {
                    System.out.println(e.toString());
                } catch (NumberFormatException e) {
                    System.out.println("Task does not exist. Please send a correct number ><");
                }
            }

            else if (input.startsWith("delete")) {
                try {
                    duke.deleteTask(input, list);
                } catch (NumberFormatException e) {
                    System.out.println("Task does not exist. Please send a correct number ><");
                } catch (OutOfBoundException e) {
                    System.out.println(e.toString());
                }
            }

            //add new task
            else {
                try {
                    duke.addTask(input, list);
                } catch (InvalidTaskException e) {
                    System.out.println(e.toString());
                } catch (EmptyDescriptionException e) {
                    System.out.println("☹ OOPS!!! The description of a" + input + "cannot be empty.");
                }
            }
            input = sc.nextLine();
        }

        System.out.println("Bye! See you again soon!!");
    }
}
