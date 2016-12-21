import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_Hex_Decrypt {
	
	private static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }
	
	private static String Decrypt(String cText, SecretKey sKey, IvParameterSpec iv) throws Exception {
		try {
			
			SecretKeySpec sKeySpec = new SecretKeySpec(sKey.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
			byte[] encrypted1 = hex2byte(cText);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			}
			catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	
	public static void Decrypt_yes_first_line (String input_cipher, String input_key, String input_iv, String outputPath) throws Exception {
		String inputfile = input_cipher;
		String get_key = input_key;
		String get_iv = input_iv;
		
		String outputfile = outputPath + "/plaintext_for_hex.txt";
		
		String line = null;
		
		FileReader fileReader_ciphertext = new FileReader(inputfile);
		BufferedReader bufferedReader_ciphertext = new BufferedReader(fileReader_ciphertext);
		
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
		
		while ((line = bufferedReader_ciphertext.readLine()) != null){
			String deString = Decrypt(line, key, iv);
			
			bufferedWriter_decrypt.write(deString);
			bufferedWriter_decrypt.newLine();
		}
		bufferedReader_ciphertext.close();
		bufferedWriter_decrypt.close();
		bufferedReader_key.close();
		bufferedReader_iv.close();
	}
	
	public static void Decrypt_no_first_line (String input_cipher, String input_key, String input_iv, String outputPath) throws Exception {
		String inputfile = input_cipher;
		String get_key = input_key;
		String get_iv = input_iv;
		
		String outputfile = outputPath + "/plaintext_for_hex.txt";
		
		String line = null;
		
		FileReader fileReader_ciphertext = new FileReader(inputfile);
		BufferedReader bufferedReader_ciphertext = new BufferedReader(fileReader_ciphertext);
		
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
		
		bufferedWriter_decrypt.write(line = bufferedReader_ciphertext.readLine());
		bufferedWriter_decrypt.newLine();
		
		while ((line = bufferedReader_ciphertext.readLine()) != null){
			String deString = Decrypt(line, key, iv);
			
			bufferedWriter_decrypt.write(deString);
			bufferedWriter_decrypt.newLine();
		}
		bufferedReader_ciphertext.close();
		bufferedWriter_decrypt.close();
		bufferedReader_key.close();
		bufferedReader_iv.close();
	}
}
