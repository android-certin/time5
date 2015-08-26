package com.ciandt.worldwonders.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.ui.fragment.LoginFragment;
import com.ciandt.worldwonders.ui.fragment.WondersFragment;
import com.ciandt.worldwonders.model.User;

/**
 * Created by jpimentel on 8/20/15.
 */
public class MainActivity extends BaseActivity {

    final static String TAG = "MainActivity";
    private boolean isLogged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginFragment loginFragment = new LoginFragment();

        loginFragment.setOnLoginListener(
                new LoginFragment.OnLoginListener() {
                    @Override
                    public void onLogin(User user) {
                        if (user.authenticate()) {
                            isLogged = true;
                            addWondersFragment();
                        }
                    }
                }
        );

        if(savedInstanceState != null && savedInstanceState.containsKey("isLogged")) {
            isLogged = savedInstanceState.getBoolean("isLogged");
        }

        if (isLogged) {
            addWondersFragment();

        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.frameMain, loginFragment)
                    .commit();

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("isLogged", isLogged);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void addWondersFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        WondersFragment wondersFragment = new WondersFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.frameMain, wondersFragment)
                .commit();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    /**
     * Dispatch onStart() to all fragments.  Ensure any created loaders are
     * now started.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }
}
