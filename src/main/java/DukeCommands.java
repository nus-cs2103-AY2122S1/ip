import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public enum DukeCommands {

    BYE("bye",
            (Map<String, String> map, ArrayList<Task> list) -> {
                Duke.printMsg("Bye. Hope to see you again soon!");
                return false;
            }), LIST("list",
            (Map<String, String> map, ArrayList<Task> list) -> {
                StringBuilder output =
                        new StringBuilder("Here are the tasks in your list:\n");
                for (Task i : list) {
                    output.append(String.format("%d. %s\n",
                            list.indexOf(i) + 1,
                            i));
                }
                output.deleteCharAt(output.length() - 1);
                Duke.printMsg(output.toString());
                return true;
            }),
    DONE("done",
            (Map<String, String> map, ArrayList<Task> list) -> {
                Parser.parseInt(map.get("done"))
                        .map(x -> {
                            if (x < 1 || x > list.size()) {
                                Duke.printMsg("Invalid index provided.");
                            } else {
                                Task toBeCompleted = list.get(x - 1);
                                toBeCompleted.markAsDone();
                                Duke.printMsg(String.format("I have marked the following task " + "as completed:\n" + "  %s",
                                        toBeCompleted.toString()));

                            }
                            return x;
                        })
                        .orElseThrow(() -> new DukeException("Positional argument 'index' is not valid."));


                return true;
            }),
    EVENT("event",
            (Map<String, String> map, ArrayList<Task> list) -> {
                if (!map.containsKey("/at")) {
                    throw new DukeException("Missing positional argument '/at'.");
                } else if (map.get("event") == null || map.get("/at") == null) {
                    throw new DukeException("Event body or /at body cannot be empty.");
                } else {
                    Task event = new Event(map.get("event"),
                            map.get("/at"));
                    list.add(event);
                    Duke.printMsg(addTaskFormatter(event,
                            list.size()));
                }
                return true;
            }),
    DELETE("delete",
            (Map<String, String> map, ArrayList<Task> list) -> {
                Parser.parseInt(map.get("delete"))
                        .map(x -> {
                            if (x < 1 || x > list.size()) {
                                Duke.printMsg("Invalid index.");
                            } else {
                                Task toBeDeleted = list.get(x - 1);
                                list.remove(x - 1);
                                Duke.printMsg(removeTaskFormatter(toBeDeleted,
                                        list.size()));
                            }
                            return x;
                        })
                        .orElseThrow(() ->
                                new DukeException(
                                        "The delete command is missing the positional argument 'index'."
                                )
                        );
                return true;
            }),
    DEADLINE("deadline",
            (Map<String, String> map, ArrayList<Task> list) -> {

                if (!map.containsKey("/by")) {
                    throw new DukeException("Missing positional argument " +
                            "'/by'.");
                } else if (map.get("deadline") == null || map.get("/by") == null) {
                    throw new DukeException("Deadline body cannot be empty.");
                } else {
                    Task event = new Deadline(map.get("deadline"),
                            map.get("/by"));
                    list.add(event);
                    Duke.printMsg(addTaskFormatter(event,
                            list.size()));
                }
                return true;
            }),
    TODO("todo",
            (Map<String, String> map, ArrayList<Task> list) -> {
                if (map.get("todo") == null) {
                    throw new DukeException("Todo body cannot be empty.");
                } else {
                    Task event = new Todo(map.get("todo"));
                    list.add(event);
                    Duke.printMsg(addTaskFormatter(event,
                            list.size()));
                }
                return true;
            }),
    INVALID("invalid",
            (Map<String, String> map, ArrayList<Task> list) -> {
                throw new DukeException("Unrecognized command.");
            });


    final String command;
    final DukeActions action;

    /**
     * Simple constructor to initialise a DukeCommands enum
     * @param command The command that this instance serves.
     * @param action The action that the command has.
     */
    DukeCommands(String command, DukeActions action) {
        this.command = command;
        this.action = action;
    }

    /**
     * Takes in a raw string command and matches the first word of the
     * command to the available enums.
     * @param command The raw user input.
     * @return An optional of the enum. Returns an empty optional if not found.
     */
    public static Optional<DukeCommands> getCommand(String command) {
        return Arrays.stream(DukeCommands.values())
                .filter(x -> command.toLowerCase()
                        .startsWith(x.command))
                .findFirst();
    }


    /**
     * Returns the string version of the message when a task is successfully
     * added.
     * @param task The task that is added.
     * @param numTasks The number of tasks that are now in the list.
     * @return String of the message.
     */
    private static String addTaskFormatter(Task task, int numTasks) {
        return String.format("I have added the following task:\n" + "  %s\n" +
                        "Now you have %s in the list.",
                task.toString(),
                numTasks == 1 ? String.format("%d task",
                        1) : String.format("%d tasks",
                        numTasks));
    }


    /**
     * Returns the string version of the message when a task is successfully
     * removed.
     * @param task The task that is removed.
     * @param numTasks The number of tasks remaining in the list.
     * @return String of the message.
     */
    private static String removeTaskFormatter(Task task, int numTasks) {
        return String.format("I have removed the following task:\n" + "  %s\n" +
                        "Now you have %s in the list.",
                task.toString(),
                numTasks == 1 ? String.format("%d task",
                        1) : String.format("%d tasks",
                        numTasks));
    }

}
