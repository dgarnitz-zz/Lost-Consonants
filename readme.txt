5001 Object Oriented Design & Modelling
Practical One
David Garnitz

The LostConsonants class features one main method and six helper methods. The
main method takes in two arguments, a string containing the filepath of a
dictionary file and a string containing a word or phrase entered by the user as
a basis for generating the lost consonant alternatives. The program has error
handling consisting of try-catch statements and if-statements to ensure that
the user enters the correct arguments. After the user enters their arguments,
the program attempts to load the contents of the dictionary file into a String
ArrayList called lines. If the dictionary contents load successfully, the
program then calls the removeConsonants method with the lines ArrayList and the
string entered by the user.

The removeConsonants method loops over the length  of the user input string,
calling the generateAlternatives method for each character in the input. The
generateAlternatives method takes the user input string and an int representing
the index of a given character in that string. It checks if the character
at a given index is a vowel using the isVowel method from the isVowel class,
and if it is not, it deletes that character from the user input string, then
returns it. Inside the generateAlternatives method, that string is checked
against the original user input and if they are not equal (meaning the string
has been altered), it runs the dictionaryComparison method. The
dictionaryComparison method ArrayList containing the dictionary, the original
user input string and the "Lost Consonant" alternative generated earlier as
arguments. It parses the two strings into string arrays using the parsedInput
method, then compares them using the phraseComparison method, which compares
the words in each array at a given index and identifies the one word with
different length (since the "Lost Consonant" alternative will have one less
letter). This "Lost Consonant" alternative word is then run through the
punctuationCleanse method, which removes any punctuation marks from it, and
returned. Inside the dictionaryComparison method, that word is then run through
a for loop which checks it against every word in the dictionary ArrayList. If
the "Lost Consonant" alternative word is equal to any word in the dictionary,
the entire "Lost Consonant" alternative phrase is printed, an int called
counter is incremented and the dictionaryComparison function returns counter.
Back in the removeConsonants method, this value increments the word count.
After every position in the user input string has gone through the
aforementioned process, the word count (number of "Lost Consonant" alternatives
printed) is returned and output to the console, terminating the program.

-------------------------------------------------------------------------------

Enhancement One

The EnhancementOne class functions almost identically to the LostConsonants
class with almost all the same methods, except that the method that initiates
the process of generating "Lost Vowels" alternatives is called removeVowels.
The removeVowels method calls the generateAlternatives method which functions the
same as the similarly-named method in Lost Consonants except that it only deletes 
vowels from words and it has additional logic to handle the exceptional cases of 
the words "a" and "I". Similarly, the dictionaryComparison method in EnhancementOne 
works the same as the one in LostConsonants (including calling the parsedInput method 
from LostConsonants), but it is altered with additional logic to handle the
aforementioned exceptional cases. Likewise, the phraseComparison method is
almost identical to the one in LostConsonants and it utilizes the
punctuationCleanse method from LostConsonants, but it has additional logic to
handle the fringe cases.

Once this implementation was complete, it was tested several times with a
variety of different user input phrases and the dictionary that is used for the
Stacscheck (the one on StudRes). 

-------------------------------------------------------------------------------------

EnhancementTwo

In order to complete the second enhancement, I created a class called
EnhancementTwo, which takes a user input string and creates "Added Consonant"
alternatives by adding consonant letters to every space in the word (including
before and after). It then checks those "Added Consonant" alternative words
against a user specified dictionary, and prints the "Added Consonant"
alternatives phrase if the "Added Consonant" alternative word matches any word
in the dictionary.

The EnhancementTwo class functions similar to the LostConsonants class and it
utilizes almost all the same methods from LostConsonants, except that it
initiates the process of generating "Added Consonant" alternatives with a
method called addLetters.  The addLetters method has two nested for loops. The 
outside loop iterates once for each letter of the user input, plus an extra time 
at the end to add consonants to the end of words. The inner loop iterates over 
the length of a string containing every consonant in the English Language, it 
grabs a specific letter from that string and passes it along with the user input 
string and the index in that string (the current iteration of the outside for loop)
to a function called generateAlternatives. 


The generateAlternatives method creates a StringBuilder using the user input 
string. The method then adds the letter it was passed as an argument to that
StringBuilder at the specified position (which is the int argument that was 
passed to the function). It returns the altered user input phrase, which is
passed to the dictionaryComparison method back inside addLetters.

The dictionaryComparison method in EnhancementTwo is the same as the one in 
LostConsonants (including calling the parsedInput, phraseComparison, and 
punctuationCleanser methods from LostConsonants). 

Once this implementation was complete, it was tested several times with a
variety of different user input phrases and the dictionary that is used for the
Stacscheck (the one on StudRes).For instance, the phrase "rest" will generate
works like "crest" and "rests", and the phrase "take" will generate words like
"stake" and "take". 
