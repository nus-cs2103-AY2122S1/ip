# User Guide

## Features 

## Feature - Store history

Able to *store* previously inputs and *load* them upon next opening

### Feature - AddTask

Able to *add* new task 

### Feature - DeleteTask

Able to *delete* tasks that have been done

### Feature-prevent duplicate

Able to *recognise* and *remove duplicated task* to prevent unnecessary task present in the note.



## Usage

### `Keyword` - Todo *input action param*

### Expected outcome:

Programs adds the *input action* to the a list object. Its
recorded and available for subsequent action.

### Description of the outcome.

Message shows that the task has been record.
Format [Type][isdone] input action
Then shows how many existing task in the list

```
expected output

Got it, I've added this task:
	[T][] input action
Now you have 1 tasks in the list.
```

### `Keyword` - Deadline *input action param* /by *input date param*

### Expected outcome:

Programs adds the *input action* to the a list object. Its
recorded and available for subsequent action.

### Description of the outcome.

Message shows that the task has been record.
Format [Type][isdone] *input action* *date*
Then shows how many existing task in the list

```
expected output

Got it, I've added this task:
	[D][] input action inpute date
Now you have 1 tasks in the list.
```
### `Keyword` - Events *input action param* /at *input date param* *input day*

### Expected outcome:

Programs adds the *input action* to the a list object. Its
recorded and available for subsequent action.

### Description of the outcome.

Message shows that the task has been record.
Format [Type][isdone] *input action* *date* *day*
Then shows how many existing task in the list

```
expected output

Got it, I've added this task:
	[D][] input action inpute date input day
Now you have 1 tasks in the list.
```

### `Keyword` - list

### Expected outcome:

Programs displays a list of all the recorded task

### Description of the outcome.

A list of all the task in their displayed format

```
expected output

1. [D][] input action inpute date
```

### `Keyword` - done *input index*

### Expected outcome:

The specific recorded task with the index will be
labelled as done.

### Description of the outcome.

Specific task will have its second box crossed to indicate done.

```
expected output

Nice! I've marked this task as done: [D][X] input action inpute date

```
### `Keyword` - delete *input index*

### Expected outcome:

The specific recorded task with the index will be
removed.

### Description of the outcome.

Specific task will no longer be present in the list

```
expected output

Now you have n tasks in the list. 
```

### `Keyword` - bye

### Expected outcome:

Player exits the progam. Current list will be saved for subsequent opening.

### Description of the outcome.

Duke wishes goodbye.

```
expected output

bye. Hope to see you again soon!
```
