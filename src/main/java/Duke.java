import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String horizontalLines = "---------------------------------";
        System.out.println(horizontalLines);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(horizontalLines);

        boolean end = false;
        Task added[] = new Task[100];
        int i = 0;

        while (!end) {
            System.out.print("Enter a text: ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equals("bye")) {
                end = true;
                System.out.println(horizontalLines);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLines);
            } else if (str.length() >= 6 && str.substring(0, 4).equals("done")) {
                int a = Integer.parseInt(str.substring(5)) - 1;
                added[a].taskDone();
                System.out.println(horizontalLines);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("" + added[a].getTask());
            }
            else if (str.equals("list")) {
                System.out.println(horizontalLines);
                for (int j = 0; j < i; j++) {
                    int num = j + 1;
                    System.out.println(num + "." + added[j].getTask());
                }
                System.out.println(horizontalLines);
            }
            else {
                System.out.println(horizontalLines);
                added[i] = new Task(str);
                System.out.println( "added: " + str);
                System.out.println(horizontalLines);
                i++;
            }
        }




    }
}

