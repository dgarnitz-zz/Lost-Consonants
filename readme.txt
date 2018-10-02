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
