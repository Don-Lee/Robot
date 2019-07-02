package cn.rjgc.robot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;

import cn.rjgc.robot.R;
import cn.rjgc.robot.bean.ChatMessage;

/**
 * Created by Don on 2016/8/5.
 */
public class ChatMessageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;

    public ChatMessageAdapter(Context context , List<ChatMessage> datas) {
        mInflater=LayoutInflater.from(context);
        this.mDatas=datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage=mDatas.get(position);
        if(chatMessage.getType()== ChatMessage.TYPE.INCOMING){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        ChatMessage chatMessage=mDatas.get(position);
        if(convertView==null){
            //根据ItemType设置不同的布局
            if(getItemViewType(position)==0){
                convertView=mInflater.inflate(R.layout.item_robot_msg,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mDate= (TextView) convertView.findViewById(R.id.robot_date);
                viewHolder.mMsg= (TextView) convertView.findViewById(R.id.robot_msg);
            }else{
                convertView=mInflater.inflate(R.layout.item_me_msg,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mDate= (TextView) convertView.findViewById(R.id.me_date);
                viewHolder.mMsg= (TextView) convertView.findViewById(R.id.me_msg);
            }
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        viewHolder.mDate.setText(sdf.format(chatMessage.getDate()));
        viewHolder.mMsg.setText(chatMessage.getMsg());
        return convertView;
    }

    private class ViewHolder{
        TextView mDate;
        TextView mMsg;
    }
}
