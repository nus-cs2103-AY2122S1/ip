public class Task {
        private final String taskName;
        private boolean done = false;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        public void markAsDone() {
            this.done = true;
        }

        @Override
        public String toString() {
            if(done) {
                return String.format("[X] %s", this.taskName);
            }
            return String.format("[ ] %s", this.taskName);
        }
}
