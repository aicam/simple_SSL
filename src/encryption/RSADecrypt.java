package encryption;

import java.math.BigInteger;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException; 

public class RSADecrypt {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		
		//User Input: test.enc pri_key.txt
		System.out.print("RSADecrypt> ");
	    String input = in.nextLine();  // Read user input
	    
	    interpret(input);
	}
	
	//Takes the user input and parses them
	public static void interpret(String input) {
		//Splits the input by the spaces
		String[] inputs = input.split(" ");
		
		RSADecrypt(inputs[0], inputs[1]);
	}
	
	//Method that does the decryption
	public static void RSADecrypt(String file, String pri_key) {
	    String privateKey = readFile(pri_key);
	    
	    //Splits the private key to get the data from it
	    String[] split = splitData(privateKey);
	    BigInteger d = new BigInteger(split[0]);
	    BigInteger n = new BigInteger(split[1]);
	    
	    String ciphertext = readFile(file);
	    
	    String[] cipherBlock = splitData(ciphertext);
	    BigInteger  num = new BigInteger(cipherBlock[0]);
	    
	    String block = decryptBlock(cipherBlock, d, n);
	    String plaintext = convertText(block);
	    
	    createFile("test.dec");
	    writeFile("test.dec", plaintext);
	}
	
	//Converts the String into letters
	public static String convertText(String block) {
		String plaintext = "";
	    for(int i = 0 ; i < block.length(); i = i + 2) {
	    	plaintext = plaintext + convertNumber(block.substring(i, i+2)); 
	    }
	    
	    return plaintext;
	}
	
	//Uses the Decryption algorithm to decrypt the block of bits
	public static String decryptBlock(String[] cipherBlock, BigInteger d, BigInteger n) {
		String block = "";
		for(int i = 0; i < cipherBlock.length; i++) {
			BigInteger num = new BigInteger(cipherBlock[i]);
			num = num.modPow(d, n);
			
			String blockText = num.toString();
			blockText = fill(blockText);
			block = block + blockText;
		}
		
		return block;
	}
	
	//Fills in the block with 0's in the front if the block is not 6 bytes long
	public static String fill(String blockText) {
		
		while(blockText.length() != 6) {
			blockText = "0" + blockText;
		}
		
		return blockText;
	}
	
	//Splits a String by the spaces
	public static String[] splitData(String data) {
		String[] split = data.split(" ");
		return split;
	}
	
	//Converts the number to the relevant character
	public static String convertNumber(String num) {
		String str = "";
		switch(num) {
			case "00": str = "A";
				break;
			case "01": str = "B";
				break;
			case "02": str = "C";
				break;
			case "03": str = "D";
				break;
			case "04": str = "E";
				break;
			case "05": str = "F";
				break;
			case "06": str = "G";
				break;
			case "07": str = "H";
				break;
			case "08": str = "I";
				break;
			case "09": str = "J";
				break;
			case "10": str = "K";
				break;
			case "11": str = "L";
				break;
			case "12": str = "M";
				break;
			case "13": str = "N";
				break;
			case "14": str = "O";
				break;
			case "15": str = "P";
				break;
			case "16": str = "Q";
				break;
			case "17": str = "R";
				break;
			case "18": str = "S";
				break;
			case "19": str = "T";
				break;
			case "20": str = "U";
				break;
			case "21": str = "V";
				break;
			case "22": str = "W";
				break;
			case "23": str = "X";
				break;
			case "24": str = "Y";
				break;
			case "25": str = "Z";
				break;
			default  : str = " ";
		}
		
		return str;
	}
	
	//Uses a String to name the new file
		public static void createFile(String fileName) {
		    try {
		        File myObj = new File(fileName);
		        if (myObj.createNewFile()) {
		          System.out.println("File created: " + myObj.getName());
		        } else {
		          System.out.println("File: \"" + myObj.getName() + "\" already exists.");
		        }
		      } catch (IOException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		      }
		}
		
		//Writes to an existing file
		public static void writeFile(String fileName, String plaintext) {
		    try {
		        FileWriter myWriter = new FileWriter(fileName);
		        myWriter.write(plaintext);
		        myWriter.close();
		        System.out.println("\nSuccessfully wrote to the file: " + fileName);
		        System.out.println(plaintext);
		      } catch (IOException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		      }
		}
		
		//Reads a file and converts the file into a String
		public static String readFile(String fileName) {
			String data = "";
		    try {
		        File myObj = new File(fileName);
		        Scanner myReader = new Scanner(myObj);
		        
		        //Checks for either pub_key or pri_key files
		        if(fileName.contains("key")) {
		        	
		        	//Grabs the numbers of the files and not the "x = "
			        while (myReader.hasNextLine()) {
			          String line = myReader.nextLine();
			          data = data + line.substring(4) + " ";
			        }
		        }
		        
		        //Any other files
		        else {
		        	
		        	//Reads the file and convert the data into Strings
			        while (myReader.hasNextLine()) {
				          String line = myReader.nextLine();
				          data = data + line;
				    }
		        }
		        myReader.close();
		      } catch (FileNotFoundException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		      }
		  
		  return data;
		}
}
