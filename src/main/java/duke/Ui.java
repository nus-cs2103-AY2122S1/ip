package duke;
import java.util.ArrayList;

public class Ui {

    /**
     * Returns the welcome message of the duke chat bot.
     *
     * @return Welcome Message
     */
    public static String welcomeMessage() {
        return "\n" + "     Hello! I'm Duke" + "\n" + "     What can I do for you?" + "\n";
    }

    /**
     * Returns updated user task list and prints feedback to the user based on their input
     *
     * @param input user input
     * @return updated task list
     */
    public String run(Storage storage, String input) throws DukeException {
        ArrayList<Task> list = storage.load();
        String parsedInput = Parser.parse(input);
        try {
            if (parsedInput.equals("list")) {
                return getListString(list);
            } else if (parsedInput.equals("done")) {
                if (input.replaceAll("\\s", "").length() == 4) {
                    throw new DukeException(DukeException.Type.EmptyDone);
                } else {
                    String modInput = input.replaceAll("\\s", "");
                    int index = Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1;
                    if (list.size() <= index) {
                        throw new DukeException(DukeException.Type.InvalidTaskNumber);
                    } else {
                        return getTaskDoneString(list, index, storage);
                    }
                }
            } else if (parsedInput.equals("delete")) {
                if (input.replaceAll("\\s", "").length() == 6) {
                    throw new DukeException(DukeException.Type.EmptyDelete);
                } else {
                    return getDeleteTaskString(list, input, storage);
                }
            } else if (parsedInput.equals("todo")) {
                if (input.replaceAll("\\s", "").length() == 4) {
                    throw new DukeException(DukeException.Type.EmptyTodo);
                } else {
                    return getNewTodoString(list, input, storage);
                }
            } else if (parsedInput.equals("event")) {
                if (input.replaceAll("\\s", "").length() == 5) {
                    throw new DukeException(DukeException.Type.EmptyEvent);
                } else {
                    return getNewEventString(list, input, storage);
                }
            } else if (parsedInput.equals("deadline")) {
                if (input.replaceAll("\\s", "").length() == 8) {
                    throw new DukeException(DukeException.Type.EmptyDeadline);
                } else {
                    return getNewDeadlineString(list, input, storage);
                }
            } else if (parsedInput.equals("find")) {
                return getFindString(list, input);
            } else if (parsedInput.equals("update")) {
                if (input.replaceAll("\\s", "").length() == 6) {
                    throw new DukeException(DukeException.Type.EmptyUpdate);
                } else {
                    return getUpdateString(list, input);
                }
            } else if (parsedInput.equals("InvalidCommand")) {
                throw new DukeException(DukeException.Type.InvalidCommand);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        storage.save(list);
        return parsedInput;
    }

    private String getListString(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "You have no tasks in your list!\n";
        } else {
            String output = "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                Task item = list.get(i);
                output += String.format("    %d. %s\n", (i + 1), item.toString());
            }
            return output;
        }
    }

    private String getDeleteTaskString(ArrayList<Task> list, String input, Storage storage) throws DukeException {
        String modInput = input.replaceAll("\\s", "");
        int index = Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1;
        if (list.size() <= index) {
            throw new DukeException(DukeException.Type.InvalidTaskNumber);
        } else {
            Task item = list.remove(index);
            String output = "Noted. I've removed this task:\n"
                    + String.format("    %s\nNow you have %d tasks in the list.\n",
                    item.toString(), list.size());
            storage.save(list);
            return output;
        }
    }

    private String getTaskDoneString(ArrayList<Task> list, int index, Storage storage) {
        Task item = list.get(index);
        item.markAsDone();
        String output = "Nice! I've marked this task as done:\n";
        output += String.format("    %s\n", item);
        storage.save(list);
        return output;
    }

    private String getNewTodoString(ArrayList<Task> list, String input, Storage storage) {
        Todo t = new Todo(input.substring(5));
        list.add(t);
        String output = "Got it. I've added this task:\n    " + t.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", list.size());
        storage.save(list);
        return output;
    }

    private String getNewEventString(ArrayList<Task> list, String input, Storage storage) {
        String[] splitString = input.substring(6).split(" /at ", 2);
        Event e = new Event(splitString[0], splitString[1]);
        list.add(e);
        String output = "Got it. I've added this task:\n"
                + "    " + e + "\n"
                + String.format("Now you have %d tasks in the list.\n", list.size());
        storage.save(list);
        return output;
    }

    private String getNewDeadlineString(ArrayList<Task> list, String input, Storage storage) {
        String[] splitString = input.substring(9).split(" /by ", 2);
        Deadline d = new Deadline(splitString[0], splitString[1]);
        list.add(d);
        String output = "Got it. I've added this task:\n"
                + "   " + d.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", list.size());
        storage.save(list);
        return output;
    }

    private String getFindString(ArrayList<Task> list, String input) {
        String query = input.substring(5).toLowerCase();
        ArrayList<Task> tasksContainingQuery = new ArrayList<Task>();
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (list.get(i).description.toLowerCase().contains(query)) {
                tasksContainingQuery.add(t);
            }
        }
        if (tasksContainingQuery.size() == 0) {
            return "You have no matching tasks in your list!\n";
        } else {
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasksContainingQuery.size(); i++) {
                Task item = tasksContainingQuery.get(i);
                output += String.format("    %d. %s\n", (i + 1), item.toString());
            }
            return output;
        }
    }

    private String getUpdateString(ArrayList<Task> list, String input) throws DukeException {
        int index = Integer.parseInt(input.substring(7, 8)) - 1;
        if (list.size() <= index) {
            throw new DukeException(DukeException.Type.InvalidTaskNumber);
        } else {
            Task item = list.get(index);
            if (item instanceof Todo) {
                list.set(index, ((Todo) item).update(input.substring(8)));
            } else if (item instanceof Deadline) {
                if (input.contains("/by")) {
                    String[] splitString = input.substring(8).split("/by", 2);
                    list.set(index, ((Deadline) item).update(splitString[0], splitString[1]));
                } else {
                    list.set(index, ((Deadline) item).updateDesc(input.substring(8)));
                }
            } else if (item instanceof Event) {
                if (input.contains("/at")) {
                    String[] splitString = input.substring(8).split("/at", 2);
                    list.set(index, ((Event) item).update(splitString[0], splitString[1]));
                } else {
                    list.set(index, ((Event) item).updateDesc(input.substring(8)));
                }
            }
            return "Your task has been updated!\n" + list.get(index);
        }
    }

}
