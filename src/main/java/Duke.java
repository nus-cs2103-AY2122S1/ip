import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean readingInput;
    private ArrayList<Task> taskList;

    Duke() {
        this.readingInput = true;
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Duke bot = new Duke();

        String openingMessage = "   -------------------------------------------- \n"
                        + "   Hello! I'm Duke \n"
                        + "   What can I do for you? \n"
                        + "   -------------------------------------------- \n";
        System.out.println(openingMessage);

        Scanner sc = new Scanner(System.in);

        while (bot.readingInput) {
            String input = sc.nextLine();
            String firstWord;
            String remainingWords = "";
            if (input.contains(" ")) {
                firstWord = bot.getFirstWord(input);
                remainingWords = bot.getRestOfWords(input);
            } else {
                firstWord = input;
            }

            String output;

            switch (firstWord) {
                case "list":
                    output = bot.printList();
                    break;

                case "bye":
                    output = bot.sayBye();
                    break;

                case "done":
                    int index = Integer.parseInt(remainingWords);
                    output = bot.completeTask(index);
                    break;

                default:
                    output = bot.addTask(input);

            }
            System.out.println(output);
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
                String lineAdded = String.format("   %d. %s \n", counter, this.taskList.get(counter - 1));
                output += lineAdded;
                counter++;
            }
        }
        return output;
    }

    public String sayBye() {
        this.readingInput = false;
        return "   -------------------------------------------- \n"
                + "   Bye! Hope to see you again soon! \n"
                + "   -------------------------------------------- \n";
    }

    public String completeTask(int index) {
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

    public String addTask(String input) {
        String firstWord;
        String remainingWords = "";
        if (input.contains(" ")) {
            firstWord = this.getFirstWord(input);
            remainingWords = this.getRestOfWords(input);
        } else {
            firstWord = input;
        }

        String output;

        switch (firstWord) {
            case "todo":
                Todo newTodo = new Todo(remainingWords);
                this.taskList.add(newTodo);
                output = "   -------------------------------------------- \n"
                        + "   Got it. I've added this task: \n"
                        + "      " + newTodo + "\n";
                break;

            case "deadline":
                String[] arr = remainingWords.split("/by", 2);
                String day = arr[1];
                Deadline newDeadline = new Deadline(arr[0].trim(), day.trim());
                this.taskList.add(newDeadline);
                output = "   -------------------------------------------- \n"
                        + "   Got it. I've added this task: \n"
                        + "      " + newDeadline + "\n";
                break;

            case "event":
                String[] arr2 = remainingWords.split("/at", 2);
                String time = arr2[1];
                Event newEvent = new Event(arr2[0].trim(), time.trim());
                this.taskList.add(newEvent);
                output = "   -------------------------------------------- \n"
                        + "   Got it. I've added this task: \n"
                        + "      " + newEvent + "\n";
                break;

            default:
                Task newTask = new Task(input);
                this.taskList.add(newTask);
                output = "   -------------------------------------------- \n"
                        + "   Got it. I've added this task: \n"
                        + "      " + newTask + "\n";

        }

        int numTasks = this.taskList.size();
        output += String.format("   Now you have %d tasks in the list.", numTasks) + "\n"
                + "   -------------------------------------------- \n";
        return output;
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
