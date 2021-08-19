public class DukeException extends RuntimeException {
    public enum exceptionType {INCOMPLETE, INVALID, OUT_OF_BOUND};

    private final String command;
    public final exceptionType type;
    public DukeException(String command, String message, exceptionType type) {
        super(message);
        this.command = command;
        this.type = type;
    }
//
//    @Override
//    public String toString() {
//        if (this.type == exceptionType.INCOMPLETE) {
//            return ": ☹ OOPS!!! Your description of a " + this.command + " is incorrect!\n" +
//                    "To use " + this.command + ", please enter '\"help'\" for instructions";
//        } else {
//            return ": ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
//        }
//    }
}
