/**
 * Represents the task user want to configure.
 *
 * @author QIN GUORUI
 */
public class Task {
        /** The content of the task. */
        protected String description;

        /** Whether the task is finished or not. */
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            isDone = true;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + getDescription();
        }
}
