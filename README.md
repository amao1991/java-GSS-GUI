# java-GSS-GUI

實習時公司的作品。

<h2 id = "introduction">Introduction</h2>

主要功能大致上就三步驟：

1. 將資料庫中需要加密的特定欄位切割出來

2. 對欄位中的每一筆資料進行加密

3. 合併回原資料庫

另一個獨立出來的「資料加密」，就是對選取的檔案逐行做 AES 加密

「第一行不加密」的選項是因為有時候第一行是欄位名稱