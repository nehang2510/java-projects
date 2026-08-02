# Contact Management System

> Phonebook CRUD with validated numbers and partial-name search.

**Category:** Management Systems &nbsp;·&nbsp; **Main class:** `ContactManagementSystem`

## Overview

Add, list, search, and delete contacts held in an `ArrayList`. Phone numbers are validated as exactly ten digits and checked for duplicates before saving, and they are stored as `String` rather than a numeric type — because a phone number is an identifier, not a quantity. Search matches partial names, case-insensitively.

## Concepts Demonstrated

- ArrayList CRUD
- Regex-style validation
- Case-insensitive search
- Domain modelling

## Features

- 10-digit phone validation with duplicate detection
- Partial, case-insensitive name search
- Delete by phone number
- Formatted table listing with a running total

## Compile & Run

```bash
javac ContactManagementSystem.java
java ContactManagementSystem
```

## Sample Output

```text
Enter choice: 1
Enter name: Nehang Makwana
Enter 10-digit phone number: 9876543210
Contact saved. Total contacts: 1

Enter choice: 2
---------------------------------------------
No.   Name                      Phone
---------------------------------------------
1     Nehang Makwana            9876543210
---------------------------------------------
Total contacts: 1
```

---

[← Back to all projects](../../../README.md)
