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

    public void stopDuke(TaskList taskList) throws IOException {
        this.isRunning = false;
        this.storage.saveFile(taskList);
        this.ui.goodbyeMsg();
    }

    public void run() throws IOException {
        this.isRunning = true;
        this.ui.greetingMsg();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning){
            String input = sc.nextLine();
            if (input.equals("bye")) {
                stopDuke(this.taskList);
            } else if (input.equals("list")) {
                this.ui.listTaskList(this.taskList);
            } else if (input.contains("done ")) {
                Integer listIndex = parser.doneInputParser(input);
                this.taskList.setTaskDone(listIndex);
                this.ui.doneTaskMsg(this.taskList.getTask(listIndex));
            } else if (input.contains("delete ")) {
                Integer removeTaskIndex = parser.deleteInputParser(input);
                Task removedTask = taskList.removeTask(removeTaskIndex);
                this.ui.deleteTaskMsg(removedTask);
            } else {
                try {
                    //Initialise the task if its a valid input.
                    Task newTask = null;
                    if (input.contains("todo")) {
                        newTask = new Todo(parser.toDoInputParser(input));
                    } else if (input.contains("deadline")) {
                        if (!input.contains("/by")) {
                            throw new MissingDateException();
                        } else {
                            newTask = new Deadline(parser.deadlineInputTaskParser(input),
                                    parser.deadlineInputDateParser(input));
                        }
                    } else if (input.contains("event")) {
                        if (!input.contains("/at")) {
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



