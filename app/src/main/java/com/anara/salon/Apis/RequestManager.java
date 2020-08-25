package com.anara.salon.Apis;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.anara.salon.Activities.ListSalonActivity;
import com.anara.salon.Activities.OTPActivity;
import com.anara.salon.MainActivity;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class RequestManager {

    public static void postLogin(final OTPActivity otpActivity, String mobileNumber) {
        try {

            RequestQueue requestQueue = Volley.newRequestQueue(otpActivity);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("mobile", mobileNumber);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("200")) {
                                Intent intent = new Intent(otpActivity, MainActivity.class);
                                otpActivity.startActivity(intent);
                            } else {
                                Toast.makeText(otpActivity, "Some Error Occurred Contact Developer", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(otpActivity, "Some Error Occurred Contact Developer", Toast.LENGTH_SHORT).show();
                            Log.e("VOLLEY Error", error.toString());
                        }
                    }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        Log.e("=======", "=========" + requestBody);
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        Log.e("=======", "--------" + uee);
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("-=-=-=-=-", "-=-=-=-=-=" + e.getMessage());
        }
    }

    public static void getService(final ListSalonActivity listSalonActivity){
        RequestQueue requestQueue = Volley.newRequestQueue(listSalonActivity);
        try {
            JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.POST, Const.LIST_SALONS_URL,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.e("-=-=-=-=-=","-=-=-=-=-=-=-="+response);
//                                JSONObject obj = response.getJSONObject("colorObject");
//                                String color = obj.getString("colorName");
//                                String desc = obj.getString("description");

                            } catch (Exception e) {

                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley", "Error");
                        }
                    }
            );
            requestQueue.add(obreq);
        }catch (Exception e){

        }
    }
}
