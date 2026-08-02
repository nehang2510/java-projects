# Bank Account

> Encapsulated account with auto-generated account numbers.

**Category:** Management Systems &nbsp;·&nbsp; **Main class:** `BankAccount`

## Overview

A step up from Account Basics: balance and holder name are `private`, all access goes through methods, and a `static` counter shared across every instance issues sequential account numbers (BA1000, BA1001, …). Deposits and withdrawals validate the amount and the available balance before changing state.

## Concepts Demonstrated

- Encapsulation
- Static members
- Constructors
- State validation

## Features

- `private` fields with method-only access
- `static` counter generates unique IDs across all instances
- Rejects negative deposits and overdrawn withdrawals
- Deposit / withdraw / view balance / view details menu

## Compile & Run

```bash
javac BankAccount.java
java BankAccount
```

## Sample Output

```text
Enter account holder name: Nehang Makwana
Enter initial deposit: 5000
Account created! Account Number: BA1000

1. Deposit
2. Withdraw
3. View Balance
4. Account Details
5. Exit
Enter choice: 1
Enter amount to deposit: 2500
Deposit successful! New balance: 7500.0
```

---

[← Back to all projects](../../../README.md)
