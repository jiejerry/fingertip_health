package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.InstitutionCharacterClassificationStatisticsFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.statistics.InstitutionCharacterClassificationStatisticsFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = InstitutionCharacterClassificationStatisticsFragmentModule.class)
public interface InstitutionCharacterClassificationStatisticsFragmentComponent {
    void inject( InstitutionCharacterClassificationStatisticsFragment fragment);
}
