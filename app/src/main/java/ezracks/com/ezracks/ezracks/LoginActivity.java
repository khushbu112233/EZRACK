package ezracks.com.ezracks.ezracks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ezracks.com.ezracks.R;
import ezracks.com.ezracks.Webservice.WebseriveUrl;
import ezracks.com.ezracks.databinding.LoginLayoutBinding;
import ezracks.com.ezracks.utils.ConnectionDetector;
import ezracks.com.ezracks.utils.Constants;
import ezracks.com.ezracks.utils.FieldsValidator;
import ezracks.com.ezracks.utils.FontCustom;
import ezracks.com.ezracks.utils.Pref;


/**
 * Created by aipxperts-ubuntu-01 on 29/3/17.
 */

public class LoginActivity extends BaseActivity {
    /**
     * declare variable for access in class
     */
    LoginLayoutBinding mBinding;
    String isRememberMe="0";
    String isAutoLoging="0";
    String driveid = "";
    String password = "";
    FieldsValidator mValidator;
    ConnectionDetector cd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.login_layout);
        mValidator = new FieldsValidator(this);
        cd = new ConnectionDetector(this);
        /**
         * set material design functionality
         */
        settext_watcher();
        /**
         * set font style
         */
        set_typeface();
        /**
         * check isremember switch on or off.
         */
        if(Pref.getValue(LoginActivity.this,"isRememberMe","").equalsIgnoreCase("1"))
        {
            mBinding.edtDriverId.setText(Pref.getValue(LoginActivity.this,"driveid1",""));
            mBinding.edtPassword.setText(Pref.getValue(LoginActivity.this,"password",""));
            mBinding.imgRememberMe.setImageResource(R.mipmap.switched);
            isRememberMe="1";

        }else
        {
            mBinding.imgRememberMe.setImageResource(R.mipmap.unswitch);
            isRememberMe="0";

        }
        /**
         *
         * check isautologin switch on or off.
         */
        if(Pref.getValue(LoginActivity.this,"isAlreadyLogin","").equalsIgnoreCase("1"))
        {
            mBinding.imgAutoLoging.setImageResource(R.mipmap.switched);
            isAutoLoging="1";

        }else
        {
            mBinding.imgAutoLoging.setImageResource(R.mipmap.unswitch);
            isAutoLoging="0";
        }
        /**
         * click listener for auto login switch.
         */
        mBinding.imgAutoLoging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAutoLoging.equalsIgnoreCase("0"))
                {
                    mBinding.imgAutoLoging.setImageResource(R.mipmap.switched);
                    isAutoLoging="1";


                }else if(isAutoLoging.equalsIgnoreCase("1"))
                {
                    mBinding.imgAutoLoging.setImageResource(R.mipmap.unswitch);
                    isAutoLoging="0";
                }

            }
        });
        /**
         * click listener for  remember switch
         */
        mBinding.imgRememberMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRememberMe.equalsIgnoreCase("0"))
                {
                    mBinding.imgRememberMe.setImageResource(R.mipmap.switched);
                    isRememberMe="1";
                }else if(isRememberMe.equalsIgnoreCase("1"))
                {
                    mBinding.imgRememberMe.setImageResource(R.mipmap.unswitch);
                    isRememberMe="0";
                }
                //  Pref.setValue(LoginActivity.this,"isRememberMe",isRememberMe);
            }
        });
        /**
         * click for login
         */
        mBinding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if(mBinding.edtDriverId.getText().toString().length()==0)
                {
                    mBinding.edtDriverId.setError("Driver ID is required!");

                }
               /* else if(mBinding.edtUsername.getText().toString().length()==0)
                {
                    mBinding.edtUsername.setError("User Name is required!");
                }*/else if(mBinding.edtPassword.getText().toString().length()==0)
                {
                    mBinding.edtPassword.setError("Password is required!");
                }else {
                    driveid = mBinding.edtDriverId.getText().toString();
                    Pref.setValue(LoginActivity.this, "driveid", driveid);
                    password = mBinding.edtPassword.getText().toString();


                    /**
                     * call api for login
                     */
                    if(cd.isConnectingToInternet()) {
                        new ExecuteTask().execute();
                    }else
                    {
                        Toast.makeText(LoginActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



        //to change color of one string ,first part string is black and second part pink.


        SpannableStringBuilder ssb = new SpannableStringBuilder("Welcome to EZRACK");
        //ssb.append("By registering you are agreeing to our privacy policy and terms of use.");

        ssb.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View v) {
                mBinding.txtAutoLoging.callOnClick();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.black));
                ds.setUnderlineText(false);
            }

        }, 0, 10, 0);

        ssb.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View v) {
                mBinding.txtDriverId.callOnClick();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorAccent));
                ds.setUnderlineText(false);
            }

        }, 11, 17, 0);

        mBinding.txtTitle.setText(ssb);

    }

    private void set_typeface() {
        mBinding.edtDriverId.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.edtPassword.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.edtUsername.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.txtDriverId.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.txtUsername.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.txtPassword.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.txtTitle.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.txtAutoLoging.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.txtRememberMe.setTypeface(FontCustom.setFont(LoginActivity.this));
        mBinding.txtLogin.setTypeface(FontCustom.setFont(LoginActivity.this));
    }

    public void settext_watcher()
    {
        setTextWatcher_new(LoginActivity.this,mBinding.edtDriverId,mBinding.txtDriverId);
        //  setTextWatcher_new(LoginActivity.this,mBinding.edtUsername,mBinding.txtUsername);
        setTextWatcher_new(LoginActivity.this,mBinding.edtPassword,mBinding.txtPassword);;
    }

    class ExecuteTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            WebseriveUrl.showProgress(LoginActivity.this);
        }

        @Override
        protected String doInBackground(String... params) {
            Map<String, String> param = new HashMap<String, String>();

            param.put(Constants.Login_param[0],driveid);
            param.put(Constants.Login_param[1], driveid);
            param.put(Constants.Login_param[2], password);
            param.put(Constants.Login_param[3], isAutoLoging);
            param.put(Constants.Login_param[4], isRememberMe);
            String res=WebseriveUrl.PostData(WebseriveUrl.LOGIN,param);
            Log.e("res....",""+res);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                WebseriveUrl.dismissProgress();
                JSONObject json2;
                Log.e(getLocalClassName(),"result"+result);
                json2 = new JSONObject(result);

                //update_preference_token(getActivity(), json2.getString("token"));
                if (json2.getString("success").equals("true")) {


                    JSONObject jsonObject = json2.getJSONObject("response");
                    String authKey = jsonObject.optString("authKey");
                    Pref.setValue(LoginActivity.this,"authKey",authKey);
                    String master_company = jsonObject.optString("master_company");
                    String force_password_change = jsonObject.optString("force_password_change");

                    if(isAutoLoging.equalsIgnoreCase("1")) {
                        Pref.setValue(LoginActivity.this, "isAlreadyLogin", "1");

                    }else
                    {
                        Pref.setValue(LoginActivity.this, "isAlreadyLogin", "0");

                    }

                    if (isRememberMe.equalsIgnoreCase("1")) {
                        Pref.setValue(LoginActivity.this, "driveid1", driveid);
                        Pref.setValue(LoginActivity.this, "password", password);
                    } else {
                        Pref.setValue(LoginActivity.this, "driveid1", "");
                        Pref.setValue(LoginActivity.this, "password", "");
                    }

                    if(isRememberMe.equalsIgnoreCase("1")) {
                        Pref.setValue(LoginActivity.this, "isRememberMe", "1");
                    }else
                    {
                        Pref.setValue(LoginActivity.this, "isRememberMe", "0");
                    }
                    /**
                     * if login successfully go to the order screen.
                     */
                    if(force_password_change.equalsIgnoreCase("N"))
                    {
                        Intent intent = new Intent(LoginActivity.this,
                                NewOrderActivity.class);
                        startActivity(intent);
                        finish();

                    }else if(force_password_change.equalsIgnoreCase("Y"))
                    {
                        Intent intent = new Intent(LoginActivity.this,
                                ChangePasswordActivity.class);
                        startActivity(intent);
                        finish();


                    }


                }else if(json2.getString("error").equals("true"))
                {
                    JSONArray jsonArray = json2.getJSONArray("errors");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String message = jsonObject.optString("message");

                        Log.e("message",""+message);
                        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
