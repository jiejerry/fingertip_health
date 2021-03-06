package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.QuestionBean;
import com.jqsoft.fingertip_health.di.ui.activity.QuestionActivity;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;


//常见问题适配器
public class QuestionAdapter extends BaseQuickAdapterEx<QuestionBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;
    private String filePath;
    private int type=TYPE_MULTIPLE_LINE;
    private  List<QuestionBean> data ;
    private Context context;

    public QuestionAdapter(List<QuestionBean> data, int type, Context context) {
        super(R.layout.item_qussingle_line, data);
        this.type = type;
        this.context=context;
        this.data=data;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final QuestionBean item) {
        helper.setText(R.id.tv_content, Util.trimString(item.getTitle()));
        QuestionActivity questionActivity=(QuestionActivity)context;

//        helper.setText(R.id.tv_size, Util.trimString(String.valueOf( questionActivity.getadaptersize()+". ")));
        helper.setText(R.id.tv_size, String.valueOf(helper.getPosition()+"."));
//        helper.setText(R.id.tv_state, Util.trimString(item.getName()));
//        helper.setText(R.id.tv_officetime, Util.trimString(item.getOfficeHours()));
//        helper.setText(R.id.tv_address, Util.trimString(item.getAddress()));
//        helper.setText(R.id.tv_officephone,Util.trimString(item.getTelephone()));
//        filePath = item.getFilePath();
//        String imageUrl = Version.FIND_FILE_URL_BASE+filePath;
//        GlideUtils.loadImageNew(imageUrl, (ImageView) helper.getView(R.id.iv_title));




//        String createDate = Util.trimString(item.getReleaseTime());
//        String processedCreateDate = createDate;
//        String processedCreateDate = Util.getYearMonthDayFromFullString(createDate);
//        if (type==TYPE_MULTIPLE_LINE){
//            processedCreateDate= Constants.PUBLISH_TIME +processedCreateDate;
//        }
//        helper.setText(R.id.tv_date,  processedCreateDate);

//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
//            }
//        });

    }



}
