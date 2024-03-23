package encryption;

import java.math.BigInteger;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException; 

public class RSAEncrypt {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		
		//User Input: test.txt pub_key.txt
		System.out.print("RSAEncrypt> ");
	    String input = in.nextLine();  // Read user input
	    
	    interpret(input);
	}
	
	//Takes the user input and parses them
	public static void interpret(String input) {
		//Splits the input by the spaces
		String[] inputs = input.split(" ");
		
		RSAEncrypt(inputs[0], inputs[1]);
	}
	
	//Method that does the encryption
	public static void RSAEncrypt(String plaintext, String pub_key) {
	    String publicKey = readFile(pub_key);
		System.out.println("keys here " + publicKey);
	    //Splits the public key to get the data from it
	    String[] split = splitData(publicKey);
	    BigInteger e = new BigInteger(split[0]);
	    BigInteger n = new BigInteger(split[1]);

	    String[] blocks = createBlock(plaintext);
	    String ciphertext = encryptBlock(blocks, e, n);
	    createFile("test.enc");
	    writeFile("test.enc", ciphertext);
	}
	
	//Creates the blocks of memory cutting up the plaintext by 3 letters (Includes spaces)
	public static String[] createBlock(String plaintext) {
		String block = "";
		
		//Uppercase the plaintext for easier conversion
		plaintext = plaintext.toUpperCase();
		
		//Splits the plaintext by 3 characters
		for(int i = 0; i< plaintext.length(); i++) {
			
			//Every third character add a space
			if(i % 3 == 0 && i != 0) {
				block = block + " ";
			}
			
			//Converts current character into a String number
			//Adds String number into current String
			block = block  + convertCharacter(plaintext.charAt(i));
		}
		
		String[] blockArray = block.split(" ");
		
		if(blockArray[blockArray.length - 1].length() != blockArray[0].length()) {
			blockArray = fill(blockArray);
		}
		
		return blockArray;
	}
	
	//Does the encryption algorithm for a block of memory
	public static String encryptBlock(String[] blockArray, BigInteger e, BigInteger n) {
		String encrypt = "";
		
		for(int i = 0; i < blockArray.length; i++) {
			BigInteger num = new BigInteger(blockArray[i]);
			num = num.modPow(e, n);
			
			encrypt = encrypt + num.toString() + " ";
			
		}
		return encrypt;
	}
	
	//For a ciphertext that is not 6 bits in length
	//Fill in the ciphertext with 26 at the end
	public static String[] fill(String[] blockArray) {
		while(blockArray[blockArray.length - 1].length() != blockArray[0].length()) {
			blockArray[blockArray.length - 1] = blockArray[blockArray.length - 1] + "26";
		}
		
		return blockArray;
	}
	
	//Splits a String by the spaces
	public static String[] splitData(String data) {
		String[] split = data.split(" ");
		return split;
	}
	
	//Converts the character to the relevant number
	public static String convertCharacter(char c) {
		String number = "";
		switch(c) {
			case 'A': number = "00"; 
			  break;
			case 'B': number = "01"; 
			  break;
			case 'C': number = "02"; 
			  break;
			case 'D': number = "03"; 
			  break;
			case 'E': number = "04"; 
			  break;
			case 'F': number = "05"; 
			  break;
			case 'G': number = "06"; 
			  break;
			case 'H': number = "07";
			  break;
			case 'I': number = "08";
			  break;
			case 'J': number = "09";
			  break;
			case 'K': number = "10";
			  break;
			case 'L': number = "11";
			  break;
			case 'M': number = "12";
			  break;
			case 'N': number = "13";
			  break;
			case 'O': number = "14";
			  break;
			case 'P': number = "15";
			  break;
			case 'Q': number = "16";
			  break;
			case 'R': number = "17";
			  break;
			case 'S': number = "18";
			  break;
			case 'T': number = "19";
			  break;
			case 'U': number = "20";
			  break;
			case 'V': number = "21";
			  break;
			case 'W': number = "22";
			  break;
			case 'X': number = "23";
			  break;
			case 'Y': number = "24";
			  break;
			case 'Z': number = "25";
			  break;
			default:  number = "26";
		}
		
		return number;
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
	public static void writeFile(String fileName, String ciphertext) {
	    try {
	        FileWriter myWriter = new FileWriter(fileName);
	        myWriter.write(ciphertext);
	        myWriter.close();
	        System.out.println("\nSuccessfully wrote to the file: " + fileName);
	        System.out.println(ciphertext);
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
		          data = data + line;
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