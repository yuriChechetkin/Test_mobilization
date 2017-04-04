package com.mobilization.favorites.history;

import dagger.Subcomponent;

/**
 * Created by mac on 04.04.17.
 */

@HistoryScope
@Subcomponent(modules = {HistoryModule.class})
public interface HistoryComponent {
    HistoryFragment inject(HistoryFragment fragment);
}
