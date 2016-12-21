import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_Hex_Encrypt {
	public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
	
	public static SecretKey key () throws Exception{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);		
		return keyGen.generateKey();
	}
	
	public static byte[] get_iv (){
		SecureRandom random = new SecureRandom();
		byte[] randBytes = new byte[16];
		random.nextBytes(randBytes);	
		return randBytes;
	}
	
	public static String Encrypt(String pText, SecretKey sKey, IvParameterSpec iv) throws Exception {
		
		// true secret key
		SecretKeySpec sKeySpec = new SecretKeySpec(sKey.getEncoded(), "AES");
		// encryption mode
		Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
		// Initial vector
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
		
		// encrypt
		byte[] encrypted = cipher.doFinal(pText.getBytes());

		return byte2hex(encrypted);
	}
	
	public static void Encrypt_no_first_line (String inputFile, String outputPath){
		String inputfile = inputFile;
		String output_key = outputPath + "/key.txt";
		String output_iv = outputPath + "/iv.txt";
		String outputfile = outputPath + "/cipher_for_hex.txt";
		String line = null;
		
    	try {
			FileReader fileReader = new FileReader(inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			FileWriter fileWriter = new FileWriter(outputfile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			SecretKey key = key();
			IvParameterSpec iv = new IvParameterSpec(get_iv());
			
			FileWriter fileWriter_key = new FileWriter(output_key);
			BufferedWriter bufferedWriter_key = new BufferedWriter(fileWriter_key);
			bufferedWriter_key.write(Base64.getEncoder().encodeToString(key.getEncoded()));
			
			FileWriter fileWriter_iv = new FileWriter(output_iv);
			BufferedWriter bufferedWriter_iv = new BufferedWriter(fileWriter_iv);
			bufferedWriter_iv.write(Base64.getEncoder().encodeToString(iv.getIV()));
			
			bufferedWriter.write(line = bufferedReader.readLine());
			bufferedWriter.newLine();
			
			while ((line = bufferedReader.readLine()) != null) {	
				String enString = Encrypt(line, key, iv);
				System.out.println(enString);
				
				bufferedWriter.write(enString);
				bufferedWriter.newLine();
			}
			bufferedReader.close();
			bufferedWriter.close();
			bufferedWriter_key.close();
			bufferedWriter_iv.close();
		}
		catch (FileNotFoundException ex){
			System.out.println("Unable to open file" + inputfile);
		}
		catch (IOException ex){
			System.out.println("Error reading file" + inputfile);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void Encrypt_yes_first_line (String inputFile, String outputPath){
		String inputfile = inputFile;
		String output_key = outputPath + "/key.txt";
		String output_iv = outputPath + "/iv.txt";
		String outputfile = outputPath + "/cipher_for_hex.txt";;
		String line = null;
		
    	try {
			FileReader fileReader = new FileReader(inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			FileWriter fileWriter = new FileWriter(outputfile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			SecretKey key = key();
			IvParameterSpec iv = new IvParameterSpec(get_iv());
			
			FileWriter fileWriter_key = new FileWriter(output_key);
			BufferedWriter bufferedWriter_key = new BufferedWriter(fileWriter_key);
			bufferedWriter_key.write(Base64.getEncoder().encodeToString(key.getEncoded()));
			
			FileWriter fileWriter_iv = new FileWriter(output_iv);
			BufferedWriter bufferedWriter_iv = new BufferedWriter(fileWriter_iv);
			bufferedWriter_iv.write(Base64.getEncoder().encodeToString(iv.getIV()));
			
			while ((line = bufferedReader.readLine()) != null) {	
				String enString = Encrypt(line, key, iv);
				System.out.println(enString);
				
				bufferedWriter.write(enString);
				bufferedWriter.newLine();
			}
			bufferedReader.close();
			bufferedWriter.close();
			bufferedWriter_key.close();
			bufferedWriter_iv.close();
		}
		catch (FileNotFoundException ex){
			System.out.println("Unable to open file" + inputfile);
		}
		catch (IOException ex){
			System.out.println("Error reading file" + inputfile);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}