package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.IndexFragmentModule;
import com.jqsoft.fingertip_health.di.module.TreatFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.IndexFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.TreatProjectFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = TreatFragmentModule.class)
public interface TreatFragmentComponent {
    void inject(TreatProjectFragment treatProjectFragment);
}
