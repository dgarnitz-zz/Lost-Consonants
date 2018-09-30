import java.util.*;

/**
* Class has one method that checks if a given char is a vowel letter
* @author CS5001 Student (dag8@st-andrews.ac.uk)
* @version 1
* @since 1
*/

public class isVowel{

  /**
  * Checks if a a char is a vowel letter, returns true if it is, false if its not
  * @param placeholder char in a give word or phrase
  * @return a Boolean, true if the char is a vowel, false if it is not
  */
  public static Boolean isVowel (char placeholder){
    Boolean isVowel;
    switch(placeholder){
      case 'A':
        isVowel = true;
        break;
      case 'E':
        isVowel = true;
        break;
      case 'I':
        isVowel = true;
        break;
      case 'O':
        isVowel = true;
        break;
      case 'a':
        isVowel = true;
      break;
      case 'e':
        isVowel = true;
        break;
      case 'i':
        isVowel = true;
        break;
      case 'o':
        isVowel = true;
        break;
      case 'u':
        isVowel = true;
        break;
      default:
        isVowel = false;
        break;
    }
    return isVowel;
  }
}
