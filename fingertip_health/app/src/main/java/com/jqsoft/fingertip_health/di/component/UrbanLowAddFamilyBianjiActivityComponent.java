package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.UrbanLowAddFamilyActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.UrbanFamilybianjiActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = UrbanLowAddFamilyActivityModule.class)
public interface UrbanLowAddFamilyBianjiActivityComponent {
    void inject(UrbanFamilybianjiActivity policyActivity);
}
