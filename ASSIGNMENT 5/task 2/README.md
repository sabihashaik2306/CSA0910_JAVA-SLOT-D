# TASK 02 – A2: Don't Read What You Don't Need (5 marks)
A 50 MiB file has 52,428,800 bytes, so a one-byte loop makes about 52.4 million Java read() calls. This creates method-call and loop overhead.
Use BufferedInputStream or a byte[] block (e.g. 64 KiB), count newline bytes per block. At 8 KiB, roughly 6,400 bulk reads replace 52.4 million single-byte reads.
Buffering helps little for tiny files or when network/CPU work dominates.
Alternative Files.readAllLines: O(file size) time and O(file size) memory plus per-line object overhead. Streaming buffered read is O(file size) time and O(buffer size) auxiliary memory. Use readAllLines only when all lines fit comfortably in memory.
