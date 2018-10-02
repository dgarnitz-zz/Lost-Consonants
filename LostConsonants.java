import java.util.*;

/**
* Class designed to generate a list of words by removing the consonants from a given word or phrase
* then match that list of words against a dictionary and if it matches the dictionary,
* print the altered word or phrase
* @author CS5001 Student (dag8@st-andrews.ac.uk)
* @version 1
* @since 1
*/

public class LostConsonants {

	/**
	* Takes an input string and parses it into a string array with no blank spaces
	* @param userInput a string with user input
	* @return string array with parsed strin
	*/
	public static String[] parsedInput(String userInput) {
		String[] splitInput = userInput.split(" ");
		return splitInput;
	}

	/**
  * Takes a string, grab a char at a specified position in that string
	* checks if that char is a vowel, if it is not, generates a new string
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

		if(!isVowel.isVowel(placeholder)){
			myWords.deleteCharAt(position);
			word = myWords.toString();
			return word;
		}
		return word;
	}

	/**
	* Takes a string with the user input and a String Arraylist containing the dictionary
	* Loops through that input string and attempts to generate a "Lost Consonants" alternative
	* If a "Lost Consonants" alternative is generated, it compares it against the dictionary
	* If the "Lost Consonants" alternative is in the dictionary, it increments the word counter
	* Returns an int representing the word count after iterating through the whole user input
	* @param input string containing the user input
	* @param lines String ArrayList coontaining the dictionary
  * @return an int representing the number of alternative words in the dictionary
	*/
	public static int removeConsonants(String input, ArrayList<String> lines) {

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
	* Takes a string that has been parsed from the user input and checks if the last char
	* in that string is a punction mark, if it is, it removes it
	* Returns a string without any punctuation marks at the end
	* @param parsedLostConsontantWord a string that has been parsed from the user input
  * @return a string that is punctuation free at the end
	*/
	public static String punctuationCleanse(String parsedLostConsontantWord){
		StringBuilder cleanser = new StringBuilder(parsedLostConsontantWord);
		int lastPosition = parsedLostConsontantWord.length()-1;
		char placeholder = parsedLostConsontantWord.charAt(lastPosition);
		if(placeholder == ',' || placeholder == '.' || placeholder == '!' || placeholder == '?' ){
			cleanser.deleteCharAt(lastPosition);
			parsedLostConsontantWord = cleanser.toString();
			return parsedLostConsontantWord;
		}
		return parsedLostConsontantWord;
	}

	/**
	* Takes two string arrays, both representing parsed strings, the first being where the
	* the lost consonant word is and the second being where the original phrase is, and
	* finds the one word in the first array that differs in length from the word in the same spot
	* in the second array. It then calls the punctuationCleanse method on that word and returns it
	* @param parsedLostC a string array that has been parsed from the lost consonant altered string
	* @param parsedOriginal a string array that has been parsed from the original user input
	* @return a punctuation-free string (word) that will be compared against the user specified dictionary later
	*/
	public static String phraseComparison(String[] parsedLostC, String[] parsedOriginal){
		for(int i=0; i<parsedLostC.length; i++){
			Boolean checkLength = (parsedLostC[i].length() == parsedOriginal[i].length());
			if(!checkLength){
					parsedLostC[i] = punctuationCleanse(parsedLostC[i]);
					return parsedLostC[i];
			}
		}
		return "Could not find the lost consonant word";
	}

	/**
	* This method takes the user-specified dictionary already loaded into an ArrayList, along with the lost consonant
	* altered user input string and the unadultered original user input string, it then calls the parsing and comparison
	* methods defined above on them. After that it loops over the ArrayList to check if the altered word is equal to any
	* word in the ArrayList, and if it is, it prints the lost consonant altered user input string and increments an int
	* tracking how many times a phrase is printed. It then returns that int.
	* @param lines an ArrayList containing the contents of the dictionary file specified by the user
	* @param LostConsontantPhrase a string with the lost consonant altered phrase
	* @param input a string with the original user input
	* @return an int that helps track how many lost consonant alternatives are created
	*/
	public static int dictionaryComparison(ArrayList<String> lines, String LostConsontantPhrase, String input){
		int counter = 0;
		String[] myList = parsedInput(LostConsontantPhrase);
		String[] parsedOriginal = parsedInput(input);

		String currentWord = phraseComparison(myList, parsedOriginal);

		if(currentWord.equals("Could not find the lost consonant word")){
			return 0;
		}

		for(int j=0; j<lines.size(); j++){
			String currentDictionaryEntry = lines.get(j);
			currentWord = currentWord.toLowerCase();
			currentDictionaryEntry = currentDictionaryEntry.toLowerCase();
			if(currentDictionaryEntry.equals(currentWord)) {
				System.out.println(LostConsontantPhrase);
				return counter+1;
			}
		}
		return counter;
	}

	/**
	* Main method of the LostConsonants class, takes in a string array an argument. This array has two strings, one
	* containing a filepath to a dictionary and the other containing a phrase, expression or word input by the user.
	* This method will take that user input, call methods on it that will generate "Lost Consonant" alternatives, then
	* check those alternatives against the dictionary provided by the user. If the "Lost Consonant" alternative is in
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

				int listSize = removeConsonants(args[1], lines);

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
