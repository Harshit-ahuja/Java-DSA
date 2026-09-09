package StacksAndQueues;

import java.util.Scanner;
import java.util.Stack;

/*
INTUITION:
    Duplicate brackets occur when a pair of brackets contains nothing inside them, so when ')' is encountered,
    if the stack's top is '(' then an unnecessary pair of brackets has been found.


APPROACH :
    Use a stack to process each bracket pair. When ')' is found, if stack.peek() is '(',
    there is nothing between the brackets (i.e., "()" or "(())"), so an extra/duplicate bracket exists.
    Otherwise, pop all characters until '(' and remove it '(' as well.
    After processing, if no empty pair was found, return false.
*/
public class DuplicateBrackets {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        System.out.println(findPresenceOfDuplicateBrackets(str));
    }

    public static boolean findPresenceOfDuplicateBrackets(String str) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if( ch == ')' ) {
                if( st.peek() == '(' ) {
                    return true;
                } else {
                    while ( st.peek() != '(' ) {
                        st.pop();
                    }
                    st.pop(); // Coz we need to remove the opening bracket as well
                }
            } else {
                st.push(ch);
            }
        }

        return false;
    }
}
