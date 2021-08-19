/**
 * Represents the To-do tasks.
 *
 * @author QIN GUORUI
 */
public class Todo extends Task{

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
}
