public class Parser {

    public Command parse(String commandLine, TaskList tasks) throws DukeException {
        String[] split = commandLine.split(" ");
        String command = split[0];
        String desc;

        switch (command) {
        case "bye":
            System.out.println("Have a pleasant day, Master Wayne.\n");
            return new ExitCommand("exit");
        case "list":
            return new ListCommand("list");
        case "done":
            checkLength(split.length);
            int doneIndex = Integer.parseInt(split[1]);
            checkIndex(doneIndex, tasks.size());
            return new DoneCommand("done", doneIndex);
        case "delete":
            checkLength(split.length);
            int deleteIndex = Integer.parseInt(split[1]);
            checkIndex(deleteIndex, tasks.size());
            return new DeleteCommand("delete", deleteIndex);
        case "todo":
            StringBuilder todoBuilder = new StringBuilder();
            for (int i = 1; i < split.length; i++) {
                if (i != 1) {
                    todoBuilder.append(" ");
                }
                todoBuilder.append(split[i]);
            }
            desc = todoBuilder.toString();
            checkDesc(desc);
            return new AddTodoCommand(desc);
        case "deadline":
            StringBuilder deadlineBuilder = new StringBuilder();
            StringBuilder byBuilder = new StringBuilder();
            String by;
            boolean byFound = false;

            for (int i = 1; i < split.length; i++) {
                if (byFound) {
                    if (!byBuilder.toString().equals("")) {
                        byBuilder.append(" ");
                    }
                    byBuilder.append(split[i]);
                } else {
                    if (i == 1) {
                        deadlineBuilder.append(split[i]);
                    } else if (split[i].equals("/by")) {
                        byFound = true;
                    } else {
                        deadlineBuilder.append(" ");
                        deadlineBuilder.append(split[i]);
                    }
                }
            }
            desc = deadlineBuilder.toString();
            by = byBuilder.toString();

            checkDesc(desc);
            return new AddDeadlineCommand(desc, by);
        case "event":
            StringBuilder eventBuilder = new StringBuilder();
            StringBuilder atBuilder = new StringBuilder();
            String at;
            boolean atFound = false;

            for (int i = 1; i < split.length; i++) {
                if (atFound) {
                    if (!atBuilder.toString().equals("")) {
                        atBuilder.append(" ");
                    }
                    atBuilder.append(split[i]);
                } else {
                    if (i == 1) {
                        eventBuilder.append(split[i]);
                    } else if (split[i].equals("/at")) {
                        atFound = true;
                    } else {
                        eventBuilder.append(" ");
                        eventBuilder.append(split[i]);
                    }
                }
            }
            desc = eventBuilder.toString();
            at = atBuilder.toString();
            checkDesc(desc);
            return new AddEventCommand(desc, at);
        }
        return new UnknownCommand("unknown");
    }

    public static void checkDesc(String test) throws DukeException {
        if (test.equals("")) {
            throw new DukeException("The description is empty");
        }
    }

    public static void checkLength(int l) throws DukeException {
        if (l == 1) {
            throw new DukeException("Please give an index number");
        }
    }

    public static void checkIndex(int i, int lengthOfList) throws DukeException {
        if (i <= 0) {
            throw new DukeException("Please give an index number > 0");
        } else if (i > lengthOfList) {
            throw new DukeException("Maximum index number is " + lengthOfList);
        }
    }
}