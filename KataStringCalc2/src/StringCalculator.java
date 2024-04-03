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
        }
        replace(data);
        a = data[0];
        b = data[1];
        Exceptions.verifyStringLengths(a, b,result);


        switch (operation) {
            case '+':
                return "\"" + (a + b) + "\"";

            case '-':
                return "\"" + (a.replace(b, "")) + "\"";

            case '*':
                int n = Integer.parseInt(b);
                result = "";
                for (int i = 0; i < n; i++) {
                    result += a;
                }
                if (n < 1 || n > 10) {
                    throw new IllegalArgumentException("Число должно быть от 1 до 10");
                }
                return "\"" + result + "\"";

            case '/':
                int divisor = Integer.parseInt(b);
                int newLength = a.length() / divisor;
                result = a.substring(0, newLength);
                if (divisor < 1 || divisor > 10) {
                    throw new IllegalArgumentException("Число должно быть от 1 до 10");
                }
                return "\"" + result + "\"";


            default:
                return "Неподдерживаемая операция: " + operation;

        }
    }

    public static String[] replace(String[] data) {
        if((!data[0].contains("\""))){
            throw new IllegalArgumentException("Первый аргумент должен быть строкой");
        }
        data[0] = data[0].replaceAll("\"","");
        data[1] = data[1].replaceAll("\"","");
        return data;
    }
}
class Exceptions {
    public static void verifyStringLengths(String a, String b, String result) {
        if (a.length() > 10 || b.length() > 10) {
            throw new IllegalArgumentException("Строка не должна быть длинее 10 символов");
        }
        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        } else if (b.contains("\"")) {
            throw new IllegalArgumentException("При делении или умножении второе число должно быть указано без кавычек");
        }
    }
}