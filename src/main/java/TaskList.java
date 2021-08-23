import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> store;
    private static String[] input;

    public TaskList(String[] input) {
        this.input = input;
        store = new ArrayList<>();
        generateStore();
    }

    public TaskList() {
        store = new ArrayList<>();
    }

    private void generateStore() {
        Integer counter = 0;
        for (int i = 0; i < input.length; i++) {
            String command = input[i];
            if (command != null) {
                String[] words = command.split(" - ");
                boolean isCompleted = false;
                if (words[1].equals("1")) {
                    isCompleted = true;
                }
                String task = words[0];
                switch (task) {
                    case ("T"):
                        Duke.addToDo("todo " + words[2], false);
                        if (isCompleted) {
                            Integer temp = counter + 1;
                            Duke.markCompleted("done " + temp.toString(), false);
                        }
                        counter++;
                        break;
                    case ("E"):
                        Duke.addEvent("event " + words[2] + "/at " + words[3], false);
                        if (isCompleted) {
                            Integer temp = counter + 1;
                            Duke.markCompleted("done " + temp.toString(), false);
                        }
                        counter++;
                        break;
                    case ("D"):
                        Duke.addDeadline("deadline " + words[2] + "/by " + words[3], false);
                        if (isCompleted) {
                            Integer temp = counter + 1;
                            Duke.markCompleted("done " + temp.toString(), false);
                        }
                        counter++;
                        break;
                }
            }
        }

    }

    public boolean isEmpty() {
        if (store.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return store.size();
    }

    public Task get(int taskNumber) {
        return store.get(taskNumber);
    }

    public void add(Task task) {
        store.add(task);
    }

    public void remove(int taskNumber) {
        store.remove(taskNumber);
    }

    /**
     * Prints out the current list of tasks the user has.
     *
     * @param command entered by user.
     * @throws DukeException upon invalid commands or empty tasks list.
     */
    public void printTaskList(String command) {
        String[] words = command.split(" ");
        if (words.length > 1) {
            throw new DukeException("invalidCommand");
        } else if (store.isEmpty()) {
            throw new DukeException("noTasksException");
        } else {
            Ui.showLine();
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < store.size(); i++) {
                System.out.printf("     %d.%s\n", i + 1, store.get(i).toString());
            }
            Ui.showLine();
        }
    }
}
