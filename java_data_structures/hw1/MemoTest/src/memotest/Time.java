
package memotest;

import java.util.Date;

/**
 * 資料結構範例 - 定時通知
 * @author Bor-Shen Lin at National Taiwan University of Science and Technology.
 *******************************************************************************
 *	棄絕管教的，輕看自己的生命；聽從責備的，卻得智慧。 箴言15:32
 */
public class Time {

	int hour, min, sec;
        
        public Time() {//如果沒匯入資料給預設參數
		this(0, 0, 0);
	}
        
	public Time(int hh, int mm) {
		this(hh, mm, 0);
	}

	public Time(int hh, int mm, int ss) {
		hour = hh;
		min = mm;
		sec = ss;
	}
	public boolean timeup(Date now) {
		int hh = now.getHours(), mm = now.getMinutes(), ss = now.getSeconds();
                //重寫判斷，效能會低一些但較直觀
		if(hh == this.hour && mm == this.min && ss == this.sec){
                    return false;
                }else{
                    return true;
                }
		
	}
	public String toString() {
		return String.format("%02d:%02d:%02d", hour, min, sec);
	}
}
