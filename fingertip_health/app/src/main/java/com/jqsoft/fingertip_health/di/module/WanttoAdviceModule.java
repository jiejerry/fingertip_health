package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.WanttoAdviceContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class WanttoAdviceModule {

    private WanttoAdviceContract.View view;

    public WanttoAdviceModule(WanttoAdviceContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public WanttoAdviceContract.View providerView(){
        return view;
    }

}
