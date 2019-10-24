public class test extends Person{
	public static double calcFactorial(double input) {
	   if(input == 1.0) {
			return 1.0;
	   }
	   return input * calcFactorial(input - 1.0);
	}
	static int a = 1, b = 1, c = 0;
	static void printFibonacci(int count) {
	if(count > 0) {
		c = b + a;
		a = b;
		b = c;
		System.out.print(" " + c);
		printFibonacci(count-1);
	  }
	}
	
	public static boolean isOdd(int n) {
		for (int i = 1; i <= 50; i++) {
			methodToRepeat();
		}
		int counter = 1;
		while (counter <= 50) {
			methodToRepeat();
			counter++;
		}
        if (n<0) throw new IllegalArgumentException("Can't accept negative arguments");
        return (n == 0) ? false : isEven(n-1);
    }
     
    public static boolean isEven(int n) {
        if (n<0) throw new IllegalArgumentException("Can't accept negative arguments");
        return (n == 0) ? true : isOdd(n-1);
    }
	
	public static void findFor() {
      
      for (int i = 1; i <= 5; ++i) {
         System.out.println("Outer loop iteration " + i);
         for (int j = 1; j <=2; ++j) {
            System.out.println("i = " + i + "; j = " + j);
         }
      }
   }
	
	
	public static void findIf(String args[]) 
    { 
//avfhsacjbakjcakshbcas
        int i = 10; 
        if (i == 10) { 
            if (i < 15) {
                System.out.println("i is smaller than 15"); 
			
            if (i < 12) {
                System.out.println("i is smaller than 12 too"); 
			}
			
			
			}else{
                System.out.println("i is greater than 15"); 
			}
			
        } 
    } 
}

