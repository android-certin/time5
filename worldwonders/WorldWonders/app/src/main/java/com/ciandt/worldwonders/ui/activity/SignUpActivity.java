package com.ciandt.worldwonders.ui.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.User;
import com.ciandt.worldwonders.protocol.Protocol;

public class SignUpActivity extends AppCompatActivity {

    EditText nameEditText, userEditText;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupButton = (Button) findViewById(R.id.btn_signup);
        nameEditText = (EditText) findViewById(R.id.input_name);
        userEditText = (EditText) findViewById(R.id.input_user);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("user_info", new User(nameEditText.getText().toString(),
                        userEditText.getText().toString()));
                setResult(Protocol.OK, intent);

                finish();
            }
        });

    }
}
