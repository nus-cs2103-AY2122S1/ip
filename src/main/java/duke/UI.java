package duke;

import java.io.IOException;

/**
 * Main logic of Duke chatbot on responding to commands and showing output to user.
 */
public class UI {
    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Necessary fields for commands to be executed.
     *
     * @param parser
     * @param storage
     * @param taskList
     */
    public UI(Parser parser, Storage storage, TaskList taskList) {
        //Greet
        this.parser = parser;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Main method to be constantly taking in commands until "bye".
     * 
     * @throws DukeException
     */
    public void start() throws DukeException{
        welcomeMsg();

        boolean isExit = false;
        while (!isExit) {
            CommandType command = parser.nextCommand();

            int taskIndex;
            Task newTask;

            switch (command) {
            case Exit:
                isExit = true;
                exitMsg();
                try {
                    storage.writeData();
                } catch (IOException e) {
                    System.out.println("Error saving file");
                }
                break;
            case List:
                listMsg();
                break;
            case DeleteTask:
                taskIndex = parser.getIndex();
                deleteMsg(taskIndex);
                taskList.deleteTask(taskIndex);
                break;
            case MarkAsDone:
                taskIndex = parser.getIndex();
                printBreakLine();
                taskList.markAsDone(taskIndex);
                printBreakLine();
                break;
            case AddToDo:
            case AddEvent:
            case AddDeadline:
                try {
                    newTask = parser.generateTask();
                    taskList.addTask(newTask);
                    addTaskMsg(newTask);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case Find:
                //TODO
                break;
            case Error:
                printBreakLine();
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            default:
                break;
            }
        }
    }

    private void addTaskMsg(Task newTask) {
        printBreakLine();
        System.out.println("  Got it. I've added this task: ");
        System.out.println("  " + newTask.toString());
        System.out.printf("  Now you have %d tasks in the list.%n", taskList.getListSize());
        printBreakLine();
    }

    private void exitMsg() {
        printBreakLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printBreakLine();
    }

    private void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printBreakLine();
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        printBreakLine();
    }

    private void listMsg() {
        printBreakLine();
        System.out.println("  Here are the tasks in your list:");
        for (int i = 1; i < taskList.getListSize() + 1; i++) {
            System.out.printf("  %d. %s%n", i, taskList.getTaskString(i - 1));
        }
        printBreakLine();
    }

    private void deleteMsg(int index) {
        printBreakLine();
        System.out.println("  Noted. I've removed this task:");
        System.out.println("  " + taskList.getTaskString(index));
        System.out.printf("  Now you have %d tasks in the list.%n", taskList.getListSize() - 1);
        printBreakLine();
    }

    private void printBreakLine() {
        for (int i = 0; i < 12; i++) {
            System.out.print("===");
        }
        System.out.println();
    }
}
