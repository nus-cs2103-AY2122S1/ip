import java.util.Scanner;

public class Duke {

    String LOGO = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";;
    String GOODBYE = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        System.out.println("Hello from\n" + LOGO);
        Scanner sc = new Scanner(System.in);
        String currLine;
        while (true) {
            currLine = sc.nextLine();
            if (currLine.equals("bye")) {
                System.out.println(styleResponse(GOODBYE));
                break;
            }
            System.out.println(styleResponse(echo(currLine)));
        }
        
        sc.close();
    }

    public static String echo(String inputString) {
        return inputString;
    }

    public static String styleResponse(String inputString) {
        return "     --------------------\n     " + inputString + "\n     --------------------";
    }
}
