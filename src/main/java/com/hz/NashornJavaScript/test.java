package com.hz.NashornJavaScript;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by HZ-PC on 2018/3/31.
 */
public class test {

    /**
     * Nashorn JavaScript 引擎
     */
    @Test
    public void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
    }
}
