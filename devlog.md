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

# Mar. 20 6:45pm

Started working on Encryption file. The Encryption Program will continuously read commands from standard input (System.in), process them, and output results to standard output (System.out). It will support four commands: PASS <key> to set a passkey, ENCRYPT <text> to encrypt a message, DECRYPT <text> to decrypt a message, and QUIT to terminate the program. If encryption or decryption is attempted without setting a passkey, the program will return an error. It will validate input to ensure only uppercase letters are used and handle errors gracefully with appropriate messages. The program will run in a loop, waiting for input until "QUIT" is received, ensuring seamless interaction and future integration with the Driver Program via inter-process communication (IPC).

# Mar. 20 9:45pm

The encryption program was successfully executed and reads commands from standard input, processes them, and outputs results to standard output. 
It supports PASS to set a passkey, ENCRYPT and DECRYPT to process text using the Vigenère cypher, and QUIT to exit. 
The program validates that inputs contain only uppercase letters and returns results in the format RESULT <text> or ERROR <message>. 
It uses a loop to handle multiple commands and ensures the passkey is set before allowing encryption or decryption. 
The logic is cleanly structured and ready for integration with the driver.

# Mar. 20 10:00pm

Starting the driver program. The driver program will start by taking the log file name as a command-line argument. 
It will launch the logger and encryption programs as separate subprocesses and establish communication with them using input/output streams. 
It will display a menu to the user and handle commands like password, encrypt, decrypt, history, and quit. For each user command, the driver will send appropriate messages to the encryption program, log the command and result (excluding passwords) via the logger, and store all entered or processed strings in a session-based history. 
It will continue looping until the quit command is entered, after which it will send the QUIT signal to both subprocesses and terminate cleanly.

# Mar. 20 11:00pm

In the Driver program, prompt the user with "Use history? (y/n):" to choose between selecting a string from the session history or 
entering a new one for commands like `password`, `encrypt`, and `decrypt`. Initially, this prompt accepted any input, including invalid 
responses like "HELLO" or "maybe", which led to unintended behavior and could confuse users by bypassing the intended logic flow. During testing, 
we identified this issue and realized it violated basic input validation expectations. To fix the problem I had to come up with a validation loop that checks 
if the input is strictly 'y' or 'n' (case-insensitive), and if not, it re-prompts the user with a clear message until valid input is received. 
This fix ensures the program behaves consistently, guides users properly, and meets the project’s requirement for a smooth, interactive, and error-resilient user interface. It also reinforces the importance of validating all interactive prompts in command-line tools to prevent logic errors and unexpected states.
The driver program was successfully executed.

# Mar. 20 11:15pm

I did the test walkthrough and it was successful. Now, I will finalize the work in the next session, and add the readme file.

# Mar. 22 10:45pm

Started working on the final session. Adding readme file.


