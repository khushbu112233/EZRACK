package ezracks.com.ezracks.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;
import ezracks.com.ezracks.Model.Report;
import ezracks.com.ezracks.R;
import ezracks.com.ezracks.databinding.ListReportBinding;

public class ReportAdapter extends BaseAdapter {

    public static List<Report> reportArrayList;
    private Context context;

    public ReportAdapter(Context context, List<Report> reportArrayList) {
        this.context = context;
        this.reportArrayList=reportArrayList;
    }
    @Override
    public int getCount() {
        if (reportArrayList != null) {
            return reportArrayList.size();
        } else {
            return 0;
        }
    }
    @Override
    public String getItem(int position) {
        if (reportArrayList != null) {
            return reportArrayList.get(position).getAddress();
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListReportBinding binding;
        if(convertView == null) {
          /*  binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context,
                    R.layout.list_report, parent, false));*/
            binding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.list_report,parent,false);
            convertView = binding.getRoot();
        }
        else {
            binding = (ListReportBinding) convertView.getTag();
        }

        binding.txtName.setText(reportArrayList.get(position).getName());
        binding.txtOrderId.setText(reportArrayList.get(position).getOrder_number());
        binding.txtTotalQty.setText(reportArrayList.get(position).getTotal_quantity());
        binding.txtAddress.setText(reportArrayList.get(position).getAddress()+" , "+reportArrayList.get(position).getCity()+" , "+reportArrayList.get(position).getState()+" - "+reportArrayList.get(position).getZipcode());
        convertView.setTag(binding);
        return convertView;
    }

}
