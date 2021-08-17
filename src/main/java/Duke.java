import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> taskArr = new ArrayList<Task>();
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }


    private void textFrame(String s) {
        System.out.println(".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.\n"
                        + s + "\n"
                        + ".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.");
    }

    private void errorFrame(String s) {
        System.out.println(".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.\n"
                        + s + "\n"
                        + ".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.");
    }

    private void listTasks() {
        String listString = "";
        if (taskArr.size() == 0) {
            listString = "\n Your list is empty!";
        } else {
            for (int i = 0; i < taskArr.size(); i++) {
                int count = i + 1;
                listString += "\n " + count + ". " + taskArr.get(i);
            }
        }
        textFrame(" This be ye list of things to do Sire:" + listString );
    }

    private void addTask(String taskString, TaskType taskType) {
        try{
            switch(taskType) {
                case TODO:
                    taskArr.add(new ToDo(taskString));
                    break;
                case DEADLINE:
                    String[] deadlineArr = taskString.split(" /by ", 2);
                    if(deadlineArr.length == 1) {
                        throw new DukeException("deadline format");
                    } else {
                        taskArr.add(new Deadline(deadlineArr[0], deadlineArr[1]));
                    }
                    break;
                case EVENT:
                    String[] eventArr = taskString.split(" /at ", 2);
                    if(eventArr.length == 1) {
                        throw new DukeException("event format");
                    } else {
                        taskArr.add(new Event(eventArr[0], eventArr[1]));
                    }
                    break;
                default:
                    System.out.println("should never reach here");
            }
            textFrame("Got it I've added this task:\n" + taskArr.get(taskArr.size() - 1));
        } catch (DukeException e) {
            errorFrame(e.getErrorMessage());
        }

    }

    public void startBob() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String inputText = scanner.nextLine();
            if (inputText.equals("bye")) {
                textFrame("Bye bye from the List Adder!");
                break;
            } else {
                String[] inputArr = inputText.split(" ", 2);
                try {
                    if (inputArr.length == 1) {
                        switch (inputArr[0]) {
                            case "list":
                                listTasks();
                                break;
                            case "delete":
                                throw new DukeException("empty delete");
                            case "todo":
                                throw new DukeException("empty todo");
                            case "deadline":
                                throw new DukeException("empty deadline");
                            case "event":
                                throw new DukeException("empty event");
                            default:
                                throw new DukeException("invalid input");
                        }
                    } else {
                        switch (inputArr[0]) {
                            case "todo":
                                addTask(inputArr[1], TaskType.TODO);
                                break;
                            case "deadline":
                                addTask(inputArr[1], TaskType.DEADLINE);
                                break;
                            case "event":
                                addTask(inputArr[1], TaskType.EVENT);
                                break;
                            case "done":
                                try {
                                    int index = Integer.parseInt(inputArr[1]) - 1;
                                    if (index >= taskArr.size() || index <= -1) {
                                        errorFrame(" That task does not exist!");
                                    } else {
                                        taskArr.get(index).setDone();
                                        textFrame("Good job, I have marked the task as done!" + "\n" +
                                                taskArr.get(index).toString());
                                    }
                                } catch (NumberFormatException e) {
                                    errorFrame("That is not a valid index!");
                                }
                                break;
                            case "delete":
                                try {
                                    int index = Integer.parseInt(inputArr[1]) - 1;
                                    if (index >= taskArr.size() || index <= -1) {
                                        errorFrame(" That task does not exist!");
                                    } else {
                                        Task deletedTask = taskArr.remove(index);
                                        textFrame("Loser! I have deleted that task for you" + "\n" +
                                                deletedTask + "\n" +
                                                "Now you have " + taskArr.size() + " tasks left.");

                                    }
                                } catch (NumberFormatException e) {
                                    errorFrame("That is not a valid index!");
                                }
                                break;
                            default:
                                throw new DukeException("invalid input");
                        }
                    }
                } catch (DukeException e) {
                    errorFrame(e.getErrorMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        String logo = "                     ,,\n" +
                "`7MM\"\"\"Yp,          *MM\n" +
                "  MM    Yb           MM\n" +
                "  MM    dP  ,pW\"Wq.  MM,dMMb.\n" +
                "  MM\"\"\"bg. 6W'   `Wb MM    `Mb\n" +
                "  MM    `Y 8M     M8 MM     M8\n" +
                "  MM    ,9 YA.   ,A9 MM.   ,M9\n" +
                ".JMMmmmd9   `Ybmd9'  P^YbmdP'"
                + "\n\n"
                + "      .--..--..--..--..--..--.\n"
                + "    .' \\  (`._   (_)     _   \\\n"
                + "  .'    |  '._)         (_)  |\n"
                + "  \\ _.')\\      .----..---.   /\n"
                + "  |(_.'  |    /    .-\\-.  \\  |\n"
                + "  \\     0|    |   ( O| O) | o|\n"
                + "   |  _  |  .--.____.'._.-.  |\n"
                + "   \\ (_) | o         -` .-`  |\n"
                + "    |    \\   |`-._ _ _ _ _\\ /\n"
                + "    \\    |   |  `. |_||_|   |\n"
                + "    | o  |    \\_      \\     |     -.   .-.\n"
                + "    |.-.  \\     `--..-'   O |     `.`-' .'\n"
                + "  _.'  .' |     `-.-'      /-.__   ' .-'\n"
                + ".' `-.` '.|='=.='=.='=.='=|._/_ `-'.'\n"
                + "`-._  `.  |________/\\_____|    `-.'\n"
                + "   .'   ).| '=' '='\\/ '=' |\n"
                + "   `._.`  '---------------'\n"
                + "           //___\\   //___\\\n"
                + "             ||       ||\n"
                + "             ||_.-.   ||_.-.\n"
                + "            (_.--__) (_.--__)\n"
                + "asciiart.eu/cartoons/spongebob-squarepants\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Let's Talk!\n");
        System.out.println("What would you like me to add to the list?");
        Duke duke = new Duke();
        duke.startBob();
    }
}
