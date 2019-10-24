class Test implements PI1, PI2 {
	// Overriding default show method
	public void show() {
		// use super keyword to call the show
		// method of PI1 interface
		PI1.super.show();

		// use super keyword to call the show
		// method of PI2 interface
		PI2.super.show();
	}

	public static void main(String args[]) {
		Test d = new Test();
		d.show();
	}
}
