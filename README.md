### Duke
> “The best thing about Duke is that it's the best app ever made.” - Albert Einstein ([source](https://www.goodreads.com/quotes))

This duke is:
- simple
- **really fast** at processing inputs
- ~~easy~~ *wonderful* to use

So why not,

1. Download it
2. Double-click it
3. Enjoy! :star_struck:

Here's the `main` method:
```javascript
public static void main(String[] args) {
    Application.launch(Main.class, args);
}
```

You can try:
- listing all tasks using "list"
- adding a todo task using "todo [description]"
- adding an event using "event [description] /at YYYY-MM-DD"
- adding a deadline using "deadline [description] /by YYYY-MM-DD"
- marking a task as completed using "done [task number]"
- deleting a task as completed using "delete [task number]"
- finding a task using "find [search query]"
- getting a date's schedule using "schedule YYYY-MM-DD"
- exiting Duke using "bye"