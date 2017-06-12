package ezracks.com.ezracks.ezracks;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ezracks.com.ezracks.Model.Store;
import ezracks.com.ezracks.R;

public class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBar();
    }


    public  void setTextWatcher_new(final Context context, final EditText editText, final TextView textView) {

        TextWatcher textWatcher = new TextWatcher() {
            boolean flag = false;

            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                if (arg0.length() == 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int a, int b, int c) {




                if (s.length() == 0) {


                    textView.setVisibility(View.INVISIBLE);

                } else {
                    if (s.length() == 1 && flag) {


                    }
                    editText.setError(null);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        };
        editText.addTextChangedListener(textWatcher);
    }

    public  void StatusBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
           getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

    }
}
