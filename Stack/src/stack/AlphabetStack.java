/**
 * The following code presents the applications of the stack's data structure characteristics
 */
package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;

/**
 * `
 *
 * @author chris, Cristian Alejandro Mantilla Duque - 1032502457
 */
public class AlphabetStack {

    private static String[] characters;
    private static int nStack;
    private static char[] stackTops;
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static String[] letters;
    private static HashSet<String> validInput;

    // Displays the menu to recieve the corresponding number of stacks and its top values
    public static void menu() throws IOException {
        System.out.println("How many stacks do you want to fill up?");
        String line = bf.readLine();
        while (!(Integer.parseInt(line) >= 1 && Integer.parseInt(line) <= 10)) {
            System.out.println("Invalid entry, try again. The number must be between 1 and 10 (inclusive)");
            line = bf.readLine();
        }
        nStack = Integer.parseInt(line);
        stackTops = new char[nStack];
        for (int i = 0; i < nStack; i++) {
            System.out.println("What is the top of the stack #" + (i + 1));
            line = bf.readLine();
            while (!validInput.contains(line)) {
                System.out.println("Invalid entry, try again. The entry must be a capital letter A-Z");
                line = bf.readLine();
            }
            stackTops[i] = line.charAt(0);
        }
    }

    // Fills an array with the characters to use it later, and also fill a set with the valid input to authenticate
    public static void fillCharacters() {
        int init = 65;
        validInput = new HashSet<String>();
        characters = new String[26];
        for (int i = 0; i < 26; i++) {
            characters[i] = Character.toString((char) (init + i));
            validInput.add(characters[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        int Continue = 1;
        while (Continue == 1) {
            Stack[] stacks;
            Stack<String> t_stack;
            fillCharacters();
            menu();
            stacks = new Stack[nStack];
            for (int i = 0; i < nStack; i++) {
                stacks[i] = new Stack<String>();
                int idx = ((int) stackTops[i]) % 65;
                int t_idx;
                for (int j = 1; j < 27; j++) {
                    t_idx = idx - j;
                    if (t_idx < 0) {
                        t_idx = 26 + t_idx;
                    }
                    stacks[i].push(characters[t_idx]);
                }
            }

            System.out.println("-------------------------------------------------------");
            System.out.println();
            System.out.println("The first character correspond to the top of the stack.");
            for (int i = 0; i < nStack; i++) {
                System.out.println("Stack # " + (i + 1));
                t_stack = (Stack) stacks[i].clone();
                for (int j = 0; j < 26; j++) {
                    System.out.print(t_stack.peek() + " ");
                    t_stack.pop();
                }
                System.out.println("");
            }

            System.out.println("Enter the letters you want to search in each stack separated by a single space");
            String line = bf.readLine();
            boolean valid = true;
            for (int i = 0; i < line.split(" ").length; i++) {
                valid = valid && validInput.contains(line.split(" ")[i]);
            }

            while (!valid || (line.split(" ").length < nStack)) {
                System.out.println("Invalid entry, try again. Follow the next indications:");
                System.out.println(" * The entry must be a capital letter A-Z");
                System.out.println(" * The entry must be separated by a single space");
                System.out.println(" * THe number of letters must be equal to the number of stacks");
                line = bf.readLine();
            }
            letters = line.split(" ");

            int ans = 0;
            System.out.println("");
            System.out.println("Answer: ");
            for (int i = 0; i < nStack; i++) {
                ans = 0;
                for (int j = 0; j < 26; j++) {
                    if (!stacks[i].peek().equals(letters[i].toUpperCase())) {
                        stacks[i].pop();
                        ans++;
                    } else {
                        System.out.println("* " + ans + " positions were removed in the stack #" + (i + 1) + " to get the element " + letters[i]);
                        break;
                    }
                }
            }

            System.out.println("");
            System.out.println("Want to do another querie?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            line = bf.readLine();
            while (!line.equals("1") && !line.equals("2")) {
                System.out.println("Invalid entry, try again. Choose 1 or 2");
                line = bf.readLine();
            }

            Continue = Integer.parseInt(line);
            if (Continue == 2) {
                System.out.println("Bye! Have a joy stacking day ");
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
    }
}
