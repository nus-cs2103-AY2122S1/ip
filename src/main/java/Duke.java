public class Duke {
    UserInterface userInterface;
    TaskList list;
    Storage storage;

    public Duke() {
        this.userInterface = new UserInterface();
        this.list = new TaskList();
        this.storage = Storage.createStorage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        this.userInterface.printInitialGreeting();
        this.list = this.storage.load(this.list);
        this.runLoop();
        this.storage.save(this.list);
        this.userInterface.printGoodByeGreeting();
        System.exit(0);
    }

    public void runLoop() {
        String commandLine;
        do {
            commandLine = this.userInterface.nextCommand();
            this.response(commandLine);
        } while (!commandLine.equals("bye"));
    }

    public void response(String input) {
        String[] words = input.split(" ");
        String command = words[0];
        try {
            switch (command) {
            case "list":
                printList();
                return;
            case "done":
                int taskNumber = Integer.parseInt(words[1]);
                markAsDone(taskNumber);
                return;
            case "delete":
                int taskNumberToBeDeleted = Integer.parseInt(words[1]);
                deleteTask(taskNumberToBeDeleted);
                return;
            case "todo":
                if (words.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Task todo = new Todo(combine(words, words.length), false);
                addToList(todo);
                return;
            case "deadline":
                String rest = combine(words, words.length);
                String[] newList = rest.split(" /by ");
                if (newList.length != 2) {
                    throw new DukeException("Incorrect command was given for deadline. " + "Try this: deadline " +
                            "name_here /by date_here");
                }
                Task deadline = new Deadline(newList[0], newList[1], false);
                addToList(deadline);
                return;
            case "event":
                String restOfWords = combine(words, words.length);
                String[] eventList = restOfWords.split(" /at ");
                if (eventList.length != 2) {
                    throw new DukeException("Incorrect command was given for event. " + "Try this: deadline name_here" +
                            " /at date_here");
                }
                Task event = new Event(eventList[0], eventList[1], false);
                addToList(event);
                return;
            case "bye":
                return;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch(DukeException e) {
            System.out.println("OOPS!!! " + e.getMessage());
        } catch(java.lang.NumberFormatException e) {
            System.out.println("OOPS!!! " + e.getLocalizedMessage() + " was input instead of an integer.");
        }
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:\n" + this.list);
    }

    private void addToList(Task input) {
        this.list.addTask(input);
        System.out.println("Got it. I've added this task:\n  " + input.details());
        printListNumber();
    }

    private void printListNumber() {
        System.out.println("You now have "
                + this.list.size() + " tasks in the list.");
    }

    private void markAsDone(int id) throws DukeException {
        Task completedTask = this.list.markAsCompleted(id);
        System.out.println("Nice! I've marked this task as done:\n "
                + completedTask.details());
    }

    private void deleteTask(int id) throws DukeException {
        Task deletedTask = this.list.deleteTask(id);
        System.out.println("Noted. I've removed this task:\n "
                + deletedTask.details()
        );
        printListNumber();
    }

    private String combine(String[] splitList, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < end; i++) {
            result.append(splitList[i]);
            result.append(" ");
        }
        return result.substring(0,result.length() - 1);
    }

}
