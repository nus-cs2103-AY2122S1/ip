public class DukeException extends Exception{
    protected static class DukeEmptyTask extends DukeException {
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

    protected static class DukeEmptyNote extends DukeException {
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
}
