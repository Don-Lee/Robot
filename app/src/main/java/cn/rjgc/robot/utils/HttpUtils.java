package cn.rjgc.robot.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import cn.rjgc.robot.bean.ChatMessage;
import cn.rjgc.robot.bean.RobotMessage;

/**
 * Created by Don on 2016/8/4.
 */
public class HttpUtils {
    private static final String URL_TL="http://www.tuling123.com/openapi/api";
    private static final String API_KEY="1351cb9495204903bcec64b9a161db3a";

    /***
     * 发送消息并解析返回的结果
     * @param msg 发送的内容
     * @return  返回自定义的消息
     */
    public ChatMessage sendMsg(String msg){
        ChatMessage chatMessage=new ChatMessage();
        String jsonResult=doPost(msg);
        RobotMessage robotMessage;
        Gson gson=new Gson();

        try {
            robotMessage=gson.fromJson(jsonResult,RobotMessage.class);
            chatMessage.setMsg(robotMessage.getText());
        } catch (JsonSyntaxException e) {
            chatMessage.setMsg("服务器繁忙，请稍候再试");
        }
        chatMessage.setType(ChatMessage.TYPE.INCOMING);
        chatMessage.setDate(new Date());
        return chatMessage;
    }

    /***
     * 发送post请求给服务器
     * @param msg  发送的内容
     * @return  服务器返回的结果
     */
    public String doPost(String msg){
        StringBuffer result=null;
        OutputStream out=null;
        BufferedReader br=null;
        try {
            URL url=new URL(URL_TL);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);//默认为false的，所以需要设置
            conn.setReadTimeout(5000);
            out=conn.getOutputStream();
            String params="key="+API_KEY+"&info="+ URLEncoder.encode(msg,"UTF-8");
            out.write(params.getBytes());
            br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            result=new StringBuffer();
            String str;

            while ((str=br.readLine())!=null){
                result.append(str);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (br!=null){
                    br.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return result.toString();
    }

}