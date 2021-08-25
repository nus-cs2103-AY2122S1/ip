package duke;

import java.util.Scanner;

public class Ui {
    private static final String divider = "\t____________________________________________________________\n";

    public void greet() {
        dukePrint("Hello! I'm Duke.\n" + "What can I do for you?");
    }

    public static void dukePrint(String str) {
        Scanner scanner = new Scanner(str);
        System.out.print(divider);
        while (scanner.hasNextLine()) {
            System.out.print("\t");
            System.out.println(scanner.nextLine());
        }
        System.out.print(divider);
    }

    public void showLoadingError() {
        dukePrint("Error with loading save file. New save will be created.");
    }

    public void printError(String errorMessage) {
        dukePrint("☹ OOPS!!! " + errorMessage);
    }
}
