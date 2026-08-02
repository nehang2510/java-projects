# Tic Tac Toe

> Two-player 3×3 grid game with full win and draw detection.

**Category:** Games &nbsp;·&nbsp; **Main class:** `TicTacToe`

## Overview

A two-player Tic Tac Toe on a numbered 3×3 board. A dedicated `Board` class owns the grid and all the rules — placing a mark, rejecting an occupied or out-of-range cell, and checking all eight winning lines — so the game loop stays readable and the board logic is testable on its own.

## Concepts Demonstrated

- 2D arrays
- Class separation
- Win-condition logic
- Input validation

## Features

- Cells numbered 1–9 so players never guess coordinates
- Rejects moves onto already-occupied cells
- Checks three rows, three columns, and both diagonals
- Detects a full-board draw as well as a win

## Compile & Run

```bash
javac TicTacToe.java
java TicTacToe
```

## Sample Output

```text
 1 | 2 | 3
---|---|---
 4 | 5 | 6
---|---|---
 7 | 8 | 9

Player X, enter position (1-9): 5

 1 | 2 | 3
---|---|---
 4 | X | 6
---|---|---
 7 | 8 | 9
```

---

[← Back to all projects](../../../README.md)
