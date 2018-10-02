import java.util.*;

/**
* Class designed to generate a list of words by adding consonants to a given word or phrase
* then match that list of words against a dictionary and if it matches the dictionary,
* print the altered word or phrase
* @author CS5001 Student (dag8@st-andrews.ac.uk)
* @version 1
* @since 1
*/

public class EnhancementTwo {

  /**
  * Inserts a char at a specified point in a string
  * @param word string representing a word
  * @param position int representing the position of a char in a string
  * @param letter char representing the letter to be inserted into the word
  * @return a string
  */
  public static String generateAlternatives(String word, int position, char letter){
    StringBuilder myWords = new StringBuilder(word);
    if(position != word.length()){
      char placeholder = word.charAt(position);
      if(placeholder == '.'|| placeholder == ',' || placeholder == '?' || placeholder == ':' || placeholder == ';' || placeholder == ' '){
        myWords.insert(position, letter);
        word = myWords.toString();
        return word;
      }
    }

    myWords.insert(position, letter);
    word = myWords.toString();
    return word;
  }

  /**
  * Takes a string with the user input and a String Arraylist containing the dictionary
  * Loops through that input string and attempts to generate added letters alternatives
  * If an alternative is generated, it compares it against the dictionary.
  * If the  alternative is in the dictionary, it increments the word counter
  * Returns an int representing the word count after iterating through the whole user input
  * @param input string containing the user input
  * @param lines String ArrayList containing the dictionary
  * @return an int representing the number of alternative words in the dictionary
  */
  public static int addLetters(String input, ArrayList<String> lines) {

    int wordCount = 0;
    String letters = "bcdfghjklmnpqrstvwxyz";

    for(int j=0; j<input.length()+1; j++){

      for(int k=0; k<letters.length(); k++){

        char letter = letters.charAt(k);
        String phrase = generateAlternatives(input, j, letter);
        wordCount = wordCount + LostConsonants.dictionaryComparison(lines, phrase, input);
      }
    }

    return wordCount;
  }

  /**
  * Main method of the EnhancementTwo class, takes in a string array an argument. This array has two strings, one
  * containing a filepath to a dictionary and the other containing a phrase, expression or word input by the user.
  * This method will take that user input, call methods on it that will generate added-consontant alternatives, then
  * check those alternatives against the dictionary provided by the user. If the alternative is in
  * the dicionary, the original phrase will be printed but with the  alternative in place. After all
  * the alternatives are printed it will print out the total number. It will also print error messages if incorrect
  * arguments are passed to it.
  * @param args a string array containing the dictionary filepath and user input string
  * @return nothing, just prints
  */
  public static void main (String[] args) {

    try{

      if(args.length != 2){
        System.out.println("Expected 2 command line arguments, but got " + args.length +".");
        System.out.println("Please provide the path to the dictionary file as the first argument and a sentence as the second argument.");
        return;
      }


        ArrayList<String> lines = FileUtil.readLines(args[0]);

        int listSize = addLetters(args[1], lines);

        if(lines.size() == 0){
          return;
        }
        else if(listSize == 0){
          System.out.println("Could not find any alternatives.");
          return;
        }
        else{
            System.out.println("Found " + listSize + " alternatives.");
        }

    }
    catch(Exception e){
      System.out.println("Unknown error, program terminating");
    }
  }
}
