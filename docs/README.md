# User Guide
<img src="Ui.png" alt="UI Screenshot" width="344"/>

Duke is a task management desktop application that provides an effective 
and efficient way to manage all your tasks.

## Features 

### 1. Simplistic Graphical User Interface

Duke features a simple and elegant chat-like interface where you can type your 
commands into a textbox and hit send, and you will hear back from Duke. 

### 2. Adding and managing tasks

There are 3 types of tasks you can add in Duke:
1) **Todo** tasks that consist of a description
2) **Event** tasks that consist of a description and a date
3) **Deadline** tasks that consist of a description and a date

You can mark a task as done once you have completed it, and you can also 
delete a task if you no longer want it on your list. 

You can also search for tasks in your list based on keywords. 

Remember to enter `bye` to save your changes and exit the app!

## Usage

### A. Adding New Tasks

You can add 3 different types of tasks.

1. **Adding a todo tasks:**

```todo {description}```

For example:

```todo read book```

2. **Adding an event task:**

```event {description} /at {date} ```

The date can be purely a date or a date with a time. It should be 
in one of the following formats:
- dd/MM/yyyy ```2/12/2019``` 
- dd/MM/yyyy HHmm ```2/12/2019 1800```

For example:

```event math exam /at 2/12/2019 1800```

```event math exam /at 2/12/2019```

3. **Adding a deadline task:**

```deadline {description} /by {date} ```

For example:

```deadline return book /by 2/12/2019 1800```

```deadline return book /by 2/12/2019```



Expected outcome of adding tasks:
```
todo read book
```

```
Got it. I've added this task:
  [T][] read book
```
### B. Viewing all tasks

Type ```list``` to view a list of all your tasks

Expected outcome:

```
list
```

```
1.[T][] read book
2.[D][] return book (by: Mon, Dec 02 2019, 18:00)
3.[E][] math exam (at: Mon, Dec 02 2019, 18:00)
```

### C. Marking task as done

By seeing the output of ```list```, obtain the number of the task that
you want to mark as done. Let's say this is task number 1.

Type ```done 1``` to mark the 1st task in the list as done. 

Expected outcome:

```
done 1
```
```
Nice! I've marked this task as done:
[T][X] read book
```

```
list
```
```
1.[T][X] read book
2.[D][] return book (by: Mon, Dec 02 2019, 18:00)
3.[E][] math exam (at: Mon, Dec 02 2019, 18:00)
```

### D. Deleting tasks

By seeing the output of ```list```, obtain the number of the task that
you want to delete. Let's say this is task number 1.

Type ```delete 1``` to delete the 1st task in the list.

Expected outcome:

```
delete 1
```
```
Noted. I've removed this task:
[T][X] read book
```

```
list
```
```
1.[D][] return book (by: Mon, Dec 02 2019, 18:00)
2.[E][] math exam (at: Mon, Dec 02 2019, 18:00)
```

### E. Searching for matching tasks

Type ```find {words}``` to search for task descriptions that match the word(s) or letters 
that you have keyed in. 

Example of usage:

`find book`

Expected outcome:

```
find book
```

```
Here are the matching tasks:
1.[D][] return book (by: Mon, Dec 02 2019, 18:00)
```

```
find t
```

```
Here are the matching tasks:
1.[D][] return book (by: Mon, Dec 02 2019, 18:00)
2.[E][] math exam (at: Mon, Dec 02 2019, 18:00)
```

### Acknowledgements:
- Original Duke template forked from Prof Damith CS2103T github
- GUI Design from Javafx tutorials
