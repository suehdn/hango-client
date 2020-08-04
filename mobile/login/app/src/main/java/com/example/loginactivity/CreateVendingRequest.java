package com.example.loginactivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class CreateVendingRequest extends StringRequest{
    final static private String URL = "http://ec2-3-34-207-199.ap-northeast-2.compute.amazonaws.com/mobile/addvending";
    private Map<String, String> vending_parameters= new HashMap<String, String>();;

    public CreateVendingRequest(String Name, String Discription, String FullSize, String SirialNumber, Response.Listener<String> listener){
        super (Method.POST, URL, listener, null);
        vending_parameters.put("vending_name", Name);
        vending_parameters.put("vending_discription", Discription);
        vending_parameters.put("full_size", FullSize);
        vending_parameters.put("sirial_number", SirialNumber);
    }
    public Map getParams() throws AuthFailureError{
        Map<String,Map<String,String>> vending = new HashMap<String,Map<String,String>>();
        vending.put("vending", vending_parameters);
        return vending;
    }
}
