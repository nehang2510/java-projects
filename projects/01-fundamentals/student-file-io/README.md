# Student File I/O

> Writing objects to disk and reading them back as a byte stream.

**Category:** Java Fundamentals &nbsp;·&nbsp; **Main class:** `StudentManager`

## Overview

Creates three `Student` objects, serialises each to a CSV line via `toString()`, writes them to `students.txt` with a `FileOutputStream`, then reads the file back byte by byte with a `FileInputStream` and prints it. Demonstrates that file work in Java is stream work, and that every stream operation is checked.

## Concepts Demonstrated

- File I/O
- FileOutputStream / FileInputStream
- toString() override
- try-catch with IOException

## Features

- Custom `toString()` produces one CSV record per student
- Writes all three records to `students.txt`
- Reads the file back one byte at a time and prints it
- All stream operations wrapped in `try-catch (IOException)`

## Compile & Run

```bash
javac StudentManager.java
java StudentManager
```

## Sample Output

```text
Student data written to file successfully.

Reading student data from file:
S101,Ravi,20
S102,Anita,21
S103,Meena,19
```

## Notes

Running this creates a `students.txt` file in the working directory.

---

[← Back to all projects](../../../README.md)
