import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_ASCII_Encrypt {
	
	private static SecretKey key () throws Exception{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);		
		
		return keyGen.generateKey();
	}
	
	private static byte[] iv (){
		SecureRandom random = new SecureRandom();
		byte[] randBytes = new byte[16];
		random.nextBytes(randBytes);
		//IvParameterSpec iv = new IvParameterSpec(randBytes);
		
		return randBytes;
	}
	
	private static byte[] Encrypt(String pText, SecretKey sKey, IvParameterSpec iv) throws Exception {
		SecretKeySpec sKeySpec = new SecretKeySpec(sKey.getEncoded(), "AES");
		Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
		byte[] encrypted = cipher.doFinal(pText.getBytes());

		return encrypted;
	}
	
	private static String byte_move (byte[] bytes){
		int move;
		String stmp = "";
		String result = "";
		for (int i = 0; i < bytes.length; i++) {
			move = ((bytes[i] & 0xff) % 94) + 32;
			stmp = (java.lang.Integer.toHexString(move & 0XFF));
			if (stmp.length() == 1) {
                result = result + "0" + stmp;
            } else {
                result = result + stmp;
            }
		}

		return result.toUpperCase();
	}
	
	private static String hexToASCII(String hexValue) {
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < hexValue.length(); i += 2){
			String str = hexValue.substring(i, i + 2);  
			int ten = Integer.parseInt(str, 16);
			output.append((char) ten);  
		}
		return output.toString();
	}
	
	public static void Encrypt_yes_first_line (String inputFile, String outputPath) throws Exception{
		String inputfile = inputFile;
		String outputfile = outputPath + "/cipher_for_ascii.txt";
		String output_key = outputPath + "/key.txt";
		String output_iv = outputPath + "/iv.txt";
		String record = outputPath + "/Record.txt";
		
		String line = null;
		
		SecretKey key = key();
		IvParameterSpec iv = new IvParameterSpec(iv());
		
		FileReader fileReader_plaintext = new FileReader(inputfile);
		BufferedReader bufferedReader_plaintext = new BufferedReader(fileReader_plaintext);
		
		FileWriter fileWriter_ciphertext = new FileWriter(outputfile);
		BufferedWriter bufferedWriter_ciphertext = new BufferedWriter(fileWriter_ciphertext);
		
		FileWriter fileWriter_key = new FileWriter(output_key);
		BufferedWriter bufferedWriter_key = new BufferedWriter(fileWriter_key);
		bufferedWriter_key.write(Base64.getEncoder().encodeToString(key.getEncoded()));
		
		FileWriter fileWriter_iv = new FileWriter(output_iv);
		BufferedWriter bufferedWriter_iv = new BufferedWriter(fileWriter_iv);
		bufferedWriter_iv.write(Base64.getEncoder().encodeToString(iv.getIV()));
		
		FileWriter fileWriter_record = new FileWriter(record);
		BufferedWriter bufferedWriter_record = new BufferedWriter(fileWriter_record);
		
		while ((line = bufferedReader_plaintext.readLine()) != null){
			
			byte[] encrypted_byte = Encrypt(line, key, iv); // 加密
			String shift = byte_move(encrypted_byte); // 做壓縮、以 hex 格式輸出密文
			String ascii = hexToASCII(shift); // 將密文格式轉成 ascii
			bufferedWriter_ciphertext.write(ascii); // 寫入密文
			bufferedWriter_ciphertext.newLine();
			
			// write record
			int[] record_int = new int[encrypted_byte.length];
			for (int i = 0; i < encrypted_byte.length; i++){
				record_int[i] = (encrypted_byte[i] & 0xff) / 94;
				bufferedWriter_record.write(Integer.toString(record_int[i]));
				bufferedWriter_record.write(",");
			}
			bufferedWriter_record.newLine();
		}
		bufferedReader_plaintext.close();
		bufferedWriter_ciphertext.close();
		bufferedWriter_key.close();
		bufferedWriter_iv.close();
		bufferedWriter_record.close();
	}
	
	public static void Encrypt_no_first_line(String inputFile, String outputPath) throws Exception{
		String inputfile = inputFile;
		String outputfile = outputPath + "/cipher_for_ascii.txt";
		String output_key = outputPath + "/key.txt";
		String output_iv = outputPath + "/iv.txt";
		String record = outputPath + "/Record.txt";
		
		String line = null;
		
		SecretKey key = key();
		IvParameterSpec iv = new IvParameterSpec(iv());
		
		FileReader fileReader_plaintext = new FileReader(inputfile);
		BufferedReader bufferedReader_plaintext = new BufferedReader(fileReader_plaintext);
		
		FileWriter fileWriter_ciphertext = new FileWriter(outputfile);
		BufferedWriter bufferedWriter_ciphertext = new BufferedWriter(fileWriter_ciphertext);
		
		FileWriter fileWriter_key = new FileWriter(output_key);
		BufferedWriter bufferedWriter_key = new BufferedWriter(fileWriter_key);
		bufferedWriter_key.write(Base64.getEncoder().encodeToString(key.getEncoded()));
		
		FileWriter fileWriter_iv = new FileWriter(output_iv);
		BufferedWriter bufferedWriter_iv = new BufferedWriter(fileWriter_iv);
		bufferedWriter_iv.write(Base64.getEncoder().encodeToString(iv.getIV()));
		
		FileWriter fileWriter_record = new FileWriter(record);
		BufferedWriter bufferedWriter_record = new BufferedWriter(fileWriter_record);
		
		bufferedWriter_ciphertext.write(line = bufferedReader_plaintext.readLine());
		bufferedWriter_ciphertext.newLine();
		
		while ((line = bufferedReader_plaintext.readLine()) != null){
			
			byte[] encrypted_byte = Encrypt(line, key, iv); // 加密
			String shift = byte_move(encrypted_byte); // 做壓縮、以 hex 格式輸出密文
			String ascii = hexToASCII(shift); // 將密文格式轉成 ascii
			bufferedWriter_ciphertext.write(ascii); // 寫入密文
			bufferedWriter_ciphertext.newLine();
			
			// write record
			int[] record_int = new int[encrypted_byte.length];
			for (int i = 0; i < encrypted_byte.length; i++){
				record_int[i] = (encrypted_byte[i] & 0xff) / 94;
				bufferedWriter_record.write(Integer.toString(record_int[i]));
				bufferedWriter_record.write(",");
			}
			bufferedWriter_record.newLine();
		}
		bufferedReader_plaintext.close();
		bufferedWriter_ciphertext.close();
		bufferedWriter_key.close();
		bufferedWriter_iv.close();
		bufferedWriter_record.close();
	}
}
