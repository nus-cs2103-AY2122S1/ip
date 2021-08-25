import duke.commands.*;
import duke.data.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Find finder;

    public Main() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList(storage.Load());
        this.parser = new Parser();
        this.finder = new Find();
    }

    public void run() {
        this.ui.PrintIntro();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!this.parser.isBye(input)) {
            String[] splitInput = this.parser.splitType(input);
            if (this.parser.isDone(splitInput[0])) {
                int index = this.parser.getIndex(splitInput);
                String returnString = this.taskList.markDone(index);
                this.ui.PrintMessage(returnString);
            } else if (this.parser.isList(splitInput[0])) {
                this.ui.PrintList(taskList);
            } else if (this.parser.isTodo(splitInput[0])) {
                try {
                    this.parser.parseTodo(splitInput);
                    this.taskList.add(new Todo(splitInput[1]));
                    ui.PrintSpecialTasks(taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (this.parser.isDeadline(splitInput[0])) {
                try {
                    String[] furtherSplits = this.parser.parseDeadline(splitInput);
                    LocalDateTime by = this.parser.parseTime(furtherSplits[1]);
                    this.taskList.add(new Deadline(furtherSplits[0], by));
                    ui.PrintSpecialTasks(this.taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (this.parser.isEvent(splitInput[0])) {
                try {
                    String[] furtherSplits = this.parser.parseEvent(splitInput);
                    LocalDateTime at = this.parser.parseTime(furtherSplits[1]);
                    this.taskList.add(new Event(furtherSplits[0], at));
                    ui.PrintSpecialTasks(this.taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (this.parser.isDelete(splitInput[0])) {
                try {
                    int index = this.parser.parseDelete(splitInput);
                    this.parser.checkTaskIndex(index, taskList);
                    Task deleted = this.taskList.delete(index);
                    ui.PrintDelete(deleted, taskList);
                } catch (DukeException e1) {
                    ui.PrintMessage(e1.getMessage());
                } catch (NumberFormatException e2) {
                    DukeException newException = new DukeException("Please input a number!");
                    ui.PrintMessage(newException.getMessage());
                }
            } else if (this.parser.isFind(splitInput[0])){
                try {
                    this.parser.parseFind(splitInput);
                    TaskList matched = this.finder.findMatch(splitInput[1], taskList);
                    ui.PrintFind(matched);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else {
                try {
                    if (this.parser.isBlah(input)) {
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
        new Main().run();
    }
}
