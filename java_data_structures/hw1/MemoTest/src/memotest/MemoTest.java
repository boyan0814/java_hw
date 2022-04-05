package memotest;

import java.util.Date;
import java.util.Random;

/**
 * 資料結構範例 - 定時通知
 * @author Bor-Shen Lin at National Taiwan University of Science and Technology.
 *******************************************************************************
 *	不輕易發怒的，勝過勇士；治服己心的，強如取城。 箴言16:32
 */
public class MemoTest {

	static Random random = new Random();
	static String verses[] = {
		"AAA", "BBB", "CCC", "DDD", "EEE"
	};
	static Memo[] memos;

	/**
	 * @param args the command line arguments
         * @throws java.lang.InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		initialize();
	}

	private static void initialize() throws InterruptedException {
		memos = new Memo[10];
		memos[0] = new Memo(new Time(7, 0), "Get up !");
		memos[1] = new Memo(new Time(8, 30), "Give me a verse", (Memo alarm) -> {
				System.out.println("Today's verse: " + verses[random.nextInt(verses.length)]);
		});
		memos[2] = new Memo(new Time(12, 00), "Lunch time ...");
		memos[3] = new Memo(new Time(17, 10), "Call my mother", new Notify() {
			@Override
			public void todo(Memo alarm) {
				System.out.println("Send a message to mother that I am going home");//print出message
			}
		});
		memos[4] = new Memo(new Time(17, 30), "Go home ~");
		memos[5] = new Memo(new Time(11, 10), "Go to sleep :)");
		
                
                
                memoService();
	}

	/**
	 * Check each alarm whether it is time up. If yes, notify me and set the
	 * notified flag to true
	 *
	 * @param now
	 */
	private static void checkForReset(Date now) {

            int hh = now.getHours(), mm = now.getMinutes(), ss = now.getSeconds();//取現在的hour，min
           
            if(hh == 23 && mm == 59 && ss == 59){//23:59啟動reset
            //if(true){//測試用
                   for (Memo memo : memos) {
                    	if (memo != null) {	
                            if (memo.notified){//如果有資料然後被啟動過就進reset
                                memo.reset();
                            }	
                    	}
                    }
            }
	}

	private static void memoService() throws InterruptedException {
            
		while (true) {
                    
			Date now = new Date();
                        System.out.println(now.getHours() + ":" + now.getMinutes()+ ":" + now.getSeconds()+ "/");//方便得知現在時間
			checkForReset(now);//丟現在時間到checkForReset()
			for (Memo memo : memos) {
				if (memo != null) {
                                        if(!memo.notified){
                                            if (!memo.notified && !memo.time.timeup(now)) { //
						memo.notified = true;
						memo.notify.todo(memo);
                                                //System.out.print(now.getHours() + "" + memo.time.hour + memo.time.timeup(now));
                                            }     
                                        }			
				}
			}
			Thread.sleep(1000);
		}        
	}

}
