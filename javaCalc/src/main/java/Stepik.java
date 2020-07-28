public class Stepik {

    public static void main(String[] args) {
        System.out.println(isPalindromeSB("ab b b a"));
        System.out.println(isPalindromeFor("ab b b a"));
    }

    public static boolean isPalindromeSB(String str) {
        str = str.replace(" ", "");
        return str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
    }

    public static boolean isPalindromeFor(String str) {
        str = str.replace(" ", "").toLowerCase();

        for (int i = 0, j = str.length() - 1; i != j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
