package com.chris.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class ApplySystemUtils {
	 private static final Logger logger = LoggerFactory.getLogger(ApplySystemUtils.class);
	 public static final int CODE_OK = 0;
	 public static final int CODE_ERR = -1;
	    public static String md5(String key) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.reset();
	            md.update(key.getBytes(Charset.forName("UTF-8")));
	            byte[] resultByte = md.digest();
	            String result = Hex.encodeHexString(resultByte);
	            return result;
	        } catch (Exception e) {
	            logger.error("生成MD5失败", e);
	            return null;
	        }
	    }

	    //Get json string
	    public static String getJSONString(int code) {
	        JSONObject json = new JSONObject();
	        json.put("status", code);
	        return json.toJSONString();
	    }
	    public static String getJSONString(int code, String msg) {
	        JSONObject json = new JSONObject();
	        json.put("status", code);
	        json.put("msg", msg);
	        return json.toJSONString();
	    }
	    public static String getJSONString(int code, Map<String, Object> map) {
	        JSONObject json = new JSONObject();
	        json.put("status", code);
	        for (Map.Entry<String, Object> entry : map.entrySet()) {
	            json.put(entry.getKey(), entry.getValue());
	        }
	        return json.toJSONString();
	    }
}
