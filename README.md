# java-GSS-GUI

第一次寫 windows form，沒什麼，就是外觀醜 code 又亂，也沒什麼厲害的功能。

但基於是第一個從零開始完全出自於我手的東西，我還是要愛她的。

<h2 id = "introduction">Introduction</h2>

主要功能大致上就三步驟：

1. 將資料庫中需要加密的特定欄位切割出來

2. 對欄位中的每一筆資料進行加密

3. 合併回原資料庫

另一個獨立出來的「資料加密」，就是對選取的檔案逐行做 AES 加密

「第一行不加密」的選項是因為有時候第一行是欄位名稱

<h2 id = "feature">Feature</h2>

![feature1](http://imgur.com/6tD08au)

![feature2](http://imgur.com/MIrAC9J)