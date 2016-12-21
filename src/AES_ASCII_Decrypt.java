import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_ASCII_Decrypt {
	
	private static byte[] byte_return (String hex, int[] record) {
		if (hex == null) {
            return null;
        }
        int l = hex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }

        int[] int_origin = new int[b.length];
        for (int i = 0; i < b.length; i++){
        	if (record[i] == 0)
        		int_origin[i] = b[i] - 32;
        	else if (record[i] == 1)
        		int_origin[i] = (b[i] - 32) + 94;
        	else if (record[i] == 2)
        		int_origin[i] = (b[i] - 32) + 94 * 2;
        }

        byte[] byte_origin = new byte[int_origin.length];
        for (int i = 0; i < int_origin.length; i++) {
        	byte_origin[i] = (byte) int_origin[i];
        }

        return byte_origin;
	}
	
	private static String Decrypt(String cText, SecretKey sKey, IvParameterSpec iv, int[] record) throws Exception {
		SecretKeySpec sKeySpec = new SecretKeySpec(sKey.getEncoded(), "AES");
		Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
		byte[] encrypted1 = byte_return(cText, record);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original);

		return originalString;
	}
	
	private static String asciiToHex(String asciiValue) {
	    char[] chars = asciiValue.toCharArray();
	    StringBuffer hex = new StringBuffer();
	    for (int i = 0; i < chars.length; i++) {
	        hex.append(Integer.toHexString((int) chars[i]));
	    }
	    return hex.toString();
	}
	
	public static void Decrypt_yes_first_line(String input_cipher, String input_key, String input_iv, String input_record, String outputPath) throws Exception{
		String inputfile = input_cipher;
		String record = input_record;
		String get_key = input_key;
		String get_iv = input_iv;
		
		String outputfile = outputPath + "/plaintext_for_ascii.txt";
		
		String line_ciphertext = null;
		String line_record = null;
		
		FileReader fileReader_ciphertext = new FileReader(inputfile);
		BufferedReader bufferedReader_ciphertext = new BufferedReader(fileReader_ciphertext);
		
		FileReader fileReader_record = new FileReader(record);
		BufferedReader bufferedReader_record = new BufferedReader(fileReader_record);
		
		FileReader fileReader_key = new FileReader(get_key);
		BufferedReader bufferedReader_key = new BufferedReader(fileReader_key);
		String str_key = bufferedReader_key.readLine();
		byte[] decodedKey = Base64.getDecoder().decode(str_key);
		SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		
		FileReader fileReader_iv = new FileReader(get_iv);
		BufferedReader bufferedReader_iv = new BufferedReader(fileReader_iv);
		String str_iv = bufferedReader_iv.readLine();
		byte[] decodedIV = Base64.getDecoder().decode(str_iv);
		IvParameterSpec iv = new IvParameterSpec(decodedIV, 0, decodedIV.length);
		
		FileWriter fileWriter_decrypt = new FileWriter(outputfile);
		BufferedWriter bufferedWriter_decrypt = new BufferedWriter(fileWriter_decrypt);
		
		while ((line_ciphertext = bufferedReader_ciphertext.readLine()) != null && (line_record = bufferedReader_record.readLine()) != null){
			String[] strArray = line_record.split(",");
			int[] intArray = new int[strArray.length];
			for (int i = 0; i < strArray.length; i++){
				intArray[i] = Integer.parseInt(strArray[i]);
			}
			String hex = asciiToHex(line_ciphertext);
			String deString = Decrypt(hex, key, iv, intArray);
			bufferedWriter_decrypt.write(deString);
			bufferedWriter_decrypt.newLine();
		}
		bufferedReader_ciphertext.close();
		bufferedReader_record.close();
		bufferedWriter_decrypt.close();
		bufferedReader_key.close();
		bufferedReader_iv.close();
	}
	
	public static void Decrypt_no_first_line(String input_cipher, String input_key, String input_iv, String input_record, String outputPath) throws Exception{
		String inputfile = input_cipher;
		String record = input_record;
		String get_key = input_key;
		String get_iv = input_iv;
		
		String outputfile = outputPath + "/plaintext_for_ascii.txt";
		
		String line_ciphertext = null;
		String line_record = null;
		
		FileReader fileReader_ciphertext = new FileReader(inputfile);
		BufferedReader bufferedReader_ciphertext = new BufferedReader(fileReader_ciphertext);
		
		FileReader fileReader_record = new FileReader(record);
		BufferedReader bufferedReader_record = new BufferedReader(fileReader_record);
		
		FileReader fileReader_key = new FileReader(get_key);
		BufferedReader bufferedReader_key = new BufferedReader(fileReader_key);
		String str_key = bufferedReader_key.readLine();
		byte[] decodedKey = Base64.getDecoder().decode(str_key);
		SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		
		FileReader fileReader_iv = new FileReader(get_iv);
		BufferedReader bufferedReader_iv = new BufferedReader(fileReader_iv);
		String str_iv = bufferedReader_iv.readLine();
		byte[] decodedIV = Base64.getDecoder().decode(str_iv);
		IvParameterSpec iv = new IvParameterSpec(decodedIV, 0, decodedIV.length);
		
		FileWriter fileWriter_decrypt = new FileWriter(outputfile);
		BufferedWriter bufferedWriter_decrypt = new BufferedWriter(fileWriter_decrypt);
		
		bufferedWriter_decrypt.write(line_ciphertext = bufferedReader_ciphertext.readLine());
		bufferedWriter_decrypt.newLine();
		
		while ((line_ciphertext = bufferedReader_ciphertext.readLine()) != null && (line_record = bufferedReader_record.readLine()) != null){
			String[] strArray = line_record.split(",");
			int[] intArray = new int[strArray.length];
			for (int i = 0; i < strArray.length; i++){
				intArray[i] = Integer.parseInt(strArray[i]);
			}
			String hex = asciiToHex(line_ciphertext);
			String deString = Decrypt(hex, key, iv, intArray);
			bufferedWriter_decrypt.write(deString);
			bufferedWriter_decrypt.newLine();
		}
		bufferedReader_ciphertext.close();
		bufferedReader_record.close();
		bufferedWriter_decrypt.close();
		bufferedReader_key.close();
		bufferedReader_iv.close();
	}
}
