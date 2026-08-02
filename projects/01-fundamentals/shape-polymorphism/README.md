# Shape Polymorphism

> Abstract classes and runtime polymorphism through area calculation.

**Category:** Java Fundamentals &nbsp;·&nbsp; **Main class:** `ShapeTest`

## Overview

An abstract `shape` class declares an `area()` method with no body. `triangle`, `rectangle`, and `circle` each extend it and supply their own formula. A single `shape` reference is pointed at each subclass in turn, and Java picks the right `area()` at runtime — the textbook demonstration of dynamic method dispatch.

## Concepts Demonstrated

- Abstract classes
- Inheritance
- Method overriding
- Runtime polymorphism

## Features

- One abstract parent, three concrete subclasses
- Same `shape` variable reused for all three objects
- Correct override invoked automatically at runtime

## Compile & Run

```bash
javac ShapeTest.java
java ShapeTest
```

## Sample Output

```text
area of triangle=25.0
area of rectangle=32
area of circle=153.86
```

## Notes

**Renamed during cleanup.** The driver class was `shapetest` (all lowercase); it is now `ShapeTest`, matching both its filename and Java's PascalCase naming convention for classes. The shape hierarchy and all area formulas are unchanged.

---

[← Back to all projects](../../../README.md)
