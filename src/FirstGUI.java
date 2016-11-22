import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.*;

public class FirstGUI implements ActionListener {
	
	public static void main(String[] args) { 
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    JFrame demo = new JFrame("Windows Form");
		demo.setFont(new Font("新細明體", Font.BOLD, 20));
	    demo.setSize(500, 400);
	    demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    demo.getContentPane().setLayout(new GridLayout(1,1));
	    
	    // 第一個分頁，檔案切割
	    JPanel file_cut = new JPanel();
	    file_cut.setLayout(new GridLayout(7,1));

	    // 這個 file_path_panel 是要加入"匯入檔案之路徑" "顯示路徑位置" "瀏覽資料夾的 Button" "確定按鈕"
	    JPanel file_path_panel = new JPanel();
	    file_path_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    file_cut.add(file_path_panel);	    

	    JLabel file = new JLabel("檔案位置: ");
	    file.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel.add(file);
	    
	    JTextField text1 = new JTextField(15);
	    text1.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel.add(text1);
	    
	    Button FileChooser_cut = new Button("...");
	    FileChooser_cut.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel.add(FileChooser_cut);
	    
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
	    JPanel start_panel = new JPanel();
	    start_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    file_cut.add(start_panel);
	    Button start = new Button("資料庫切割");
	    start.setFont(new Font("新細明體", Font.BOLD, 20));
	    start_panel.add(start);
	    
	    // 按鈕：切割欄位資料加密
	    JPanel data_encrypt_panel = new JPanel();
	    data_encrypt_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    file_cut.add(data_encrypt_panel);
	    Button data_encrypt = new Button("切割欄位資料加密");
	    data_encrypt.setFont(new Font("新細明體", Font.BOLD, 20));
	    data_encrypt_panel.add(data_encrypt);
	    
	    // 加密進度條
	    JProgressBar progressbar = new JProgressBar();
	    progressbar.setOrientation(JProgressBar.HORIZONTAL);
	    progressbar.setMinimum(0);
	    progressbar.setMaximum(100);
	    progressbar.setValue(0);
	    progressbar.setStringPainted(true);
	    progressbar.setPreferredSize(new Dimension(200,20));
	    progressbar.setBorderPainted(true);
	    file_cut.add(progressbar);
	    
	    // 按鈕：資料庫加入序號
	    JPanel gg_panel = new JPanel();
	    gg_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    file_cut.add(gg_panel);
	    Button gg = new Button("資料庫加入序號");
	    gg.setFont(new Font("新細明體", Font.BOLD, 20));
	    gg_panel.add(gg);
	    
	    // 按鈕：資料庫合併
	    JPanel db_combine_panel = new JPanel();
	    db_combine_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    file_cut.add(db_combine_panel);
	    Button db_combine = new Button("資料庫合併");
	    db_combine.setFont(new Font("新細明體", Font.BOLD, 20));
	    db_combine_panel.add(db_combine);
	    
	    // 第二個分頁，資料加密
	    JPanel col_encrypt = new JPanel();
	    col_encrypt.setLayout(new GridLayout(5,1));
	    
	    // 這個 file_path_panel_2 是要加入"匯入檔案之路徑" "顯示路徑位置" "瀏覽資料夾的 Button"
	    JPanel file_path_panel_2 = new JPanel();
	    file_path_panel_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    col_encrypt.add(file_path_panel_2);

	    JLabel file2 = new JLabel("明文檔案位置: ");
	    file2.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel_2.add(file2);	    

	    JTextField text2 = new JTextField(15);
	    text2.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel_2.add(text2);    

	    Button FileChooser_encrypt = new Button("...");
	    FileChooser_encrypt.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel_2.add(FileChooser_encrypt);	 

	    // 這個 file_path_panel_2 是要加入"加密後檔案之路徑" "顯示路徑位置" "瀏覽資料夾的 Button"
	    JPanel file_path_panel_3 = new JPanel();
	    file_path_panel_3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    col_encrypt.add(file_path_panel_3);

	    JLabel file3 = new JLabel("密文儲存位置: ");
	    file3.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel_3.add(file3);	    

	    JTextField text3 = new JTextField(10);
	    text3.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel_3.add(text3);

	    JLabel cipher = new JLabel("/cipher.txt");
	    cipher.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel_3.add(cipher);

	    Button FileChooser_cipher = new Button("...");
	    FileChooser_cipher.setFont(new Font("新細明體", Font.BOLD, 20));
	    file_path_panel_3.add(FileChooser_cipher);

	    JCheckBox first_line = new JCheckBox("第一行不加密");
	    first_line.setFont(new Font("新細明體", Font.BOLD, 20));
	    col_encrypt.add(first_line);
	    // 加密進度條
	    JProgressBar progressbar_2 = new JProgressBar();
	    progressbar_2.setOrientation(JProgressBar.HORIZONTAL);
	    progressbar_2.setMinimum(0);
	    progressbar_2.setMaximum(100);
	    progressbar_2.setValue(0);
	    progressbar_2.setStringPainted(true);
	    progressbar_2.setPreferredSize(new Dimension(200,20));
	    progressbar_2.setBorderPainted(true);
	    col_encrypt.add(progressbar_2);

	    // 這個 Panel 加入"加密"的按扭
	    JPanel encrypt_last_panel = new JPanel();
	    encrypt_last_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    col_encrypt.add(encrypt_last_panel);

	    Button encrypt = new Button("加密");
	    encrypt.setFont(new Font("新細明體", Font.BOLD, 20));
	    encrypt_last_panel.add(encrypt);    
	    
	    JTabbedPane tp = new JTabbedPane();
	    tp.setFont(new Font("新細明體", Font.BOLD, 20));
	    tp.addTab("所有功能", file_cut);
	    tp.addTab("資料加密", col_encrypt);
	    demo.add(tp);
	     
	    // 第一個分頁動作
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
	    
	    // 第二分頁動作
	    FileChooser_encrypt.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	JFileChooser chooser = new JFileChooser();
	        	chooser.setMultiSelectionEnabled(false);
	        	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        	if (chooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	        		text2.setText(chooser.getSelectedFile().getAbsolutePath());
	        	}
	        	String inputfile = chooser.getSelectedFile().getAbsolutePath();
	        	
	        	FileChooser_cipher.addActionListener(new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	        	JFileChooser chooser2 = new JFileChooser();
	    	        	chooser2.setMultiSelectionEnabled(false);
	    	        	chooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	        	if (chooser2.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
	    	        		text3.setText(chooser2.getSelectedFile().getAbsolutePath());
	    	        	}
	    	        	String outputfile = chooser2.getSelectedFile().getAbsolutePath() + "/cipher.txt";
	    	        	System.out.println(outputfile);

	    	        	encrypt.addActionListener(new ActionListener() {
	    	        		public void actionPerformed(ActionEvent e){	
//	    	        			new Thread(){
//	    	        				public void run(){
//	    	        					ProgressMonitor monitor = new ProgressMonitor(null, "Encrypt...", "Encrypt...", 0, 100);
//	    	        				}
//	    	        			}.start();
//	    	        		}
	    	        		if (first_line.isSelected()){
    	    	        		AES.Encrypt_no_first_line(inputfile, outputfile);
	    	    	        	}
    	    	        	else 
    	    	        		AES.Encrypt_yes_first_line(inputfile, outputfile);
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
