EddieCorp working hours checker
Job application assignment for Visma Solutions

This program was written as a job application assignment, according to requirements from Visma Solutions. The
requirements also requested a small description of the process of writing the program that I will include as this
readme file.

The requirements asked me to spend roughly 2-3 hours on the assignment. I started writing the code a tad after nine on
tuesday morning and finished writing last bits of code just before half past eleven the same morning. This readme was
finished some fifteen minutes after that.

I started with creating all the three classes I knew I would need, Client, HoursChecker and HoursChecker test. I wrote 
the test cases for HoursChecker first and moved on to write the class itself and wrote the client last, after the 
HoursChecker passed all the tests.

I didn't face a lot of problems writing the code, but I did do a minor overhaul to the HoursChecker class about halfway 
into my writing process of the assignment. I had originally planned for the class to be used as a singleton with methods
simply transforming input data into output data, but realized actually storing the data in an instance would be far more
beneficial. I had to rewrite the testing completely to faciliate this change (kept the same test cases, but had to swap 
method names and test locations) and then I could continue again. Other than that, the whole writing process was smooth
and I enjoyed the assignment.

What it comes to understanding the requirements and my own design choices made I had a lot of trouble pondering whether
to enable overnight shifts in the code. In the end I took the requirement "no negative time shifts" as meaning "users
should not be able to input shifts with starting time earlier than finishing time" and that completely invalidates any
chance of making overnight shifts possible, as the input parameter times don't have a date assigned to them. I did
however include a commented out snippet of theoretical code that could be used to enable overnight shifts, should the
policy around inputs change.

I also took a risk and included a second way to get shift duration data from an HoursChecker instance. The requirements
only specified shift duration to be given as a float representing hours in the shift, but I wanted to be able to use
Java's Duration-class for cleaner formatting in the client. Thus I added the getShift() getter to the HoursChecker class.
I thought this was ok as the original functionality asked for was still available in the getShiftHours() getter.