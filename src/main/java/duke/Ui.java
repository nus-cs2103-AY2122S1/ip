package duke;

import java.time.format.DateTimeFormatter;

/**
 * Methods for printing. Defines format, indentation etc.
 * 
 * @author Tianqi-Zhu
 */
public class Ui {
    public static final String LINE_STRING = "----------------------------------------";
    public static final String SPACE_STRING = "    ";
    public static final String DECORATOR_STRING = SPACE_STRING + LINE_STRING; 
    public static final DateTimeFormatter OUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
    public static final DateTimeFormatter OUT_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

    /**
     * Print exit message.
     */
    public static void exit() {
        printString("Bye. Hope to see you again soon!");
    }
    
    /**
     * Print greeting messgae.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printString("Hello, I am your personal asistant\n" + logo + SPACE_STRING + "What can I do for you?");
    }

    /** 
     * Print in the format with lines.
     * 
     * @param inputString string to print
     */
    public static void printString(String inputString) {
        System.out.println(DECORATOR_STRING);
        System.out.println(SPACE_STRING + inputString);
        System.out.println(DECORATOR_STRING);
    }

    /**
     * Print in the format with lines.
     *
     * @param inputString string to print
     */
    public static void printStringCli(String inputString) {
        System.out.println(DECORATOR_STRING);
        System.out.println(SPACE_STRING + inputString);
        System.out.println(DECORATOR_STRING);
    }

    /** 
     * Method that adjust printing format to suit list printing.
     * 
     * @param taskString multi-line string of tasks to be printed
     */
    public static void printList(String taskString) {
        System.out.println(DECORATOR_STRING);
        System.out.print(SPACE_STRING + taskString);
        System.out.println(DECORATOR_STRING);
    }

    /**
     * Method that adjust printing format to suit list printing.
     *
     * @param taskString multi-line string of tasks to be printed
     */
    public static void printListCli(String taskString) {
        System.out.println(DECORATOR_STRING);
        System.out.print(SPACE_STRING + taskString);
        System.out.println(DECORATOR_STRING);
    }
}