public class SumLongOctal {
    public static void main(String[] args) {
        long sum = 0;
        StringBuilder currentNum = new StringBuilder();
        int base = 10;
        for (String arg : args) {
            for (int j = 0; j < arg.length(); j++) {
                char currentChar = arg.charAt(j);
                boolean skip = Character.isWhitespace(currentChar);
                if (!skip) {
                    if (currentChar == 'o' || currentChar == 'O') {
                        base = 8;
                    }
                    else {
                        currentNum.append(currentChar);
                    }
                }
                if (skip || j + 1 == arg.length()) {
                    if (currentNum.length() > 0) {
                        sum += Long.parseLong(currentNum.toString(), base);
                    }
                    currentNum = new StringBuilder();
                    base = 10;
                }
            }
        }
        System.out.println(sum);
    }
}