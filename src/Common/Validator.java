package Common;

import Model.Words;

import java.util.Stack;

/**
 * @author phamm
 */
public class Validator {

    public String normalize(Words text) {
        Stack<Character> quoteStack = new Stack<>();
        StringBuilder s = new StringBuilder();
//        Initialize
        for (String word : text) {
            word = word.toLowerCase().trim().replaceAll("\\s+", " ");
            s.append(word);
        }
        s.setCharAt(0, Character.toUpperCase(s.charAt(0))); // Uppercase the first letter of the text
        int i;
        for (i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
//            Delete spaces before and insert space after these characters if needed
            if (currentChar == ',' || currentChar == '.' || currentChar == ':') {
//                Delete spaces
                if (s.charAt(i - 1) == ' ') {
                    s.deleteCharAt(i - 1);
                    i--;
                }
//                Add spaces
                if (s.charAt(i + 1) != ' ') {
                    s.insert(i + 1,' ');
                }
//                Uppercase the next letter if current letter is a dot
                if (currentChar == '.') {
                    s.setCharAt(i + 2, Character.toUpperCase(s.charAt(i + 2)));
                }
            }
//            Delete spaces between the two quotes
            if (currentChar == '"') {
//                This means that we only need to delete the next spaces (opening quote)
                if (quoteStack.isEmpty()) {
                    char after = s.charAt(i+1);
                    while (after == ' ') {
                        s.deleteCharAt(i + 1);
                        after = s.charAt(i+1);
                    }
                    quoteStack.push(currentChar);
//                    The stack is pushed so that the next quote means closing quote, so we only delete the spaces before
                } else {
                    char before = s.charAt(i-1);
                    while (before == ' ') {
                        s.deleteCharAt(i - 1);
                        i--;
                        before = s.charAt(i-1);
                    }
                    quoteStack.pop();
                }
            }
        }
        if (!(s.charAt(s.length()-1) == '.')) s.append('.'); // Add a dot at the end if needed
        // TODO: Concat lines and add a space between them.
        return s.toString();
    }
}
