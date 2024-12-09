package com.xu.spider4j.util;

import com.google.common.base.Charsets;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


/**
 * JS工具类
 */
public class JSUtil {

    private static Invocable inv;

    public static final String ENC_TEXT = "encText";

    public static final String ENC_SEC_KEY = "encSecKey";

    static {
        try {
            Path path = Paths.get("core.js");
            System.out.println(path.getFileName());
            byte[] bytes = Files.readAllBytes(path);
            String js = new String(bytes, Charsets.UTF_8);
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
        return (ScriptObjectMirror) inv.invokeFunction("myHook", paras);
    }

    public static HashMap<String, String> getDatas(String paras) {
        try {
            ScriptObjectMirror so = (ScriptObjectMirror) inv.invokeFunction("myHook", paras);
            HashMap<String, String> datas = new HashMap<>();
            datas.put("params", so.get(JSUtil.ENC_TEXT).toString());
            datas.put("encSecKey", so.get(JSUtil.ENC_SEC_KEY).toString());
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
