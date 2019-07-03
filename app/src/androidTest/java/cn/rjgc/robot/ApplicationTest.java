package cn.rjgc.robot;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.rjgc.robot.utils.HttpUtils;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {
    public void testSendMsg(){
        HttpUtils httpUtils=new HttpUtils();
        String s=httpUtils.doPost("给我讲个笑话");
        Log.e("TAG",s);

        s=httpUtils.doPost("你真好");
        Log.e("TAG",s);
    }
}