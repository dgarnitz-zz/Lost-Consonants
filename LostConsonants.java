
import java.util.*;

public class LostConsonants {

	public static String[] parsedInput(String userInput) {
		return userInput.split(" ");
	}

	public static ArrayList<String> generateAlternatives(String word, StringBuilder myWords, ArrayList<String> newWordList){
		for(int i=0; i<word.length(); i++){
			char placeholder = word.charAt(i);
			if(!isVowel.isVowel(placeholder)){
				myWords.deleteCharAt(i);
				newWordList.add(myWords.toString());
				myWords.replace(0, word.length(), word);
			}
		}
		return newWordList;
	}

	public static ArrayList<String> removeConsonants(String input) {
		String[] parsedUserInput = parsedInput(input);
		ArrayList<String> storingList = new ArrayList<String>();

		for(int j=0; j<parsedUserInput.length; j++){

			String word = parsedUserInput[j];
			StringBuilder myWords = new StringBuilder(word);

			storingList = generateAlternatives(word, myWords, storingList);
		}

		return storingList;
	}

	public static ArrayList<String> dictionaryComparison(ArrayList<String> lines, ArrayList<String> myList){
		ArrayList<String> matches = new ArrayList<String>();
		for(int i=0; i<myList.size(); i++){
			String currentWord = myList.get(i);
			for(int j=0; j<lines.size(); j++){
				if(lines.get(j).equals(currentWord)) {
					matches.add(currentWord);
				}
			}
		}
		return matches;
	}

	public static void main (String[] args) {

		try{

			if(args.length!=2){
				System.out.println("Expected 2 command line arguments, but got " + args.length +".");
				System.out.println("Please provide the path to the dictionary file as the first argument and a sentence as the second argument.");
				return;
			}


				ArrayList<String> lines = FileUtil.readLines(args[0]);

				ArrayList<String> LostConsonantList = removeConsonants(args[1]);

				ArrayList<String> finalList = dictionaryComparison(lines, LostConsonantList);
				if(lines.size()==0){
					return;
				}
				else if(finalList.size()==0){
					System.out.println("Could not find any alternatives.");
					return;
				}
				else{
						int counter=0;
						for (int i=0; i < finalList.size(); i++){
							System.out.println(finalList.get(i));
							counter = counter + 1;
						}
						System.out.println("Found " + counter + " alternatives.");
				}

		}
		catch(Exception e){
			System.out.println("Unknown error, program terminating");
		}
	}
}
