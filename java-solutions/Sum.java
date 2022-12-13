public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        String input = "";
        for (String arg : args) {
            input = input.concat(arg + " ");
        }
        String currentNum = "";
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isWhitespace(currentChar)) {
                if (currentNum.length() > 0)
                    sum += Integer.parseInt(currentNum);
                currentNum = "";
            }
            else {
                currentNum += currentChar;
            }
        }
        if (currentNum.length() > 0) 
            sum += Integer.parseInt(currentNum);
        System.out.println(sum);
    }
}