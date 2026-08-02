import java.io.*;
class Student {
String id;
String name;
int age;
// Constructor
Student(String id, String name, int age) {
this.id = id;
this.name = name;
this.age = age;
}
// Convert Student info to string
public String toString() {
return id + "," + name + "," + age + "\n";
}
}
class StudentManager {
public static void main(String[] args) {
try {
// Create some student objects
Student s1 = new Student("S101", "Ravi", 20);
Student s2 = new Student("S102", "Anita", 21);
Student s3 = new Student("S103", "Meena", 19);
// Write student info to file
FileOutputStream fos = new FileOutputStream("students.txt");
fos.write(s1.toString().getBytes());
fos.write(s2.toString().getBytes());
fos.write(s3.toString().getBytes());
fos.close();
System.out.println("Student data written to file successfully.");
// Read student info from file
FileInputStream fis = new FileInputStream("students.txt");
int i;
System.out.println("\nReading student data from file:");
while ((i = fis.read()) != -1) {
System.out.print((char) i);
}
fis.close();
} catch (IOException e) {
System.out.println("Error: " + e.getMessage());
}
}
}