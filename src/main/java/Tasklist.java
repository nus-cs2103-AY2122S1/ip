/**
 * This is the Tasklist class.
 * It stores Tasks as a list.
 */
public class Tasklist {
    private final Task[] list;
    private int numTasks;

    public Tasklist() {
        this.list = new Task[100]; // list of size 100
        this.numTasks = 0;
    }

    public Task GetTaskAt(int n) throws ArrayIndexOutOfBoundsException {
        if (n <= 0 || n > numTasks) {
            throw new ArrayIndexOutOfBoundsException("That is an invalid task number!");
        }
        return this.list[n-1];
    }

    public Boolean isEmpty() {
        return numTasks == 0;
    }

    public void addTask(String line, String type) throws AisuException { // adds new task to taskList
        Task newTask;
        if (type.equals("T")) {
            newTask = new Todo(line);
        } else if (type.equals("D")) {
            try {
                String[] temp = line.split(" /by ");
                newTask = new Deadline(temp[0], temp[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new AisuException("Your formatting is wrong! Write as: deadline (task) /by (date)");
            }
        } else if (type == "E") { // later on need to add else if (type == "E"), else portion for validation
            try {
                String[] temp = line.split(" /at ");
                newTask = new Event(temp[0], temp[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new AisuException("Your formatting is wrong! Write as: event (task) /at (date range)");
            }
        } else {
            throw new AisuException("I don't understand what you mean :(");
        }

        this.list[numTasks] = newTask;
        numTasks += 1;

        System.out.println(" Got it! I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + this.numTasks + " task(s) in the list.");
    }

    public void markDone(int n) throws ArrayIndexOutOfBoundsException {
        if (n <= 0 || n > numTasks) {
            throw new ArrayIndexOutOfBoundsException("That is an invalid task number!");
        }
        this.list[n - 1].markAsDone();
    }

    @Override
    public String toString() { // displays the list
        if (this.isEmpty()) {
            return "Oops, the list is empty! :O";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numTasks; i++) {
            result.append(i + 1).append(". ").append(list[i]).append("\n");
        }
        return result.toString();
    }

}
