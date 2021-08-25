package duke;

import duke.task.*;
import dukeException.DukeException;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }

    public void run() {
        //Greet
        this.ui.greet();

        //Get input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Parser parser = new Parser();

            try {
                Command inputCommand = parser.parseCommand(input);
                switch (inputCommand) {
                    case LIST: {
                        if (this.tasks.isEmpty()) {
                            this.ui.showNoTaskMessage();
                        } else {
                            this.ui.showTaskList(this.tasks);
                        }
                        break;
                    }

                    case DONE: {
                        int taskNo = parser.getTaskNo(input);
                        Task doneTask = this.tasks.get(taskNo);
                        String oldContent = doneTask.toString();
                        doneTask.markAsDone();
                        this.storage.editTask(oldContent, doneTask.toString());
                        this.ui.showDoneMessage(doneTask);
                        break;

                    }

                    case DELETE: {
                        int taskNo = parser.getTaskNo(input);
                        Task deletedTask = this.tasks.get(taskNo);
                        this.tasks.remove(taskNo);
                        this.storage.deleteTask(deletedTask.toString());
                        this.ui.showDeleteMessage(deletedTask, this.tasks.size());
                        break;

                    }

                    case TODO: {
                        String description = parser.parseDescription(input);
                        Task newTask = new Todo(description);
                        this.tasks.add(newTask);
                        this.storage.writeTask(newTask.toString());
                        this.ui.showAddTaskMessage(newTask, this.tasks.size());
                        break;
                    }

                    case DEADLINE: {
                        String[] separatedContent = parser.parseDescription(input, "by");
                        Task newTask = new Deadline(separatedContent[0], separatedContent[1]);
                        this.tasks.add(newTask);
                        this.storage.writeTask(newTask.toString());
                        this.ui.showAddTaskMessage(newTask, this.tasks.size());
                        break;
                    }

                    case EVENT: {
                        String[] separatedContent = parser.parseDescription(input, "at");
                        Task newTask = new Event(separatedContent[0], separatedContent[1]);
                        this.tasks.add(newTask);
                        this.storage.writeTask(newTask.toString());
                        this.ui.showAddTaskMessage(newTask, this.tasks.size());
                        break;
                    }
                }
                input = sc.nextLine();
            } catch (DukeException e) {
                this.ui.showErrorMessage(e);
                input = sc.nextLine();
            }
        }

        this.ui.bye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}