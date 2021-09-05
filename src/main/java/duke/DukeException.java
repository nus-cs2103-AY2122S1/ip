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
            return "OOPS!!! The description of a " + t + " need to have " + t.note() +
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
            return "Unexpected argument: " + input;
        }
    }
}
