# Student Information System

> Student records with grades derived, never stored.

**Category:** Management Systems &nbsp;·&nbsp; **Main class:** `StudentInformationSystem`

## Overview

Add students with roll number, name, course, and marks; list them all; search by roll number. The design decision worth noting is that grade is computed from marks every time it is asked for rather than stored as a field — so marks and grade can never drift out of sync, which is a real class of bug in record systems.

## Concepts Demonstrated

- Derived properties
- Immutable fields
- ArrayList search
- Formatted tables

## Features

- Roll number, name, course, and marks per student
- Grade (A–F) derived from marks on demand, never stored
- Search by roll number
- Aligned tabular listing via `String.format`

## Compile & Run

```bash
javac StudentInformationSystem.java
java StudentInformationSystem
```

## Sample Output

```text
===== STUDENT INFORMATION SYSTEM =====
1. Add Student
2. Display All Students
3. Search by Roll Number
4. Exit
Enter choice: 2

Roll     Name                 Course              Marks  Grade
101      Nehang Makwana       Computer Eng.       88.00      B
102      Priya Shah           Information Tech.   93.50      A
```

---

[← Back to all projects](../../../README.md)
