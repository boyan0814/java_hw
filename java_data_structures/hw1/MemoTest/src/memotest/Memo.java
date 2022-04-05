package memotest;

/**
 * 資料結構範例 - 定時通知
 * @author Bor-Shen Lin at National Taiwan University of Science and Technology.
 *******************************************************************************
 *	吃素菜，彼此相愛，強如吃肥牛，彼此相恨。 箴言15:17
 */
public class Memo {

	Time time;
	boolean notified = false;
	String message;
	Notify notify;

	public Memo(Time time, String message) {
                this.time = time;//存入資料
                this.message = message;
		this.notify = (Memo alarm) -> {
			System.out.println("通知事項: " + alarm.message);
		};
	}

	public Memo(Time time, String message, Notify notify) {
		this.time = time;
		this.message = message;
		this.notify =  notify;
                this.notified = false;
	}
	public void reset() {
		if(this.notified) this.notified = false;
	}
	@Override
	public String toString() {
		return "Alarm:" + time + " " + message;
	}
}
