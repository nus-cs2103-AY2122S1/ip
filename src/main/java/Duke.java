import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    private ArrayList<Task> taskList;

    Duke() {
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();

        bot.accessFile();

        String openingMessage = "   -------------------------------------------- \n"
                        + "   Hello! I'm Duke \n"
                        + "   What can I do for you? \n"
                        + "   -------------------------------------------- \n";
        System.out.println(openingMessage);

        Scanner sc = new Scanner(System.in);
        boolean isReadingInput = true;
        while (sc.hasNextLine() && isReadingInput) {
            String input = sc.nextLine();
            String firstWord;
            String remainingWords = "";
            if (input.contains(" ")) {
                firstWord = bot.getFirstWord(input);
                remainingWords = bot.getRestOfWords(input);
            } else {
                firstWord = input;
            }

            switch (firstWord) {
            case "list":
                System.out.println(bot.printList());
                break;
            case "bye":
                System.out.println(bot.sayBye());
                isReadingInput = false;
                break;
            case "done":
                int index = Integer.parseInt(remainingWords);
                try {
                    System.out.println(bot.completeTask(index));
                } catch (DukeException e) {
                    bot.printErrorMessage("done");
                }
                break;
            case "todo":
                try {
                    System.out.println(bot.addTodo(remainingWords));
                } catch (DukeException e) {
                    bot.printErrorMessage("todo");
                }
                break;
            case "deadline":
                try {
                    System.out.println(bot.addDeadline(remainingWords));
                } catch (DukeException e) {
                    bot.printErrorMessage("deadline");
                }
                break;
            case "event":
                try {
                    System.out.println(bot.addEvent(remainingWords));
                } catch (DukeException e) {
                    bot.printErrorMessage("event");
                }
                break;
            case "delete":
                int deleteIndex = Integer.parseInt(remainingWords);
                try {
                    System.out.println(bot.deleteTask(deleteIndex));
                } catch (DukeException e) {
                    bot.printErrorMessage("event");
                }
                break;
            default:
                bot.printErrorMessage("error");
            }
        }
        sc.close();
    }

    public void printErrorMessage(String message) {
        String errorMessage = "   -------------------------------------------- \n";
        switch(message) {
        case "done":
            errorMessage += "      OOPS!!! Invalid task number given \n";
            break;
        case "todo":
            errorMessage += "      OOPS!!! The description of a todo cannot be empty. \n";
            break;
        case "deadline":
            errorMessage += "      OOPS!!! The description of a deadline cannot be empty. \n";
            break;
        case "event":
            errorMessage += "      OOPS!!! The description of an event cannot be empty. \n";
            break;
        case "delete":
            errorMessage += "      Invalid index. Specified task does not exist to be deleted. \n";
            break;
        default:
            errorMessage += "      OOPS!!! I have no idea what that means :-( \n";
        }
        errorMessage += "   -------------------------------------------- \n";
        System.out.println(errorMessage);
    }

    public void accessFile() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", "ip", "src", "main", "java");
        boolean directoryExists = java.nio.file.Files.exists(path);
        if (!directoryExists) {
            System.out.println("   Specified tasklist directory does not exist. " +
                    "Duke is unable to save future data unless directory is created.");
        } else {
            try {
                List<String> lines = Files.readAllLines(Paths.get(home, "Desktop", "ip", "src", "main", "java", "tasklist.txt"));
                for (String s: lines) {
                    parseTaskFromFile(s);
                }
            } catch (NoSuchFileException e) {
                System.out.println("   Tasklist file not found. Initialising empty tasklist...");
            } catch (IOException e) {
                System.out.println("   An error occurred reading the tasklist file. Initialising empty tasklist...");
            }
        }
    }

    public void parseTaskFromFile(String taskInput) {
        List<String> words = List.of(taskInput.split(" \\| "));
        switch (words.get(0)) {
        case "T":
            Todo t = new Todo(words.get(2));
            if (words.get(1).equals("X")) {
                t = t.markAsDone();
            }
            this.taskList.add(t);
            break;
        case "D":
            Deadline d = new Deadline(words.get(2), words.get(3));
            if (words.size() == 4) { // add only if date is  specified
                if (words.get(1).equals("X")) {
                    d = d.markAsDone();
                }
                this.taskList.add(d);
            }
            break;
        case "E":
            Event e = new Event(words.get(2), words.get(3));;
            if (words.size() == 4) { // add only if date is specified
                if (words.get(1).equals("0")) {
                    e = e.markAsDone();
                }
                this.taskList.add(e);
            }
        default:
            break;
        }
    }

    public String printList() {
        boolean isEmptyList = false;
        int counter = 1;
        String output = "   -------------------------------------------- \n"
                    + "   Here are the tasks in your list: \n";

        while (!isEmptyList) {
            if (this.taskList.isEmpty()) {
                output += "   Current List is empty... \n"
                        + "   -------------------------------------------- \n";
                isEmptyList = true;
            } else if (counter - this.taskList.size() == 1) { // when there are no more tasks to iterate
                output += "   -------------------------------------------- \n";
                isEmptyList = true;
            } else { // adds current task to the list based on counter index
                String lineAdded = String.format("   %d.%s \n", counter, this.taskList.get(counter - 1));
                output += lineAdded;
                counter++;
            }
        }
        return output;
    }

    public String sayBye() {
        return "   -------------------------------------------- \n"
                + "   Bye! Hope to see you again soon! \n"
                + "   --------------------------------------------";
    }

    public String completeTask(int index) throws DukeException {
        if (index <= 0 || index > taskList.size() || taskList.isEmpty()) {
            String errorMessage = "OOPS!!! Invalid task number given.";
            throw new DukeException(errorMessage);
        } else {
            String taskClass;
            if (this.taskList.get(index - 1) instanceof Todo) {
                taskClass = "Todo";
            } else if (this.taskList.get(index - 1) instanceof Deadline) {
                taskClass = "Deadline";
            } else if (this.taskList.get(index - 1) instanceof Event) {
                taskClass = "Event";
            } else {
                taskClass = "Task";
            }

            String output = "   -------------------------------------------- \n"
                    + "   Nice! I've marked this task as done: \n"
                    + "      ";

            switch (taskClass) {
            case "Todo":
                Todo completedTask = ((Todo) this.taskList.get(index - 1)).markAsDone();
                this.taskList.set(index - 1, completedTask);
                output += completedTask.toString() + "\n";
                break;
            case "Deadline":
                Deadline completedTask2 = ((Deadline) this.taskList.get(index - 1)).markAsDone();
                this.taskList.set(index - 1, completedTask2);
                output += completedTask2.toString() + "\n";
                break;
            case "Event":
                Event completedTask3 = ((Event) this.taskList.get(index - 1)).markAsDone();
                this.taskList.set(index - 1, completedTask3);
                output += completedTask3.toString() + "\n";
                break;
            default:
                Task completedTask4 = this.taskList.get(index - 1).markAsDone();
                this.taskList.set(index - 1, completedTask4);
                output += completedTask4.toString() + "\n";
            }
            output += "   -------------------------------------------- \n";
            return output;
        }
    }

    public String deleteTask(int deleteIndex) throws DukeException {
        if (deleteIndex <= 0 || deleteIndex > taskList.size() || taskList.isEmpty()) {
            String errorMessage = "OOPS!!! Invalid index to be deleted.";
            throw new DukeException(errorMessage);
        } else {
            String deletedTask = this.taskList.get(deleteIndex - 1).toString();
            if (this.taskList.size() == 1) { // if there is only one task in the list
                this.taskList.clear();
            } else if (deleteIndex == 1){ // deleting leftmost element
                this.taskList = new ArrayList<>(taskList.subList(1, taskList.size()));
            } else if (deleteIndex == taskList.size()) { // deleting rightmost element
                this.taskList = new ArrayList<>(taskList.subList(0, taskList.size() - 1));
            } else { // deleting somewhere in between
                ArrayList<Task> newList = new ArrayList<>(this.taskList.subList(0, deleteIndex - 1));
                for (int i = deleteIndex; i < taskList.size(); i++) {
                    newList.add(taskList.get(i));
                }
                this.taskList = newList;
            }

            return "   -------------------------------------------- \n"
                    + "   Noted. I've removed this task: \n      "
                    + deletedTask
                    + String.format("\n   Now you have %d tasks in the list.", taskList.size()) + "\n"
                    + "   -------------------------------------------- \n";
        }
    }

    public String addTodo(String input) throws DukeException {
        if (!input.equals("")) {
            Todo newTodo = new Todo(input);
            this.taskList.add(newTodo);
            return printTaskMessage(newTodo.toString(), this.taskList.size());
        } else {
            String errorMessage = "OOPS!!! The description of a todo cannot be empty.";
            throw new DukeException(errorMessage);
        }
    }

    public String addDeadline(String input) throws DukeException {
        if (!input.equals("")) {
            String[] arr = input.split("/by", 2);
            String day = arr[1];
            Deadline newDeadline = new Deadline(arr[0].trim(), day.trim());
            this.taskList.add(newDeadline);
            return printTaskMessage(newDeadline.toString(), this.taskList.size());
        } else {
            String errorMessage = "OOPS!!! The description of a deadline cannot be empty.";
            throw new DukeException(errorMessage);
        }
    }

    public String addEvent(String input) throws DukeException {
        if (!input.equals("")) {
            String[] arr = input.split("/at", 2);
            String time = arr[1];
            Event newEvent = new Event(arr[0].trim(), time.trim());
            this.taskList.add(newEvent);
            return printTaskMessage(newEvent.toString(), this.taskList.size());
        } else {
            String errorMessage = "OOPS!!! The description of an event cannot be empty.";
            throw new DukeException(errorMessage);
        }
    }

    public String printTaskMessage(String input, int numTasks) {
        return "   -------------------------------------------- \n"
                + "   Got it. I've added this task: \n      "
                + input
                + String.format("\n   Now you have %d tasks in the list.", numTasks) + "\n"
                + "   -------------------------------------------- \n";
    }

    public String getFirstWord(String input) {
        String[] arr = input.split(" ", 2);
        return arr[0];
    }

    public String getRestOfWords(String input) {
        String[] arr = input.split(" ", 2);
        return arr[1];
    }
}
