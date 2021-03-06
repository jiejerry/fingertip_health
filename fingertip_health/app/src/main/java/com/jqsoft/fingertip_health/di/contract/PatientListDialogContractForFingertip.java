package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface PatientListDialogContractForFingertip {
    interface View{

        void onLoadPatientListInfoSuccess(HttpResultBaseBeanForFingertip<String> bean);

        void onLoadPatientListInfoFailure(String message);


    }

    interface  presenter{

    }
}
