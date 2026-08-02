import java.util.Scanner;

class Account {
	int acno;
	String name;
	double balance;

	void setdata(int ac, String nm, double bal)
	{
		acno = ac;
		name = nm;
		balance = bal;
	}

	void deposit(double amount)
	{
		balance = balance + amount;
		System.out.println("Deposited: " + amount);
	}

	void display()
	{
		System.out.println("Account No : " + acno);
		System.out.println("Name       : " + name);
		System.out.println("Balance    : " + balance);
	}

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		Account acc = new Account();
		acc.setdata(101, "ravi", 5000);
		System.out.println("before deposited");
		acc.display();

		System.out.println("enter amount to deposit");
		double amount = sc.nextDouble();
		acc.deposit(amount);

		System.out.println("after deposit");
		acc.display();

		sc.close();
	}
}
