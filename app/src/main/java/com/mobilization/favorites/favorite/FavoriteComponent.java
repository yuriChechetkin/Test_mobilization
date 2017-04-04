package com.mobilization.favorites.favorite;

import dagger.Subcomponent;

/**
 * Created by mac on 04.04.17.
 */

@FavoriteScope
@Subcomponent(modules = {FavoriteModule.class})
public interface FavoriteComponent {
    FavoriteFragment inject(FavoriteFragment fragment);
}