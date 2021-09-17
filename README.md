# Duke
As Nat said:
> Who has time to use other apps for a to-do list?

Introducing Duke, Nat's personalised chat bot, adapted from the Duke project. 🤓

* ~~easy~~ *SUPER EASY* to use
* fuss-free
* **$0!** 🤑

## Features
- [x] add task as todo, deadline or event
- [x] delete task
- [x] show all tasks
- [x] mark task as done
- [x] find task
- [x] undo the previous command (works for add, delete and mark task as done!) 

## How to use
1. Run `Launcher.main()` It will trigger this method:

```java
public static void main(String[] args) {
    Application.launch(Duke.class, args);
}
```

2. Write the command in the input box:

**Displaying all tasks**
* `ls`

**Adding a task**
* `todo [task description]\n`
* `deadline [task description] /by [date and/or time]`
* `event [task description] /at [date and/or time]`

**Deleting a task/Marking a task as done**
* display all task
* `delete [the task number]`

**Finding a task**
* `find [keyword(s)]`

**Undoing a command**
* `undo`

What are you waiting for? Download it here! [click me](https://github.com/nataniayp/ip)
