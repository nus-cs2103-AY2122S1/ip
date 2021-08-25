package duke.task;

public abstract class Task {
        private final String taskName;
        private boolean done;

        public Task(boolean done, String taskName) {
            this.taskName = taskName;
            this.done = done;
        }

        public void markAsDone() {
            this.done = true;
        }

        public String encode() {
            return done
                    ? String.format("1|%s", this.taskName)
                    : String.format("0|%s", this.taskName);
        }

        @Override
        public String toString() {
                return done
                        ? String.format("[X] %s", this.taskName)
                        : String.format("[ ] %s", this.taskName);
        }
}
