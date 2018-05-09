## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=FIRST -Darg1=SECOND

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip or tar -zcvf dhananjay_jakhadi_assign_1.tar.gz dhananjay_jakhadi_assign_1

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.”

[Date: 10/19/2017]

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

O(n)

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).

-----------------------------------------------------------------------

Command line validations

1. The code accepts only 2 arguments, if argument length doesn’t match, the code throws an error : Arguments are missing, Enter Input File & Output File.

2. If the input file path is invalid, the code throws an error : File Not found, Please enter valid file.

3. If the input file is empty, the code throws an error : Input file is empty.

-----------------------------------------------------------------------
