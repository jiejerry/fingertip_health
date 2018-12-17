package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.SelectOutPatientChargesBean;
import com.jqsoft.fingertip_health.bean.SelectOutPatientChargesBean;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.view.HorizontalListView;

import java.util.List;


public class SelectChargeListActivityAdapter extends BaseQuickAdapterEx<SelectOutPatientChargesBean, BaseViewHolder> {
    private Context context;

    //  private ListAdapter adapter;
    public SelectChargeListActivityAdapter(List<SelectOutPatientChargesBean> data, Context context) {
        super(R.layout.item_select_charge_layout, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SelectOutPatientChargesBean item) {
        // HorizontalListView listView=helper.getView(R.id.listview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.addItemDecoration(new DividerItemDecoration(context, RecyclerView.HORIZONTAL));
        //   adapter = new HighBloodListItemAdapter(context,item.getPropMap());
        //  listView.setAdapter(adapter);
//        item.setPhotoUrl("http://192.168.88.36:8080/fdss-api/photo/0123456.jpg");
        //  String photoUrl = Util.trimString(item.getPhotoUrl());
        //    String imageUrl = Version.FILE_URL_BASE+photoUrl;

        //   GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));

//        String photoUrl = Util.trimString(item.getFilePath());
//        String imageUrl = Version.FIND_FILE_URL_BASE + photoUrl;
//         GlideUtils.loadImageNew(imageUrl, (ImageView) helper.getView(R.id.iv_title));
//        GlideUtils.load(context,imageUrl,(ImageView) helper.getView(R.id.iv_title));
        //http://192.168.44.134:8080/sri/JingQi_Sri_File/upload/sriattach/wechat/EKZ6H7c7YIGX.jpg
//        Uri uri = Uri.parse(imageUrl);
        SimpleDraweeView draweeView = (SimpleDraweeView) helper.getView(R.id.iv_title);
//        draweeView.setImageURI(uri);
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.objectname, Util.trimString(item.getName()));
        }
        if (!TextUtils.isEmpty(item.getFeeTotal())) {
            helper.setText(R.id.objectidcard, Util.trimString("总费用:" + item.getFeeTotal()+"元"));
        }
        helper.setText(R.id.visitnum, Util.trimString("就诊号:" + item.getVisitNumber()));
        helper.setText(R.id.doctorname, Util.trimString("开单医生: " + item.getSquareDepartmentName()));
//        ImageView iv_sex = helper.getView(R.id.objectsex);
//
//        String sex = Util.trimString(item.getSbp());
//        if (sex.equals("男")) {
//            iv_sex.setImageResource(R.mipmap.icon_sex_man);
//        } else {
//            iv_sex.setImageResource(R.mipmap.icon_sex_woman);
//        }


//        helper.setText(R.id.tv_message, Util.trimString(item.getPostMessage()));
//        String time = Util.trimString(item.getSetTime());
//        String canonicalTime = Util.getYearMonthDayFromFullString(time);
//        helper.setText(R.id.tv_time, canonicalTime);
//        ImageView iv_tang =helper.getView(R.id.iv_tang);


//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
//            }
//        });

    }

//    OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClickListener(String id, String imgUrl, View view);}

}
