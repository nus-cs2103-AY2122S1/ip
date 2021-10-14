package pib.utility;

import java.util.Scanner;

import pib.enums.TaskType;
import pib.pibexception.PibException;

/**
 * Class to parse the user input
 */
public class Parser {
    private Scanner sc;
    private TaskList list;

    /**
     * Constructs a Parser object
     *
     * @param list TaskList that is used to store the Tasks where this parser is used
     */
    public Parser(TaskList list) {
        this.sc = new Scanner(System.in);
        this.list = list;
    }

    /**
     * Uses the scanner to read inputs from the user
     */
    public void readInput() {
        scanner:
        while (sc.hasNextLine()) {
            try {
                String nextLine = sc.nextLine();

                if (nextLine.contains(" ")) {
                    taskCommand(nextLine);
                } else { // Can't extract this to method since closeScanner has to break out of while loop
                    switch (nextLine.toLowerCase()) {
                    case "list":
                        Ui.printList(list);
                        break;
                    case "bye":
                        closeScanner();
                        break scanner;
                    default:
                        throw new PibException("unknown-command");
                    }
                }
            } catch (PibException e) {
                e.print();
            }
        }
    }

    /**
     * Uses the scanner to read inputs from the user
     *
     * @param input user inputted command
     * @return String containing the response
     */
    public String readInput(String input) {
        try {
            if (input.contains(" ")) {
                return taskCommandGui(input);
            } else {
                switch (input.toLowerCase()) {
                case "list":
                    return Ui.printList(list);
                case "bye":
                    return closeScanner();
                default:
                    throw new PibException("unknown-command");
                }
            }
        } catch (PibException e) {
            return e.print();
        }
    }

    private void taskCommand(String input) throws PibException {
        try {
            int spaceIndex = input.indexOf(" ");
            String taskType = input.substring(0, spaceIndex).toLowerCase();
            String taskDetails = input.substring(1 + spaceIndex);

            switch (taskType) {
            case "todo":
                list.add(TaskType.TODO, taskDetails);
                break;
            case "deadline":
                list.add(TaskType.DEADLINE, taskDetails);
                break;
            case "event":
                list.add(TaskType.EVENT, taskDetails);
                break;
            case "done":
                list.markAsDone(Integer.parseInt(taskDetails));
                break;
            case "delete":
                list.delete(Integer.parseInt(taskDetails));
                break;
            case "find":
                list.find(taskDetails);
                break;
            case "edit":
                list.edit(taskDetails);
                break;
            default:
                throw new PibException("unknown-command");
            }
        } catch (NumberFormatException e) {
            throw new PibException("ioob-exception");
        }
    }

    private String taskCommandGui(String input) throws PibException {
        try {
            int spaceIndex = input.indexOf(" ");
            String taskType = input.substring(0, spaceIndex).toLowerCase();
            String taskDetails = input.substring(1 + spaceIndex);

            switch (taskType) {
            case "todo":
                return list.add(TaskType.TODO, taskDetails);
            case "deadline":
                return list.add(TaskType.DEADLINE, taskDetails);
            case "event":
                return list.add(TaskType.EVENT, taskDetails);
            case "done":
                return list.markAsDone(Integer.parseInt(taskDetails));
            case "delete":
                return list.delete(Integer.parseInt(taskDetails));
            case "find":
                return list.find(taskDetails);
            case "edit":
                return list.edit(taskDetails);
            default:
                throw new PibException("unknown-command");
            }
        } catch (NumberFormatException e) {
            throw new PibException("ioob-exception");
        }
    }

    private String closeScanner() {
        sc.close();
        return Ui.printEnd();
    }
}
