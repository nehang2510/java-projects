# Simple Calculator

> Menu-driven arithmetic with division-by-zero and input guards.

**Category:** Calculators & Utilities &nbsp;·&nbsp; **Main class:** `SimpleCalculator`

## Overview

A looping console calculator supporting add, subtract, multiply, divide, and modulo. It keeps running until the user chooses Exit. Division and modulo both check for a zero divisor before computing, and every numeric read is validated so a stray letter re-prompts instead of throwing.

## Concepts Demonstrated

- Switch expressions
- While loops
- Input validation
- Guard clauses

## Features

- Five operations plus a clean exit option
- Division by zero caught before it happens
- Non-numeric input re-prompts rather than crashing
- Menu loops until the user explicitly exits

## Compile & Run

```bash
javac SimpleCalculator.java
java SimpleCalculator
```

## Sample Output

```text
===== Simple Calculator =====

===== Menu =====
1. Add
2. Subtract
3. Multiply
4. Divide
5. Modulo (remainder)
6. Exit
Enter choice: 1
Enter first number : 12
Enter second number: 8
Result: 12 + 8 = 20
```

---

[← Back to all projects](../../../README.md)
