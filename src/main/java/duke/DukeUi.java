package duke;

import java.util.Scanner;

public class DukeUi {
    private static Scanner sc;
    private static String selfIntro = "*Quack* *Quack*, I'm Duck\nWhat do you need?";
    private static String goodBye = "See ya next time! *quack* *quack* *quack*";
    //private static String logo;

    public static void greet() {
        //System.out.println(logo);
        printLine(selfIntro);
        sc = new Scanner(System.in);
    }

    public static void sayBye() {
        printLine(goodBye);
        sc.close();
    }

    public static void printError(String error) {
        printLine(error);
    }

    public static void printLine(String content) {
        System.out.println(
                "------------------------------------------------\n"
                        + "Duck says:\n"
                        + content
                        + "\n------------------------------------------------"
        );

    }

    public static String readCommand() {
        String input = sc.nextLine();
        return input;
    }
}
