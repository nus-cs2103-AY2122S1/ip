public class DukeException extends Exception{
    protected static class DukeEmptyTask extends DukeException {
        private Task t;

        protected DukeEmptyTask(Task t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return "OOPS!!! The description of a " + t.taskKind() + " cannot be empty.";
        }
    }
}
