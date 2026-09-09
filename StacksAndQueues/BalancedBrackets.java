package StacksAndQueues;

import java.util.Scanner;
import java.util.Stack;

/*
INTUITION:
    Brackets must close in the reverse order in which they open, which follows the LIFO nature of a stack.
    So, keep opening brackets in a stack and ensure every closing bracket matches the most recently opened bracket.


APPROACH :
    Use a stack to store opening brackets. 
    For every closing bracket, the most recent opening bracket (stack.peek()) must be its matching pair.
    If the stack is empty or the top bracket doesn't match, brackets are unbalanced. 
    After processing the string, the stack must also be empty (no unmatched openings).
    Non-bracket characters are ignored, and the helper method validates + removes each matched opening bracket.
*/
public class BalancedBrackets {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        System.out.println(AreBracketsBalanced(str));
    }

    public static boolean AreBracketsBalanced(String str) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // Only bracket characters matter, so only those will be pushed. Rest of the characters will be ignored
            if(ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if(ch == ')') {
                if(handleClosing('(', st) == false) return false;
            } else if(ch == '}') {
                if(handleClosing('{', st) == false) return false;
            } else if(ch == ']') {
                if(handleClosing('[', st) == false) return false;
            }
        }

        if(st.size() != 0) return false; // Opening brackets are more
        else return true;
    }

    public static boolean handleClosing(char correspondingOpeningCharacter, Stack<Character> st) {
        if(st.isEmpty()) {
            return false;  // Closing brackets are more
        }
        else if(st.peek() != correspondingOpeningCharacter) {
            return false;  // Bracket mismatch found
        }
        else {
            st.pop();
            return true;
        }
    }
}
