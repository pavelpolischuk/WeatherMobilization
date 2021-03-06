package com.ikholopov.yamblz.weather.weathermobilization;

import com.ikholopov.yamblz.weather.weathermobilization.di.component.ApplicationComponent;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.UpdateServiceController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.threeten.bp.Duration;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 26.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class OnBootTest {

    @Mock WeatherApplication application;
    @Mock ApplicationComponent component;
    @Mock PreferencesProvider preferences;
    @Mock UpdateServiceController serviceController;

    private OnBoot onBoot = new OnBoot();

    @Before
    public void setUp() throws Exception {
        when(application.getApplicationContext()).thenReturn(application);
        when(application.getComponent()).thenReturn(component);
        onBoot.preferences = preferences;
        onBoot.serviceController = serviceController;
    }

    @Test
    public void startUpdatingOnBootTest() throws Exception {
        Duration duration = Duration.ofSeconds(5);
        when(preferences.getAutoUpdateInterval()).thenReturn(duration);

        onBoot.onReceive(application, null);

        verify(serviceController).enableService(duration);
    }

    @Test
    public void stopUpdatingOnBootTest() throws Exception {
        when(preferences.getAutoUpdateInterval()).thenReturn(Duration.ZERO);

        onBoot.onReceive(application, null);

        verify(serviceController).disableService();
    }
}