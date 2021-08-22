import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    private final ArrayList<Task> arrList;
    private final Storage storage;

    public Duke(String filePath) {
        this.arrList = new ArrayList<Task>();
        this.storage = new Storage(filePath);
    }

    private void printMessage(String message) {
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE);
        System.out.println(Ui.INDENT + message);
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE + "\n");
    }

    private void printIntro() {
        System.out.println(Ui.logo);
        printMessage(Ui.INTRODUCTION);
    }

    private void printBye() {
        printMessage(Ui.BYE_MESSAGE);
    }

    private void printList() {
        if (arrList.size() == 0) {
            printMessage(Ui.NO_TASK_MESSAGE);
            return;
        }
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE);
        System.out.println(Ui.LIST_MESSAGE);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(Ui.INDENT + (i + 1) + ". " + arrList.get(i).toString());
        }
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE + "\n");
    }

    private void doneItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task curr = arrList.get(index);
        this.arrList.set(index, curr.markAsDone());
        curr = arrList.get(index);

        this.printMessage(Ui.DONE_MESSAGE + "\n" + Ui.INDENT + "  " + curr.toString());
    }

    private void addTask(Task task) throws IOException {
        arrList.add(task);
        this.printMessage("Got it. I've added this task:\n" + Ui.INDENT + "  " + task.toString() + "\n" + Ui.INDENT
                + "Now you have " + this.arrList.size() + " tasks in the list.");
    }

    private void deleteItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task task = arrList.get(index);
        this.arrList.remove(index);
        this.printMessage("Noted. I've removed this task:\n" + Ui.INDENT + "  " + task.toString() + "\n" + Ui.INDENT
                + "Now you have " + this.arrList.size() + " tasks in the list.");
    }

    private String dateTimeFormatter(String unformattedDate) {
        // 2/12/2019 1800
        String stringDate = unformattedDate.split(" ")[1];
        String time = unformattedDate.split(" ")[2];

        LocalDate date = LocalDate.of(Integer.parseInt(stringDate.split("/")[2]),
                Integer.parseInt(stringDate.split("/")[1]), Integer.parseInt(stringDate.split("/")[0]));

        LocalDateTime dateTime = date.atTime(Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)));

        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
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
        ArrayList<Task> savedTasks = storage.loadData();
        savedTasks.forEach(x -> {
            this.arrList.add(x);
        });

        this.printIntro();
        while (true) {
            userInput = sc.nextLine();

            actionDescription = userInput.split(" ", 2);

            try {
                action = actionDescription[0];
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
                        storage.updateDone(userInput.split(" ")[1]);
                        break;

                    case "todo":
                        descriptions = actionDescription[1];
                        ToDos todos = new ToDos(descriptions);
                        this.addTask(todos);
                        inputToStorage = todos.getSymbol() + " | 0 | " + todos.getDescription();
                        storage.appendToData(inputToStorage);
                        break;

                    case "deadline":
                        descriptions = actionDescription[1];
                        onlyDescription = descriptions.split("/")[0];
                        by = descriptions.split("/by")[1];
                        by = dateTimeFormatter(by);
                        Deadline deadline = new Deadline(onlyDescription, by);
                        this.addTask(deadline);
                        inputToStorage = deadline.getSymbol() + " | 0 | " + deadline.getDescription() + "|"
                                + deadline.getBy();
                        storage.appendToData(inputToStorage);
                        break;

                    case "event":
                        descriptions = actionDescription[1];
                        onlyDescription = descriptions.split("/")[0];
                        dayTime = descriptions.split("/at")[1];
                        Events event = new Events(onlyDescription, dayTime);
                        this.addTask(event);
                        inputToStorage = event.getSymbol() + " | 0 | " + event.getDescription() + "|"
                                + event.getDayTime();
                        storage.appendToData(inputToStorage);
                        break;

                    case "delete":
                        this.deleteItem(userInput.split(" ")[1]);
                        storage.deleteTaskFromData(userInput.split(" ")[1]);
                        break;

                    case "":
                        break;

                    default:
                        this.printMessage(Ui.ERROR_MSG_UNKOWN_MSG);
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
                this.printMessage(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
                continue;
            }

        }

    }
}