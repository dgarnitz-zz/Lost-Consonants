import java.util.*;

/**
* Class designed to generate a list of words by removing the vowels from a given word or phrase
* then match that list of words against a dictionary and if it matches the dictionary,
* print the altered word or phrase
* @author CS5001 Student (dag8@st-andrews.ac.uk)
* @version 1
* @since 1
*/

public class EnhancementOne {

  /**
	* Takes a string with the user input and a String Arraylist containing the dictionary
	* Loops through that input string and attempts to generate a "Lost Vowels" alternative
	* If a "Lost Vowels" alternative is generated, it compares it against the dictionaryComparison
	* If the "Lost Vowels" alternative is in the dictionary, it increments the word counter
	* Returns an int representing the word count after iterating through the whole user input
	* @param input string containing the user input
	* @param lines String ArrayList coontaining the dictionary
  * @return an int representing the number of alternative words in the dictionary
	*/
	public static int removeVowels(String input, ArrayList<String> lines) {

		int wordCount = 0;

		for(int j=0; j<input.length(); j++){

			String LostConsontantPhrase = generateAlternatives(input, j);
			if(!LostConsontantPhrase.equals(input)){
				wordCount = wordCount + dictionaryComparison(lines, LostConsontantPhrase, input);
			}
		}

		return wordCount;
	}

  /**
  * Takes a string, grab a char at a specified position in that string
	* checks if that char is a vowel, if it is, generates a new string
	* without that char
  * @param word string representing a word
	* @param position int representing the position of a char in a string
  * @return a string
  */
	public static String generateAlternatives(String word, int position){
		StringBuilder myWords = new StringBuilder(word);
		char placeholder = word.charAt(position);
		if(placeholder == '.'|| placeholder == ',' || placeholder == '?' || placeholder == ':' || placeholder == ';' || placeholder == ' '){
			return word;
		}

		if(isVowel.isVowel(placeholder)){
      if(word.charAt(position-1) == ' ' && word.charAt(position+1) == ' '){
        myWords.deleteCharAt(position);
        myWords.deleteCharAt(position-1);
        word = myWords.toString();
        return word;
      }
      else{
        myWords.deleteCharAt(position);
        word = myWords.toString();
        return word;
      }
		}
		return word;
	}

  /**
  * Takes two string arrays, both representing parsed strings, the first being where the
  * the lost vowel word is and the second being where the original phrase is, and
  * finds the one word in the first array that differs in length from the word in the same spot
  * in the second array. It then calls the punctuationCleanse method on that word and returns it
  * @param parsedLostV a string array that has been parsed from the lost vowel altered string
  * @param parsedOriginal a string array that has been parsed from the original user input
  * @return a punctuation-free string (word) that will be compared against the user specified dictionary later
  */
  public static String phraseComparison(String[] parsedLostV, String[] parsedOriginal){
    for(int i=0; i<parsedLostV.length; i++){
      Boolean checkLength = (parsedLostV[i].length() == parsedOriginal[i].length());
      if(!checkLength){
          parsedLostV[i] = LostConsonants.punctuationCleanse(parsedLostV[i]);
          return parsedLostV[i];
      }
      else if(parsedLostV.length + 1 == parsedOriginal.length){
        return parsedLostV[i] = "Exception case of one letter word found";
      }
    }
    return "Could not find the lost vowel word";
  }

  /**
  * This method takes the user-specified dictionary already loaded into an ArrayList, along with the lost vowel
  * altered user input string and the unadultered original user input string, it then calls the parsing and comparison
  * methods on them. After that it loops over the ArrayList to check if the altered word is equal to any
  * word in the ArrayList, and if it is, it prints the lost vowel altered user input string and increments an int
  * tracking how many times a phrase is printed. It then returns that int.
  * @param lines an ArrayList containing the contents of the dictionary file specified by the user
  * @param LostVowelPhrase a string with the lost vowel altered phrase
  * @param input a string with the original user input
  * @return an int that helps track how many lost vowel alternatives are created
  */
  public static int dictionaryComparison(ArrayList<String> lines, String LostVowelPhrase, String input){
    int counter = 0;
    String[] myList = LostConsonants.parsedInput(LostVowelPhrase);
    String[] parsedOriginal = LostConsonants.parsedInput(input);

    String currentWord = phraseComparison(myList, parsedOriginal);

    if(currentWord.equals("Could not find the lost vowel word")){
      return 0;
    }

    if(currentWord.equals("Exception case of one letter word found")){
      System.out.println(LostVowelPhrase);
      return counter+1;
    }

    for(int j=0; j<lines.size(); j++){
      String currentDictionaryEntry = lines.get(j);
      currentWord = currentWord.toLowerCase();
      currentDictionaryEntry = currentDictionaryEntry.toLowerCase();
      if(currentDictionaryEntry.equals(currentWord)) {
        System.out.println(LostVowelPhrase);
        return counter+1;
      }
    }
    return counter;
  }

  /**
	* Main method of the EnhancementOne class, takes in a string array an argument. This array has two strings, one
	* containing a filepath to a dictionary and the other containing a phrase, expression or word input by the user.
	* This method will take that user input, call methods on it that will generate "Lost Vowels" alternatives, then
	* check those alternatives against the dictionary provided by the user. If the "Lost Vowel" alternative is in
	* the dicionary, the original phrase will be printed but with the "Lost Consonant" alternative in place. After all
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

				int listSize = removeVowels(args[1], lines);

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
