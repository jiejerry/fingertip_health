package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.callback.AddressCallBack;
import com.jqsoft.fingertip_health.di.ui.activity.IgGuideActivity;
import com.jqsoft.fingertip_health.util.StringUtils;
import com.jqsoft.fingertip_health.util.Util;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class IgGuideCityFragment implements AdapterView.OnItemClickListener{
    private AddressCallBack callBack;
    private String code;
    private Context context;
    private AddressAdapter adapter;
    private ListView listview;
    private String provinceCode ;

    private   List<SRCLoginAreaBean>   allcityList   = DataSupport.where(" areaLevel=? and state=?","area_3","0" ).find(SRCLoginAreaBean.class);
    List<SRCLoginAreaBean> cityList = new ArrayList<>();
    public IgGuideCityFragment(Context context, AddressCallBack callBack){
        this.context = context;
        this.callBack = callBack;
        initView();
    }
    public void setCode(String provinceCode){

//        List<gdws_sys_area>   list = DataSupport.where(" areaCode=? ", Identity.srcInfo.getAreaId() ).find(gdws_sys_area.class);
//        if (list.get(0).getAreaLevel().equals("area_2")){
//            allcityList = DataSupport.where(" areaLevel=? ","area_4" ).find(gdws_sys_area.class);
//
//        }else {

//        }

        if (!provinceCode.equals(this.provinceCode)){
            this.code = null;
        }
        if (StringUtils.isNoEmpty(code)){
            this.code = code;
        }
        this.provinceCode = provinceCode;
        cityList.clear();
        for (int i=0;i<allcityList.size();i++){
            if (allcityList.get(i).getAreaPid().equals(provinceCode)){
                cityList.add(allcityList.get(i));
            }

        }
        adapter.notifyDataSetChanged();
    }
    public ListView getListview() {
        return listview;
    }

    public View initView() {
        listview = (ListView) LayoutInflater.from(context).inflate(R.layout.select_address_pop_listview,null);
        //cityList = AddressManager.newInstance().findProvinceByCode(provinceCode).getAllCities();
        adapter = new AddressAdapter(cityList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        return listview;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        code = cityList.get(i).getAreaPid();
        if (callBack != null){


//                callBack.selectCity(cityList.get(i));
                code = cityList.get(i).getAreaCode();

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.GUIDE_ITEM_ACTIVITY_KEY,  cityList.get(i).getAreaCode());

                Util.gotoActivityWithBundle(context, IgGuideActivity.class, bundle);

        }
        adapter.notifyDataSetChanged();
    }

    class AddressAdapter extends BaseAdapter {

        private  List<SRCLoginAreaBean> list;

        public AddressAdapter( List<SRCLoginAreaBean> list){
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).inflate(R.layout.address_listiew_item_textview, null);
            TextView text = (TextView) view.findViewById(R.id.tvTextName);
            ImageView ivSelect = (ImageView) view.findViewById(R.id.ivSelect);
            text.setText(list.get(i).getAreaName());
            if (list.get(i).getAreaCode().equals(code)) {
                text.setTextColor(context.getResources().getColor(R.color.new_redb));
                ivSelect.setVisibility(View.VISIBLE);
            }
            return view;
        }
    }
}
