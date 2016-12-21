import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

public class FirstGUI implements ActionListener {
	
	public static void main(String[] args) { 
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    JFrame demo = new JFrame("Windows Form");
		demo.setFont(new Font("新細明體", Font.BOLD, 20));
	    demo.setSize(700, 400);
	    demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    demo.getContentPane().setLayout(new GridLayout(1,1));
	    
	 // 第一個分頁，檔案切割
	    JPanel file_cut = new JPanel();
	    file_cut.setLayout(new GridLayout(6,1));

	    // 這個 file_path_panel 是要加入"匯入檔案之路徑" "顯示路徑位置" "瀏覽資料夾的 Button" "確定按鈕"
	    JPanel file_path_panel = new JPanel();
	    file_path_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    file_cut.add(file_path_panel);	    
	    // 檔案位置字樣
	    JLabel file = new JLabel("檔案位置: ");
	    file.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel.add(file);    
	    // 顯示路徑位置
	    JTextField text1 = new JTextField(15);
	    text1.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel.add(text1);    
	    // 瀏覽資料夾的 Button
	    Button FileChooser_cut = new Button("...");
	    FileChooser_cut.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel.add(FileChooser_cut); 
	    // 確定這是要切割的檔案
	    Button enter = new Button("確定");
	    enter.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel.add(enter);
	    
	    // 這個 Panel 是要在選擇檔案後，放入可以切割欄位的選項
	    JPanel panel_col = new JPanel();
	    panel_col.setLayout(new FlowLayout(FlowLayout.LEFT));
	    file_cut.add(panel_col);
	    // 新增下拉式選單
	    JMenu all_col = new JMenu("預切割欄位");
	    all_col.setFont(new Font("新細明體", Font.BOLD, 20));	    
	    JTextField select_col_text = new JTextField(5);
	    select_col_text.setFont(new Font("新細明體", Font.BOLD, 20));	    
	    JMenuBar menubar = new JMenuBar();
	    menubar.add(all_col);
	    panel_col.add(menubar);
	    panel_col.add(select_col_text);
	    
	    // 按鈕：資料庫切割
	    Button start = new Button("資料庫切割");
	    start.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_cut.add(start);
	    
	    // 按鈕：切割欄位資料加密
	    Button data_encrypt = new Button("切割欄位資料加密");
	    data_encrypt.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_cut.add(data_encrypt);

	    // 按鈕：資料庫加入序號
	    Button gg = new Button("資料庫加入序號");
	    gg.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_cut.add(gg);
	    
	    // 按鈕：資料庫合併
	    Button db_combine = new Button("資料庫合併");
	    db_combine.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_cut.add(db_combine);
	    
	 // 第二個分頁，普通的 AES 加密
	    JPanel col_encrypt = new JPanel();
	    col_encrypt.setLayout(new GridLayout(5,1));

	    JLabel explain_aes = new JLabel("說明：一般的 AES - 256 加密");
	    explain_aes.setFont(new Font("新細明體", Font.BOLD, 18));
	    explain_aes.setForeground(Color.gray);
	    col_encrypt.add(explain_aes);

	    // tp2_plaintext_panel "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp2_plaintext_panel = new JPanel();
	    tp2_plaintext_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    col_encrypt.add(tp2_plaintext_panel);

	    JLabel tp2_plaintext_file = new JLabel("Plaintext: ");
	    JTextField tp2_plaintext_text = new JTextField(15);
	    Button tp2_plaintext_button = new Button("...");

	    tp2_plaintext_file.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp2_plaintext_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp2_plaintext_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp2_plaintext_panel.add(tp2_plaintext_file);   
	    tp2_plaintext_panel.add(tp2_plaintext_text);
	    tp2_plaintext_panel.add(tp2_plaintext_button);	 

	    // tp2_ciphertext_panel "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp2_ciphertext_panel = new JPanel();
	    tp2_ciphertext_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    col_encrypt.add(tp2_ciphertext_panel);

	    JLabel tp2_ciphertext_file = new JLabel("Ciphertext: ");
	    JTextField tp2_ciphertext_text = new JTextField(10);
	    JLabel cipher_for_hex = new JLabel("/cipher_for_hex.txt");
	    Button tp2_ciphertext_button = new Button("...");

	    tp2_ciphertext_file.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp2_ciphertext_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    cipher_for_hex.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp2_ciphertext_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp2_ciphertext_panel.add(tp2_ciphertext_file);
	    tp2_ciphertext_panel.add(tp2_ciphertext_text);
	    tp2_ciphertext_panel.add(cipher_for_hex);
	    tp2_ciphertext_panel.add(tp2_ciphertext_button);

	    // 第一行是否加密
	    JCheckBox tp2_first_line = new JCheckBox("第一行不加密");
	    tp2_first_line.setFont(new Font("新細明體", Font.BOLD, 20));
	    col_encrypt.add(tp2_first_line);

	    // Encrypt
	    Button tp2_encrypt = new Button("Encrypt");
	    tp2_encrypt.setFont(new Font("新細明體", Font.BOLD, 20));
	    col_encrypt.add(tp2_encrypt);   
	    
	 // 第三個分頁，普通的 AES 解密
	    JPanel col_decrypt = new JPanel();
	    col_decrypt.setLayout(new GridLayout(7,1));

	    JLabel explain_aes_decrypt = new JLabel("說明：一般的 AES - 256 解密");
	    explain_aes_decrypt.setFont(new Font("新細明體", Font.BOLD, 18));
	    explain_aes_decrypt.setForeground(Color.gray);
	    col_decrypt.add(explain_aes_decrypt);

	    // tp3_ciphertext_panel "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp3_ciphertext_panel = new JPanel();
	    tp3_ciphertext_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    col_decrypt.add(tp3_ciphertext_panel);

	    JLabel tp3_ciphertext_file = new JLabel("Ciphertext: ");
	    JTextField tp3_ciphertext_text = new JTextField(15);
	    Button tp3_ciphertext_button = new Button("...");

	    tp3_ciphertext_file.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_ciphertext_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_ciphertext_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp3_ciphertext_panel.add(tp3_ciphertext_file);
	    tp3_ciphertext_panel.add(tp3_ciphertext_text);
	    tp3_ciphertext_panel.add(tp3_ciphertext_button);

	    // tp3_key_panel "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp3_key_panel = new JPanel();
	    tp3_key_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    col_decrypt.add(tp3_key_panel);

	    JLabel tp3_key_file = new JLabel("Key: ");
	    JTextField tp3_key_text = new JTextField(15);
	    Button tp3_key_button = new Button("...");

	    tp3_key_file.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_key_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_key_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp3_key_panel.add(tp3_key_file);
	    tp3_key_panel.add(tp3_key_text);
	    tp3_key_panel.add(tp3_key_button);

	    // tp3_iv_panel "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp3_iv_panel = new JPanel();
	    tp3_iv_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    col_decrypt.add(tp3_iv_panel);

	    JLabel tp3_iv_file = new JLabel("IV: ");
	    JTextField tp3_iv_text = new JTextField(15);
	    Button tp3_iv_button = new Button("...");

	    tp3_iv_file.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_iv_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_iv_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp3_iv_panel.add(tp3_iv_file);
	    tp3_iv_panel.add(tp3_iv_text);
	    tp3_iv_panel.add(tp3_iv_button);

	    // tp3_plaintext_panel "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp3_plaintext_panel = new JPanel();
	    tp3_plaintext_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    col_decrypt.add(tp3_plaintext_panel);

	    JLabel tp3_plaintext_file = new JLabel("Plaintext: ");
	    JTextField tp3_plaintext_text = new JTextField(15);
	    JLabel tp3_plaintext = new JLabel("/plaintext_for_hex.txt");
	    Button tp3_plaintext_button = new Button("...");

	    tp3_plaintext_file.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_plaintext_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_plaintext.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp3_plaintext_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp3_plaintext_panel.add(tp3_plaintext_file);
	    tp3_plaintext_panel.add(tp3_plaintext_text);
	    tp3_plaintext_panel.add(tp3_plaintext);
	    tp3_plaintext_panel.add(tp3_plaintext_button);

	    // 第一行是否解密
	    JCheckBox tp3_first_line_dec_or_not = new JCheckBox("第一行不解密");
	    tp3_first_line_dec_or_not.setFont(new Font("新細明體", Font.BOLD, 20));
	    col_decrypt.add(tp3_first_line_dec_or_not);

	    // Decrypt
	    Button tp3_dncrypt = new Button("Decrypt");
	    tp3_dncrypt.setFont(new Font("新細明體", Font.BOLD, 20));
	    col_decrypt.add(tp3_dncrypt);
	    
	 // 第四個分頁，AES-ASCII-Encrypt
	    JPanel aes_ascii_Encrypt = new JPanel();
	    aes_ascii_Encrypt.setLayout(new GridLayout(5,1));

	    JLabel explain_ascii = new JLabel("說明：AES 加密，以 ASCII 格式輸出且排除控制字元");
	    explain_ascii.setFont(new Font("新細明體", Font.BOLD, 18));
	    explain_ascii.setForeground(Color.gray);
	    aes_ascii_Encrypt.add(explain_ascii);

	    // tp4_plaintext_panel "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp4_plaintext_panel = new JPanel();
	    tp4_plaintext_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    aes_ascii_Encrypt.add(tp4_plaintext_panel);

	    JLabel tp4_plaintext_file = new JLabel("Plaintext: ");
	    JTextField tp4_plaintext_text = new JTextField(15);
	    Button tp4_plaintext_button = new Button("...");

	    tp4_plaintext_file.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp4_plaintext_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp4_plaintext_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp4_plaintext_panel.add(tp4_plaintext_file);	    
	    tp4_plaintext_panel.add(tp4_plaintext_text);    
	    tp4_plaintext_panel.add(tp4_plaintext_button);

	    // tp4_ciphertext_panel "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp4_ciphertext_panel = new JPanel();
	    tp4_ciphertext_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    aes_ascii_Encrypt.add(tp4_ciphertext_panel);

	    JLabel tp4_ciphertext_file = new JLabel("Ciphertext: ");
	    JTextField tp4_ciphertext_text = new JTextField(10);
	    JLabel tp4_cipher = new JLabel("/cipher_for_ascii.txt");
	    Button tp4_ciphertext_button = new Button("...");

	    tp4_ciphertext_file.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp4_ciphertext_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp4_cipher.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp4_ciphertext_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp4_ciphertext_panel.add(tp4_ciphertext_file);	    
	    tp4_ciphertext_panel.add(tp4_ciphertext_text);
	    tp4_ciphertext_panel.add(tp4_cipher);
	    tp4_ciphertext_panel.add(tp4_ciphertext_button);

	    // 第一行是否加密
	    JCheckBox tp4_first_line = new JCheckBox("第一行不加密");
	    tp4_first_line.setFont(new Font("新細明體", Font.BOLD, 20));
	    aes_ascii_Encrypt.add(tp4_first_line);

	    // Encrypt
	    Button tp4_encrypt = new Button("Encrypt");
	    tp4_encrypt.setFont(new Font("新細明體", Font.BOLD, 20));
	    aes_ascii_Encrypt.add(tp4_encrypt);
	    
	 // 第五個分頁，AES_ASCII_Decrypt
	    JPanel aes_ascii_Decrypt = new JPanel();
	    aes_ascii_Decrypt.setLayout(new GridLayout(8, 1));

	    JLabel explain_decrypt = new JLabel("說明：以 ASCII 排除控制字元進行 AES 解密");
	    explain_decrypt.setFont(new Font("新細明體", Font.BOLD, 18));
	    explain_decrypt.setForeground(Color.gray);
	    aes_ascii_Decrypt.add(explain_decrypt);

	    // ciphertext: "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp5_ciphertext_panel = new JPanel();
	    tp5_ciphertext_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    aes_ascii_Decrypt.add(tp5_ciphertext_panel);

	    JLabel tp5_ciphertext_label = new JLabel("Ciphertext: ");
	    JTextField tp5_ciphertext_text = new JTextField(15);
	    Button tp5_ciphertext_button = new Button("...");

	    tp5_ciphertext_label.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_ciphertext_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_ciphertext_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp5_ciphertext_panel.add(tp5_ciphertext_label);
	    tp5_ciphertext_panel.add(tp5_ciphertext_text);
	    tp5_ciphertext_panel.add(tp5_ciphertext_button);

	    // Key: "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp5_key_panel = new JPanel();
	    tp5_key_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    aes_ascii_Decrypt.add(tp5_key_panel);

	    JLabel tp5_key_label = new JLabel("Key: ");
	    JTextField tp5_key_text = new JTextField(15);
	    Button tp5_key_button = new Button("...");

	    tp5_key_label.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_key_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_key_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp5_key_panel.add(tp5_key_label);
	    tp5_key_panel.add(tp5_key_text);
	    tp5_key_panel.add(tp5_key_button);

	    // IV: "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp5_iv_panel = new JPanel();
	    tp5_iv_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    aes_ascii_Decrypt.add(tp5_iv_panel);

	    JLabel tp5_iv_label = new JLabel("IV: ");
	    JTextField tp5_iv_text = new JTextField(15);
	    Button tp5_iv_button = new Button("...");

	    tp5_iv_label.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_iv_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_iv_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp5_iv_panel.add(tp5_iv_label);
	    tp5_iv_panel.add(tp5_iv_text);
	    tp5_iv_panel.add(tp5_iv_button);

	    // Record: "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp5_record_panel = new JPanel();
	    tp5_record_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    aes_ascii_Decrypt.add(tp5_record_panel);

	    JLabel tp5_record_label = new JLabel("Record: ");
	    JTextField tp5_record_text = new JTextField(15);
	    Button tp5_record_button = new Button("...");

	    tp5_record_label.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_record_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_record_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp5_record_panel.add(tp5_record_label);
	    tp5_record_panel.add(tp5_record_text);
	    tp5_record_panel.add(tp5_record_button);

	    // Plaintext: "路徑、顯示路徑、瀏覽按鈕"
	    JPanel tp5_plaintext_panel = new JPanel();
	    tp5_plaintext_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    aes_ascii_Decrypt.add(tp5_plaintext_panel);

	    JLabel tp5_plaintext_label = new JLabel("Plaintext: ");
	    JTextField tp5_plaintext_text = new JTextField(15);
	    JLabel tp5_plaintext_label_2 = new JLabel("/plaintext_for_ascii.txt");
	    Button tp5_plaintext_button = new Button("...");

	    tp5_plaintext_label.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_plaintext_text.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_plaintext_label_2.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp5_plaintext_button.setFont(new Font("新細明體", Font.BOLD, 20));

	    tp5_plaintext_panel.add(tp5_plaintext_label);
	    tp5_plaintext_panel.add(tp5_plaintext_text);
	    tp5_plaintext_panel.add(tp5_plaintext_label_2);
	    tp5_plaintext_panel.add(tp5_plaintext_button);

	    // first line or not
	    JCheckBox tp5_first_line_dec_or_not = new JCheckBox("第一行不解密");
	    tp5_first_line_dec_or_not.setFont(new Font("新細明體", Font.BOLD, 20));
	    aes_ascii_Decrypt.add(tp5_first_line_dec_or_not);

	    // Decrypt
	    Button ascii_Decrypt = new Button("Decrypt");
	    ascii_Decrypt.setFont(new Font("新細明體", Font.BOLD, 20));
	    aes_ascii_Decrypt.add(ascii_Decrypt);
	    
	    JTabbedPane tp = new JTabbedPane();
	    tp.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp.addTab("資料庫切割加密", file_cut);
	    tp.addTab("AES-Hex-Encrypt", col_encrypt);
	    tp.addTab("AES-Hex-Decrypt", col_decrypt);
	    tp.addTab("AES-ASCII-Encrypt", aes_ascii_Encrypt);
	    tp.addTab("AES-ASCII-Decrypt", aes_ascii_Decrypt);
	    demo.add(tp);
	    
//	    demo.add(file_cut);
 
	 // 第一個分頁動作，DMexpress
	    // 讀取原始資料庫之檔案路徑
	    FileChooser_cut.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	JFileChooser chooser = new JFileChooser();
	        	chooser.setMultiSelectionEnabled(false);
	        	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        	if (chooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	        		text1.setText(chooser.getSelectedFile().getAbsolutePath());
	        	}
	        	
	        	// 按下確定之後才真正讀取所選之檔案內容
	        	enter.addActionListener(new ActionListener() {
	        		@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e){
	    	        	FileReader fileReader;
	    	        	FileWriter fileWriter_all; // 寫入"所有欄位"之檔案
	    	        	FileWriter fileWriter_bat; // 寫入切割欄位的批次檔
	    	        	JRadioButtonMenuItem[] item;
	    	        	ButtonGroup bg = new ButtonGroup();
	    	        	
	    				try {
	    					fileReader = new FileReader(chooser.getSelectedFile());
	    					BufferedReader bufferedReader = new BufferedReader(fileReader);
	    					
	    					// 寫入批次檔
	    					fileWriter_bat = new FileWriter("C:/Users/Mao/Desktop/GSS-WindowsForm/1_SegmentNo.bat");
	    					BufferedWriter bufferWriter_bat = new BufferedWriter(fileWriter_bat);
	    					bufferWriter_bat.write("@echo off");
	    					bufferWriter_bat.newLine();
	    					bufferWriter_bat.write("rem 所有欄位設定於2_all_col.txt");
	    					bufferWriter_bat.newLine();
	    					bufferWriter_bat.write("set SourceFile=" + chooser.getSelectedFile().getAbsolutePath());
	    					bufferWriter_bat.newLine();
	    					bufferWriter_bat.write("rem 切割欄位設定於3_select_col.txt");
	    					bufferWriter_bat.newLine();
	    					bufferWriter_bat.write("set SegmentFile=C:/Users/Mao/Desktop/GSS-WindowsForm/5_select_db.txt");
	    					bufferWriter_bat.newLine();
	    					bufferWriter_bat.write("set SegmentFile_2=C:/Users/Mao/Desktop/GSS-WindowsForm/6_remain_db.txt");
	    					bufferWriter_bat.newLine();
	    					bufferWriter_bat.write("dmxjob /RUN C:/Users/Mao/Desktop/GSS-WindowsForm/Segment.dxj 2>Segment2log.txt");
	    					bufferWriter_bat.newLine();
	    					
	    					// 選擇要切割的欄位
	    					fileWriter_all = new FileWriter("C:/Users/Mao/Desktop/GSS-WindowsForm/2_all_col.txt");
	    					BufferedWriter bufferWriter_all = new BufferedWriter(fileWriter_all);
	    					
	    		  	    	String line = bufferedReader.readLine();  // line = 第一行 = 欄位名稱
	    		  	    	
	    		  	    	// 直接將第一行(欄位名稱)寫入"所有欄位"之檔案
	    		  	    	bufferWriter_all.write("/delimitedrecordlayout AllList{");
	    		  	    	bufferWriter_all.write(line); 
	    		  	    	bufferWriter_all.write("}");
	    		  	    	bufferWriter_all.flush();
	    		  	    	bufferWriter_all.close();

	    		  	    	String[] splitted_line = line.split(",");  // 用逗號切割字串
	    		  	    	item = new JRadioButtonMenuItem[splitted_line.length]; // 轉成陣列方式處理
	    		  	    	
	    		  	    	for (int i = 0; i < splitted_line.length; i++){
	    		  	    		item[i] = new JRadioButtonMenuItem(splitted_line[i]);
	    		  	    		item[i].setFont(new Font("新細明體", Font.BOLD, 20));
	    		  	    		item[i].setActionCommand(item[i].getLabel());
	    		  	    		all_col.add(item[i]);
	    		  	    		bg.add(item[i]);
	    		  	    	}	    	
	    		  	    	
//	    		  	    	all_col.addActionListener(new ActionListener() {
//	    		        		public void actionPerformed(ActionEvent e){
//	    		        			bg.getSelection().addActionListener(new ActionListener() {
//	    	    		        		public void actionPerformed(ActionEvent e){
//	    	    		        			select_col_text.setText(bg.getSelection().getActionCommand());
//	    	    		        			System.out.println("hi");
//	    	    		        		}
//	    	    		  	    	});	
//	    		        		}
//	    		  	    	});

	    		  	    	// 按下開始切割後，寫入要切割之欄位(.txt)、剩下的欄位(.txt)，並開始切割
	    		  	    	start.addActionListener(new ActionListener() {
	    		        		public void actionPerformed(ActionEvent e){
	    		        			String select_col = bg.getSelection().getActionCommand();
	    		        			select_col_text.setText(select_col);
	    		        			FileWriter fileWriter_select_col; 
	    		        			FileWriter fileWriter_remain_col;
	    		        			FileWriter fileWriter_select_col_No;
	    		        			try {
	    		        				// 寫入要切割之欄位
	    		        				fileWriter_select_col = new FileWriter("C:/Users/Mao/Desktop/GSS-WindowsForm/3_select_col.txt");
										BufferedWriter bufferWriter_select_col = new BufferedWriter(fileWriter_select_col);
										bufferWriter_select_col.write("/delimitedrecordlayout SelectList{");
										bufferWriter_select_col.write(select_col); 
										bufferWriter_select_col.write("}");
										bufferWriter_select_col.flush();
										bufferWriter_select_col.close();
										
										// 寫入要切割欄位加上序號變數(合併時使用)
										fileWriter_select_col_No = new FileWriter("C:/Users/Mao/Desktop/GSS-WindowsForm/8_select_col_No.txt");
										BufferedWriter bufferWriter_select_col_No = new BufferedWriter(fileWriter_select_col_No);
										bufferWriter_select_col_No.write("/delimitedrecordlayout SelectList_No{");
										bufferWriter_select_col_No.write(select_col);
										bufferWriter_select_col_No.write(",incrementCounter}");
										bufferWriter_select_col_No.flush();
										bufferWriter_select_col_No.close();
										
										// 寫入剩下的欄位，並加入序號變數
										fileWriter_remain_col = new FileWriter("C:/Users/Mao/Desktop/GSS-WindowsForm/4_remain_col_No.txt");
										BufferedWriter bufferWriter_remain_col = new BufferedWriter(fileWriter_remain_col);
										bufferWriter_remain_col.write("/delimitedrecordlayout SelectList_2{");
										for (int i = 0; i < splitted_line.length; i++){
											if (splitted_line[i] != select_col) {
												bufferWriter_remain_col.write(splitted_line[i]);
												bufferWriter_remain_col.write(",");
											}
										}
										bufferWriter_remain_col.write("incrementCounter");
										bufferWriter_remain_col.write("}");
										bufferWriter_remain_col.flush();
										bufferWriter_remain_col.close();
										
										// 執行 dmexpress
										Runtime.getRuntime().exec("C:/Users/Mao/Desktop/GSS-WindowsForm/1_SegmentNo.bat");
									
										bufferWriter_select_col.close();
										bufferWriter_remain_col.close();
	    		        			} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
	
	    		        			data_encrypt.addActionListener(new ActionListener() {
	    	    		        		public void actionPerformed(ActionEvent e){
    	    		        					try{
    	    	    								Runtime.getRuntime().exec("C:/Users/Mao/Desktop/GSS-WindowsForm/AES_for_DMExpress.bat");
    	    	    								
        	    		        				} catch (IOException e1) {
    	    	    								// TODO Auto-generated catch block
    	    	    								e1.printStackTrace();
    	    	    							}

	    	    		        			// 加密後的資料，用 DMExpress 加上序號
	    	    		        			gg.addActionListener(new ActionListener() {
	    	    	    		        		public void actionPerformed(ActionEvent e){
	    	    	    		        			try{
			    	    								Runtime.getRuntime().exec("C:/Users/Mao/Desktop/GSS-WindowsForm/2_AddNumber.bat");
		    	    								} catch (IOException e1) {
			    	    								// TODO Auto-generated catch block
			    	    								e1.printStackTrace();
			    	    							}
	    	    	    		        			
	    	    	    		        			db_combine.addActionListener(new ActionListener() {
	    	    	    	    		        		public void actionPerformed(ActionEvent e){
	    	    	    	    		        			try {
	    	    	    										Runtime.getRuntime().exec("C:/Users/Mao/Desktop/GSS-WindowsForm/3_Merge.bat");
	    	    	    									} catch (IOException e1) {
	    	    	    										// TODO Auto-generated catch block
	    	    	    										e1.printStackTrace();
	    	    	    									}
	    	    	    	    		        		}
	    	    	    		        			});
	    	    	    		        		}
	    	    		        			});
	    	    		        		}
	    		        			});
	    		        		}
	    		  	    	});
 		  	    	
	    		  	    	bufferedReader.close();
	    		  	    	bufferWriter_bat.close();
	    		  	    	bufferWriter_all.close();

	    				} catch (FileNotFoundException e1) {
	    					// TODO Auto-generated catch block
	    					e1.printStackTrace();
	    				} catch (IOException e1) {
	    					// TODO Auto-generated catch block
	    					e1.printStackTrace();
	    				}	
	        		}
	        	});
	        }

			private Component getContentPane() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	    
	 // 第二分頁動作，AES Hex Encrypt
	    tp2_plaintext_button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	JFileChooser chooser = new JFileChooser();
	        	chooser.setMultiSelectionEnabled(false);
	        	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        	if (chooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	        		tp2_plaintext_text.setText(chooser.getSelectedFile().getAbsolutePath());
	        	}
	        	String inputfile = chooser.getSelectedFile().getAbsolutePath();
	        	
	        	tp2_ciphertext_button.addActionListener(new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	        	JFileChooser chooser2 = new JFileChooser();
	    	        	chooser2.setMultiSelectionEnabled(false);
	    	        	chooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	        	if (chooser2.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	        		tp2_ciphertext_text.setText(chooser2.getSelectedFile().getAbsolutePath());
	    	        	}
	    	        	String outputfile = chooser2.getSelectedFile().getAbsolutePath();

	    	        	tp2_encrypt.addActionListener(new ActionListener() {
	    	        		public void actionPerformed(ActionEvent e){	
		    	        		if (tp2_first_line.isSelected()){
	    	    	        		AES_Hex_Encrypt.Encrypt_no_first_line(inputfile, outputfile);
		    	    	        	}
	    	    	        	else 
	    	    	        		AES_Hex_Encrypt.Encrypt_yes_first_line(inputfile, outputfile);
	    	        		}
	    	        	});    
	    	        }
	        	});
	        }

			private Component getContentPane() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	    
	 // 第三分頁動作，AES Hex Decrypt
	    tp3_ciphertext_button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JFileChooser chooser_1 = new JFileChooser();
	    		chooser_1.setMultiSelectionEnabled(false);
	    		chooser_1.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    		if (chooser_1.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	        		tp3_ciphertext_text.setText(chooser_1.getSelectedFile().getAbsolutePath());
	        	}
	    		String input_cipher = chooser_1.getSelectedFile().getAbsolutePath();
	    		
	    		tp3_key_button.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent e) {
	    	    		JFileChooser chooser_2 = new JFileChooser();
	    	    		chooser_2.setMultiSelectionEnabled(false);
	    	    		chooser_2.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    	    		if (chooser_2.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	        		tp3_key_text.setText(chooser_2.getSelectedFile().getAbsolutePath());
	    	        	}
	    	    		String input_key = chooser_2.getSelectedFile().getAbsolutePath();
	    	    		
	    	    		tp3_iv_button.addActionListener(new ActionListener() {
	    	    	    	public void actionPerformed(ActionEvent e) {
	    	    	    		JFileChooser chooser_3 = new JFileChooser();
	    	    	    		chooser_3.setMultiSelectionEnabled(false);
	    	    	    		chooser_3.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    	    	    		if (chooser_3.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	    	        		tp3_iv_text.setText(chooser_3.getSelectedFile().getAbsolutePath());
	    	    	        	}
	    	    	    		String input_iv = chooser_3.getSelectedFile().getAbsolutePath();
	    	    	    		
	    	    	    		tp3_plaintext_button.addActionListener(new ActionListener() {
	    	    	    	    	public void actionPerformed(ActionEvent e) {
	    	    	    	    		JFileChooser chooser_4 = new JFileChooser();
	    	    	    	    		chooser_4.setMultiSelectionEnabled(false);
	    	    	    	    		chooser_4.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	    	    	    		if (chooser_4.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	    	    	        		tp3_plaintext_text.setText(chooser_4.getSelectedFile().getAbsolutePath());
	    	    	    	        	}
	    	    	    	    		String outputPath = chooser_4.getSelectedFile().getAbsolutePath();
	    	    	    	    		
	    	    	    	    		tp3_dncrypt.addActionListener(new ActionListener() {
	    	    	    	    	    	public void actionPerformed(ActionEvent e) {
	    	    	    	    	    		if (tp3_first_line_dec_or_not.isSelected()){
	    	    	    	    	        		try {
	    	    										AES_Hex_Decrypt.Decrypt_no_first_line(input_cipher, input_key, input_iv, outputPath);
	    	    									} catch (Exception e1) {
	    	    										// TODO Auto-generated catch block
	    	    										e1.printStackTrace();
	    	    									}
	    	    		    	    	        	} 
	    	    	    	    	        	else
	    	    									try {
	    	    										AES_Hex_Decrypt.Decrypt_yes_first_line(input_cipher, input_key, input_iv, outputPath);
	    	    									} catch (Exception e1) {
	    	    										// TODO Auto-generated catch block
	    	    										e1.printStackTrace();
	    	    									}
	    	    	    	    	    	}
	    	    	    	    		});
	    	    	    	    	}
	    	    	    		});
	    	    	    	}
	    	    		});
	    	    	}
	    		});
	    	}

			private Component getContentPane() {
				// TODO Auto-generated method stub
				return null;
			}
	    });
	    
	 // 第四分頁動作，AES ASCII Encrypt
	    tp4_plaintext_button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JFileChooser chooser_1 = new JFileChooser();
	    		chooser_1.setMultiSelectionEnabled(false);
	    		chooser_1.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        	if (chooser_1.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	        		tp4_plaintext_text.setText(chooser_1.getSelectedFile().getAbsolutePath());
	        	}
	        	String inputfile = chooser_1.getSelectedFile().getAbsolutePath();
	        	
	        	tp4_ciphertext_button.addActionListener(new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	        	JFileChooser chooser2 = new JFileChooser();
	    	        	chooser2.setMultiSelectionEnabled(false);
	    	        	chooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	        	if (chooser2.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	        		tp4_ciphertext_text.setText(chooser2.getSelectedFile().getAbsolutePath());
	    	        	}
	    	        	String outputfile = chooser2.getSelectedFile().getAbsolutePath();
	    	        	
	    	        	tp4_encrypt.addActionListener(new ActionListener() {
	    	    	        public void actionPerformed(ActionEvent e) {
	    	    	        	if (tp4_first_line.isSelected()){
	    	    	        		try {
										AES_ASCII_Encrypt.Encrypt_no_first_line(inputfile, outputfile);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
		    	    	        	} 
	    	    	        	else
									try {
										AES_ASCII_Encrypt.Encrypt_yes_first_line(inputfile, outputfile);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
	    	    	        }
	    	        	});
	    	        }
	        	});
	        	
	        	
	    	}

			private Component getContentPane() {
				// TODO Auto-generated method stub
				return null;
			}
	    });
	    
	 // 第五分頁動作，AES ASCII Decrypt
	    tp5_ciphertext_button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JFileChooser chooser_1 = new JFileChooser();
	    		chooser_1.setMultiSelectionEnabled(false);
	    		chooser_1.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        	if (chooser_1.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	        		tp5_ciphertext_text.setText(chooser_1.getSelectedFile().getAbsolutePath());
	        	}
	        	String input_cipher = chooser_1.getSelectedFile().getAbsolutePath();
	        	
	        	tp5_key_button.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent e) {
	    	    		JFileChooser chooser_2 = new JFileChooser();
	    	    		chooser_2.setMultiSelectionEnabled(false);
	    	    		chooser_2.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    	        	if (chooser_2.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	        		tp5_key_text.setText(chooser_2.getSelectedFile().getAbsolutePath());
	    	        	}
	    	        	String input_key = chooser_2.getSelectedFile().getAbsolutePath();
	    	        	
	    	        	tp5_iv_button.addActionListener(new ActionListener() {
	    	    	    	public void actionPerformed(ActionEvent e) {
	    	    	    		JFileChooser chooser_3 = new JFileChooser();
	    	    	    		chooser_3.setMultiSelectionEnabled(false);
	    	    	    		chooser_3.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    	    	        	if (chooser_3.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	    	        		tp5_iv_text.setText(chooser_3.getSelectedFile().getAbsolutePath());
	    	    	        	}
	    	    	        	String input_iv = chooser_3.getSelectedFile().getAbsolutePath();
	    	    	        	
	    	    	        	tp5_record_button.addActionListener(new ActionListener() {
	    	    	    	    	public void actionPerformed(ActionEvent e) {
	    	    	    	    		JFileChooser chooser_4 = new JFileChooser();
	    	    	    	    		chooser_4.setMultiSelectionEnabled(false);
	    	    	    	    		chooser_4.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    	    	    	        	if (chooser_4.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	    	    	        		tp5_record_text.setText(chooser_4.getSelectedFile().getAbsolutePath());
	    	    	    	        	}
	    	    	    	        	String input_record = chooser_4.getSelectedFile().getAbsolutePath();
	    	    	    	        	
	    	    	    	        	tp5_plaintext_button.addActionListener(new ActionListener() {
	    	    	    	    	    	public void actionPerformed(ActionEvent e) {
	    	    	    	    	    		JFileChooser chooser_5 = new JFileChooser();
	    	    	    	    	    		chooser_5.setMultiSelectionEnabled(false);
	    	    	    	    	    		chooser_5.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	    	    	    	        	if (chooser_5.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	    	    	    	        		tp5_plaintext_text.setText(chooser_5.getSelectedFile().getAbsolutePath());
	    	    	    	    	        	}
	    	    	    	    	        	String outputPath = chooser_5.getSelectedFile().getAbsolutePath();
	    	    	    	    	        	
	    	    	    	    	        	ascii_Decrypt.addActionListener(new ActionListener() {
	    	    	    	    	    	        public void actionPerformed(ActionEvent e) {
	    	    	    	    	    	        	if (tp5_first_line_dec_or_not.isSelected()){
    	    	    										try {
																AES_ASCII_Decrypt.Decrypt_no_first_line(input_cipher, input_key, input_iv, input_record, outputPath);
															} 
    	    	    										catch (Exception e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
	    	    	    								} 
	    	    	    	    	    	        	else
															try {
																AES_ASCII_Decrypt.Decrypt_yes_first_line(input_cipher, input_key, input_iv, input_record, outputPath);
															} catch (Exception e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
	    	    	    	    	    	        }
	    	    	    	    	        	});
	    	    	    	    	    	}
	    	    	    	        	});
	    	    	    	    	}
	    	    	        	});
	    	    	    	}
	    	        	});
	    	    	}
	        	});
	    	}

			private Component getContentPane() {
				// TODO Auto-generated method stub
				return null;
			}
	    });

	    demo.setVisible(true);
	    
	    demo.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
