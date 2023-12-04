import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        String output = calc(input);
        System.out.println(output);

    }
    public static String calc(String input) {
        int num1 = 0;
        int num2 = 0;
        String num1str = getOperands(input)[0];
        String num2str = getOperands(input)[2];
        String operator = getOperands(input)[1];
        if (!isArabic(num1str) && !isArabic(num2str)){
            num1 = romanToArabic(num1str);
            num2 = romanToArabic(num2str);
        } else if (isArabic(num1str) && isArabic(num2str)){
            num1 = Integer.parseInt(num1str);
            num2 = Integer.parseInt(num2str);
        } else {
            throw new IllegalArgumentException("Используются разные системы счисления");
        }
        validate(num1, num2);

        String result = switch (operator) {
            case "+" -> String.valueOf(num1 + num2);
            case "-" -> String.valueOf(num1 - num2);
            case "*" -> String.valueOf(num1 * num2);
            case "/" -> String.valueOf(num1 / num2);
            default -> throw new IllegalArgumentException("Неверный оператор");
        };
        return !isArabic(num1str) && !isArabic(num2str)? arabicToRoman(Integer.parseInt(result)): result;
    }
    public static int romanToArabic(String roman) {
        int[] arabicArray = new int[roman.length()];
        for (int i = 0; i < roman.length(); i++) {
            int n = switch (roman.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                default -> throw new IllegalArgumentException("Неверный символ римского числа");
            };
            arabicArray[i] = n;
        }
        int arabic = arabicArray[roman.length() - 1];
        for (int i = 0; i < arabicArray.length - 1; i++) {
            if (arabicArray[i] < arabicArray[i + 1]) {
                arabic -= arabicArray[i];
            } else {
                arabic += arabicArray[i];
            }
        }
        return arabic;
    }
    public static String arabicToRoman(int arabic) {
        if (arabic < 1){
            throw new IllegalArgumentException("В римской системе нет ноля и отрицательных чисел");
        }
        String[] romanConst = {"I","IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] arabicConst = {1, 4, 5, 9, 10, 40, 50, 90, 100};
        String roman = "";
        int index = arabicConst.length - 1;
        while (arabic != 0){
            int current = arabicConst[index];
            if (arabic >= current){
                arabic -= current;
                roman += romanConst[index];
            } else {
                index--;
            }
        }
        return roman;
    }
    public static boolean isArabic(String number){
        boolean isArabic = true;
        for (char c: number.toCharArray()){
            if (!Character.isDigit(c)) {
                isArabic = false;
            }
        }
        return isArabic;
    }
    public static String[] getOperands(String input) {
        String[] operands = input.split(" ");
        if (operands.length !=3){
            throw new IllegalArgumentException("Неверный формат выражения");
        }
        return operands;
    }
    public static void validate(int num1, int num2){
        if (num1 > 10 || num2 > 10 || num1 < 1 || num2 < 1){
            throw new IllegalArgumentException("Принимаются только числа от 1 до 10 включительно");
        }
    }

}

