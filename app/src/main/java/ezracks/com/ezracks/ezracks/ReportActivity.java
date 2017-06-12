package ezracks.com.ezracks.ezracks;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ezracks.com.ezracks.Adapter.ReportAdapter;
import ezracks.com.ezracks.Model.Report;
import ezracks.com.ezracks.R;
import ezracks.com.ezracks.databinding.ActivityReportBinding;

public class ReportActivity extends BaseActivity {

    ActivityReportBinding mBinding;
    ArrayList<Report> reportArrayList;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_report);
        myCalendar = Calendar.getInstance();
        /**
         *  set material design functionality
         */
        settext_watcher();
        mBinding.edtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mBinding.edtDate.setEnabled(false);
                date();
                return false;
            }
        });
        mBinding.txtGetReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                if(mBinding.edtDriverId.getText().toString().length()==0)
                {
                    mBinding.edtDriverId.setError("Driver ID is required!");

                }
                else if(mBinding.edtDate.getText().toString().length()==0)
                {
                    mBinding.edtDate.setError("Date is required!");
                }
                else {
                    reportArrayList = new ArrayList<>();

                    Report[] reports = new Report[2];
                    reports[0] = new Report();
                    reports[0].setName("Lowes");
                    reports[0].setAddress("5143 highway 90");
                    reports[0].setOrder_number("295275");
                    reports[0].setTotal_quantity("280");
                    reports[0].setCity("Pace");
                    reports[0].setState("Fl");
                    reports[0].setZipcode("32571");

                    reports[1] = new Report();
                    reports[1].setName("Home Depot");
                    reports[1].setAddress("414 Mary Esther Cutt Off");
                    reports[1].setOrder_number("295611");
                    reports[1].setTotal_quantity("319");
                    reports[1].setCity("Fort Walton Bch");
                    reports[1].setState("Fl");
                    reports[1].setZipcode("32547");
                    reportArrayList.add(reports[0]);
                    reportArrayList.add(reports[1]);
                    ReportAdapter adapter = new ReportAdapter(ReportActivity.this, reportArrayList);
                    mBinding.lstreport.setAdapter(adapter);
                }

            }
        });

    }

    public void date()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ReportActivity.this, date,
                myCalendar.get(Calendar.YEAR) , myCalendar
                .get(Calendar.MONTH), myCalendar
                .get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();

        datePickerDialog.setCanceledOnTouchOutside(false);

        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mBinding.edtDate.setEnabled(true);
                mBinding.txtDate.setVisibility(View.INVISIBLE);
                mBinding.edtDate.setText("");
                mBinding.edtDate.setError(null);
            }
        });

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
    public void settext_watcher()
    {
        setTextWatcher_new(ReportActivity.this,mBinding.edtDriverId,mBinding.txtDriverId);

    }
}
