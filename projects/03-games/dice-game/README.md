# Dice Game

> Best-of-five dice duel against the computer, with fairness stats.

**Category:** Games &nbsp;·&nbsp; **Main class:** `DiceGame`

## Overview

Player and computer each roll a die per round across a best-of-five match; the higher roll takes the round. Beyond the game itself, it tracks how often each face has come up across the whole session and prints the distribution at the end — a simple, visible demonstration that the RNG is fair.

## Concepts Demonstrated

- Random
- Frequency arrays
- Match state tracking
- Replay loops

## Features

- Best-of-five match structure with draw handling
- Frequency array counts every face rolled in the session
- End-of-session distribution report
- Play-again loop across multiple matches

## Compile & Run

```bash
javac DiceGame.java
java DiceGame
```

## Sample Output

```text
===== Dice Game: You vs Computer =====
Best of 5 rounds. Higher roll wins each round.

--- Round 1 of 5 ---
Press Enter to roll...
You rolled     : 5
Computer rolled: 2
You win this round!
Score -> You: 1 | Computer: 0 | Draws: 0
```

---

[← Back to all projects](../../../README.md)
