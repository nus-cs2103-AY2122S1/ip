import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class encapsulates a Duke chat-bot.
 */
public class Duke {
    private static final int lv = 7;
    private static final String[] features = {
            "",
            "Greet, Echo, Exit",
            ", Add, List",
            ", Mark as Done",
            ", ToDos, Events, Deadlines",
            ", Handle Errors",
            ", Delete",
            ", Save"
    };
    private static boolean canExit = false;
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    private static final String dukeFilePath = "./data/duke.txt";

    /**
     * This method takes an input string and formats it by including horizontal lines above
     * and below the input string
     *
     * @param str input string to be sandwiched
     * @return the original string sandwiched between two horizontal lines
     */
    public static String sandwich(String str) {
        return "____________________________________________________________\n"
                + str + "\n"
                + "____________________________________________________________";
    }

    /**
     * This method takes the user's input list and beautifies it for display.
     *
     * @param taskArrayList the user's input list to be beautified
     * @return the beautified string to display
     */
    public static String listBeautify(ArrayList<Task> taskArrayList) {
        StringBuilder listBeautified = new StringBuilder();
        for (int i = 0; i < taskArrayList.size(); i++) {
            listBeautified.append(i + 1)
                    .append(".")
                    .append(taskArrayList.get(i).toString());
            if (i < taskArrayList.size() - 1) { // new line except for last item
                listBeautified.append("\n");
            }
        }
        return listBeautified.toString();
    }

