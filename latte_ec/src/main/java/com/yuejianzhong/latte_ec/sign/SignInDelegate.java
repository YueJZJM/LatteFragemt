package com.yuejianzhong.latte_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.yuejianzhong.latte_core.delegate.LatteDelegate;
import com.yuejianzhong.latte_core.log.LogUtils;
import com.yuejianzhong.latte_core.net.RestClient;
import com.yuejianzhong.latte_core.net.callback.ISuccess;
import com.yuejianzhong.latte_ec.R;
import com.yuejianzhong.latte_ec.R2;

import butterknife.BindView;

public class SignInDelegate extends LatteDelegate implements View.OnClickListener {

    private TextInputEditText mEmail = null;
    private TextInputEditText mPassword = null;
    private ISignListener mISignListener = null;



    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        $(R.id.btn_sign_in).setOnClickListener(this);
        mEmail = $(R.id.edit_sign_in_email);
        mPassword = $(R.id.edit_sign_in_password);
        $(R.id.btn_sign_in).setOnClickListener(this);
        $(R.id.tv_link_sign_up).setOnClickListener(this);
        $(R.id.icon_sign_in_wechat).setOnClickListener(this);
//        $(R.id.tb_sign_in).setPadding(0, getStatusBarHeight(), 0, 0);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_sign_in) {
            onClickSignIn();
        } else if (i == R.id.tv_link_sign_up) {
            onClickLink();
        } else if (i == R.id.icon_sign_in_wechat) {
            onClickWeChat();
        }
    }

    private boolean checkFrom() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }


    private void onClickSignIn() {
        if (checkFrom()) {
            RestClient.builder()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LogUtils.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                            Log.d("yuejz", response);
//                            getSupportDelegate().startWithPop(new EcBottomFragment());
                        }
                    })
                    .loader(getContext())
                    .build()
                    .post();
        }
    }

    private void onClickLink() {

        getSupportDelegate().start(new SignInDelegate(), SINGLETASK);
    }

    private void onClickWeChat() {
//        LatteWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
//            @Override
//            public void onSignInsuccess(String userInfo) {
//                Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
//                Log.e("xxxx", "userInfo");
//            }
//        }).signIn();
    }
}
