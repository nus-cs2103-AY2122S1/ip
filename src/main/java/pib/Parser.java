package pib;

import java.util.Scanner;

public class Parser {
    private Scanner sc;
    private TaskList list;

    public Parser(TaskList list) {
        this.sc = new Scanner(System.in);
        this.list = list;
    }

    public void readInput() {
        scanner: while (sc.hasNextLine()) {
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
                    Ui.printError("unknown-command");
                    break;
                }
            }
        }
    }

    private void taskCommand(String input) {
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
            default:
                Ui.printError("unknown-command");
                break;
            }
        } catch (NumberFormatException e) {
            Ui.printError("ioob-exception");
        }
    }

    private void pibCommand(String input) {
        String command = input.toLowerCase();

        switch (command) {
        case "list":
            Ui.printList(list);
            break;
        case "bye":
            closeScanner();
            break;
        default:
            Ui.printError("unknown-command");
            break;
        }
    }

    private void closeScanner() {
        Ui.printEnd();
        sc.close();
    }
}
