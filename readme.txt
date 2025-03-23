Class:  CS-4348.003
Author: Gaurang Rameshbhai Dhanani
netid:  GXD220027

-----PROJECT 1-----

-------
 Files 
-------

Driver.java       – Main program that interacts with the user, manages history, and communicates 
                    with the logger and encryption processes via standard input/output.

Logger.java       – Standalone process that logs activity to a specified file in the format:
                    YYYY-MM-DD HH:MM [ACTION] MESSAGE

Encryption.java   – Standalone process that performs encryption and decryption using the 
                    Vigenère cipher. It receives commands like PASS, ENCRYPT, DECRYPT, and QUIT 
                    and responds with RESULT or ERROR.

samplelog.txt     – Example output log file generated during program execution (can be recreated).

README.txt        – This file. Describes the project, how to run it, and notes for grading.

devlog.md         – Development log containing session notes and progress entries.

------------------------------------
 How to compile and run the program 
------------------------------------

To compile:  javac Logger.java Encryption.java Driver.java

To run:      java Driver <filename>.txt

I used "java Driver Samplelog.txt".

-----------------------------------------
 How to clone the repository from Github 
-----------------------------------------

git clone https://github.com/gaurangdhanani/CS-4348-P1.git

THANK YOU!