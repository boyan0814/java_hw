package scores;

/**
 * 資料結構範例 - 排序/氣泡排序法
 *
 * @author Bor-Shen Lin at National Taiwan University of Science and Technology.
 * ******************************************************************************
 * 喜樂的心乃是良藥；憂傷的靈使骨枯乾。	箴17:22
 */
public class BubbleSort {
        @Override
	public String toString() {
		return "BubbleSort";
	}
	//@Override
	public void sort(String[][] x) {
		while (true) {
			boolean swapping = false;
			for (int i = 1; i < 500; i++) {
				if (Integer.parseInt(x[i][4]) > Integer.parseInt(x[i - 1][4])) {
					String[] arrtemp = x[i];
					x[i] = x[i - 1];
					x[i - 1] = arrtemp;
					swapping = true;
				}
                                
                                if((Integer.parseInt(x[i][4]) == Integer.parseInt(x[i - 1][4]))){
                                    boolean sameScoreChange = false;
                                    for(int j = 1; j < 4; j++){
                                        if(sameScoreChange == true)break;
                                        if(Integer.parseInt(x[i][j]) >= Integer.parseInt(x[i-1][j])){
                                            if(Integer.parseInt(x[i][j]) == Integer.parseInt(x[i-1][j]))continue;
                                            sameScoreChange = true;
                                        }else{
                                            break;
                                        }
                                    }                                    
                                    if(sameScoreChange == true){
                                        String[] arrtemp = x[i];
					x[i] = x[i - 1];
					x[i - 1] = arrtemp;
                                        swapping = true;
                                    }                                    
                                }                           
			}
			if (!swapping) {
				break;
			}
		}
	}
}
