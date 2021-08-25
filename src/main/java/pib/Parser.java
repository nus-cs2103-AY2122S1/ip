package pib;

import pib.enums.TaskType;
import pib.pibexception.PibException;

import java.util.Scanner;

public class Parser {
    private Scanner sc;
    private TaskList list;

    public Parser(TaskList list) {
        this.sc = new Scanner(System.in);
        this.list = list;
    }

    public void readInput() {
        scanner:
        while (sc.hasNextLine()) {
            try {
                String nextLine = sc.nextLine();

                if (nextLine.contains(" ")) {
                    taskCommand(nextLine);
                } else {
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
            default:
                throw new PibException("unknown-command");
            }
        } catch (NumberFormatException e) {
            throw new PibException("ioob-exception");
        }
    }

    private void closeScanner() {
        Ui.printEnd();
        sc.close();
    }
}
