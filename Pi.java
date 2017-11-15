
public class Pi{
	public static void main(String[] args) {
		
		if(!twoArgs(args) || !isInteger(args))
		{
			System.out.println("USAGE: java Pi <numThreads> <numIterations>");
			System.exit(1);
		}
		
	}

	/**
	* isInteger - ensures args are a 32 bit integer
	* @return true if integer, false otherwise 
	*/
	public static boolean isInteger(String[] args) {
		try {
			Integer.parseInt(args[0]);
			Integer.parseInt(args[1]);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	* twoArgs - ensures two arguments
	* @return true if two arg, false otherwise 
	*/
	public static boolean twoArgs(String[] args) {
		if(args.length != 2) {
			return false;
		}
		return true; 
	}

}