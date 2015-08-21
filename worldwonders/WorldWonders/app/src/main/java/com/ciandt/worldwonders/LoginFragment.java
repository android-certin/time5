package com.ciandt.worldwonders;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ciandt.worldwonders.model.User;

/**
 * Created by daianefs on 21/08/15.
 */
public class LoginFragment extends android.support.v4.app.Fragment {


    Button loginButton, signupButton;
    EditText usernameEditText, passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) view.findViewById(R.id.btn_login);
        signupButton = (Button) view.findViewById(R.id.btn_signup);
        usernameEditText = (EditText) view.findViewById(R.id.input_user_name);
        passwordEditText = (EditText) view.findViewById(R.id.input_password);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SignUpActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            User u = (User) data.getSerializableExtra("user_info");
            usernameEditText.setText(u.user);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



}