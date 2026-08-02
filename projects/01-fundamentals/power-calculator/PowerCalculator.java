class PowerCalculator {

	// Method to compute x^y using repetitive multiplication
	public static int power(int x, int y) throws IllegalArgumentException {
		if (y < 0) {
			throw new IllegalArgumentException("Exponent must be non-negative");
		}
		int result = 1;
		for (int i = 1; i <= y; i++) {
			result = result * x;
		}
		return result;
	}

	public static void main(String[] args) {
		try {
			if (args.length < 2) {
				throw new IllegalArgumentException("Please provide 2 integers as arguments");
			}
			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[1]);
			int pow = power(x, y);
			System.out.println(x + " ^ " + y + " = " + pow);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input! Please enter integers.");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred.");
		}
	}
}
