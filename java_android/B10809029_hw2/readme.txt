data 直接拿 T09b.06 的 database 來修改。

AppExecutors 也直接拿來用

MainActivity 改寫 原 Waitlist 的 addToWaitlist，
用  diskIO 的 execute insert task.
onCreate 中 加 retrieveTasks() 將資料庫刷入。

剩下的大多也拿  T09b.06 的程式碼來改

TaskAdapter 也是將用不到的註解起來，改一些變數。
恩 ... 總體而言就是拿 T09b.06 的程式碼來閹割 (owo。