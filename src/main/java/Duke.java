import java.util.Scanner;

public class Duke {

    public static String echo(String inputString) {
        return inputString;
    }

    public static String styleResponse(String inputString) {
        return "     --------------------\n     " + inputString + "\n     --------------------";
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        while (true) {
            String currLine = sc.nextLine();
            if (currLine.equals("bye")) {
                System.out.println(styleResponse("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(styleResponse(echo(currLine)));
        }
        sc.close();
    }
}
