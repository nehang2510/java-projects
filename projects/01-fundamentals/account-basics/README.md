# Account Basics

> A first class-and-object program modelling a bank account.

**Category:** Java Fundamentals &nbsp;·&nbsp; **Main class:** `Account`

## Overview

The starting point for object-oriented thinking in Java. A single `Account` class holds account number, holder name, and balance as instance fields, exposes methods to set data, deposit money, and print the current state, and the `main` method drives it end to end.

## Concepts Demonstrated

- Classes and objects
- Instance fields
- Methods
- Scanner input

## Features

- Stores account number, holder name, and balance in one object
- `setdata()` initialises all three fields in a single call
- `deposit()` mutates state and reports the amount added
- `display()` prints a labelled snapshot of the account

## Compile & Run

```bash
javac Account.java
java Account
```

## Sample Output

```text
before deposited
Account No : 101
Name       : ravi
Balance    : 5000.0
enter amount to deposit
1500
Deposited: 1500.0
after deposit
Account No : 101
Name       : ravi
Balance    : 6500.0
```

## Notes

**Fixed during cleanup.** The original version declared the fields `Name` and `Balance` (capitalised) but assigned to `name` and `balance` (lowercase), so it would not compile. Fields are now consistently lowercase, the constructor parameter is renamed to avoid shadowing, and output lines are labelled.

---

[← Back to all projects](../../../README.md)
