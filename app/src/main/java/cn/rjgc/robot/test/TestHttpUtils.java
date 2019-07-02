package cn.rjgc.robot.test;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cn.rjgc.robot.utils.HttpUtils;

/**
 * Created by Don on 2016/8/5.
 */
@RunWith(JUnit4.class)
public class TestHttpUtils {
    @Test
    public void testSendMsg(){
        HttpUtils httpUtils=new HttpUtils();
        String s=httpUtils.doPost("给我讲个笑话");
        Log.e("TAG",s);

        s=httpUtils.doPost("你真好");
        Log.e("TAG",s);
    }
}
