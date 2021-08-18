public class DukeException extends Exception {

    public DukeException() {
        super();
    }

    static class DukeTaskNotFoundException extends DukeException {
        @Override
        public String toString() {
            return "Sorry, this task number cannot be found :(";
        }
    }

    static class DukeTaskFailException extends DukeException {
        @Override
        public String toString() {
            return "Sorry, I am not sure what the task number is :(";
        }
    }

    static class DukeNoDescriptionException extends DukeException {
        @Override
        public String toString() {
            return "Oops :( Please key in a valid description!";
        }
    }

    static class DukeNoTimeGivenException extends DukeException {
        @Override
        public String toString() {
            return "Oops :( Please key in a valid time to proceed";
        }
    }

    static class DukeInvalidInputException extends DukeException {
        @Override
        public String toString() {
            return "Oops :( Friend does not recognise this command! " +
                    "Did you spell your command correctly?";
        }
    }
}
