package edu.eci.ieti.triddy.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.eci.ieti.triddy.android.R;
import edu.eci.ieti.triddy.android.model.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {

    List<Message> messages = null;
    Context context;
    String user;

    public MessageAdapter(Context context, String user) {
        this.context = context;
        this.user = user;
    }


    public void addMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public void addMessage(Message message){
        if (this.messages != null){
            this.messages.add(message);
        }
        else{
            this.messages = new ArrayList<Message>();
            this.messages.add(message);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return messages != null ? messages.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);
        if (message.getUser().equals(this.user)) {
            convertView = messageInflater.inflate(R.layout.my_message, null);
            ((TextView) convertView.findViewById(R.id.message_body)).setText(message.getContent());
        } else {
            convertView = messageInflater.inflate(R.layout.their_message, null);
            ((TextView) convertView.findViewById(R.id.message_body)).setText(message.getContent());
        }
        return convertView;
    }

}

