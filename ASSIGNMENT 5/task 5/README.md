# TASK 05 – A5: Correctness and Safety (4 marks)
## A5.1 Transaction pseudocode
```text
setAutoCommit(false)
try:
  debit hostel wallet by 500 only if balance >= 500
  require exactly one row updated
  credit canteen wallet by 500
  require exactly one row updated
  commit()
catch error:
  rollback()
finally:
  restore previous autoCommit
```
Both updates commit or neither does. Production systems should also lock/check rows and use a unique transaction ID for safe retries.
## A5.2 SQL injection
Concatenating input into SQL lets crafted text alter SQL syntax; e.g. ID `admin' -- ` may comment out the password predicate in some dialects. Use `SELECT ... WHERE id=? AND pwd_hash=?` with PreparedStatement and bind values. Store password hashes, not plaintext.
Alternative quote escaping/regex validation is weaker due to dialects, encodings and unanticipated syntax. PreparedStatement separates SQL structure from data. Validation costs O(input length), but is not a substitute for parameterization. Separate auto-commit wallet updates can leave money debited if credit fails; a transaction protects correctness.
