# How to Compile and Run

## Prerequisites

A Java Development Kit, version 21 or newer. Verify your install:

```bash
java -version
javac -version
```

If `javac` is missing you have a JRE, not a JDK — install a JDK from [Adoptium](https://adoptium.net/) or [OpenJDK](https://openjdk.org/).

## The General Pattern

Every project is a single self-contained `.java` file with no external dependencies and no build tool. From the repository root:

```bash
cd projects/<category>/<project-name>
javac <MainClass>.java
java <MainClass>
```

Note that `javac` takes the **filename** (with `.java`) while `java` takes the **class name** (without it).

## Projects That Take Arguments

`PowerCalculator` reads both operands from the command line:

```bash
cd projects/01-fundamentals/power-calculator
javac PowerCalculator.java
java PowerCalculator 2 10      # -> 2 ^ 10 = 1024
```

## Projects That Write Files

`StudentManager` creates `students.txt` in whatever directory you run it from. That file is git-ignored.

## Full Command Reference

| Folder | Compile | Run |
|--------|---------|-----|
| `01-fundamentals/account-basics` | `Account.java` | `java Account` |
| `01-fundamentals/shape-polymorphism` | `ShapeTest.java` | `java ShapeTest` |
| `01-fundamentals/district-array-lookup` | `GujaratDistricts.java` | `java GujaratDistricts` |
| `01-fundamentals/power-calculator` | `PowerCalculator.java` | `java PowerCalculator 2 10` |
| `01-fundamentals/student-file-io` | `StudentManager.java` | `java StudentManager` |
| `02-calculators/simple-calculator` | `SimpleCalculator.java` | `java SimpleCalculator` |
| `02-calculators/employee-salary-calculator` | `EmployeeSalaryCalculator.java` | `java EmployeeSalaryCalculator` |
| `02-calculators/electricity-bill-calculator` | `ElectricityBillCalculator.java` | `java ElectricityBillCalculator` |
| `03-games/number-guessing-game` | `NumberGuessingGame.java` | `java NumberGuessingGame` |
| `03-games/dice-game` | `DiceGame.java` | `java DiceGame` |
| `03-games/tic-tac-toe` | `TicTacToe.java` | `java TicTacToe` |
| `04-management-systems/bank-account` | `BankAccount.java` | `java BankAccount` |
| `04-management-systems/atm-simulation` | `ATMSimulation.java` | `java ATMSimulation` |
| `04-management-systems/contact-management-system` | `ContactManagementSystem.java` | `java ContactManagementSystem` |
| `04-management-systems/library-system` | `LibrarySystem.java` | `java LibrarySystem` |
| `04-management-systems/student-information-system` | `StudentInformationSystem.java` | `java StudentInformationSystem` |
| `04-management-systems/login-registration-system` | `LoginRegistrationSystem.java` | `java LoginRegistrationSystem` |
| `04-management-systems/online-quiz-system` | `OnlineQuizSystem.java` | `java OnlineQuizSystem` |

## Compiling Everything at Once

To verify the whole repository builds, from the root:

```bash
find projects -name "*.java" -execdir javac {} \; && echo "All projects compiled."
```

## Cleaning Up

```bash
find . -name "*.class" -delete
```

---

[← Back to README](../README.md)
