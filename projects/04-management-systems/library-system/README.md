# Library Book System

> Book catalogue with issue and return tracking.

**Category:** Management Systems &nbsp;·&nbsp; **Main class:** `LibrarySystem`

## Overview

Manages a catalogue of books, each with an auto-assigned ID, title, author, and issue status. Books can be issued to a named member and returned. `Optional` is used for lookups so a missing book ID is handled explicitly rather than by a null check that someone forgets to write.

## Concepts Demonstrated

- Optional
- Domain models
- List streams
- State transitions

## Features

- Auto-incrementing book IDs
- Issue a book to a named member, return it to the shelf
- `Optional`-based lookup — no silent nulls
- Catalogue listing showing AVAILABLE vs ISSUED TO

## Compile & Run

```bash
javac LibrarySystem.java
java LibrarySystem
```

## Sample Output

```text
===== LIBRARY BOOK SYSTEM =====
1. Add Book
2. Issue Book
3. Return Book
4. Display All Books
5. Exit
Enter choice: 1
Enter book title: The Pragmatic Programmer
Enter author name: Hunt & Thomas
Book added successfully with ID 1
```

---

[← Back to all projects](../../../README.md)
