import java.util.ArrayList;

/**
 * Represents specific operations.
 *
 * @author QIN GUORUI
 */
public enum Operation {
    TODO {
        @Override
        protected String doneOrDelete(String response, int len, ArrayList<Task> list, int count) {
            return null;
        }

        /**
         * Returns the string representation for to-do task.
         *
         * @param response User input.
         * @param len The length of user input.
         * @param list The Task list in Duke class.
         * @return String representation of to-do.
         */
        @Override
        public String eventOrDeadlineOrTodo(String response, int len, ArrayList<Task> list) {
            Todo todo = new Todo(response.substring(5, len));
            list.add(todo);
            return todo.toString();
        }
    },
    DONE {
        /**
         * Returns the string representation for done operation, or
         * it returns the error message.
         *
         * @param response User input.
         * @param len Length of user input.
         * @param list The task list in duke class.
         * @param count The number of tasks in task list.
         * @return The string representation of done.
         */
        @Override
        public String doneOrDelete(String response, int len, ArrayList<Task> list, int count) {
            try {
                int curr = Integer.parseInt(response.substring(5, len));
                Task shouldMark;
                try {
                    shouldMark = list.get(curr - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new OutOfRangeException();
                }
                shouldMark.markAsDone();
                String title = "Nice! I've marked this task as done: \n";
                String out = "     " + shouldMark.toString();
                return title + out;
            } catch (OutOfRangeException e) {
                return e.getMessage();
            }
        }

        @Override
        protected String eventOrDeadlineOrTodo(String response, int len, ArrayList<Task> list) {
            return null;
        }
    },
    DEADLINE {
        @Override
        protected String doneOrDelete(String response, int len, ArrayList<Task> list, int count) {
            return null;
        }

        /**
         * Returns the string representation of deadline task.
         *
         * @param response User input.
         * @param len Length of user input.
         * @param list The task list in duke class.
         * @return The string representation of the deadline task.
         */
        @Override
        public String eventOrDeadlineOrTodo(String response, int len, ArrayList<Task> list) {
            String[] parts = response.substring(9, len).split(" /by ");
            String content = parts[0];
            String time = parts[1];
            Deadline deadline = new Deadline(content, time);
            list.add(deadline);
            return deadline.toString();
        }
    },
    EVENT {
        @Override
        protected String doneOrDelete(String response, int len, ArrayList<Task> list, int count) {
            return null;
        }

        /**
         * Returns the string representation of event task.
         *
         * @param response User input.
         * @param len Length of user input.
         * @param list The task list in duke class.
         * @return The string representation of event task.
         */
        @Override
        public String eventOrDeadlineOrTodo(String response, int len, ArrayList<Task> list) {
            String[] parts = response.substring(6, len).split(" /at ");
            String content = parts[0];
            String time = parts[1];
            Event event = new Event(content, time);
            list.add(event);
            return event.toString();
        }
    },
    DELETE {
        @Override
        public String eventOrDeadlineOrTodo(String response, int len, ArrayList<Task> list) {
            return "false";
        }

        /**
         * Returns the string representation of delete operation, or
         * the error message occurred.
         *
         * @param response User input.
         * @param len Length of user input.
         * @param list The task list in duke class.
         * @param count The number of tasks in list.
         * @return String representation of delete operation or error message.
         */
        @Override
        public String doneOrDelete(String response, int len, ArrayList<Task> list, int count) {
            try {
                int curr = Integer.parseInt(response.substring(7, len));
                Task shouldDelete;
                try {
                    shouldDelete = list.get(curr - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new OutOfRangeException();
                }
                list.remove(curr - 1);
                count--;
                String title = "Noted. I've removed this task: \n";
                String out = "     " + shouldDelete.toString() + "\n   ";
                String end = "Now you have " + count + " tasks in the list.";
                return title + out + end + "S";
            } catch (OutOfRangeException e) {
                return e.getMessage() + "F";
            }
        }
    };
    /** Used by DONE and DELETE enum. */
    protected abstract String doneOrDelete(String response, int len, ArrayList<Task> list, int count);

    /** Used by EVENT, DEADLINE and TODO enum. */
    protected abstract String eventOrDeadlineOrTodo(String response, int len, ArrayList<Task> list);
}
