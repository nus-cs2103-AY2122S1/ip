package com.duke.exception;

public class DukeException extends IllegalArgumentException {

    public DukeException(String message) {
        super(message);
    }

    public static class EmptyTimelineDescription extends DukeException {
        public EmptyTimelineDescription(String message) {
            super("the timeline of a " + message + " cannot be empty!");
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static class EmptyDescriptionException extends DukeException {
        public EmptyDescriptionException() {
            super("the description cannot be empty!");
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException(String message) {
            super("he does not recognize your command! Try another command!");
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static class InvalidInputException extends DukeException {
        public InvalidInputException(String message) {
            super("you need to input a number as index!");
        }

        @Override
        public String toString() { return super.toString(); }
    }

    @Override
    public String toString() {
        return "com.duke.Duke is confused! com.duke.Duke says that " + getMessage();
    }
}
