package com.example.sakshi.esamadhan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by SHIVANI on 20-03-2017.
 */
public class LawyerSignup extends Fragment {
    EditText Name, Address, Pincode, Email, Phone, DOB, Password, CPassword;
    String name, phone, address, password, cpassword, email, spincode, dob;
    double pincode;
    ProgressDialog progress;
    Button LawyerRegister;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.lawyer_signup, container, false);


        Name = (EditText) rootView.findViewById(R.id.lawyer_name);
        Address = (EditText) rootView.findViewById(R.id.lawyer_address);
        Pincode = (EditText) rootView.findViewById(R.id.lawyer_pincode);
        Email = (EditText) rootView.findViewById(R.id.lawyer_email);
        Phone = (EditText) rootView.findViewById(R.id.lawyer_phone);
        DOB = (EditText) rootView.findViewById(R.id.laywer_dob);
        Password = (EditText) rootView.findViewById(R.id.lawyer_password);
        CPassword = (EditText) rootView.findViewById(R.id.lawyer_cpassword);
        LawyerRegister = (Button) rootView.findViewById(R.id.lawyer_register);
        LawyerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = Name.getText().toString();
                phone = Phone.getText().toString();
                address = Address.getText().toString();
                password = Password.getText().toString();
                cpassword = CPassword.getText().toString();
                email = Email.getText().toString();
                spincode = Pincode.getText().toString();
                try {
                    pincode = Double.parseDouble(spincode);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                dob = DOB.getText().toString();

                Name.setText(name);
                Phone.setText(phone);
                Address.setText(address);
                Email.setText(email);
                Pincode.setText(spincode);
                DOB.setText(dob);

                if (name.length() > 0) {
                    if (phone.length() == 10) {
                        if (address.length() > 0) {
                            if (100000 < pincode && pincode < 999999) {
                                if (email.length() > 0) {
                                    if (dob.length() > 0) {
                                        if (password.length() > 0) {
                                            if (cpassword.equals(password)) {
                                                progress = new ProgressDialog(getActivity());
                                                progress.setTitle("Signing in...");
                                                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                                                progress.show();
                                                Signup("https://esamadhan.herokuapp.com/lawyer/register");
                                            } else {
                                                CPassword.requestFocus();
                                                CPassword.setError("Please enter same Password");
                                            }
                                        } else {
                                            Password.requestFocus();
                                            Password.setError("Please enter your Password");
                                        }
                                    } else {
                                        DOB.requestFocus();
                                        DOB.setError("Please enter your Date Of Birth");
                                    }

                                } else {
                                    Email.requestFocus();
                                    Email.setError("Please enter your login email");
                                }
                            } else {
                                Pincode.requestFocus();
                                Pincode.setError("Please enter your pincode");
                            }
                        } else {
                            Address.requestFocus();
                            Address.setError("Please Enter your Address");
                        }
                    } else {
                        Phone.requestFocus();
                        Phone.setError("Please enter 10 digit number");

                    }
                } else {
                    Name.requestFocus();
                    Name.setError("Please enter the name");
                }
            }
        });
        return rootView;
    }

        public boolean isonline() {
            Log.e("coool start is online", " ");
            ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            } else {
                return false;
            }

        }

        public void responseAnekeBaad() {
            progress.dismiss();
            Toast.makeText(getActivity(), " Login to continue ", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);

        }

        public void lawyerSignup(String uri) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, uri,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String success = "";
                            String message = "";
                            try {
                                JSONObject obje = new JSONObject(response);
                                success = obje.getString("success");
                                message = obje.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (success.equals("true")) {
                                responseAnekeBaad();
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                            }

                        }


                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.dismiss();
                    Toast.makeText(getActivity(), "Ërror in sign in ", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new Hashtable<String, String>();
                    params.put("email", email);
                    params.put("password", password);
                    params.put("name", name);
                    params.put("address", address);
                    params.put("phone", phone);
                    params.put("pincode", spincode);
                    params.put("dob", dob);
                    //returning parameters
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            //Adding request to the queue
            requestQueue.add(stringRequest);


        }


        public void Signup(String uri){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, uri,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String success="";
                            String message="";
                            try {
                                JSONObject obje= new JSONObject(response);
                                success=obje.getString("success");
                                message=obje.getString("message");
                            } catch (JSONException e) {
                                progress.dismiss();
                                e.printStackTrace();
                            }
                            if(success.equals("true")){
                                responseAnekeBaad();
                            }
                            else{
                                progress.dismiss();
                                Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                            }

                        }



                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.dismiss();
                    Toast.makeText(getActivity(), "Ërror in sign in ", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new Hashtable<String, String>();
                    params.put("email", email);
                    params.put("password",password);
                    params.put("name",name);
                    params.put("location",address);
                    params.put("phone",phone);
                    params.put("dob",dob);
                    params.put("pincode",spincode);

                    //returning parameters
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            //Adding request to the queue
            requestQueue.add(stringRequest);


        }


    }


