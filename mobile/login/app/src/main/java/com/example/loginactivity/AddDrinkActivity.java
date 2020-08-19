package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddDrinkActivity extends AppCompatActivity {
    private Button btn_regi_drink;
    private EditText tv_drink_name,tv_drink_position,tv_drink_price;

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_drink);

        Intent intent = getIntent();
        final String serialNumber = intent.getStringExtra("VendingSerialNumber");;

        btn_regi_drink = findViewById(R.id.btn_regi_drink);

        tv_drink_name = findViewById(R.id.tv_drink_name);
        tv_drink_position = findViewById(R.id.tv_drink_position);
        tv_drink_price = findViewById(R.id.tv_drink_price);

        final RequestQueue queue = Volley.newRequestQueue((this));

        btn_regi_drink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String drinkName = tv_drink_name.getText().toString();
                String _drinkPosition = tv_drink_position.getText().toString();
                int drinkPosition = Integer.parseInt(_drinkPosition);
                String _drinkPrice = tv_drink_price.getText().toString();
                int drinkPrice = Integer.parseInt(_drinkPrice);

                JSONObject drink_info = new JSONObject();
                try {
                    drink_info.put("name",drinkName);
                    drink_info.put("position",drinkPosition);
                    drink_info.put("price",drinkPrice);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JSONObject object = new JSONObject();

                try {
                    object.put("serialNumber", serialNumber);
                    object.put("drink",drink_info);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("TAG", "결과 : " + object);
                String URL = "http://192.168.0.31:80/mobile/drink/create";
                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, URL,object, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            boolean success = response.getBoolean("success");
                            Log.d("TAG", "결과 : " + success);
                            if (success) {
                                Log.d("TAG", "성공");
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue.add(objectRequest);
            }
        });
    }
}