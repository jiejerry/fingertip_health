package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.FamilyMemberActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.FamilyMemberActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = FamilyMemberActivityModule.class)
public interface FamilyMemberActivityComponent {
    void inject(FamilyMemberActivity familyMemberActivity);
}
