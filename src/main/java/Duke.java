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
        String added[] = new String[100];
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
            } else if (str.equals("list")) {
                System.out.println(horizontalLines);
                for (int j = 0; j < i; j++) {
                    int num = j + 1;
                    System.out.println(num + ". " + added[j]);
                }
                System.out.println(horizontalLines);
            }
            else {
                System.out.println(horizontalLines);
                System.out.println( "added: " + str);
                added[i] = str;
                System.out.println(horizontalLines);
                i++;
            }
        }




    }
}

