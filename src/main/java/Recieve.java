import java.util.Scanner;

public class Recieve {
    private static int index = 1;
    private String input;
    private static Task[] inputs = new Task[100];


    public Recieve() {
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.startsWith("done ") && input.length() >= 6 && Character.isDigit(input.charAt(5))) {
                //check 1x ? done x? done 2x?
                int pos = Integer.parseInt(input.substring(5)) - 1;

                if (pos < index - 1) {
                    inputs[pos].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(inputs[pos].toString());
                }
                else {
                    System.out.println("There are only " + pos + " tasks!");
                }
            }
            else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i < index; i++) {
                    System.out.println(i + "." + inputs[i-1].toString());
                }
            }
            else {
                if (input.startsWith("todo ") && input.length() >= 6) {
                    Todo task = new Todo(input.substring(5));
                    inputs[index - 1] = task;
                    index++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + (index - 1) + " tasks in the list.");

                }
                else if (input.startsWith("deadline ") && input.length() >= 10) {
                    String[] spl = input.substring(9).split("/");
                    Deadline task = new Deadline(spl[0], spl[1].substring(3));
                    inputs[index - 1] = task;
                    index++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + (index - 1) + " tasks in the list.");

                }
                else if (input.startsWith("event ") && input.length() >= 7) {
                    String[] spl = input.substring(6).split("/");
                    Event task = new Event(spl[0], spl[1].substring(3));
                    inputs[index - 1] = task;
                    index++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + (index - 1) + " tasks in the list.");

                }
                else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
        sc.close();
    }



}
