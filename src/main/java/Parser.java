import java.util.Scanner;

public class Parser {
    private boolean isRunning;
    private Ui ui;
    private MyList list;
    private Storage storage;
    public Parser(MyList list, Storage storage) {
        this.isRunning = true;
        this.list = list;
        this.ui = new Ui();
        this.storage = storage;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void readInput(String command) {

        Scanner s = new Scanner(command);
        String input = s.next();

        if (!input.equals("bye")) {
            if (input.equals("list")) {
                this.list.listAll();
            } else if (input.equals("done")) {
                Scanner s1 = new Scanner(s.nextLine());
                int counter = 0;
                while (s1.hasNextInt()) {
                    int index = s1.nextInt();
                    this.list.markComplete(index);
                    this.storage.writeToFile();
                    counter ++;
                }
                if (counter == 0) {
                    this.ui.invalidIndexMessage();
                }
            } else if (input.equals("todo")) {
                Scanner s2 = new Scanner(s.nextLine());
                String description = "";
                while (s2.hasNextLine()) {
                    description = s2.nextLine();
                }
                try {
                    Todo newTodo = new Todo(description, false);
                    this.list.addTask(newTodo);
                    this.storage.writeToFile();
                } catch (WrongCommandFormatException e) {
                    this.ui.formatExceptionMessage(e);
                }

            } else if (input.equals("deadline")) {
                Scanner s3 = new Scanner(s.nextLine());
                String description = "";
                while (s3.hasNextLine()) {
                    description = s3.nextLine();
                }
                try {
                    Deadline newDeadline = new Deadline(description, false);
                    this.list.addTask(newDeadline);
                    this.storage.writeToFile();
                } catch (WrongCommandFormatException e) {
                    this.ui.formatExceptionMessage(e);
                }
            } else if (input.equals("event")) {
                Scanner s4 = new Scanner(s.nextLine());
                String description = "";
                while (s4.hasNextLine()) {
                    description = s4.nextLine();
                }
                try {
                    Event newEvent = new Event(description, false);
                    this.list.addTask(newEvent);
                    this.storage.writeToFile();
                } catch (WrongCommandFormatException e) {
                    this.ui.formatExceptionMessage(e);
                }
            } else if (input.equals("delete")) {
                Scanner s5 = new Scanner(s.nextLine());
                if (s5.hasNextInt()) {
                    int index = s5.nextInt();
                    this.list.deleteTask(index);
                    this.storage.writeToFile();
                } else {
                    this.ui.invalidIndexMessage();
                }
            } else if (input.equals("setFormat")) {
                Scanner s6 = new Scanner(s.nextLine());
                if (s6.hasNextLine()) {
                    try {
                        Duke.setFormat(s6.nextLine().substring(1));
                        System.out.println(
                                "Date format has been updated to: "
                                        + Duke.getFormat()
                        );
                        this.ui.formatUpdatedMessage();
                        this.storage.writeToFile();
                    } catch (IllegalArgumentException e) {
                        this.ui.unacceptableFormatMessage();
                    }
                }
            } else if (input.equals("format")) {
                this.ui.currentDateFormatMessage();
            } else {
                this.ui.noSpecificCmdMessage();
            }
        } else {
            this.ui.botShutdownMessage();
            this.isRunning = false;
        }

    }
}
