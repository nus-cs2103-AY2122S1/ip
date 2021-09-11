package meow;

abstract class MeowException extends Exception {
}

class InvalidInputException extends MeowException {
    @Override
    public String toString() {
        return "Meow: ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

class EmptyTodoDescriptionException extends MeowException {
    @Override
    public String toString() {
        return "Meow: ☹ OOPS!!! The description of a todo cannot be empty~";
    }
}

class EmptyDeadlineDescriptionException extends MeowException {
    @Override
    public String toString() {
        return "Meow: ☹ OOPS!!! The description of a deadline cannot be empty~";
    }
}

class EmptyEventDescriptionException extends MeowException {
    @Override
    public String toString() {
        return "Meow: ☹ OOPS!!! The description of an event cannot be empty~";
    }
}

class EmptyDeadlineTimeException extends MeowException {
    @Override
    public String toString() {
        return "Meow: ☹ OOPS!!! The date/time of a deadline cannot be empty~";
    }
}

class EmptyEventTimeException extends MeowException {
    @Override
    public String toString() {
        return "Meow: ☹ OOPS!!! The starting and ending time of an event cannot be empty~\n";
    }
}

class InvalidTaskIndex extends MeowException {
    @Override
    public String toString() {
        return "Meow: ☹ OOPS!!! This task is not in your task list,\n "
                +
                "try entering a correct task number~";
    }
}

class NotSuchTaskFoundException extends MeowException {
    @Override
    public String toString() {
        return "Meow: ☹ OOPS!!! I'm sorry, but I don't know what that means,\n"
                +
                "please enter a number to indicate the correct task number~";
    }
}

class NoItemInTheListException extends MeowException {
    @Override
    public String toString() {
        return "Meow: You have not added any tasks yet, please add one now~";
    }
}

class NoSearchResultException extends MeowException {
    @Override
    public String toString() {
        return "Meow: Meow can't find any matching results, try entering a different keyword~";
    }
}

class InvalidDateTimeException extends MeowException {
    @Override
    public String toString() {
        return "Meow: The format of your deadline is invalid, please try again~";
    }
}
