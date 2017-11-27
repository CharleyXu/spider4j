package com.xu.spider4j.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * JS工具类
 */
public class JSUtil {
	private static Invocable inv;
	public static final String encText = "encText";
	public static final String encSecKey = "encSecKey";

	/**
	 * 从本地加载修改后的 js 文件到 scriptEngine
	 */
	static {
		try {
			Path path = Paths.get("core.js");
			System.out.println(path.getFileName());
			byte[] bytes = Files.readAllBytes(path);
			String js = new String(bytes);
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			engine.eval(js);
			inv = (Invocable) engine;
			System.out.println("Init completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ScriptObjectMirror get_params(String paras) throws Exception {
		ScriptObjectMirror so = (ScriptObjectMirror) inv.invokeFunction("myHook", paras);
		return so;
	}

	public static HashMap<String, String> getDatas(String paras) {
		try {
			ScriptObjectMirror so = (ScriptObjectMirror) inv.invokeFunction("myHook", paras);
			HashMap<String, String> datas = new HashMap<>();
			datas.put("params", so.get(JSUtil.encText).toString());
			datas.put("encSecKey", so.get(JSUtil.encSecKey).toString());
			return datas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
