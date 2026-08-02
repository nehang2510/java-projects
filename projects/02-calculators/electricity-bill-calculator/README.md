# Electricity Bill Calculator

> Progressive slab tariff billing with an itemised breakdown.

**Category:** Calculators & Utilities &nbsp;·&nbsp; **Main class:** `ElectricityBillCalculator`

## Overview

Calculates a domestic electricity bill from units consumed. Crucially it uses a **progressive** tariff — each slab rate applies only to the units that fall inside that slab, not to the whole consumption, which is how real utility billing works and a common thing to get wrong. Slabs are modelled as objects, so adding or repricing a tier is a one-line change.

## Concepts Demonstrated

- Nested classes
- Object arrays
- Progressive tariff logic
- Tabular printf output

## Features

- Four-tier progressive slab structure (0–100, 101–200, 201–400, 401+)
- Slabs modelled as a `Slab[]`, not hard-coded if-else chains
- Fixed meter charge and 5% electricity duty applied on top
- Prints per-slab units, rate, and amount plus an effective average rate

## Compile & Run

```bash
javac ElectricityBillCalculator.java
java ElectricityBillCalculator
```

## Sample Output

```text
Enter units consumed: 350

================ ELECTRICITY BILL ================
Units consumed : 350
--------------------------------------------------
Slab         Units      Rate/Unit    Amount
--------------------------------------------------
0-100        100        Rs. 3.50     Rs. 350.00
101-200      100        Rs. 5.00     Rs. 500.00
201-400      150        Rs. 6.50     Rs. 975.00
--------------------------------------------------
Energy charges                      Rs.    1825.00
Fixed meter charge                  Rs.      50.00
Tax (5% on energy charges)          Rs.      91.25
--------------------------------------------------
TOTAL PAYABLE                       Rs.    1966.25
==================================================
Effective average rate: Rs. 5.62 per unit
```

---

[← Back to all projects](../../../README.md)
