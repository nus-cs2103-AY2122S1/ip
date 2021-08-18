import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String end = "    ---------------------------------------------------------------------------------";
        System.out.println("Hello from\n" + logo);
        String str = sc.nextLine();
        while (true) {
            System.out.println(end + "\n" + "     " + str + "\n" + end);
            str = sc.nextLine();
            if (str.equals("Bye!")) {
                System.out.println(end + "\n" + "     " + "Bye. Hope to see you again soon! :D");
                break;
            }
        }
    }
}