    /**
     * This method copies the contents of the file from filePath and
     * converts them into Task objects into the taskArrayList with the
     * help of the parse() method.
     *
     * @param filePath the file path to the file from which to copy from
     * @throws IOException if filePath does not exist
     */
    private static void copyFileContents(String filePath) throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        StringBuilder targetBuilder = new StringBuilder();
        while (s.hasNext()) {
            targetBuilder.append(s.nextLine()).append("\n");
        }
        taskArrayList = parse(targetBuilder.toString());
    }

    /**
     * This method parses the string copied from duke.txt and converts them into task objects
     * into the taskArrayList.
     *
     * @param toParse the string to parse
     * @return a taskArrayList made up of tasks
     */
    private static ArrayList<Task> parse(String toParse) {
        ArrayList<Task> result = new ArrayList<>();
        Scanner ps = new Scanner(toParse); // passes whole file into the scanner
//        [[T][ ] 1, [E][ ] 2 (at: 2), [D][ ] 3 (by: 3)]
        while (ps.hasNextLine()) {
            String nLine = ps.nextLine(); // parse one line at a time
            int ref = 3; // reference point
            char taskType = nLine.charAt(ref);
            boolean isDone = nLine.charAt(ref + 3) == 'X';
            int strLength = nLine.length();
            switch (taskType) {
            case 'T':
                String todoName = nLine.substring(ref + 5, strLength);
                Task newestTodo = new ToDo(todoName);
                if (isDone) {
                    newestTodo.markAsDone();
                }
                result.add(newestTodo);
                break;
            case 'D':
                String deadlineInfo = nLine.substring(ref + 5, strLength);
                String[] arrD = deadlineInfo.split("\\(by: ", 2);
                String deadlineName = arrD[0];
                String deadlineReminder = arrD[1].substring(0, arrD[1].length() - 1);
                deadlineReminder = parseDate(deadlineReminder);
                Task newestDeadline = new Deadline(deadlineName, deadlineReminder);
                result.add(newestDeadline);
                if (isDone) {
                    newestDeadline.markAsDone();
                }
                break;
            case 'E':
                String eventInfo = nLine.substring(ref + 5, strLength);
                String[] arrE = eventInfo.split("\\(at: ", 2);
                String eventName = arrE[0];
                String eventReminder = arrE[1].substring(0, arrE[1].length() - 1);
                eventReminder = parseDate(eventReminder);
                Task newestEvent = new Event(eventName, eventReminder);
                result.add(newestEvent);
                if (isDone) {
                    newestEvent.markAsDone();
                }
                break;
            default:
                System.out.println("Unknown input");
                break;
            }
        }
        System.out.println("result is: " + result);
        return result;
    }

    /**
     * Parses input string in the format "yyyy MM dd" and returns it in the format
     * "YYYY-MM-DD".
     *
     * @param input string in format "MMM d yyyy"
     * @return string in format "YYYY-MM-DD"
     */
    private static String parseDate(String input) {
        String year = input.substring(0, 4);
        String month = input.substring(5, 7);
        String day = input.substring(8, 10);

        return year + "-" + month + "-" + day;
    }

    /**
     * This method overwrites the file.
     *
     * @param filePath     the path to the file to append to
     * @param textToAppend the text to append
     * @throws IOException if filePath does not exist
     */

    private static void overwriteFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * This method appends to the file instead of overwrites.
     *
     * @param filePath     the path to the file to append to
     * @param textToAppend the text to append
     * @throws IOException if filePath does not exist
     */

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Validates input string to ensure it follows the valid format YYYY-MM-DD, and is a valid date.
     *
     * @param input the string to be validated
     * @return true if the string is a valid date
     */
    private static boolean isValidDate(String input) {
        String[] splitInputs = input.split("-");
        boolean isLeapYear;
        String year = splitInputs[0];
        String month = splitInputs[1];
        String day = splitInputs[2];
        int maxDay;

        if (splitInputs.length != 3) {
            return false;
        }

        // check year
        if (year.length() != 4 || !year.matches("\\d+")) {
            return false;
        } else {
            isLeapYear = (Integer.parseInt(year) % 4 == 0);
        }

        // check month
        if (month.length() != 2 || !month.matches("\\d+")) {
            return false;
        } else {
            switch (Integer.parseInt(month)) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                maxDay = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = 30;
                break;
            case 2:
                if (isLeapYear) {
                    maxDay = 29;
                } else {
                    maxDay = 28;
                }
                break;
            default:
                return false;
            }
        }

        // check day
        if (day.length() != 2 || !day.matches("\\d+")) {
            return false;
        } else {
            return Integer.parseInt(day) <= maxDay;
        }
    }

    public static void main(String[] args) throws IOException {
        // commented out logo
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // populating featuresCombined so each level has all elements of levels before it
        StringBuilder featuresCombined = new StringBuilder();
        for (int count = 0; count <= lv; count++) {
            featuresCombined.append(features[count]);
        }

        // parsing duke.txt
        copyFileContents(dukeFilePath);

        // Welcome message
        String welcome = "Hello! I'm Duke: Level " + lv + "\n"
                + "What would you like to do today?\n"
                + "My current features are: " + featuresCombined + "\n"
                + "Here are your tasks: " + "\n"
                + sandwich(listBeautify(taskArrayList));

        System.out.println(sandwich(welcome));

        // load the data from the hard disk for dukeFile
        File dukeFile = new File(dukeFilePath);

        // creates directory if it does not exist
        if (dukeFile.mkdir()) {
            System.out.println("folder: 'data/' has been created");
        }
        // creates file if it does not exist
        if (dukeFile.createNewFile()) {
            System.out.println("'duke.txt' has been created in the 'data/' folder ");
        }

        // Goodbye message
        String goodbye = "Thank you for using Duke: Level " + lv + "\n"
                + "See you soon!";

        // Scanner to read user inputs
        Scanner scanner = new Scanner(System.in);

        while (!canExit) {
            String userInput = scanner.next();
            try {
                if (userInput.equals("bye")) { // user inputs "bye", set canExit to true and Exit
                    canExit = true;
                    String temp = listBeautify(taskArrayList);
                    overwriteFile(dukeFilePath, temp); // append temp.toString() to dukeFile
                    System.out.println(sandwich(goodbye));
                } else { // check first input
                    switch (userInput) {
                    case "list":  // user inputs 'list', return all text stored
                        System.out.println(sandwich(listBeautify(taskArrayList)));
                        break;
                    case "done":  // first input is done, check second input for integer
                        if (scanner.hasNextInt()) {
                            int taskNum = scanner.nextInt();
                            if (taskNum > taskArrayList.size()) {
                                throw new DukeException("No such task");
                            }
                            taskArrayList.get(taskNum - 1).markAsDone();
                            System.out.println(sandwich("Congratulations! You have finished this task: "
                                    + taskArrayList.get(taskNum - 1).toString()));
                        } else throw new DukeException("unspecified task to mark as done");
                        break;
                    case "delete":
                        if (scanner.hasNextInt()) {
                            int taskNum = scanner.nextInt();
                            if (taskNum > taskArrayList.size()) {
                                throw new DukeException("No such task");
                            }
                            System.out.println(sandwich("Got it, I have deleted this task: "
                                    + taskArrayList.get(taskNum - 1).toString()
                                    + "\nYou now have "
                                    + (taskArrayList.size() - 1)
                                    + " item(s) in your task list."));
                            // actual logic of deletion
                            taskArrayList.remove(taskNum - 1);
                        } else throw new DukeException("unspecified task to delete");
                        break;
                    case "todo":
                        String todoName = scanner.nextLine();
                        if (todoName.trim().equals("")) {
                            throw new DukeException("No task description");
                        }
                        Task newestTodo = new ToDo(todoName);
                        taskArrayList.add(newestTodo);
                        System.out.println(sandwich("New todo task added:\n"
                                + newestTodo
                                + "\nYou now have "
                                + taskArrayList.size()
                                + " item(s) in your task list."));
                        break;
                    case "deadline":
                        String[] deadlineTokens = scanner.nextLine().split("\\s*/by\\s*");
                        if (deadlineTokens.length == 0) {
                            throw new DukeException("No task description");
                        } else if (deadlineTokens.length == 1) {
                            throw new DukeException("No task deadline");
                        }
                        String deadlineName = deadlineTokens[0];
                        String deadlineReminder = deadlineTokens[1];
                        if (!isValidDate(deadlineReminder)) {
                            throw new DukeException("Invalid Date, please follow the format YYYY-MM-DD");
                        }
                        Task newestDeadline = new Deadline(deadlineName, deadlineReminder);
                        taskArrayList.add(newestDeadline);
                        System.out.println(sandwich("New deadline task added:\n"
                                + newestDeadline
                                + "\nYou now have "
                                + taskArrayList.size()
                                + " item(s) in your task list."));
                        break;
                    case "event":
                        String[] eventTokens = scanner.nextLine().split("\\s*/at\\s*");
                        if (eventTokens.length == 0) {
                            throw new DukeException("No task description");
                        } else if (eventTokens.length == 1) {
                            throw new DukeException("No task duration");
                        }
                        String eventName = eventTokens[0];
                        String eventReminder = eventTokens[1];
                        if (!isValidDate(eventReminder)) {
                            throw new DukeException("Invalid Date, please follow the format YYYY-MM-DD");
                        }
                        Task newestEvent = new Event(eventName, eventReminder);
                        taskArrayList.add(newestEvent);
                        System.out.println(sandwich("New event task added:\n"
                                + newestEvent
                                + "\nYou now have "
                                + taskArrayList.size()
                                + " item(s) in your task list."));
                        break;
                    default:
                        throw new DukeException("Unknown Input"); // unknown input
                    }
                }
            } catch (DukeException e) {
                e.printStackTrace(); // print stack trace for e
            }
        }
    }
}