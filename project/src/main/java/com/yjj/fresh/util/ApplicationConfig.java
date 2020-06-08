package com.yjj.fresh.util;

public class ApplicationConfig {
	    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
		public static String app_id = "2016102400748299";
	 
		// 商户私钥，您的PKCS8格式RSA2私钥
		public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDTcUPbyeVtd/a7mgtC/cs1QhvXDo8BJM6ijym8d01n73AS9I3Sj83TeHSsl+iq3c/XgJUY7soP6pvDxsihz2ziwA7wuWRd+gAEAIdUjlSNjmIHtKQfuq59mkfHI71N6NOifFPfZBWjK/hOZ9kTygGRlT9VA/pq4EUHzjZGSyyp6PQmTMGW/Ct9VHpxIdD6Uz+cbPGz1wj32W7cSZUfxkNOUK62iwOm0jNRgRsgJngdlrH6dqBKg+KSGo2TZuzuN3mo36KvVPtuOIFFCwdoCqt2zSkeY4OT55VZE/P7jauCBj7DGe26BMqDycOvFg92Lsvi6nmWQcwmCJf9OE6+WCx7AgMBAAECggEAFW3bRME3gVqxJN492fJC0Aj9LVSKZcFHN+5uem1U6P25TBKA9SAh6hbBLGTQ7dBBKkQb2EULMYXDd5oD53JQRMLWQMtpQqT9r2omTQmHxDjz8nf6UkTnPk+Oir4yh1zbZhJXpcJM9xaTuc6Ij5rfgdOwcXtPu9ky5dGeao1f33BPWeAFHlXb3UwzF7Zbb47usIL2GHtLo/d2nk+q36J8mjTWAT1mOJHPvsxqC0G8CpLxBOf9zkHympjj0Zju/ZESK4Z/Agm4yB37DxuxzVoN2gNqevaMrGq9CFj5VAKFgmz8JHpp1pp2tdXCtNbTIdGQ9JNxuIpqPYwZcTLM6qa/sQKBgQD48PHi56gnJRahcXzbPjjtXzEROvSbN2Giw+GG735ZBvs97paIpJsezg1DkDgynfV6zBoBPZRTi3PXjWZbZsCtNkgkDk13IdjofPloKKT4mBL3BXiVx7uInn4DSmyL4RUJyIVQzyT0VGR7zpVB2FJEM04+0pNe1okfar7b2ZdyjwKBgQDZcB4+GkjotB/sbW9XZ64pQFSsxP22UUS+iXq90MwHss/ruqRo5RNgpnFrgF457INmNX3lCMhdv13tMpsGS0IVHbH9zI3r+/G0fX+Wa2sgGOC4IkhLCI1t9/H+ERs03iD6VnHV2CGKKugS0biJctIrt8QSN9fyITvUQOitp1QtVQKBgQDVHiFWqhAxAvVu3SOQ5qIBxV7Sa3CBeVYi7CMlS98VBH8OtDU3YgRSyQBd+RfDbat8p0MUEM3tABP6U1lvonYNU81K4Nu8w8NYtbUnULR7BmYmCA8F22pXOaiT1KE810MVjw9i8ROoAJ/NxjvNlQ5IyMuRyODVbkESHmaq/ItFTQKBgHUxu+snCaL66rXoqflHNZSgSsMwDbtFhDLaMKC8OCvyEaTxXQn4ZgBS1ZDmiGLZ+lyNekSG/k+hBEQDYZD74Ss2bSfpJ5lImxb4CVuOnBeZsQ0t1R80Bd5+pByXJb3R955WF1b1/eXViG4UQU076NGNY42aMbcceH3HWmG2bWVdAoGAbxH9NrmHLYxbJqJekZL9FcN7GVW30iTLFG3J5EkSz3ScxbJfgRYZ/96/iCQSAt0HDw+10zMD1Zo86Pj5bVmmhb0p5KMhKyDowFgz4eNhcGcNRbKYUWgJTr5kLSoxLhSRC8T1qEIi4KrSw6IJn3Gwvjmxmd+MGlF8UdrPD1HRbAM=";
	 
		// 支付宝公钥
	    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuwji9kfz8TFMz694GcfsjWqOWvGNdtZX/QeewkAUSnT67942ScR0VL7xdjZLYQGRJS2fg4pxoS6/+R5LLR6lPAlqy16sQLWV94CPZoX2RvjQPyoK82gpB2GRhmBjZtBkuHx/kVbWfYkih6+VPNX/i3FbCB4Q46U2M/At/JI2BDwLnWh0a4J6/Dc9chiUirTtZdbjey4bb8DKLrDSuFoWXQxhVJhPW0Gp7WKtBiPSkVovQoOwY/bFKfyDJSY92oS1mNWjoIzr7vS52fc374rm9fVbZPFau5xu5q6hNmOCekM/W9SEKPyxk4lAB+hQYcgiSjGANBo+v3y55OHQIzrylQIDAQAB";
	 
	    /*
	     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数
	     * 必须外网可以正常访问
		 * 一般就是支付成功时，修改数据库之类的操作
		 */
		public static String notify_url = "http://localhost:8080/project/apli/update";
		
		/*
		 * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
		 * 通知页面一般用来，支付成功后，需要跳转什么页面之类的处理
		 */
	    public static String return_url = "http://localhost:8080/project/apli/return";
	    
		// 签名方式，注意这里，如果步骤设置的是RSA则用RSA
		public static String sign_type = "RSA2";
	 
		// 字符编码格式
		public static String charset = "utf-8";
	 
		// 支付宝网关
		public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	 
		// 支付宝网关
		public static String log_path = "C:\\";
	}
