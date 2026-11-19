# TASK 01 – A1: Design a File Format (7 marks)
## A1.1 Record layouts
Delimited text: `passId,regNo,name,routeNo,validityDate,amountPaid`, one UTF-8 record per line. Use proper CSV quoting: quote fields containing commas/quotes/newlines and double embedded quotes.
Binary DataOutputStream order: writeUTF(passId), writeUTF(regNo), writeUTF(name), writeInt(routeNo), writeUTF(validityDate in ISO yyyy-MM-dd), writeDouble(amountPaid). Add a magic header and format version.
## A1.2 Pseudocode
ADD: validate fields; open BufferedWriter with CREATE+APPEND; encode one escaped CSV record; write line; close via try-with-resources.
FIND: if missing return NOT_FOUND; BufferedReader reads line by line; parse CSV; skip/log malformed lines; return matching pass ID or NOT_FOUND.
LIST_BY_ROUTE: read each line; parse; skip damaged records; collect rows whose route matches.
Buffered streams reduce per-character overhead; try-with-resources ensures closure.
## A1.3 Damaged data
Detect wrong field count, invalid route/date/amount and malformed quoting; log line number and quarantine/skip. Missing file gets a clear not-created/empty-store response. For power failure, write a complete temp file, flush/close, then atomic-move it into place; keep a backup/journal for recovery.
## Alternative: RandomAccessFile
Fixed record i is at headerSize+i*recordSize, O(1) seek once the index is known. Arbitrary ID lookup still O(n) without an ID index. Fixed-size names waste padding and limit field lengths. Text scan is O(n) but easier to inspect and extend.
