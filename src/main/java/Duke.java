import java.util.Scanner;

//note that automated testing script simply doesn't work
//for some reason terminal is unable to detect FC even though i checked and even manually added
//system32 to path. The window also disappears extremely fast, and a pause command does not prevent this

public class Duke {
    private static Task[] store = new Task[100];
    private static int index = 0;

    //subroutine for adding tasks to the array of tasks
    public static void addTask(String descriptor) {
        if (descriptor.startsWith("todo")) {
            descriptor = descriptor.replaceFirst("todo ", "");
            store[index] = new Todo(descriptor);
        } else if (descriptor.startsWith("deadline")) {
            descriptor = descriptor.replaceFirst("deadline ", "");
            descriptor = descriptor.replaceFirst("/by ", "(by: ");
            descriptor = descriptor + ")";
            store[index] = new Deadline(descriptor);
        } else if (descriptor.startsWith("event")) {
            descriptor = descriptor.replaceFirst("event ", "");
            descriptor = descriptor.replaceFirst("/at ", "(at: ");
            descriptor = descriptor + ")";
            store[index] = new Event(descriptor);
        } else {
            //if necessary, future error handling goes here.
            //current assumption is no input errors.
            System.out.println("ADD ERROR");
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + store[index]);
        System.out.println("Now you have " + (index + 1) + " tasks in the list.");
        index++;
    }

    public static void main(String[] args) {
        System.out.println(
                "░▄░█░░░▄▀▀▀▀▀▄░░░█░▄░\n" +
                "▄▄▀▄░░░█─▀─▀─█░░░▄▀▄▄\n" +
                "░░░░▀▄▒▒▒▒▒▒▒▒▒▄▀░░░░\n" +
                "░░░░░█────▀────█░░░░░\n" +
                "░░░░░█────▀────█░░░░░\n");
        System.out.println("I'm Frosty! How can I help?");

        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")) {
            if (in.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + store[i]);
                }
            } else if (in.startsWith("done")) {
                String[] temp = in.split(" ");
                store[Integer.parseInt(temp[1]) - 1].setFlag(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + store[Integer.parseInt(temp[1]) - 1]);
            } else if (in.startsWith("todo") || in.startsWith("deadline") || in.startsWith("event")) {
                addTask(in);
            } else {
                //possibly useless from task 4 onwards. For default tasks
                //left here for now. Possibly remove in future levels.
                System.out.println("added: " + in);
                store[index] = new Task(in);
                index++;
            }
            in = sc.nextLine();
        }

        System.out.println("Have a Merry Christmas and a Happy New Year!");
    }
}
