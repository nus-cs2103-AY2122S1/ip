package duke.Client;

public abstract class Person {
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Lesson lesson;

    Person(Name name, Phone phone, Email email, Lesson lesson) {
        //identity fields
        this.name = name;
        this.phone = phone;
        this.email = email;

        //data fields
        this.lesson = lesson;
    }
}
