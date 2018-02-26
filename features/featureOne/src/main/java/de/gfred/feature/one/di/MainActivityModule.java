package de.gfred.feature.one.di;

import com.mytaxi.shared.models.navigation.IFeatureOneNavigator;
import com.mytaxi.shared.models.navigation.INavigationProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.gfred.feature.one.IMainPresenter;
import de.gfred.feature.one.MainPresenter;

@Singleton
@Module
public class MainActivityModule
{
    @Provides
    public IFeatureOneNavigator provideFeatureOneNavigator(final INavigationProvider navigationProvider)
    {
        return navigationProvider.provideFeatureOneNavigator();
    }

    @Provides
    public IMainPresenter provideMainPresenter(final IFeatureOneNavigator navigator)
    {
        return new MainPresenter(navigator);
    }
}