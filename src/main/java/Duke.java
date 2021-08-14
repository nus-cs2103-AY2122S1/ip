import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            String response = sc.next();
            flag = echo(response);
        }
    }

    public static boolean echo(String response) {
        switch (response) {
            case "bye":
                System.out.println("Bye, see you soon.");
                return false;
            default:
                System.out.println(response);
                return true;
        }
    }
}
