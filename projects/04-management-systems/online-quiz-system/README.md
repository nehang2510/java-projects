# Online Quiz System

> Shuffled MCQ quiz with scoring, grading, and a wrong-answer review.

**Category:** Management Systems &nbsp;·&nbsp; **Main class:** `OnlineQuizSystem`

## Overview

A multiple-choice quiz engine. Questions are `Question` objects that validate themselves on construction — exactly four options, correct index in range — so a malformed question fails immediately rather than midway through a quiz. Question order is shuffled each run, and at the end the player gets a score, percentage, grade, and a review of everything they got wrong.

## Concepts Demonstrated

- Collections.shuffle
- Constructor validation
- Nested record classes
- Result aggregation

## Features

- Question bank as self-validating objects
- Shuffled order every run
- A–D answer input validated before scoring
- Final score with percentage and grade
- End-of-quiz review of every wrong answer with the correct one

## Compile & Run

```bash
javac OnlineQuizSystem.java
java OnlineQuizSystem
```

## Sample Output

```text
===== ONLINE QUIZ SYSTEM =====

Question 1 of 5
Which keyword is used to inherit a class in Java?
  A. implement
  B. extends
  C. inherits
  D. super
Your answer (A-D): B
Correct!

===== RESULT =====
Score: 4 / 5 (80.0%)  Grade: B
```

---

[← Back to all projects](../../../README.md)
