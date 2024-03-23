package encryption;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RSAGenKey {
	
	//Takes the user input as a String
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		System.out.print("utils.RSAGenKey> ");
	    String input = in.nextLine();  // Read user input
	    
	    interpret(input);
	}
	
	//Takes the user input and parses them
	public static void interpret(String input) {
		//Splits the input by the spaces
		String[] inputs = input.split(" ");
		
		//If there is more than 1 input parse the numbers into BigInteger
		if(inputs.length > 1) {
			BigInteger p = new BigInteger(inputs[0]);
			BigInteger q = new BigInteger(inputs[1]);
			BigInteger e = new BigInteger(inputs[2]);
			RSAGenKey(p, q, e);
		}
		
		//If there is only one input parse the number into an integer
		else {
			int K = Integer.parseInt(input);
			RSAGenKey(K);
		}
	}
	
	//utils.RSAGenKey for input K
	public static void RSAGenKey(int K) {
		
		//p,q,e Inputs a BigInteger.probablePrime to pick a prime number
		//Does all n, thetaN, d calculations
		BigInteger p = BigInteger.probablePrime(K, new Random());
		BigInteger q = BigInteger.probablePrime(K, new Random());
		BigInteger n = p.multiply(q);
		BigInteger thetaN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger e = BigInteger.probablePrime(K, new Random());
		
		while(thetaN.gcd(e).compareTo(BigInteger.ONE) != 0){
			e  = BigInteger.probablePrime(K, new Random());
		}
		
		BigInteger d = e.modInverse(thetaN);
		
		createFile("pub_key.txt");
		createFile("pri_key.txt");
		
		writeFile("pub_key.txt", e, n);
		writeFile("pri_key.txt", d, n);
	}
	
	public static void RSAGenKey(BigInteger p, BigInteger q, BigInteger e) {
		//Does all n, thetaN, d calculations
		BigInteger n = p.multiply(q);
		BigInteger thetaN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger d = e.modInverse(thetaN);

		createFile("pub_key.txt");
		createFile("pri_key.txt");
		
		writeFile("pub_key.txt", e, n);
		writeFile("pri_key.txt", d, n);
	}
	
	//Writes to an existing file
	public static void writeFile(String fileName, BigInteger key, BigInteger n) {
	    try {
	        FileWriter myWriter = new FileWriter(fileName);
	        
	        //Checks if the filename is pub so it will write to the pub_key.txt
	        if(fileName.contains("pub")) {
	        	 myWriter.write("e = " + key.toString() + "\nn = " + n.toString());
	        }
	        
	        //Checks if the filename is pri_key.txt
	        else {
	        	myWriter.write("d = " + key.toString() + "\nn = " + n.toString());
	        }
	        myWriter.close();
	        System.out.println("\nSuccessfully wrote to the file: " + fileName);
	        
	        if(fileName.contains("pub")) {
	        	System.out.println("e = " + key.toString() + "\nn = " + n.toString());
	        }
	        else {
	        	System.out.println("d = " + key.toString() + "\nn = " + n.toString());
	        }
	        
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	
	//Uses a String to name the new file
	public static void createFile(String fileName) {
	    try {
	        File myObj = new File(fileName);
	        if (myObj.createNewFile()) {
	          System.out.println("File created: " + myObj.getName());
	        } else {
	          System.out.println("File: \"" + myObj.getName() + "\" already exists." );
	        }
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
}
