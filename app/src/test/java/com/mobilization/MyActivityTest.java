package com.mobilization;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by mac on 24.04.17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21, application = BaseApplication.class)
public class MyActivityTest {
    private MainActivity mainActivity;
    private DetailTextActivity detailTextActivity;

    @Before
    public void setup()  {
        mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create().get();
        detailTextActivity = Robolectric.buildActivity(DetailTextActivity.class)
                .create().get();
    }
    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(mainActivity);
        assertNotNull(detailTextActivity);
    }

    @Test
    public void checkResources() throws Exception {
        String favorite = mainActivity.getResources().getString(R.string.favorite);
        String history = mainActivity.getResources().getString(R.string.history);
        assertEquals(favorite, "Избранное");
        assertEquals(history, "История");
    }
}