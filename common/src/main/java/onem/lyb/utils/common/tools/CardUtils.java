package onem.lyb.utils.common.tools;

/**
 *
 * @deprecated  身份证,卡号，ID各种号码处理工具集
 *
 */
public class CardUtils {
	
	private final static char STAR = '*';
	
	/**
	 * 隐藏字符串, 隐藏部分使用*,参数不合法直接返回
	 * @param target 目标字符串
	 * @param start 前面显示位数
	 * @param end  尾部显示位数
	 */
	public static String hiddenString(String target, int start, int end){
        if (start<=target.length()-end){
            return target;
        }
		StringBuilder builder = new StringBuilder(target);
		int t = target.length() - end;
		for (int i = start; i < t; i++) {
			builder.setCharAt(i, STAR);
		}
		return builder.toString();
	}

    public static void main(String[] s){
        String ss="510322199306204758";
        System.out.println(hiddenString(ss,18,1));
    }

    /**
     * 隐藏字符串, 隐藏部分使用*,若隐藏的*小于count，补*。参数不合法直接返回
     * @param target 目标字符串
     * @param start 前面显示位数
     * @param end  尾部显示位数
     * @param count 填充的*个数
     */
    public static String hiddenStringAddStar(String target, int start, int end,int count) {
        if (start<=target.length()-end){
            return target;
        }
        StringBuilder builder = new StringBuilder(target);
        int t = target.length() - end;
        for (int i = start; i < t; i++) {
            builder.setCharAt(i, STAR);
        }
        if (t-start<count){
            int seam=count-(t-start);
            for (int j=0;j<seam;j++){
                builder.insert(start,STAR);
            }
        }
        return builder.toString();
    }

	
}
