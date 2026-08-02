# Employee Salary Calculator

> Payroll breakdown from a basic salary using named component rates.

**Category:** Calculators & Utilities &nbsp;·&nbsp; **Main class:** `EmployeeSalaryCalculator`

## Overview

Takes an employee name and basic salary, then derives HRA (20%), DA (10%), PF (12%), and tax (10%) to produce gross and net pay. Every rate is a named constant rather than a magic number, so the whole tariff can be adjusted in one place, and the result prints as a formatted salary slip.

## Concepts Demonstrated

- Named constants
- Percentage arithmetic
- printf formatting
- Input validation

## Features

- Gross = Basic + HRA + DA; Net = Gross − PF − Tax
- All four rates declared as `private static final` constants
- Rejects a negative basic salary before doing any maths
- Thousands-separated, right-aligned salary slip output

## Compile & Run

```bash
javac EmployeeSalaryCalculator.java
java EmployeeSalaryCalculator
```

## Sample Output

```text
Enter employee name: Nehang Makwana
Enter basic salary: 40000

========= SALARY SLIP =========
Employee Name : Nehang Makwana
-------------------------------
Basic Salary  :    40,000.00
HRA (20%)     :     8,000.00
DA  (10%)     :     4,000.00
-------------------------------
Gross Salary  :    52,000.00
PF  (12%)     :     4,800.00 (deduction)
Tax (10%)     :     5,200.00 (deduction)
-------------------------------
Net Salary    :    42,000.00
===============================
```

---

[← Back to all projects](../../../README.md)
