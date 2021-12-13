package com.dbc.viibez_messagingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatList extends AppCompatActivity {




    public static final String CHAT_PREFS = "ChatPrefs";
    public static final String DISPLAY_NAME_KEY = "username";
    // TODO: Add member variables here:
    private String mDisplayName;
    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;

    private ChatListAdapter mAdapter;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        // TODO: Set up the display name and get the Firebase reference


        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        setDisplayNameX();


        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        // TODO: Send the message when the "enter" button is pressed


        // TODO: Add an OnClickListener to the sendButton to send a message
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMessage();
            }
        }) ;

    }


    // TODO: Retrieve the display name from the Shared Preferences



    private void setDisplayNameX(){
        SharedPreferences prefs = getSharedPreferences(CHAT_PREFS,MODE_PRIVATE);

        mDisplayName = prefs.getString(DISPLAY_NAME_KEY,null);

        if(mDisplayName == null)mDisplayName = "Anonymous";


    }


    public void sendMessage() {


       String input = mInputText.getText().toString();
       if(!input.equals("")){
           InstantMessage chat = new InstantMessage(input,mDisplayName);
           mDatabaseReference.child("messages").push().setValue(chat);
           mInputText.setText("");
       }


    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.
    @Override
    public void onStart(){
        super.onStart();
        mAdapter  = new ChatListAdapter(this,mDatabaseReference,mDisplayName);
        mChatListView.setAdapter(mAdapter);
    }


    @Override
    public void onStop() {
        super.onStop();

        // TODO: Remove the Firebase event listener on the adapter.

        mAdapter.cleanup();
    }
}