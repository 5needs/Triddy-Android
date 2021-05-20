package edu.eci.ieti.triddy.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.eci.ieti.triddy.android.model.Chat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import edu.eci.ieti.triddy.android.R;
import edu.eci.ieti.triddy.android.model.Message;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    List<Chat> chatList = null;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    OnChatListener onChatListener;

    public ChatListAdapter(OnChatListener onChatListener){
        this.onChatListener = onChatListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType )
    {
        return new ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.chat_item, parent, false ), this.onChatListener );
    }

    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position )
    {
        Chat chat = chatList.get( position );
        String date = this.formatter.format(chat.getLastMessage().getDate());
        ((TextView) holder.itemView.findViewById(R.id.name)).setText(chat.getUser2());
        ((TextView) holder.itemView.findViewById(R.id.message)).setText(chat.getLastMessage().getContent());
        ((TextView) holder.itemView.findViewById(R.id.date)).setText(date);
    }

    @Override
    public int getItemCount()
    {
        return chatList != null ? chatList.size() : 0;
    }

    public Chat getChat(int position){
        return this.chatList.get(position);
    }

    public void updateChat(List<Chat> chats){
        this.chatList = chats;
        notifyDataSetChanged();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private OnChatListener onChatListener;
        ViewHolder( @NonNull View itemView, OnChatListener onChatListener )
        {
            super( itemView );
            this.onChatListener = onChatListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onChatListener.onChatClicked(getAdapterPosition());
        }
    }

    public interface OnChatListener{
        void onChatClicked(int position);
    }

}
