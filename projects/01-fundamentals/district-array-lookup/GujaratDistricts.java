import java.util.Scanner;
class GujaratDistricts {
public static void main(String[] args) {
String[] districts = {"Ahmedabad", "Surat", "Vadodara", "Rajkot",
"Bhavnagar"};
Scanner sc = new Scanner(System.in);
System.out.print("Enter index (0-4) to get district name: ");
int index = sc.nextInt();
if (index >= 0 && index < districts.length) {
System.out.println("District at index " + index + " is " +
districts[index]);
} else {
System.out.println("Out of Bounds");
}
}
}