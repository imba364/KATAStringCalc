import java.util.Scanner;

public class StringCalculator {
    public static void main(String[] args) {
        OperationHandler.print();
    }
}
class OperationHandler {
    public static void print() {

        System.out.println("Введите выражение в формате строка операция строка, число операция число или строка операция число:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(StringHandler.processing(input));
    }
}
class StringHandler {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static String processing(String input) {
        char operation = 0;
        String a = "";
        String b = "";
        String[] data = new String[2];
        String result = "";
        if (input.contains(" + ")) {
            data = input.split(" \\+ ");
            operation = '+';
        } else if (input.contains(" - ")) {
            data = input.split(" - ");
            operation = '-';
        } else if (input.contains(" * ")) {
            data = input.split(" \\* ");
            operation = '*';
        } else if (input.contains(" / ")) {
            data = input.split(" / ");
            operation = '/';
        } else
            return "Некорректная операция";
        if ((!data[0].contains("\""))) {
            throw new IllegalArgumentException("Первый аргумент должен быть строкой");
        }
        if (data[0].length() > 10 || data[1].length() > 10) {
            throw new IllegalArgumentException("Строка не должна быть длинее 10 символов");
        }
        if (operation == '*' || operation == '/') {
            if (data[1].contains("\"")) {
                throw new IllegalArgumentException("Делить и умножать на строку нельзя");
            }
        }
        replace(data);
        a = data[0];
        b = data[1];

        switch (operation) {

            case '+':
                return "\"" + (a + b) + "\"";

            case '-':
                return "\"" + (a.replace(b, "")) + "\"";

            case '*':
                try {
                    if (!isInteger(b)) {
                        throw new IllegalArgumentException("Число должно быть целым");
                    }
                    int n = Integer.parseInt(b);
                    result = "";
                    for (int i = 0; i < n; i++) {
                        result += a;
                    }
                    if (result.length() > 40) {
                        result = result.substring(0, 40) + "...";
                        return result;
                    }
                    if (b.contains("\"")) {
                        throw new IllegalArgumentException("При делении или умножении второе число должно быть указано без кавычек");
                    }
                    if (n < 1 || n > 10) {
                        throw new IllegalArgumentException("Число должно быть от 1 до 10");
                    } else
                        return "\"" + result + "\"";
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("При делении или умножении второе число должно быть указано без кавычек");
                }

            case '/':
                try {
                    if (!isInteger(b)) {
                        throw new IllegalArgumentException("Число должно быть целым");
                    }
                    int divisor = Integer.parseInt(b);
                    int newLength = a.length() / divisor;
                    result = a.substring(0, newLength);
                    if (divisor < 1 || divisor > 10) {
                        throw new IllegalArgumentException("Число должно быть от 1 до 10");

                    }
                    if (b.contains("\"")) {
                        throw new IllegalArgumentException("При делении или умножении второе число должно быть указано без кавычек");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("При делении или умножении второе число должно быть указано без кавычек");
                }

                return "\"" + result + "\"";
        }
        return input;
    }
    public static String[] replace (String[]data){
        data[0] = data[0].replaceAll("\"", "");
        data[1] = data[1].replaceAll("\"", "");
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }
        return data;
    }
}


























