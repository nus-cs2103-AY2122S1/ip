import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    private final ArrayList<Task> arrList;
    private final String filePath;
    private final File data;

    public Duke(String filePath) {
        this.arrList = new ArrayList<Task>();
        this.filePath = filePath;
        this.data = new File(filePath);
    }

    private void printMessage(String message) {
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE);
        System.out.println(STATICS.INDENT + message);
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE + "\n");
    }

    private void printIntro() {
        System.out.println(STATICS.logo);
        printMessage(STATICS.INTRODUCTION);
    }

    private void printBye() {
        printMessage(STATICS.BYE_MESSAGE);
    }

    private void printList() {
        if (arrList.size() == 0) {
            printMessage(STATICS.NO_TASK_MESSAGE);
            return;
        }
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE);
        System.out.println(STATICS.LIST_MESSAGE);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(STATICS.INDENT + (i + 1) + ". " + arrList.get(i).toString());
        }
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE + "\n");
    }

    private void doneItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task curr = arrList.get(index);
        this.arrList.set(index, curr.markAsDone());
        curr = arrList.get(index);

        this.printMessage(STATICS.DONE_MESSAGE + "\n" + STATICS.INDENT + "  " + curr.toString());
    }

    private void addTask(Task task) throws IOException {
        arrList.add(task);
        this.printMessage("Got it. I've added this task:\n" + STATICS.INDENT + "  " + task.toString() + "\n"
                + STATICS.INDENT + "Now you have " + this.arrList.size() + " tasks in the list.");
    }

    private void deleteItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task task = arrList.get(index);
        this.arrList.remove(index);
        this.printMessage("Noted. I've removed this task:\n" + STATICS.INDENT + "  " + task.toString() + "\n"
                + STATICS.INDENT + "Now you have " + this.arrList.size() + " tasks in the list.");
    }

    private void loadData() throws FileNotFoundException {
        String currLine;
        String type;
        String descriptions;
        String[] stringArr;
        String dateTimeLocation;
        boolean isDone;

        Scanner sc = new Scanner(data);
        while (sc.hasNext()) {
            currLine = sc.nextLine();
            stringArr = currLine.replace("|", "/").split("/");
            type = currLine.split("")[0];
            isDone = false;
            switch (type) {
                case "T":
                    descriptions = stringArr[2];
                    ToDos todos;
                    if (stringArr[1].contains("1")) {
                        isDone = true;
                    }
                    todos = new ToDos(descriptions, isDone);
                    this.arrList.add(todos);
                    break;

                case "D":
                    descriptions = stringArr[2];
                    dateTimeLocation = stringArr[3];
                    Deadline deadline;
                    if (stringArr[1].contains("1")) {
                        isDone = true;
                    }
                    deadline = new Deadline(descriptions, dateTimeLocation, isDone);
                    this.arrList.add(deadline);
                    break;

                case "E":
                    descriptions = stringArr[2];
                    dateTimeLocation = stringArr[3];
                    Events event;
                    if (stringArr[1].contains("1")) {
                        isDone = true;
                    }
                    event = new Events(descriptions, dateTimeLocation, isDone);
                    this.arrList.add(event);
                    break;

                default:
                    break;
            }
        }
        sc.close();
    }

    private void appendToData(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.data, true);
        fw.append(textToAppend + "\n");
        fw.close();
    }

    private void updateDone(String number) throws FileNotFoundException, IOException {
        int index = Integer.parseInt(number);
        int count = 0;
        String currLine;
        String finalAppend = "";
        Scanner sc = new Scanner(this.data);
        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            count++;
            if (count != index) {
                finalAppend += currLine + "\n";
            } else {
                currLine = currLine.replace("| 0 |", "| 1 |");
                finalAppend += currLine + "\n";
            }
        }
        FileWriter fw = new FileWriter(this.data, false);
        fw.append(finalAppend);
        fw.close();
        sc.close();
    }

    private void deleteTaskFromData(String number) throws FileNotFoundException, IOException {
        int index = Integer.parseInt(number);
        int count = 0;
        String currLine;
        String finalAppend = "";
        Scanner sc = new Scanner(this.data);
        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            count++;
            if (count != index) {
                finalAppend += currLine + "\n";
            }
        }
        FileWriter fw = new FileWriter(this.data, false);
        fw.append(finalAppend);
        fw.close();
        sc.close();
    }

    public void start() throws FileNotFoundException, IOException {
        String userInput = "";
        String action = "";
        String descriptions = "";
        String[] actionDescription = new String[2];
        String by = "";
        String dayTime = "";
        String onlyDescription = "";
        Scanner sc = new Scanner(System.in);
        String inputToStorage;
        this.loadData();

        this.printIntro();
        while (true) {
            userInput = sc.nextLine();

            actionDescription = userInput.split(" ", 2);

            try {
                action = userInput.split(" ")[0];
                switch (action) {
                    case "bye":
                        this.printBye();
                        sc.close();
                        return;

                    case "list":
                        this.printList();
                        break;

                    case "done":
                        this.doneItem(userInput.split(" ")[1]);
                        this.updateDone(userInput.split(" ")[1]);
                        break;

                    case "todo":
                        descriptions = actionDescription[1];
                        ToDos todos = new ToDos(descriptions);
                        this.addTask(todos);
                        inputToStorage = todos.getSymbol() + " | 0 | " + todos.getDescription();
                        this.appendToData(inputToStorage);
                        break;

                    case "deadline":
                        descriptions = actionDescription[1];
                        onlyDescription = descriptions.split("/")[0];
                        by = descriptions.split("/by")[1];
                        Deadline deadline = new Deadline(onlyDescription, by);
                        this.addTask(deadline);
                        inputToStorage = deadline.getSymbol() + " | 0 | " + deadline.getDescription() + "|"
                                + deadline.getBy();
                        this.appendToData(inputToStorage);
                        break;

                    case "event":
                        descriptions = actionDescription[1];
                        onlyDescription = descriptions.split("/")[0];
                        dayTime = descriptions.split("/at")[1];
                        Events event = new Events(onlyDescription, dayTime);
                        this.addTask(event);
                        inputToStorage = event.getSymbol() + " | 0 | " + event.getDescription() + "|"
                                + event.getDayTime();
                        this.appendToData(inputToStorage);
                        break;

                    case "delete":
                        this.deleteItem(userInput.split(" ")[1]);
                        this.deleteTaskFromData(userInput.split(" ")[1]);
                        break;

                    case "":
                        break;

                    default:
                        this.printMessage(STATICS.ERROR_MSG_UNKOWN_MSG);
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                this.printMessage(STATICS.ERROR_MSG_EMPTY_DESCRIPTION);
                continue;
            }

        }

    }
}