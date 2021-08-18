import java.util.Scanner;

public class Duke {
    private static void printline() {
//        printPadding();
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void printPadding() {
        for (int i = 0; i < 5; i++) {
            System.out.print(" ");
        }
    }

    private static void printStatement(String statement) {
        System.out.println();
        printline();
//        printPadding();
        System.out.println(statement);
        System.out.println();
        printline();
        System.out.println();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printStatement("你好! 我是杜克\n能为您做什么吗？\n");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            printStatement(command);
            command = sc.nextLine();
        }
        printStatement("再见，请再光临！");



    }
}
