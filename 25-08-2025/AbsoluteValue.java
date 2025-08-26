import java.util.Scanner;

public class AbsoluteValue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number
        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        // Calculate absolute value
        int absoluteValue = (num < 0) ? -num : num;

        System.out.println("Absolute value: " + absoluteValue);

        sc.close();
    }
}
