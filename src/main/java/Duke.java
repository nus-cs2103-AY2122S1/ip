import java.util.Scanner;

/**
 * This program Duke is a chatbot.
 *
 * @author: Toh Bing Cheng
 * @version: CS2103T AY21 Semester 1
 */
public class Duke {
    public static void main(String[] args) {
        runProgram();
    }

    /**
     * This method runs the program indefinitely till user types in "bye".
     */
    public static void runProgram() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (run) {
            // wait to read in the user's input
            String input = scanner.nextLine();
            // Ensures that all bye would end the program.
            if (input.toUpperCase().equals("BYE")) {
                System.out.println("Bye. Hope to see you again soon!");
                run = false;
            } else if (input.toUpperCase().equals("LIST")) {
                taskManager.listAll();
            }else if (isDone(input)) {
                String[] stringArr = input.split(" ");
                if (stringArr.length > 1) {
                    String taskNumber = stringArr[1];
                    if (isNumeric(taskNumber)) {
                        int taskNum = Integer.parseInt(taskNumber);
                        if (taskNum > 0 && (taskNum - 1) < taskManager.getTotalNumberOfTask()) {
                            taskManager.markTaskDoneByIndex(taskNum);
                        } else {
                            System.out.println("Task number is not in the list!");
                        }

                    } else {
                        System.out.println("Please enter a valid task number!");
                    }

                } else {
                    System.out.println("Please enter the task number after done! E.g \"done 2\"");
                }
            }else {
                taskManager.addTask(new Task(input));
                System.out.println(input);
            }
        }
    }

    /**
     * Check if a given input has done command.
     * @param input a string that is the input of the user.
     * @return a boolean if done command is found.
     */
    public static boolean isDone(String input) {
        if (input.length() >= 4) {
            return input.toUpperCase().substring(0,4).equals("DONE");
        } else {
            return false;
        }
    }

    /**
     * Check if input string is numeric or not.
     * @param input a string input from user.
     * @return a boolean if input is numeric.
     */
    public static boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
