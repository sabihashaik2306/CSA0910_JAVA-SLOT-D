# TASK 04 – A4: What Should Be Serialized? (4 marks)
Mark live JDBC Connection transient: it is a runtime resource and cannot be meaningfully restored after restart. Never serialize plaintext passwords; store salted password hashes securely. Persist only required Resident fields such as name/register number.
Serialization traverses the reachable object graph Hostel -> Rooms -> Residents. Reachable non-transient objects must implement Serializable or NotSerializableException occurs.
When phoneNumber is added, keep an explicit serialVersionUID. With compatible UID, old streams load and new String field defaults to null; migration can populate it. Incompatible UID can cause InvalidClassException.
Alternative CSV/JSON is portable and easier to evolve but needs explicit parsing/mapping. Save/load is O(n) time and O(n) storage for n residents. Java serialization is convenient for Java graphs but less interoperable and more brittle across class changes.
