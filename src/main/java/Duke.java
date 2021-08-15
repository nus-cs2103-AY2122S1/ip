import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {

        ArrayList<Task> list = new ArrayList<>();

        String line = "-------------------------------------------------------\n\n";
        // greeting
        System.out.print(
                line +
                "\tHello! I'm Duke\n\n" +
                "\tWhat can I do for you?\n\n" +
                line
        );

        Scanner scanner = new Scanner(System.in);
        try {
            String text;
            boolean leave = false;
            while (!leave) {
                // get text
                text = scanner.nextLine();

                if (text.equals("bye")) {
                    // exit
                    text = "Bye. Hope to see you again soon!";
                    leave = true;
                } else if (text.equals("list")) {
                    // print list
                    if (list.isEmpty()) text = "There are no items in the list.";
                    else {
                        text = "";
                        String status = " ";
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isCompleted()) status = "X";
                            else status = " ";
                            if (i != 0) text += "\t";
                            text += (i + 1) + ".[" + status + "] " + list.get(i).getDetails();
                            if (i != list.size() - 1) text += "\n";
                        }
                    }
                } else if(text.length() > 4 && text.substring(0,4).equals("done")) {
                    // complete task
                    try {
                        int index = Integer.parseInt(text.substring(5));
                        list.get(index - 1).complete();
                        text = "Nice! I've marked this task as done:\n" +
                                "\t\t[X] " + list.get(index - 1).getDetails();
                    } catch(NumberFormatException e) {
                        text = "To complete a task: enter \"done (task number)\"";
                    } catch (IndexOutOfBoundsException e) {
                        text = "Task number does not exist.";
                    }
                } else {
                    // add text to list
                    Task task = new Task(text);
                    list.add(task);
                    text = "added new task: " + text;
                }

                // print text
                System.out.print(
                        "\n" + line +
                        "\t" + text + "\n" +
                        "\n" + line
                );
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            System.out.println(e);
        }
    }
}
