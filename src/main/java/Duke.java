import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        displayWithStyle(beginScript());
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            switch (userInput) {
                case "bye":
                    displayWithStyle(endScript());
                    break;
                default:
                    displayWithStyle(userInput);
            }
        }

    }

    public static String beginScript() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return ("Hello from\n" + logo);
    }
//    public static void echo(String text) {
//        System.out.println(text);
//    }

    public static String endScript() {
        String exitStatment = "Bye, hope to see you again! :)";
        return exitStatment;
//        System.out.println(exitStatment);
    }
    public static void displayWithStyle(String text) {
        String indent = "    ";
        String topBorder    = "____________________________________\n";
        String bottomBorder = "------------------------------------\n";
        String textWithBorders = topBorder +  text + "\n" + bottomBorder;
        String[] lines = textWithBorders.split("\n");
//        System.out.println(Arrays.toString(lines));
        for (String line : lines){
            System.out.println(indent + line);
        }
    }
}

