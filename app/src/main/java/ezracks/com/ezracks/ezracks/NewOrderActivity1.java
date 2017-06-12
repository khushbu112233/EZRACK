package ezracks.com.ezracks.ezracks;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import ezracks.com.ezracks.Adapter.StoreNameAdapter;
import ezracks.com.ezracks.Model.Store;
import ezracks.com.ezracks.R;
import ezracks.com.ezracks.Webservice.WebseriveUrl;
import ezracks.com.ezracks.databinding.ActivityNewOrderBinding;
import ezracks.com.ezracks.utils.ConnectionDetector;
import ezracks.com.ezracks.utils.Constants;
import ezracks.com.ezracks.utils.FieldsValidator;
import ezracks.com.ezracks.utils.FontCustom;
import ezracks.com.ezracks.utils.Pref;

public class NewOrderActivity1 extends BaseActivity{

    /**
     * Data object declaration
     */
    ActivityNewOrderBinding mBinding;
    Calendar myCalendar;
    public  ArrayList<Store> storeArrayList = new ArrayList<>();
    StoreNameAdapter adapter;
    String ischecked="";
    ConnectionDetector cd;
    FieldsValidator mValidator;
    int isdisplay=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_order);
        myCalendar = Calendar.getInstance();
        cd = new ConnectionDetector(this);
        mValidator = new FieldsValidator(NewOrderActivity1.this);
        if(Pref.getValue(NewOrderActivity1.this,"success","").equalsIgnoreCase("1"))
        {
            mBinding.edtOrderNumber.setText("");
            mBinding.edtDate.setText("");
            mBinding.autoStoreCode.setText("");
            myCalendar = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(myCalendar.getTime());
            mBinding.edtDate.setHint(formattedDate);
            storeArrayList = new ArrayList<>();
            storeArrayList.clear();
            Pref.setValue(NewOrderActivity1.this, "Address","");
            Pref.setValue(NewOrderActivity1.this, "Name", "");
            Pref.setValue(NewOrderActivity1.this, "City", "");
            Pref.setValue(NewOrderActivity1.this, "State","");
            Pref.setValue(NewOrderActivity1.this, "Zip", "");
            Pref.setValue(NewOrderActivity1.this, "FLStore", "");

        }
        /**
         * set font style
         */
        set_typeface();
        /**
         *  set default date
         */
        set_default_date();
        /**
         *  set material design functionality
         */
        //settext_watcher();
        /**
         *  set default date
         */
