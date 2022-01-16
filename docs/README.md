# User Guide

## Introduction

This is a very simple program. It supports all essential features of a CS2103T iP project. You can use it to store some todo list, events and deadlines if you wish.

## Installation

Refer to our latest release on the [Github Page](https://github.com/zhenghanlee/ip) for download. Once you have downloaded the jar file, you can open the application via one of the following:

1. Double click the jar file.
2. Run `java -jar Duke.jar` at the directory that the jar file is located.

## Commands

### `todo <name>`

Adds a todo task.

### `deadline <name> /by <yyyy-mm-dd>`

Adds a task with a deadline.

### `event <name> /at <yyyy-mm-dd>`

Adds an event with a date.

### `duration <name> /dura <minutes>`

Adds an event/task with a duration (in minutes).

### `list`

List all the tasks/events.

### `done <index>`

Mark a task as done based on its index shown in the output of `list`.

### `delete <index>`

Deletes a task **forever** based on its index shown in the output of `list`.
