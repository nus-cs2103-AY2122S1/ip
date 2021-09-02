package edith.ui;

import edith.tasks.Task;
import edith.tasks.TaskList;

import java.util.Scanner;

/**
 * deals with interactions with the user.
 */
public class Ui {
    /**
     * Reads line of command.
     *
     * @return String of command.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints welcome message, to be shown when Duke starts up at the beginning.
     */
    public String printWelcome() {
        return "Hi Peter~\n" + "I am E.D.I.T.H. Tony's A.I.\n" + "Let me know what do you need help in!";
    }

    /**
     * Prints bye messsage, to be shown when user terminates Duke with "Bye" command.
     */
    public String printBye() {
        return "Come back soon! \n" + "I'll always be here for you <3";
    }

    /**
     * Prints loading error message, to be shown during start of Duke.
     */
    public String printLoadingError() {
        return "Oh no, there was a problem loading this file...\n" + "Wanna try another file instead?";
    }

    /**
     * Prints invalid type error, to be shown when Type command is not included in Instructions enum.
     */
    public String printInvalidTypeError() {
        return "Oh no, I do not understand this type...\n" + "So far, I am only able to understand these types: \n" 
            + "Todo, Deadline, Event, List, Delete, Done, Bye";
    }

    /**
     * Prints invalid index error, to be shown when index does not exist/is more than size of task list.
     */
    public String printInvalidIndexError() {
        return "Oh no, this index does not exist!\n" + "Please make sure that index < size of tasks";
    }

    /**
     * Shows all the items in the task list.
     *
     * @param tasks Task list to be printed.
     */
    public String printList(TaskList tasks) {
        StringBuilder listMsg = new StringBuilder("You wanna know what you have on your list?\n" 
                + "I got you covered!!\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            int taskNo = i + 1;
            Task curr = tasks.getTask(i);
            listMsg.append(taskNo).append(". ").append(curr.toString()).append("\n");
        }
        return listMsg.toString();
    }

    /**
     * Prints the entire tasks list when user uses "find" command.
     *
     * @param taskList Task list to be printed.
     * @param keyword Search task list and only print items that matches the keyword.
     */
    public String printFindResults(TaskList taskList, String keyword) {
        StringBuilder findResultsMsg;
        if (taskList.getSize() > 0) {
            findResultsMsg = new StringBuilder("This is what I have found based on the keyword: " + keyword);
            for (int i = 0; i < taskList.getSize(); i++) {
                int taskNo = i + 1;
                Task curr = taskList.getTask(i);
                findResultsMsg.append(taskNo).append(". ").append(curr.toString()).append("\n");
            }
        } else {
            findResultsMsg = new StringBuilder("Oops, based on your keyword: "
                    + keyword + ", I am not able to find any match :(");
        }
        return findResultsMsg.toString();
    }
    
    public String printAddCommand(Task task, TaskList taskList) {
        return "Sure Peter!\n" + "I Have added " + task.toString() + " to your list.\n"
                + "Now you have a total of " + taskList.getSize() + " tasks!";
    }
    
    public String printDoneCommand(Task task, TaskList taskList) {
        return "Good job on completing your task Peter!\n" + "Mr. Stark would have been so proud of you.\n"
                + "I have marked this task as done: " + task.toString() + "\n Now you have "
                + taskList.getSize() + " tasks left.";
    }
    
    public String printEmptyList() {
        return "Wow Peter, there's nothing on your list\n" + "Hmm......... I wonder why.";
    }
    
    public String printDeleteCommand(Task task, TaskList taskList) {
        return "Okay Peter, I have deleted this task from you list:\n" + task.toString()
                + "\n Now, you have " + taskList.toString() + " tasks left.";
    }
    
    public String printFileNotFoundError() {
        return "Sorry Peter, I am not able to find your file.";
    }
}
