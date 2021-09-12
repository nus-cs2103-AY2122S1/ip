package duke;

public class DukeException extends Exception {
    /**
     * Class handles if the task is empty
     */
    public static class DukeEmptyTask extends DukeException {
        private Task.TaskKind t;

        protected DukeEmptyTask(Task.TaskKind t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return "OOPS!!! The description of a " + t + " cannot be empty."
                    + "\nTry something like: \"" + t.getExample() + "\"";
        }
    }

    /**
     * Class handles if the task Deadline or Event doesn't specify the date
     */
    public static class DukeEmptyNote extends DukeException {
        private Task.TaskKind t;

        protected DukeEmptyNote(Task.TaskKind t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return "OOPS!!! The description of a " + t + " need to have keyword " + t.note()
                    + "\nTry something like: \"" + t.getExample() + "\"";
        }
    }

    /**
     * Class handles if the task Deadline or Event doesn't specify the date
     */
    public static class DukeParseTaskException extends DukeException {
        private Task.TaskKind t;

        protected DukeParseTaskException(Task.TaskKind t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return "OOPS!!! Something went wrong with " + t + " that I cannot understand"
                    + "\nTry something like: \"" + t.getExample() + "\"";
        }
    }

    /**
     * Class handles if the task Deadline or Event doesn't specify the date
     */
    public static class DukeParseCommandException extends DukeException {
        private Command command;

        protected DukeParseCommandException(Command command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return "OOPS!!! Something went wrong with command " + "\"" + command.getCommandName()
                    + "\"" + " that I cannot understand";
        }
    }

    /**
     * Class handles if the task Deadline or Event doesn't specify the date
     */
    public static class DukeIllegalArgumentException extends DukeException {
        private UserInput input;

        protected DukeIllegalArgumentException(UserInput input) {
            this.input = input;
        }

        @Override
        public String toString() {
            return "OOPS!!! I don't understand: " + input.preCommand;
        }
    }

    /**
     * Class handles if the task Deadline or Event doesn't specify the date
     */
    public static class DukeIndexOutOfBoundsException extends DukeException {
        protected DukeIndexOutOfBoundsException() {
        }

        @Override
        public String toString() {
            return "OOPS!!! Seem like you only have " + Duke.todoList.size() + " task(s) :)\n"
                    + "Try with number from 1 to " + Duke.todoList.size();
        }
    }


}
