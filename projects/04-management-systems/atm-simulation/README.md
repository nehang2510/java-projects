# ATM Simulation (Multi-User)

> Four-account ATM with per-account PINs, lockout, and withdrawal limits.

**Category:** Management Systems &nbsp;·&nbsp; **Main class:** `ATMSimulation`

## Overview

The largest of the account projects. Four accounts are held in a `LinkedHashMap`, each with its own PIN and balance. A user enters an account number, gets three PIN attempts before that account locks, then transacts. PINs are stored as `String` specifically so leading zeros (`0456`) survive — the kind of detail that bites when you store them as `int`.

## Concepts Demonstrated

- LinkedHashMap
- Nested classes
- Authentication flow
- Session management

## Features

- Four independent accounts, each with its own PIN and balance
- Three-attempt limit, then the account locks
- Per-transaction withdrawal cap of ₹25,000
- Multiple sessions — one user exits, the next logs in
- PINs stored as `String` to preserve leading zeros

## Compile & Run

```bash
javac ATMSimulation.java
java ATMSimulation
```

## Sample Output

```text
===== WELCOME TO THE ATM =====
Enter account number: 1001
Enter PIN: ****
Login successful. Welcome, Ravi Sharma!

1. Check Balance
2. Deposit
3. Withdraw
4. Mini Statement
5. Logout
Enter choice: 1
Current balance: Rs. 15000.00
```

## Notes

**Renamed during cleanup.** The class was `ATMSimulationmultiuser` in a file of a different name, which Java rejects for `public` classes. Class and file are now both `ATMSimulation`.

---

[← Back to all projects](../../../README.md)
