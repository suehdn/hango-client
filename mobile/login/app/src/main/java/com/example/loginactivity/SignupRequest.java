package com.example.loginactivity;

import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignupRequest extends StringRequest {

    // 서버 URL 설정
    final static private String URL = "http://192.168.123.107:80/mobile/signup";
    private Map<String, String> map;

    public SignupRequest(String userName, String userId, String userPasswd, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userName", userName);
        map.put("userId", userId);
        map.put("userPasswd", userPasswd);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
