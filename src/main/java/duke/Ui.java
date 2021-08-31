//package duke;
//
//import java.util.Scanner;
//
///**
// * Ui class to handle reading the user's inputs.
// */
//public class Ui {
//    private Scanner scanner;
//    private Parser parser;
//
//    /**
//     * Creates new user interface and shows the starting title screen.
//     */
//    public Ui() {
//        String logo = " ____        _\n"
//                + "|  _ \\ _   _| | _____\n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        printLine();
//        System.out.println("Hello! I'm duke.Duke");
//        System.out.println("What can I do for you?");
//        printLine();
//        scanner = new Scanner(System.in);
//        parser = new Parser();
//    }
//
//    /**
//     * Reads the next line by the user and parses it
//     *
//     * @return true to continue. false to break.
//     */
//    public String nextLine() {
//        String input = scanner.nextLine();
//        return parser.parse(input);
//    }
//
//    /**
//     * Message to show an loading error at the beginning.
//     */
//    public void showLoadingError() {
//        System.out.println("    Error loading oops");
//    }
//
//
//    private void printLine() {
//        System.out.println("    ____________________________________________________________");
//    }
//
//}
//
