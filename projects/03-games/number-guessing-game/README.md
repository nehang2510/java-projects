# Number Guessing Game

> Guess 1–100 in seven attempts with higher/lower hints.

**Category:** Games &nbsp;·&nbsp; **Main class:** `NumberGuessingGame`

## Overview

The computer picks a random number between 1 and 100 and the player has seven attempts to find it. After each guess the game narrows the displayed range, which teaches the player binary search implicitly — seven attempts is exactly ceil(log₂ 100), so a player using binary search always wins.

## Concepts Demonstrated

- Random number generation
- While loops
- Range narrowing
- Session scoring

## Features

- Attempt limit derived from binary search, not picked arbitrarily
- Live range hint updates after every guess
- Win/loss tally maintained across replays
- Rejects out-of-range and non-numeric guesses

## Compile & Run

```bash
javac NumberGuessingGame.java
java NumberGuessingGame
```

## Sample Output

```text
===== Number Guessing Game =====
I'm thinking of a number between 1 and 100.
You have 7 attempts. Good luck!
[Attempt 1/7, range 1-100] Your guess: 50
Too high.
Attempts remaining: 6
[Attempt 2/7, range 1-49] Your guess: 25
Correct! You got it in 2 attempts.
Score: 1 won / 1 played
```

---

[← Back to all projects](../../../README.md)
