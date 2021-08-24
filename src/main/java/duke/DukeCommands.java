package duke;

import duke.util.DukeConfig;
import duke.util.DukeTaskList;
import duke.util.Parser;
import duke.util.Ui;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public enum DukeCommands {

    BYE("bye",
            (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) -> {
                Duke.printMsg("Bye. Hope to see you again soon!");
                return false;
            }), LIST("list",
            (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) -> {
                ui.printTaskList(list);
                return true;
            }), DONE("done",
            (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) -> {
                list.setDone(Parser.parseInt(map.get("done"))
                        .orElseThrow(() -> new DukeException("Positional argument 'index' is not valid.")) - 1)
                        .map(x -> {
                            ui.completeTaskUpdate(x,
                                    list.getSize());
                            return x;
                        })
                        .orElseThrow(() -> new DukeException("The provided index is out of bounds."));
                return true;
            }), EVENT("event",
            (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) -> {
                if (!map.containsKey("/at")) {
                    throw new DukeException("Missing positional argument '/at'.");
                } else if (map.get("event") == null || map.get("/at") == null) {
                    throw new DukeException("duke.Event body or /at body cannot be empty.");
                } else {
                    Task event = new Event(map.get("event"),
                            map.get("/at"));
                    list.addTask(event)
                            .orElseThrow(() -> new DukeException("Failed to add the task to the list"));
                    ui.addTaskUpdate(event,
                            list.getSize());
                }
                return true;
            }), DELETE("delete",
            (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) -> {
                list.toRemove(Parser.parseInt(map.get("delete"))
                        .orElseThrow(() -> new DukeException("The delete command is either missing the positional argument 'index' or it " + "is invalid.")) - 1)
                        .orElseThrow(() -> new DukeException("Failed to remove task from the tasklist."));
                return true;
            }), DEADLINE("deadline",
            (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) -> {
                if (!map.containsKey("/by")) {
                    throw new DukeException("Missing positional argument " + "'/by'.");
                } else if (map.get("deadline") == null || map.get("/by") == null) {
                    throw new DukeException("Deadline body cannot be empty.");
                } else {
                    LocalDateTime by = Parser.parseDateTime(map.get("/by"),
                            config)
                            .orElseThrow(() -> new DukeException("Invalid" + " date time specified"));
                    Task event = new Deadline(map.get("deadline"),
                            by);
                    list.addTask(event)
                            .orElseThrow(() -> new DukeException("Failed to add the task to the list"));
                    ui.addTaskUpdate(event,
                            list.getSize());
                }
                return true;
            }), TODO("todo",
            (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) -> {
                if (map.get("todo") == null) {
                    throw new DukeException("duke.Todo body cannot be empty.");
                } else {
                    Task event = new Todo(map.get("todo"));
                    list.addTask(event)
                            .orElseThrow(() -> new DukeException("Failed to add the task to the list"));
                    ui.addTaskUpdate(event,
                            list.getSize());
                }
                return true;
            }), INVALID("invalid",
            (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) -> {
                throw new DukeException("Unrecognized command.");
            });


    final String command;
    final DukeActions action;

    /**
     * Simple constructor to initialise a duke.DukeCommands enum
     *
     * @param command The command that this instance serves.
     * @param action  The action that the command has.
     */
    DukeCommands(String command, DukeActions action) {
        this.command = command;
        this.action = action;
    }

    /**
     * Takes in a raw string command and matches the first word of the
     * command to the available enums.
     *
     * @param command The raw user input.
     * @return An optional of the enum. Returns an empty optional if not found.
     */
    public static Optional<DukeCommands> getCommand(String command) {
        return Arrays.stream(DukeCommands.values())
                .filter(x -> command.toLowerCase()
                        .startsWith(x.command))
                .findFirst();
    }
}
