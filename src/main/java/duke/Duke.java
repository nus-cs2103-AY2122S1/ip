package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Boolean isRunning;
    private Parser parser;

    public Duke(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.isRunning = false;
        this.parser = new Parser();
        if(storage.getDoesFileExists()) {
            this.storage.retrieveFile(this.taskList);
        } else {
            this.storage.createFile();
        }
    }

    /**
     * Stops duke bot and save file.
     *
     * @param taskList Task list to save onto file.
     * @throws IOException If there is wrong input or output.
     */
    public void stopDuke(TaskList taskList) throws IOException {
        this.isRunning = false;
        this.storage.saveFile(taskList);
        this.ui.goodbyeMsg();
    }

    /**
     * Runs duke bot.
     *
     * @throws IOException If there is wrong input or output.
     */
    public void run() throws IOException {
        this.isRunning = true;
        this.ui.greetingMsg();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning){
            String input = sc.nextLine();
            if (input.equals("bye")) {
                //Ends duke bot
                stopDuke(this.taskList);
            } else if (input.equals("list")) {
                //List task list
                this.ui.listTaskList(this.taskList);
            } else if (input.contains("done ")) {
                //Set task as done
                Integer listIndex = parser.doneInputParser(input);
                this.taskList.setTaskDone(listIndex);
                this.ui.doneTaskMsg(this.taskList.getTask(listIndex));
            } else if (input.contains("delete ")) {
                //Deletes task
                Integer removeTaskIndex = parser.deleteInputParser(input);
                Task removedTask = taskList.removeTask(removeTaskIndex);
                this.ui.deleteTaskMsg(removedTask);
            } else if(input.contains("find ")) {
                //Find tasks
                String keyword = parser.findInputParser(input);
                TaskList taskListWithKeyword = this.taskList.findTasks(keyword);
                this.ui.findTaskMsg(taskListWithKeyword);
            } else {
                try {
                    //Initialise the task if its a valid input.
                    Task newTask = null;
                    if (input.contains("todo")) {
                        newTask = new Todo(parser.toDoInputParser(input));
                    } else if (input.contains("deadline")) {
                        if (!input.contains("/by")) {
                            //for missing dateline
                            throw new MissingDateException();
                        } else {
                            newTask = new Deadline(parser.deadlineInputTaskParser(input),
                                    parser.deadlineInputDateParser(input));
                        }
                    } else if (input.contains("event")) {
                        if (!input.contains("/at")) {
                            //for missing dateline
                            throw new MissingDateException();
                        } else {
                            newTask = new Event(parser.eventInputTaskParser(input),
                                    parser.eventInputDateParser(input));
                        }
                    }
                    if (newTask != null) {
                        //Add task to the list and print message.
                        taskList.addTask(newTask);
                        this.ui.addTaskMsg(newTask);
                    } else {
                        //For invalid input message
                        throw new WrongInputException();
                    }

                } catch (DukeException e) {
                    System.out.print( e.toString()
                            + "\n");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/dukeTaskList.txt").run();
    }
}



