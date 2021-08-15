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
        String output = "   -------------------------------------------- \n";

        while (!isEmptyList) {
            if (this.taskList.isEmpty()) {
                output += "   Current List is empty... \n"
                        + "   -------------------------------------------- \n";
                isEmptyList = true;
            } else if (counter - this.taskList.size() == 1) { // when there are no more tasks to iterate
                output += "   -------------------------------------------- \n";
                isEmptyList = true;
            } else { // adds current task to the list based on counter index
                String lineAdded = String.format("   %d.[%s] %s \n", counter, this.taskList.get(counter - 1).getStatusIcon(),
                                    this.taskList.get(counter - 1).description);
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
        Task completedTask = this.taskList.get(index - 1).markAsDone();
        this.taskList.set(index - 1, completedTask);
        return "   -------------------------------------------- \n"
                + "   Nice! I've marked this task as done: \n"
                + "   "
                + String.format("[X] %s \n", this.taskList.get(index - 1).description)
                + "   -------------------------------------------- \n";
    }

    public String addTask(String input) {
        Task newTask = new Task(input);
        this.taskList.add(newTask);
        return "   -------------------------------------------- \n"
                + "   added: " + input + "\n"
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
