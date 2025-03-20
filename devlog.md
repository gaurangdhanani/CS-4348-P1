Project 1 - CS 4348

# Feb. 25 6:30pm

For Project 1, I will implement three Java programs: a logger to record activity, an encryption program using the Vigenère cypher, 
and a driver program that manages both using inter-process communication (IPC) with the Process class. My plan is to start 
with the logger, ensuring it logs messages with timestamps, then implement the encryption program to handle encryption and decryption 
with a stored passkey. Finally, I'll develop the driver program to manage user interaction and coordinate communication between processes. 
Challenges include ensuring smooth IPC, correctly implementing the cypher, and handling errors effectively. My next step is setting 
up the Git repository.

# Mar. 20 6:00pm

Started working on logger file. For the Logger file, I will create a Java program that accepts a log file name as a command-line 
argument and writes log messages to it. The logger will continuously read input from the user, where the first word will be treated 
as the action and the rest as the message. Each log entry will be formatted in the YYYY-MM-DD HH:MM [ACTION] MESSAGE structure, 
with timestamps recorded in 24-hour notation. The program will keep running until it receives the "QUIT" command, at which point 
it will close the file and exit. To ensure reliability, I will implement proper error handling for file operations, validating 
input to prevent malformed log entries. The logger will be designed to integrate seamlessly with the Driver Program, allowing logs 
to be recorded dynamically during execution.

# Mar. 20 6:30pm

The Logger program was successfully executed, correctly logging messages into log.txt in the required YYYY-MM-DD HH:MM [ACTION] MESSAGE format. 
It was started with -- java Logger log.txt --, and sample inputs such as "START Logging Initialized.", "INFO User logged in.", 
and "ERROR Invalid password attempt." were entered. Each message was properly formatted, written to the log file, 
and displayed as confirmation. The program correctly handled the "QUIT" command, shutting down perfectly. Finally, verified the log.txt if it 
was working and I copy all the output into samplelog text file.