/**
 * logout
 */
        mBinding.txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(NewOrderActivity1.this);
                //Uncomment the below code to Set the message and title from the strings.xml file
                //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Are you sure you want to log out ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Pref.deleteAll(NewOrderActivity1.this);
                                Intent intent = new Intent(NewOrderActivity1.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle(getResources().getString(R.string.app_name));
                alert.show();


            }
        });
        /**
         *text watcher for auto complete textview
         */
        mBinding.autoStoreCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               /* storeArrayList.clear();
                mBinding.edtDate.setEnabled(true);
*/

                if(editable.toString().trim().equalsIgnoreCase("")){

                   /* Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = df.format(c.getTime());
                    mBinding.edtDate.setHint(formattedDate);
                    mBinding.edtDate.setText(formattedDate);
                    mBinding.edtDate.setEnabled(true);*/
                    storeArrayList.clear();
                    Pref.setValue(NewOrderActivity1.this, "Address", "");
                    Pref.setValue(NewOrderActivity1.this, "Name","");
                    //date();
                    // Pref.setValue(NewOrderActivity.this,"OrderDate","");
                    //mBinding.edtOrderNumber.setText(Pref.getValue(NewOrderActivity.this,"OrderDate",""));
                    Pref.setValue(NewOrderActivity1.this, "City","");
                    Pref.setValue(NewOrderActivity1.this, "State", "");
                    Pref.setValue(NewOrderActivity1.this, "FLStore","");
                    Pref.setValue(NewOrderActivity1.this, "Zip", "");
                    Pref.setValue(NewOrderActivity1.this,"descrip1","");
                    Pref.setValue(NewOrderActivity1.this,"descrip2","");
                    Pref.setValue(NewOrderActivity1.this,"descrip3","");
                    Pref.setValue(NewOrderActivity1.this,"descrip4","");
                    Pref.setValue(NewOrderActivity1.this,"descrip5","");
                    Pref.setValue(NewOrderActivity1.this,"descrip6","");
                    Pref.setValue(NewOrderActivity1.this,"descrip7","");
                    Pref.setValue(NewOrderActivity1.this,"descrip8","");
                }
                if (editable.toString().trim().length()>0) {
                    if (mValidator.isValidWord(editable.toString().trim())) {
                        if (cd.isConnectingToInternet()) {
                            storeArrayList.clear();
                            isdisplay=0;
                            new ExecuteTask().execute(editable.toString().trim().toLowerCase());
                        } else {
                            Toast.makeText(NewOrderActivity1.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        mBinding.edtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mBinding.edtDate.setEnabled(false);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c.getTime());
                mBinding.edtDate.setHint(formattedDate);
                date();
                return false;
            }
        });
        /**
         * click event for go to the store page
         */

        mBinding.txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    store_code = mBinding.edtStoreCode.getText().toString();
                Pref.setValue(NewOrderActivity1.this,"Order_Number",mBinding.edtOrderNumber.getText().toString());
                Pref.setValue(NewOrderActivity1.this,"OrderDate",mBinding.edtDate.getText().toString());
                Pref.setValue(NewOrderActivity1.this, "StoreCode", mBinding.autoStoreCode.getText().toString());
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                Log.e("storeArrayList...k",""+storeArrayList.size());
                if(mBinding.autoStoreCode.getText().toString().trim().length()>0) {
                    if(mBinding.edtOrderNumber.getText().toString().trim().length()>0) {
                        if (storeArrayList.size()>0) {
                            for (int temp = 0;temp<storeArrayList.size();temp++) {
                                if (mBinding.autoStoreCode.getText().toString().toLowerCase().equalsIgnoreCase(storeArrayList.get(temp).getStore_name())) {
                                    isdisplay++;
                                    Pref.setValue(NewOrderActivity1.this,"store_code",storeArrayList.get(temp).getStore_name());
                                    Log.e("isdisplay",""+isdisplay);
                                    Log.e("editvalue",""+mBinding.autoStoreCode.getText().toString());
                                    Log.e("editarrayvalue",""+storeArrayList.get(temp).getStore_name());

                                }else
                                {
                                    Pref.setValue(NewOrderActivity1.this,"store_code",mBinding.autoStoreCode.getText().toString());
                                }
                            }

                            if(isdisplay>0)
                            {
                                Log.e("size_of_array",""+storeArrayList.size());
                                Log.e("selected_position_submit",""+Pref.getValue(NewOrderActivity1.this, "selected_position", 0));
                                if (storeArrayList.size() == 1) {
                                    Pref.setValue(NewOrderActivity1.this, "Address",storeArrayList.get(0).getAddress());
                                    Pref.setValue(NewOrderActivity1.this, "Name", storeArrayList.get(0).getStore_name());
                                    Pref.setValue(NewOrderActivity1.this, "City", storeArrayList.get(0).getCity());
                                    Pref.setValue(NewOrderActivity1.this, "State", storeArrayList.get(0).getState());
                                    Pref.setValue(NewOrderActivity1.this, "Zip", storeArrayList.get(0).getZipcode());
                                    Pref.setValue(NewOrderActivity1.this, "FLStore", storeArrayList.get(0).getFl_store_to_be_reassigned());


                                } else {
                                    Pref.setValue(NewOrderActivity1.this, "Address", storeArrayList.get(Pref.getValue(NewOrderActivity1.this, "selected_position", 0)).getAddress());
                                    Pref.setValue(NewOrderActivity1.this, "Name", storeArrayList.get(Pref.getValue(NewOrderActivity1.this, "selected_position", 0)).getStore_name());
                                    Pref.setValue(NewOrderActivity1.this, "City", storeArrayList.get(Pref.getValue(NewOrderActivity1.this, "selected_position", 0)).getCity());
                                    Pref.setValue(NewOrderActivity1.this, "State", storeArrayList.get(Pref.getValue(NewOrderActivity1.this, "selected_position", 0)).getState());
                                    Pref.setValue(NewOrderActivity1.this, "Zip", storeArrayList.get(Pref.getValue(NewOrderActivity1.this, "selected_position", 0)).getZipcode());
                                    Pref.setValue(NewOrderActivity1.this, "FLStore", storeArrayList.get(Pref.getValue(NewOrderActivity1.this, "selected_position", 0)).getFl_store_to_be_reassigned());

                                }

                            }
                            // }
                        }else
                        {
                            storeArrayList.clear();
                            Pref.setValue(NewOrderActivity1.this, "Address","");
                            Pref.setValue(NewOrderActivity1.this, "Name", "");
                            Pref.setValue(NewOrderActivity1.this, "City", "");
                            Pref.setValue(NewOrderActivity1.this, "State","");
                            Pref.setValue(NewOrderActivity1.this, "Zip", "");
                            Pref.setValue(NewOrderActivity1.this, "FLStore", "");

                        }
                        if(mValidator.isValidWord(mBinding.autoStoreCode.getText().toString())) {
                            if(storeArrayList.size()>0) {
                                Intent intent = new Intent(NewOrderActivity1.this, StoreDetailsActivity.class);
                                startActivity(intent);
                            }else
                            {
                                storeArrayList.clear();
                                Pref.setValue(NewOrderActivity1.this, "Address","");
                                Pref.setValue(NewOrderActivity1.this, "Name", "");
                                Pref.setValue(NewOrderActivity1.this, "City", "");
                                Pref.setValue(NewOrderActivity1.this, "State","");
                                Pref.setValue(NewOrderActivity1.this, "Zip", "");
                                Pref.setValue(NewOrderActivity1.this, "FLStore", "");

                            }
                        }else
                        {
                            Toast.makeText(NewOrderActivity1.this,"Please enter correct store code",Toast.LENGTH_LONG).show();
                        }
                    }else
                    {
                        mBinding.edtOrderNumber.setError("Order Number is required!");
                    }
                }else
                {
                    mBinding.autoStoreCode.setError("Store Code is required!");
                }


            }
        });


    }
    private void set_typeface() {
        mBinding.autoStoreCode.setTypeface(FontCustom.setFont(NewOrderActivity1.this));
        mBinding.txtStoreCode.setTypeface(FontCustom.setFont(NewOrderActivity1.this));
        mBinding.edtOrderNumber.setTypeface(FontCustom.setFont(NewOrderActivity1.this));
        mBinding.txtOrderNumber.setTypeface(FontCustom.setFont(NewOrderActivity1.this));
        mBinding.edtDate.setTypeface(FontCustom.setFont(NewOrderActivity1.this));
        mBinding.txtDate.setTypeface(FontCustom.setFont(NewOrderActivity1.this));
        mBinding.txtNext.setTypeface(FontCustom.setFont(NewOrderActivity1.this));
        mBinding.txtTitle.setTypeface(FontCustom.setFont(NewOrderActivity1.this));
    }
    public void set_default_date()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(myCalendar.getTime());
        mBinding.edtDate.setText(formattedDate);
        mBinding.txtDate.setVisibility(View.VISIBLE);
    }
    public void date()
    {
        if(Pref.getValue(NewOrderActivity1.this,"OrderDate","").equalsIgnoreCase("")) {
            myCalendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(NewOrderActivity1.this, date,
                    myCalendar.get(Calendar.YEAR), myCalendar
                    .get(Calendar.MONTH), myCalendar
                    .get(Calendar.DAY_OF_MONTH));

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);

            datePickerDialog.show();

            datePickerDialog.setCanceledOnTouchOutside(false);

            datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    mBinding.edtDate.setEnabled(true);
                    mBinding.txtDate.setVisibility(View.VISIBLE);
                }
            });

           /* datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Log.e("cancel","cancel");
                    mBinding.edtDate.setEnabled(true);
                    mBinding.txtDate.setVisibility(View.VISIBLE);
                    mBinding.edtDate.setText("");
                }
            });*/
        }else {
            DatePickerDialog datePickerDialog = new DatePickerDialog(NewOrderActivity1.this, date,
                    myCalendar.get(Calendar.YEAR), myCalendar
                    .get(Calendar.MONTH), myCalendar
                    .get(Calendar.DAY_OF_MONTH));

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);

            datePickerDialog.show();

            datePickerDialog.setCanceledOnTouchOutside(false);

            datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    mBinding.edtDate.setEnabled(true);
                    mBinding.txtDate.setVisibility(View.VISIBLE);
                }
            });

            /*datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Log.e("cancel","cancel");
                    mBinding.edtDate.setEnabled(true);
                    mBinding.txtDate.setVisibility(View.VISIBLE);
                    mBinding.edtDate.setText("");
                }
            });*/
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
// TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mBinding.edtDate.setEnabled(true);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(myCalendar.getTime());
            mBinding.edtDate.setText(formattedDate);
            mBinding.txtDate.setVisibility(View.VISIBLE);

        }

    };

    @Override
    protected void onResume() {
        super.onResume();
       /* if(!Pref.getValue(NewOrderActivity.this, "StoreCode","").equalsIgnoreCase(""))
        {*/

        //}
        if (storeArrayList.size() > 0) {

            ischecked = storeArrayList.get(0).getStore_name();
            Log.e("ischecked",""+ischecked);

            // if (storeArrayList.size() == 1) {
            mBinding.autoStoreCode.setText(Pref.getValue(NewOrderActivity1.this,"store_code",""));
            /*} else {
                mBinding.autoStoreCode.setText(storeArrayList.get(Pref.getValue(NewOrderActivity.this, "selected_position", 0)).getStore_name());

            }*/
            mBinding.edtOrderNumber.setText(Pref.getValue(NewOrderActivity1.this,"Order_Number",""));
        }


    }

    public void settext_watcher()
    {
        setTextWatcher_new(NewOrderActivity1.this,mBinding.autoStoreCode,mBinding.txtStoreCode);
        setTextWatcher_new(NewOrderActivity1.this,mBinding.edtOrderNumber,mBinding.txtOrderNumber);

    }

    /**
     * call api for get store code and details of store code
     */
    class ExecuteTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // WebseriveUrl.showProgress(NewOrderActivity.this);
        }

        @Override
        protected String doInBackground(String... params) {
            Map<String, String> param = new HashMap<String, String>();

            param.put(Constants.Store_detail_param[0],Pref.getValue(NewOrderActivity1.this,"authKey",""));
            param.put(Constants.Store_detail_param[1], params[0]);
            String res=WebseriveUrl.PostData(WebseriveUrl.GET_ROUTE,param);
           // Log.e("res....",""+res);

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

                    //  Pref.setValue(NewOrderActivity.this,"isAlreadyLogin","1");
                    storeArrayList=new ArrayList<>();
                    storeArrayList.clear();
                    JSONObject jsonObject = json2.optJSONObject("response");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Store[] store = new Store[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        store[i]=new Store();
                        store[i].setId(jsonArray.getJSONObject(i).getString("id"));
                        store[i].setStore_name(jsonArray.getJSONObject(i).getString("store_name"));
                        store[i].setAddress(jsonArray.getJSONObject(i).getString("address"));
                        store[i].setCity(jsonArray.getJSONObject(i).getString("city"));
                        store[i].setState(jsonArray.getJSONObject(i).getString("state"));
                        store[i].setZipcode(jsonArray.getJSONObject(i).getString("zipcode"));
                        store[i].setFl_store_to_be_reassigned(jsonArray.getJSONObject(i).getString("fl_store_to_be_reassigned"));
                        //if (mBinding.autoStoreCode.getText().toString().toLowerCase().equalsIgnoreCase(jsonArray.getJSONObject(i).getString("store_name"))) {
                        storeArrayList.add(store[i]);
                        // }
                    }
                   Log.e("size_of_data",storeArrayList.size()+" ");
                    adapter  = new StoreNameAdapter(NewOrderActivity1.this,storeArrayList);
                    mBinding.autoStoreCode.setAdapter(adapter);
                    mBinding.autoStoreCode.setThreshold(1);
                   mBinding.autoStoreCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                            Log.e("poslong",""+i);
                         //  Pref.setValue(NewOrderActivity.this,"selected_position",i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                  /*  mBinding.autoStoreCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                            String selection = (String) parent.getItemAtPosition(i);
                            int pos = -1;

                            for (int j = 0; j < storeArrayList.size(); j++) {
                                if (storeArrayList.get(j).equals(selection)) {
                                    pos = j;
                                    break;
                                }
                            }

                            Pref.setValue(NewOrderActivity.this,"selected_position",pos);
                            Log.e("position",""+pos);
                        }
                    });*/
                    //  filter = adapter.getFilter();


                   /* if(Pref.getValue(NewOrderActivity.this, "StoreCode","").equalsIgnoreCase(ischecked))
                    {
                        Log.e("match","match");
                    }else
                    {
                        Log.e("not match","not match");
                        Pref.setValue(NewOrderActivity.this, "Address", "");
                        Pref.setValue(NewOrderActivity.this, "Name","");
                        //date();

                        Pref.setValue(NewOrderActivity.this, "City","");
                        Pref.setValue(NewOrderActivity.this, "State", "");
                        Pref.setValue(NewOrderActivity.this, "FLStore","");
                        Pref.setValue(NewOrderActivity.this, "Zip", "");
                        Pref.setValue(NewOrderActivity.this,"descrip1","");
                        Pref.setValue(NewOrderActivity.this,"descrip2","");
                        Pref.setValue(NewOrderActivity.this,"descrip3","");
                        Pref.setValue(NewOrderActivity.this,"descrip4","");
                        Pref.setValue(NewOrderActivity.this,"descrip5","");
                        Pref.setValue(NewOrderActivity.this,"descrip6","");
                        Pref.setValue(NewOrderActivity.this,"descrip7","");
                        Pref.setValue(NewOrderActivity.this,"descrip8","");
                    }*/
                }else if(json2.getString("error").equals("true"))
                {
                    JSONObject jsonObject = json2.getJSONObject("errors");
                    String message = jsonObject.optString("message");
                    Log.e("message",""+message);
                    Toast.makeText(NewOrderActivity1.this,message,Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
