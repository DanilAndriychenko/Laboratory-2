public class Utility {

    public static boolean numberInGivenRange(String n, int a, int b) {
        for (int i = 0; i < n.length(); i++) if (!Character.isDigit(n.charAt(i))) return true;
        return !n.equals("") && (Integer.parseInt(n) < a || Integer.parseInt(n) > b);
    }

    public static boolean lineContainsOnlyLetters(String line) {
        for (int i = 0; i < line.length(); i++)
            if (!Character.isLetter(line.charAt(i)) && line.charAt(i) != ' ') return false;
        return true;
    }

    public static void swap(int[] arr, int i) {
        int tmp = arr[i + 1];
        arr[i + 1] = arr[i];
        arr[i] = tmp;
    }

    public static boolean stringContainsOnlyDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    public static boolean nameIsUniqueInDynamicArray(String name, String[] dynamicArray) {
        for (int i = 0; i < dynamicArray.length; i++) {
            if (dynamicArray[i].equals(name)) return false;
        }
        return true;
    }
}
