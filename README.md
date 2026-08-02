# Java Projects

> A collection of 18 Java programs written by **Nehang Makwana** while studying Computer Science at New LJ Institute of Engineering and Technology, Ahmedabad.

[![Portfolio](https://img.shields.io/badge/Portfolio-Live-2563eb?style=flat-square)](https://nehang2510.github.io/portfolio/)
[![GitHub](https://img.shields.io/badge/GitHub-nehang2510-181717?style=flat-square&logo=github)](https://github.com/nehang2510)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)](LICENSE)

---

## About This Repository

These programs were built alongside my Computer Science coursework. They are ordered roughly by the concepts they introduce — starting with classes and objects, moving through arrays, inheritance, and exception handling, and ending with menu-driven applications that model a domain and manage state.

Every project in this repository compiles and runs on **JDK 21**. Each one has its own README with an explanation, the concepts it covers, and sample output.

## Repository Structure

```text
java-projects/
├── README.md
├── LICENSE
├── .gitignore
├── docs/
│   └── how-to-run.md
└── projects/
    ├── 01-fundamentals/
    │   ├── account-basics/
    │   ├── shape-polymorphism/
    │   ├── district-array-lookup/
    │   ├── power-calculator/
    │   └── student-file-io/
    ├── 02-calculators/
    │   ├── simple-calculator/
    │   ├── employee-salary-calculator/
    │   └── electricity-bill-calculator/
    ├── 03-games/
    │   ├── number-guessing-game/
    │   ├── dice-game/
    │   └── tic-tac-toe/
    └── 04-management-systems/
        ├── bank-account/
        ├── atm-simulation/
        ├── contact-management-system/
        ├── library-system/
        ├── student-information-system/
        ├── login-registration-system/
        └── online-quiz-system/
```

## Projects

### Java Fundamentals

Core language mechanics: classes, objects, arrays, inheritance, polymorphism, exception handling, and file I/O.

| # | Project | What It Does | Key Concepts |
|---|---------|--------------|--------------|
| 1 | **[Account Basics](projects/01-fundamentals/account-basics)** | A first class-and-object program modelling a bank account. | Classes and objects, Instance fields, Methods |
| 2 | **[Shape Polymorphism](projects/01-fundamentals/shape-polymorphism)** | Abstract classes and runtime polymorphism through area calculation. | Abstract classes, Inheritance, Method overriding |
| 3 | **[District Array Lookup](projects/01-fundamentals/district-array-lookup)** | Array indexing with explicit bounds checking. | Arrays, Array indexing, Bounds checking |
| 4 | **[Power Calculator (x^y)](projects/01-fundamentals/power-calculator)** | Command-line exponentiation with layered exception handling. | Command-line arguments, Loops, Custom exceptions |
| 5 | **[Student File I/O](projects/01-fundamentals/student-file-io)** | Writing objects to disk and reading them back as a byte stream. | File I/O, FileOutputStream / FileInputStream, toString() override |

### Calculators & Utilities

Menu-driven console tools that turn user input into validated, formatted output using real-world business rules.

| # | Project | What It Does | Key Concepts |
|---|---------|--------------|--------------|
| 6 | **[Simple Calculator](projects/02-calculators/simple-calculator)** | Menu-driven arithmetic with division-by-zero and input guards. | Switch expressions, While loops, Input validation |
| 7 | **[Employee Salary Calculator](projects/02-calculators/employee-salary-calculator)** | Payroll breakdown from a basic salary using named component rates. | Named constants, Percentage arithmetic, printf formatting |
| 8 | **[Electricity Bill Calculator](projects/02-calculators/electricity-bill-calculator)** | Progressive slab tariff billing with an itemised breakdown. | Nested classes, Object arrays, Progressive tariff logic |

### Games

Interactive console games covering randomness, game-state tracking, 2D arrays, and win-condition logic.

| # | Project | What It Does | Key Concepts |
|---|---------|--------------|--------------|
| 9 | **[Number Guessing Game](projects/03-games/number-guessing-game)** | Guess 1–100 in seven attempts with higher/lower hints. | Random number generation, While loops, Range narrowing |
| 10 | **[Dice Game](projects/03-games/dice-game)** | Best-of-five dice duel against the computer, with fairness stats. | Random, Frequency arrays, Match state tracking |
| 11 | **[Tic Tac Toe](projects/03-games/tic-tac-toe)** | Two-player 3×3 grid game with full win and draw detection. | 2D arrays, Class separation, Win-condition logic |

### Management Systems

Larger menu-driven applications with domain models, collections-backed storage, full CRUD operations, and input validation.

| # | Project | What It Does | Key Concepts |
|---|---------|--------------|--------------|
| 12 | **[Bank Account](projects/04-management-systems/bank-account)** | Encapsulated account with auto-generated account numbers. | Encapsulation, Static members, Constructors |
| 13 | **[ATM Simulation (Multi-User)](projects/04-management-systems/atm-simulation)** | Four-account ATM with per-account PINs, lockout, and withdrawal limits. | LinkedHashMap, Nested classes, Authentication flow |
| 14 | **[Contact Management System](projects/04-management-systems/contact-management-system)** | Phonebook CRUD with validated numbers and partial-name search. | ArrayList CRUD, Regex-style validation, Case-insensitive search |
| 15 | **[Library Book System](projects/04-management-systems/library-system)** | Book catalogue with issue and return tracking. | Optional, Domain models, List streams |
| 16 | **[Student Information System](projects/04-management-systems/student-information-system)** | Student records with grades derived, never stored. | Derived properties, Immutable fields, ArrayList search |
| 17 | **[Login & Registration System](projects/04-management-systems/login-registration-system)** | Salted SHA-256 credential storage with per-user lockout. | SecureRandom, SHA-256 hashing, Salting |
| 18 | **[Online Quiz System](projects/04-management-systems/online-quiz-system)** | Shuffled MCQ quiz with scoring, grading, and a wrong-answer review. | Collections.shuffle, Constructor validation, Nested record classes |

---

## Quick Start

You need a JDK installed (21 or newer recommended). Check with `java -version`.

```bash
git clone https://github.com/nehang2510/java-projects.git
cd java-projects/projects/04-management-systems/atm-simulation
javac ATMSimulation.java
java ATMSimulation
```

Every project follows the same pattern: `cd` into its folder, `javac` the `.java` file, then `java` the class name. See [docs/how-to-run.md](docs/how-to-run.md) for the full guide, including how to run the two projects that take command-line arguments.

## Notes on Cleanup

Three files needed fixes before they would compile. The logic was left as originally written in every case:

| File | Problem | Fix |
|------|---------|-----|
| `Account.java` | Fields declared `Name`/`Balance` but assigned as `name`/`balance` | Field names made consistently lowercase |
| `PowerCalculator.java` | Two string literals split across newlines | Joined into single-line literals |
| `ATMSimulation.java` | `public class` name did not match its filename | Class and file both renamed to `ATMSimulation` |

Compiled `.class` files were removed from version control and are now ignored via `.gitignore`.

## Contact

**Nehang Makwana** — Ahmedabad, Gujarat, India

- Email: [nehang.makwana@gmail.com](mailto:nehang.makwana@gmail.com)
- GitHub: [@nehang2510](https://github.com/nehang2510)
- LinkedIn: [Nehang Makwana](https://www.linkedin.com/in/nehang-makwana-a7ab793b8/)

## License

Released under the [MIT License](LICENSE) — free to read, use, and learn from.
