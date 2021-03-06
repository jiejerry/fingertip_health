package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignPeopleManagementActivityModuleForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.SignPeopleManagementActivityForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = SignPeopleManagementActivityModuleForFingertip.class)
public interface SignPeopleManagementActivityComponentForFingertip {
    void inject(SignPeopleManagementActivityForFingertip activity);
}
