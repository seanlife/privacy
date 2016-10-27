package onem.lyb.utils.common.tools.http;

public class HttpResult {
	
	/**
	 * 接口返回码
	 */
	private int code;
	
	/**
	 * 返回结果，json格式
	 */
	private String message;

	public HttpResult(){
		
	}

	public HttpResult(int code,String message){
		this.code=code;
		this.message=message;
	}

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
	
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
