
import java.util.*;

public class LostConsonants {

	public static String[] parsedInput(String userInput) {
		return userInput.split(" ");
	}

	public static String generateAlternatives(String word, int position){
		StringBuilder myWords = new StringBuilder(word);
		char placeholder = word.charAt(position);
		if(!isVowel.isVowel(placeholder)){
			myWords.deleteCharAt(position);
			word = myWords.toString();
			return word;

			//newWordList.add(myWords.toString());
			//myWords.replace(0, word.length(), word);
		}
		return word;
	}

	public static int removeConsonants(String input, ArrayList<String> lines) {
		//remove a consonant from the whole input String
		//then split the string
		//then compare each word in the string to the dictionary
		//then if they all evaluate to true, print the whole string (with consonant removed)

		int wordCount = 0;

		for(int j=0; j<input.length(); j++){

			String LostConsontantPhrase = generateAlternatives(input, j);
			if(!LostConsontantPhrase.equals(input))

				wordCount = wordCount + dictionaryComparison(lines, LostConsontantPhrase);
			}

		return wordCount;
	}

	public static int dictionaryComparison(ArrayList<String> lines, String LostConsontantPhrase){
		int counter = 0;
		int wordCount = 0;
		String[] myList = parsedInput(LostConsontantPhrase);
		for(int i=0; i<myList.length; i++){

			String currentWord = myList[i];

			for(int j=0; j<lines.size(); j++){
				if(lines.get(j).equals(currentWord)) {
					counter++;
				}
			}
		}
		if(counter==myList.length){
				System.out.println(LostConsontantPhrase);
				wordCount++;
		}
		return wordCount;
	}

	public static void main (String[] args) {

		try{

			if(args.length!=2){
				System.out.println("Expected 2 command line arguments, but got " + args.length +".");
				System.out.println("Please provide the path to the dictionary file as the first argument and a sentence as the second argument.");
				return;
			}


				ArrayList<String> lines = FileUtil.readLines(args[0]);

				int listSize = removeConsonants(args[1], lines);

				if(lines.size()==0){
					return;
				}
				else if(listSize==0){
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
