# Power Calculator (x^y)

> Command-line exponentiation with layered exception handling.

**Category:** Java Fundamentals &nbsp;·&nbsp; **Main class:** `PowerCalculator`

## Overview

Computes x raised to the power y by repeated multiplication, taking both values as command-line arguments. The interesting part is the error handling: three separate `catch` blocks handle missing arguments, non-numeric input, and a negative exponent, each with its own message.

## Concepts Demonstrated

- Command-line arguments
- Loops
- Custom exceptions
- Multi-catch handling

## Features

- Reads both operands from `args[]`
- Throws `IllegalArgumentException` on a negative exponent
- Separate handlers for `NumberFormatException`, `IllegalArgumentException`, and any unexpected `Exception`

## Compile & Run

```bash
javac PowerCalculator.java
java PowerCalculator 2 10
```

## Sample Output

```text
2 ^ 10 = 1024

# Error cases:
$ java PowerCalculator 2 -3
Error: Exponent must be non-negative

$ java PowerCalculator abc 3
Invalid input! Please enter integers.

$ java PowerCalculator 5
Error: Please provide 2 integers as arguments
```

## Notes

**Fixed during cleanup.** Two string literals were split across newlines (`"Exponent must be non-` / `negative"`), which is a compile error in Java. Both are now single-line literals. The logic is unchanged.

---

[← Back to all projects](../../../README.md)
