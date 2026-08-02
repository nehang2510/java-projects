# District Array Lookup

> Array indexing with explicit bounds checking.

**Category:** Java Fundamentals &nbsp;·&nbsp; **Main class:** `GujaratDistricts`

## Overview

Holds five Gujarat district names in a `String[]`, asks the user for an index, and returns the matching name. The point of the exercise is the guard clause: the index is validated against the array length before access, so an out-of-range value produces a friendly message instead of an `ArrayIndexOutOfBoundsException`.

## Concepts Demonstrated

- Arrays
- Array indexing
- Bounds checking
- Conditional logic

## Features

- Fixed-size `String[]` of district names
- Index validated against `districts.length` before access
- Graceful 'Out of Bounds' message instead of a crash

## Compile & Run

```bash
javac GujaratDistricts.java
java GujaratDistricts
```

## Sample Output

```text
Enter index (0-4) to get district name: 2
District at index 2 is Vadodara
```

---

[← Back to all projects](../../../README.md)
