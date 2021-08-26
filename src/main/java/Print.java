public class Print {
    static void printProgramStartMessage() {
        printHorizontalLine();
        printGreetMessage();
        printMenuOptions();
        printHorizontalLine();
    }

    static void printResponse(String message) {
        printHorizontalLine();
        System.out.println(message);
        printHorizontalLine();
    }

    static void printErrorMessage(String message) {
        printHorizontalLine();
        System.out.println("ERROR MESSAGE:");
        System.out.println("\t" + "☹ " + message);
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    private static void printGreetMessage() {
        String message = "\t" + "Hello! I'm Duke, your Personal Assistant Chatbot."
                + System.lineSeparator()
                + "\t" + "What can I do for you?"
                + System.lineSeparator();
        System.out.println(message);
    }

    private static void printMenuOptions() {
        String format = "%-25s%s%n";

        String menuOption1 = "\t" + "list:";
        String menuOption1Info = "List the tasks in your list";

        String menuOption2 = "\t" + "todo ABC:";
        String menuOption2Info = "Add a todo [T] task, ABC, into your list";

        String menuOption3 = "\t" + "deadline ABC /by XYZ:";
        String menuOption3Info = "Add a deadline [D] task, ABC, into your list "
                + "and specify the date/time, XYZ, it needs to be completed by";

        String menuOption4 = "\t" + "event ABC /at XYZ:";
        String menuOption4Info = "Add an event [E] task, ABC, into your list "
                + "and specify the start and end date/time, XYZ";

        String menuOption5 = "\t" + "done N:";
        String menuOption5Info = "Mark a task number, N, as done";

        String menuOption6 = "\t" + "delete N:";
        String menuOption6Info = "Delete a task number, N, from your list";

        String menuOption7 = "\t" + "bye:";
        String menuOption7Info = "Exit the chatbot";

        System.out.printf(format, menuOption1, menuOption1Info);
        System.out.printf(format, menuOption2, menuOption2Info);
        System.out.printf(format, menuOption3, menuOption3Info);
        System.out.printf(format, menuOption4, menuOption4Info);
        System.out.printf(format, menuOption5, menuOption5Info);
        System.out.printf(format, menuOption6, menuOption6Info);
        System.out.printf(format, menuOption7, menuOption7Info);
    }
}
