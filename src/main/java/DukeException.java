public class DukeException extends Exception {
    protected String linebreak = "~~*********___\\(owo)/___\\(owo)/___*********~~";

    DukeException(String message) {
        super(message + '\n');
    }
}

class InvalidInputException extends DukeException {
    InvalidInputException() {
        super ("Sowwy, thiws commandw iswn't supporwted! TwT");
    }
}

class IncorrectInputException extends DukeException {
    IncorrectInputException(String keyword, String suggestion) {
        super ("Ohw no! Youw cawn't use " + keyword + " likw that! Twy " + suggestion + " inswead!");
    }
}

class MissingInputException extends DukeException {
    MissingInputException(String keyword) {
        super ("Oopsie uwu! Youw cawn't use " + keyword + " withoutw a descwiption forw it!");
    }
}

class MissingNoException extends DukeException {
    MissingNoException(String keyword) {
        super ("Thwere's no suchw taskw! Pwease enterw a *validw* numbewr after '"+ keyword + "'!");
    }
}
