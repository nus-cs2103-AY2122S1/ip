import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    int numOfTasks;
    ArrayList<Task> tasks;

    Duke() {
        this.numOfTasks = 0;
        this.tasks = new ArrayList<Task>();
    }

    public void list() {
        if (this.numOfTasks == 0) {
            System.out.println("List is empty!");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.numOfTasks; i++) {
            Task temp = this.tasks.get(i);
            System.out.printf("%s. %s\n", i + 1, temp);
        }

    }

    public void markAsDone(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index > numOfTasks - 1) {
                if (numOfTasks > 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There are only %s tasks in your list.", taskNumber, numOfTasks));
                } else if (numOfTasks == 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is only %s task in your list.", taskNumber, numOfTasks));
                } else {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is no task in your list.", taskNumber));
                }
            } else if (index < 0) {
                throw new DukeException(String.format("There is no task %s.",taskNumber));
            }
            this.tasks.get(index).markAsDone();
        } catch (NumberFormatException ex) {
            System.err.println("Task must be an integer!");
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addTask(String task) {
        try {
            if (task.startsWith("todo")) {
                if (task.substring(4).equals("") || task.substring(4).equals(" ")) {
                    throw new DukeException("The description of todo cannot be empty");
                }
                this.tasks.add(new ToDo(task.substring(5)));
            } else if (task.startsWith("event")) {
                int index = task.indexOf("/at ");
                if (index == -1) {
                    throw new DukeException("You must specify the time for an event.");
                }
                String description = task.substring(6, index - 1);
                String time = task.substring(index + 4);
                this.tasks.add(new Event(description, time));
            } else if (task.startsWith("deadline")) {
                int index = task.indexOf("/by ");
                if (index == -1) {
                    throw new DukeException("You must specify the deadline.");
                }
                String description = task.substring(9, index - 1);
                String time = task.substring(index + 4);
                this.tasks.add(new Deadline(description, time));
            } else {
                throw new DukeException("Sorry I don't understand what that means:(");
            }
            System.out.println("Got it! I have added this task:");
            System.out.println(this.tasks.get(this.numOfTasks));
            this.numOfTasks = this.numOfTasks + 1;
            if (this.numOfTasks > 1) {
                System.out.printf("Now you have %s tasks in your list.\n", this.numOfTasks);
            } else if (this.numOfTasks == 1){
                System.out.printf("Now you have 1 task in your list.\n");
            } else {
                System.out.println("Your list is empty!");
            }
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index > numOfTasks - 1) {
                if (numOfTasks > 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There are only %s tasks in your list.", taskNumber, numOfTasks));
                } else if (numOfTasks == 1){
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is only 1 task in your list.", taskNumber));
                } else {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is no task in your list.", taskNumber));
                }
            } else if (index < 0) {
                throw new DukeException(String.format("There is no task %s.",taskNumber));
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(this.tasks.get(index));
            this.tasks.remove(index);
            this.numOfTasks = this.numOfTasks - 1;
            if (this.numOfTasks > 1) {
                System.out.printf("You have %s tasks left on your list.\n", this.numOfTasks);
            } else {
                System.out.printf("You have %s task left on your list.\n", this.numOfTasks);
            }
        } catch (NumberFormatException ex) {
            System.err.println("Task must be an integer!");
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        System.out.println("Hello I am Duke.\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        while (true) {
            if (echo.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            }
            if (echo.equals("List")) {
                duke.list();
                echo = scanner.nextLine();
                continue;
            }
            if (echo.startsWith("done")) {
                String[] parsed = echo.split(" ");
                if (parsed.length > 1) {
                    duke.markAsDone(parsed[1]);
                } else {
                    duke.addTask(echo);
                }
                echo = scanner.nextLine();
                continue;
            }
            if (echo.equals("")) {
                System.out.println("Please enter a new task or action.");
                echo = scanner.nextLine();
                continue;
            }
            if (echo.startsWith("delete")) {
                String[] parsed = echo.split(" ");
                if (parsed.length > 1) {
                    duke.deleteTask(parsed[1]);
                } else {
                    duke.addTask(echo);
                }
                echo = scanner.nextLine();
                continue;
            }
            duke.addTask(echo);
            echo = scanner.nextLine();
        }

    }
}
