package com.jqsoft.fingertip_health.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.bean.TreatdirectoryBean;
import com.jqsoft.fingertip_health.bean.fingertip.OutpatientBeanForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.ElectronicPrescriptionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.OutpatientChargesActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;

public class RecipeFragmentAdapter extends RecyclerView.Adapter<RecipeFragmentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DrugInfo> datalist = new ArrayList<>();


    public RecipeFragmentAdapter(ArrayList<DrugInfo> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    //返回子项个数
    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipeinfo_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DrugInfo drugInfo = datalist.get(position);
        holder.tv_disname.setText(drugInfo.getName());
        holder.productadd.setText(drugInfo.getSupplier());
        holder.tv_disguige.setText(drugInfo.getSpec());
        holder.tv_pin.setText(drugInfo.getTrans()+drugInfo.getUnit()+"/"+drugInfo.getUnitYkName());
        holder.jixing.setText(drugInfo.getDose());
        holder.tv_yibao.setText(drugInfo.getIsReim());
        holder.time.setText(drugInfo.getDay());
        holder.durgcode.setText(drugInfo.getId());
        holder.ku_cun.setText(drugInfo.getStock());
        holder.ku_cun_pic.setText(drugInfo.getUnit());
        holder.price.setText(drugInfo.getPriceSale());
        holder.pricepic.setText("元/"+drugInfo.getUnitYkName());
        holder.add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                didSelectOnePatient(datalist.get(position));
            }
        });


    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView add_btn;
        public TextView tv_disname;
        public TextView productadd;
        public TextView tv_disguige, tv_pin, jixing, tv_yibao, time, durgcode, ku_cun,ku_cun_pic, price,pricepic;

        public ViewHolder(View view) {
            super(view);
            add_btn = (TextView) view.findViewById(R.id.add_btn);
            tv_disname = (TextView) view.findViewById(R.id.tv_disname);
            productadd = (TextView) view.findViewById(R.id.productadd);
            tv_disguige = (TextView) view.findViewById(R.id.tv_disguige);
            tv_pin = (TextView) view.findViewById(R.id.tv_pin);
            jixing = (TextView) view.findViewById(R.id.jixing);
            tv_yibao = (TextView) view.findViewById(R.id.tv_yibao);
            time = (TextView) view.findViewById(R.id.time);
            durgcode = (TextView) view.findViewById(R.id.durgcode);
            ku_cun = (TextView) view.findViewById(R.id.ku_cun);
            ku_cun_pic=(TextView)view.findViewById(R.id.ku_cun_pic);
            price = (TextView) view.findViewById(R.id.price);
            pricepic=(TextView)view.findViewById(R.id.pricepic);
        }
    }

    private void didSelectOnePatient(DrugInfo bean) {
        Activity activity = Util.scanForActivity(context);
        if (activity instanceof ElectronicPrescriptionActivity) {
            ElectronicPrescriptionActivity oca = (ElectronicPrescriptionActivity) activity;
            oca.handleDrugInfo(bean);
        }
    }
}
