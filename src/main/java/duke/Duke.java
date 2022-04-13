package duke;

import java.io.IOException;
import java.util.Locale;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Boolean isRunning;
    private Parser parser;

    /**
     * Constructor for Duke program.
     *
     * @throws IOException
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/dukeTaskList.txt");
        this.taskList = new TaskList();
        this.isRunning = false;
        this.parser = new Parser();
        if (storage.getDoesFileExists()) {
            this.storage.retrieveFile(this.taskList);
        } else {
            this.storage.createFile();
        }
    }

    String getResponse(String input) {
        input = input.toLowerCase(Locale.ROOT);
        assert this.storage.getDoesFileExists();
        String response;
        try {
            if (input.equals("list")) {
                //List task list.
                response = this.ui.listTaskList(this.taskList);
            } else if (input.contains("done")) {
                //Set task as done.
                Integer listIndex = parser.doneInputParser(input);
                this.taskList.setTaskDone(listIndex);
                response = this.ui.doneTaskMsg(this.taskList.getTask(listIndex));
            } else if (input.contains("delete")) {
                //Deletes task.
                Integer removeTaskIndex = parser.deleteInputParser(input);
                Task removedTask = taskList.removeTask(removeTaskIndex);
                response = this.ui.deleteTaskMsg(removedTask, this.taskList.taskListSize());
            } else if (input.contains("find")) {
                //Find tasks
                String keyword = parser.findInputParser(input);
                TaskList taskListWithKeyword = this.taskList.findTasks(keyword);
                response = this.ui.findTaskMsg(taskListWithKeyword);
            } else if (input.equals("help")) {
                //Help message.
                response = this.ui.helpMsg();
            } else if (input.contains("help")) {
                //Returns format of command.
                String helpCommand = parser.helpInputParser(input);
                response = this.ui.helpCommandMsg(helpCommand);
            } else {
                //Initialise the task if its a valid input.
                Task newTask = null;
                if (input.contains("todo")) {
                    newTask = new Todo(parser.toDoInputParser(input));
                } else if (input.contains("deadline")) {
                    newTask = new Deadline(parser.deadlineInputParser(input)[0],
                            parser.deadlineInputParser(input)[1]);
                } else if (input.contains("event")) {
                    newTask = new Event(parser.eventInputParser(input)[0],
                            parser.eventInputParser(input)[1]);
                }
                if (newTask != null) {
                    //Add task to the list and print message.
                    taskList.addTask(newTask);
                    response = this.ui.addTaskMsg(newTask, this.taskList.taskListSize());
                } else {
                    //For invalid input message.
                    throw new WrongInputException();
                }

            }
        } catch (DukeException e) {
            return (e.toString()
                    + "\n");
        }
        this.storage.saveFile(this.taskList);
        return response;
    }
}



