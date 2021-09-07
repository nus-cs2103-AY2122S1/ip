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
            return "OOPS!!! The description of a " + t + " cannot be empty." +
                    "\nTry something like: \"" + t.getExample() + "\"";
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
            return "OOPS!!! The description of a " + t + " need to have keyword " + t.note() +
                    "\nTry something like: \"" + t.getExample() + "\"";
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
            return "OOPS!!! Something went wrong with " + t + " that I cannot understand" +
                    "\nTry something like: \"" + t.getExample() + "\"";
        }
    }

    /**
     * Class handles if the task Deadline or Event doesn't specify the date
     */
    public static class DukeIllegalArgumentException extends DukeException {
        UserInput input;

        protected DukeIllegalArgumentException(UserInput input) {
            this.input = input;
        }

        @Override
        public String toString() {
            return "OOPS!!! I don't understand: " + input.pre_command;
        }
    }
}
