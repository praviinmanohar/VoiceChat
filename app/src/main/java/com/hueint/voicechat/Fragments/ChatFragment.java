package com.hueint.voicechat.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hueint.voicechat.Adapters.UsersAdapter;
import com.hueint.voicechat.Models.ChatMessage;
import com.hueint.voicechat.Models.User;
import com.hueint.voicechat.R;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private View rootView;

    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;

    private List<User> mUsers;

    FirebaseUser fusers;
    DatabaseReference reference;

    private List<String> usersList;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fusers = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    ChatMessage chats = snapshot.getValue(ChatMessage.class);

                    if (chats.getSender().equals(fusers.getUid())){
                        usersList.add(chats.getReceiver());
                    }
                    if (chats.getReceiver().equals(fusers.getUid())){
                        usersList.add(chats.getSender());
                    }
                }
                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        readChats();
    }

    private void readChats() {
        mUsers = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);

                    for (String id : usersList){
                        if (user.getId().equals(id)){
                            if (mUsers.size() != 0){

                                for (int m=0; m<mUsers.size(); m++){
                                    Log.e("TAG",""+mUsers.get(m).getId());

                                    if (!user.getId().equals(mUsers.get(m).getId())){
                                        mUsers.add(user);
                                        Log.e("TAG",""+mUsers.get(m).getUsername());
                                    }
                                }

                            } else {
                                mUsers.add(user);
                            }
                        }
                    }
                }
                usersAdapter = new UsersAdapter(getContext(),mUsers);
                recyclerView.setAdapter(usersAdapter);
                usersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
