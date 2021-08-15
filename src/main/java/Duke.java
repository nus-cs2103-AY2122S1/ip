import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> taskList;

    Duke() {
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();

        String openingMessage = "   -------------------------------------------- \n"
                        + "   Hello! I'm Duke \n"
                        + "   What can I do for you? \n"
                        + "   -------------------------------------------- \n";
        System.out.println(openingMessage);

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (sc.hasNextLine() && flag) {
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
                    flag = false;
                    break;

                case "done":
                    int index = Integer.parseInt(remainingWords);
                    try {
                        System.out.println(bot.completeTask(index));
                    } catch (DukeException e) {
                        String errorMessage = "   -------------------------------------------- \n"
                                            + "      OOPS!!! Invalid task number given \n"
                                            + "   -------------------------------------------- \n";
                        System.out.println(errorMessage);
                    }
                    break;

                case "todo":
                    try {
                        System.out.println(bot.addTodo(remainingWords));
                    } catch (DukeException e) {
                        String errorMessage = "   -------------------------------------------- \n"
                                            + "      OOPS!!! The description of a todo cannot be empty. \n"
                                            + "   -------------------------------------------- \n";
                        System.out.println(errorMessage);
                    }
                    break;

                case "deadline":
                    try {
                        System.out.println(bot.addDeadline(remainingWords));
                    } catch (DukeException e) {
                        String errorMessage = "   -------------------------------------------- \n"
                                            + "      OOPS!!! The description of a deadline cannot be empty. \n"
                                            + "   -------------------------------------------- \n";
                        System.out.println(errorMessage);
                    }
                    break;

                case "event":
                    try {
                        System.out.println(bot.addEvent(remainingWords));
                    } catch (DukeException e) {
                        String errorMessage = "   -------------------------------------------- \n"
                                            + "      OOPS!!! The description of an event cannot be empty. \n"
                                            + "   -------------------------------------------- \n";
                        System.out.println(errorMessage);
                    }
                    break;

                case "delete":
                    int deleteIndex = Integer.parseInt(remainingWords);
                    try {
                        System.out.println(bot.deleteTask(deleteIndex));
                    } catch (DukeException e) {
                        String errorMessage = "   -------------------------------------------- \n"
                                + "      Invalid index. Specified task does not exist to be deleted. \n"
                                + "   -------------------------------------------- \n";
                        System.out.println(errorMessage);
                    }
                    break;

                default:
                    String errorMessage = "   -------------------------------------------- \n"
                                        + "      OOPS!!! I have no idea what that means :-( \n"
                                        + "   -------------------------------------------- \n";
                    System.out.println(errorMessage);
            }
        }
        sc.close();
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
