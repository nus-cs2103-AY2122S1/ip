# User Guide

## Duke is an easy to use app that allows you to track your various tasks

## Quickstart

1. Ensure you have java 11 or above on your PC.
2. Download the latest `Duke.jar` from here
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double click the file to start the app. You should see a GUI similar to the one below. 

## Features 

List of features:
  1. todo
  2. deadline
  3. event
  4. delete
  5. list
  6. find
  7. remind
  8. format
  9. setFormat
  10. clear 
  11. bye
  12. help

## Adding a task: todo/deadline/event
Duke allows you to categorise your tasks into 3 types: todo, deadline and event

## Adding a `todo`
todo is the most basic of all tasks. 

Format: `todo {description}`

  * {description} can be anything you want

Examples:

`todo go swimming with family`

`todo run`

## Adding a `deadline`

Has an element of date.

Can be complemented with the `remind` method

Format: `deadline {description} /by {date}`

{description} can be anything you want.

{date} the format has been set to yyyy-MM-dd by default. Can be changed using `setFormat`

Examples:

`deadline CS2100 assignment 1 /by 2021-09-15`

`deadline discord nitro expiry /by 2021-09-12`

## Adding an `event`

Allows you to specify a general timeframe or location.

Format: `event {description} /at {timeframe/location}

  * {description} and {timeframe} can be anything you want.

Examples:

`event family outing /at disney land`

`event event project meeting /at Mon 2-4pm`

## Deleting a task: `delete`

Deletes the task at the specified index of the list.

Format: `delete {Index}`
  * Deletes the task at index {Index}
  * {Index} refers to the index number shown on the list
  * {index} needs to be a positive integer

Example:

`delete 3`

## Display the list: `list`

Displays the whole list

Format: `list`

## Find a task: `find`

Format: find {keyword}
  * Searches for tasks with that keyword
  * Not case-sensitive

Examples:

`find hello`: looks for any task with the subtring hello
`find NKN`: looks for any task with the substring nkn

## Get deadline reminders: `remind`

Format: `remind {days}`
  * Lists out any overdue deadlines and deadlines within {days} days

Example:

`remind 5`

## Check the cuurent date format in use: `format`

Displays the current date format used.

Format: `format`

Example:

`format`

## Change the current date format in use: `setFormat`

Format: `setFormat {new format}`
  * The new format has to be an acceptable format by [DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)

Examples:

`setFormat dd-MM-yy`
`setFormat dd MM yy`

## Clear the entire list: `clear`

Removes every task in the list.

Format: `clear`

Example:

`clear`

## Exit the application: `bye`

Closes the GUI. 

Alternatively, you can just press the **X** at the top right of the window

Format: `bye`

Example:

`bye`

## List all possible commands: `help`

Generate the list of commands supported by Duke.

Format `help`

Example:

`help`
