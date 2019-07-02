package cn.rjgc.robot;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.rjgc.robot.adapter.ChatMessageAdapter;
import cn.rjgc.robot.bean.ChatMessage;
import cn.rjgc.robot.utils.HttpUtils;

public class MainActivity extends AppCompatActivity {

    private final String TAG=MainActivity.class.getSimpleName();
    private ListView mLvContent;
    private EditText mEtSend;
    private Button mBtnSend;

    private List<ChatMessage> mDatas;
    private ChatMessageAdapter mAdapter;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 等待接收，子线程完成数据的返回
            ChatMessage robotMessge = (ChatMessage) msg.obj;
            mDatas.add(robotMessge);
            mAdapter.notifyDataSetChanged();
            mLvContent.setSelection(mDatas.size()-1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
    }

    private void initDatas() {
        mDatas = new ArrayList<ChatMessage>();
        mAdapter=new ChatMessageAdapter(MainActivity.this,mDatas);
        mLvContent.setAdapter(mAdapter);
    }

    public void initView(){
        mLvContent= (ListView) findViewById(R.id.id_lv_content);
        mEtSend= (EditText) findViewById(R.id.id_et_send);
        mBtnSend= (Button) findViewById(R.id.id_btn_send);
    }


    public void send(View view){
        final String sendContent=mEtSend.getText().toString();
        if(TextUtils.isEmpty(sendContent)){
            Toast.makeText(MainActivity.this,"发送的内容不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }else{

            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setDate(new Date());
            chatMessage.setType(ChatMessage.TYPE.OUTCOMING);
            chatMessage.setMsg(sendContent);
            mDatas.add(chatMessage);
            mAdapter.notifyDataSetChanged();
            mLvContent.setSelection(mDatas.size()-1);

            mEtSend.setText("");

            new Thread(){
                @Override
                public void run() {
                    HttpUtils httpUtils=new HttpUtils();
                    ChatMessage cm=httpUtils.sendMsg(sendContent);
                    Message msg=Message.obtain();
                    msg.obj=cm;
                    mHandler.sendMessage(msg);
                }
            }.start();

        }

    }
}
