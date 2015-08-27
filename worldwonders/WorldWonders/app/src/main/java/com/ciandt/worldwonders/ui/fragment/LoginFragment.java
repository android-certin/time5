package com.ciandt.worldwonders.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.User;
import com.ciandt.worldwonders.protocol.Protocol;
import com.ciandt.worldwonders.ui.activity.SignUpActivity;

/**
 * Created by daianefs on 21/08/15.
 */
public class LoginFragment extends android.support.v4.app.Fragment {


    private Button loginButton, signupButton;
    private EditText usernameEditText, passwordEditText;
    private OnLoginListener onLoginListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setOnLoginListener(OnLoginListener listener) {
        this.onLoginListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) view.findViewById(R.id.btn_login);
        signupButton = (Button) view.findViewById(R.id.buttonSignUp);
        usernameEditText = (EditText) view.findViewById(R.id.input_user_name);
        passwordEditText = (EditText) view.findViewById(R.id.inputPassword);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivityForResult(intent, Protocol.SIGNUP);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginListener.onLogin(new User());
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Protocol.SIGNUP:
                if (data != null && resultCode == Protocol.OK) {
                    User u = (User) data.getSerializableExtra("user_info");
                    usernameEditText.setText(u.user);
                }
                break;
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface OnLoginListener {
        void onLogin(User user);
    }

}

