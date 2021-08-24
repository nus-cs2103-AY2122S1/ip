import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    enum SPECIAL_TASK {
        bye,
        done,
        list,
        todo,
        deadline,
        event,
        delete
    }

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList(storage.Load());
        this.parser = new Parser();
    }

    public void run() {
        this.ui.PrintIntro();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals(SPECIAL_TASK.bye.name())) {
            String[] splitInput = this.parser.splitType(input);
            if (splitInput[0].equals(SPECIAL_TASK.done.name())) {
                int index = this.parser.getIndex(splitInput);
                String returnString = this.taskList.markDone(index);
                this.ui.PrintMessage(returnString);
            } else if (input.equals(SPECIAL_TASK.list.name())) {
                this.ui.PrintList(taskList);
            } else if (splitInput[0].equals(SPECIAL_TASK.todo.name())) {
                try {
                    this.parser.checkDesc(splitInput, SPECIAL_TASK.todo.name());
                    this.taskList.add(new Todo(splitInput[1]));
                    ui.PrintSpecialTasks(taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (splitInput[0].equals(SPECIAL_TASK.deadline.name())) {
                try {
                    this.parser.checkDesc(splitInput, SPECIAL_TASK.deadline.name());
                    String[] furtherSplits = this.parser.furtherSplit(splitInput[1], "/by");
                    this.parser.checkFurtherDesc(furtherSplits, "deadline");
                    LocalDateTime by = this.parser.parseTime(furtherSplits[1]);
                    this.taskList.add(new Deadline(furtherSplits[0], by));
                    ui.PrintSpecialTasks(this.taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (splitInput[0].equals(SPECIAL_TASK.event.name())) {
                try {
                    this.parser.checkDesc(splitInput, SPECIAL_TASK.event.name());
                    String[] furtherSplits = this.parser.furtherSplit(splitInput[1], "/at");
                    this.parser.checkFurtherDesc(furtherSplits, "event");
                    LocalDateTime by = this.parser.parseTime(furtherSplits[1]);
                    this.taskList.add(new Event(furtherSplits[0], by));
                    ui.PrintSpecialTasks(this.taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (splitInput[0].equals(SPECIAL_TASK.delete.name())) {
                try {
                    this.parser.checkDesc(splitInput, SPECIAL_TASK.delete.name());
                    int index = this.parser.getIndex(splitInput);
                    this.parser.checkTaskIndex(index, taskList);
                    Task deleted = this.taskList.delete(index);
                    ui.PrintDelete(deleted, taskList);
                } catch (DukeException e1) {
                    ui.PrintMessage(e1.getMessage());
                } catch (NumberFormatException e2) {
                    DukeException newException = new DukeException("Please input a number!");
                    ui.PrintMessage(newException.getMessage());
                }
            } else {
                try {
                    if (input.equals("blah")) {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    this.taskList.add(new Task(input));
                    ui.PrintMessage(String.format("added: %s", input));
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            }
            this.storage.Save(taskList);
            input = sc.nextLine();
        }
        ui.PrintMessage("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
