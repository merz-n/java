import java.util.Scanner;
public class Symbolsstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. FindSymbolOccurance ");
        System.out.println("Enter string: ");
        String text = scanner.nextLine();
        System.out.println("Enter symbol: ");
        String symbol = scanner.nextLine();
        if (symbol.length() == 1) {
            char symbolwell = symbol.charAt(0);
            System.out.println("You entered symbol: " + symbolwell);
            int itog = findSubstringOccurrence(text, symbolwell);
            System.out.println("So many of this character in a line: " + itog);
        } else {
            System.out.println("Please enter exactly one symbol.");
        }
        System.out.println("2. findWordPosition ");
        System.out.println("Enter source (we will look here): ");
        String source = scanner.nextLine();
        System.out.println("Enter target (what we will be looking for): ");
        String target = scanner.nextLine();
        int index = source.indexOf(target);
        System.out.println("Result: " + index);
        System.out.println("3. stringReverse ");
        System.out.println("Enter string: ");
        String str1 = scanner.nextLine();
        String reversedString = stringReverse(str1);
        System.out.println("Reversed string: " + reversedString);
        System.out.println("4. isPalindrome ");
        System.out.println("Enter string: ");
        String pol = scanner.nextLine();
        System.out.println("Antwort: " + isPalindrome(pol));
        System.out.println("5 ");


        scanner.close();
    }

    public static int findSubstringOccurrence(String str, char symbolwell) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == symbolwell) {
                count++;
            }
        }
        return count;
    }

    public static int findWordPosition(String source, String target) {
        return source.indexOf(target);
    }

    public static String stringReverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static boolean isPalindrome(String pol) {
        StringBuilder newpol = new StringBuilder();
        for (int i = pol.length() - 1; i >= 0; i--) {
            newpol.append(pol.charAt(i));
        }
        return newpol.toString().equalsIgnoreCase(pol);
    }
}
