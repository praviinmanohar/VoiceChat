package com.hueint.voicechat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hueint.voicechat.MessageActivity;
import com.hueint.voicechat.Models.ChatMessage;
import com.hueint.voicechat.Models.User;
import com.hueint.voicechat.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mcontext;
    private List<ChatMessage> mchats;

    private String imageUrl;

     FirebaseUser fuser;

    public MessagesAdapter(Context context, List<ChatMessage> chats, String imageUrl){
        this.mcontext = context;
        this.mchats = chats;
        this.imageUrl = imageUrl;
    }


    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.chat_item_right, parent, false);
            return new MessagesAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.chat_item_left, parent, false);
            return new MessagesAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {

        ChatMessage chats = mchats.get(position);
        holder.show_message.setText(chats.getMessage());
        if (imageUrl.equals("default")){
            holder.profilepic.setImageResource(R.drawable.man);
        } else {
            Glide.with(mcontext).load(imageUrl).into(holder.profilepic);
        }
    }

    @Override
    public int getItemCount() {
        return mchats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView show_message;
        public ImageView profilepic;

        public ViewHolder(View itemView){

            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profilepic = itemView.findViewById(R.id.profile_image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mchats.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}