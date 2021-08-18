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
            else if (input.startsWith("done ") && input.length() >= 6) {
                int pos = Integer.parseInt(input.substring(5)) - 1;

                if (pos < index - 1) {
                    inputs[pos].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(inputs[pos].getStatusIcon() + inputs[pos].description);
                }
                else {
                    System.out.println("There are only " + pos + " tasks!");
                }
            }
            else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i < index; i++) {
                    System.out.println(i + "." + inputs[i - 1].getStatusIcon() + inputs[i - 1].description);
                }
            }
            else {
                Task task = new Task(input);
                inputs[index - 1] = task;
                index++;
                System.out.println("added: " + this.input);
            }
        }
        sc.close();
    }



}
