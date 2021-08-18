import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
//        Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<Task>();
        int counter = 0;

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (check) {
            String s = sc.nextLine();
            String[] strArray = s.split(" ");

            if (s.equals("bye")) {
                check = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (s.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + tasks.get(i).toString());
                }
            } else if (strArray[0].equals("done")) {
                int idx = Integer.parseInt(strArray[1]) - 1;
                tasks.get(idx).setDone();
                System.out.println("Nice! I've marked this task as done: \n" + "\t" + tasks.get(idx).toString());
            } else if (strArray[0].equals("todo")) {
                String description = new String();
                for (int i = 1; i < strArray.length; i++) {
                    description = description + strArray[i] + " ";
                }

                tasks.add(new Todo(description));
                System.out.println("Got it. I've added this task:\n" + "\t" + tasks.get(counter).toString());
                counter++;
                System.out.println("Now you have " + counter + " tasks in your list.");
            } else if (strArray[0].equals("deadline")) {
                String description = new String();
                String date = new String();
                for (int i = 1; i < strArray.length; i++) {
                    if (strArray[i].equals("/by")) {
                        i++;
                        while (i < strArray.length) {
                            date = " " + date + strArray[i];
                            i++;
                        }
                        break;
                    }
                    description = description + strArray[i] + " ";
                }

                if (date.equals("")) {
                    System.out.println("Invalid input. Please try again.");
                } else {
                    tasks.add(new Deadline(description, date));
                    System.out.println("Got it. I've added this task:\n" + "\t" + tasks.get(counter).toString());
                    counter++;
                    System.out.println("Now you have " + counter + " tasks in your list.");
                }
            } else if (strArray[0].equals("event")) {
                String description = new String();
                String date = new String();
                for (int i = 1; i < strArray.length; i++) {
                    if (strArray[i].equals("/at")) {
                        i++;
                        while (i < strArray.length) {
                            date = " " + date + strArray[i];
                            i++;
                        }
                        break;
                    }
                    description = description + strArray[i] + " ";
                }

                if (date.equals("")) {
                    System.out.println("Invalid input. Please try again.");
                } else {
                    tasks.add(new Event(description, date));
                    System.out.println("Got it. I've added this task:\n" + "\t" + tasks.get(counter).toString());
                    counter++;
                    System.out.println("Now you have " + counter + " tasks in your list.");
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
