package com.mobilization.favorites.history;

import com.mobilization.models.Translate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 04.04.17.
 */

public interface HistoryInteractor {

    void addHistory(Translate t);

    List<Translate> getHistory();

    List<Translate> getHistory(String substr);

    void deleteHistory(Translate t);

    void addFavorite(Translate t);

}
