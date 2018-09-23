import java.util.*;

public class isVowel{
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
      case 'Y':
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
      case 'y':
        isVowel = true;
        break;
      default:
        isVowel = false;
        break;
    }
    return isVowel;
  }
}
