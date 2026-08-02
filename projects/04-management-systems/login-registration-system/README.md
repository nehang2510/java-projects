# Login & Registration System

> Salted SHA-256 credential storage with per-user lockout.

**Category:** Management Systems &nbsp;·&nbsp; **Main class:** `LoginRegistrationSystem`

## Overview

Register and log in users — with passwords never stored in plain text. Each registration generates a random salt via `SecureRandom`, hashes salt + password with SHA-256, and stores only the Base64 salt and hash. Login re-hashes the attempt with the stored salt and compares. Three failures lock the account.

## Concepts Demonstrated

- SecureRandom
- SHA-256 hashing
- Salting
- HashMap storage
- Account lockout

## Features

- Passwords never stored in plain text — salt + hash only
- Unique random salt per user via `SecureRandom`
- Password strength rules (min 8 chars, letter + digit)
- Case-insensitive usernames — `Admin` and `admin` are one account
- Three-attempt lockout per user

## Compile & Run

```bash
javac LoginRegistrationSystem.java
java LoginRegistrationSystem
```

## Sample Output

```text
===== Login & Registration System =====
Enter choice: 1
Choose a username (3-20 chars, letters/digits/_): nehang
Choose a password (min 8 chars, at least 1 letter and 1 digit): ********
Confirm password: ********
Registration successful. You can now log in as "nehang".

Enter choice: 2
Username: nehang
Password: ********

Login successful. Welcome, nehang!
```

## Notes

**Learning project, not production auth.** Real systems use a deliberately slow password hash (bcrypt, scrypt, or Argon2) rather than SHA-256, and persist users to a database instead of an in-memory `HashMap`. The salting and never-store-plaintext principles demonstrated here are exactly right; the choice of hash function is the part that would change in production.

---

[← Back to all projects](../../../README.md)
