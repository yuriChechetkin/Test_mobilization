package com.mobilization.favorites.history;

import com.mobilization.models.Translate;

/**
 * Created by mac on 04.04.17.
 */

public interface HistoryPresenter
{
    void deleteHistory(Translate t);

    void addFavorite(Translate t);

    void displayHistory();

    void clearHistory();

    void displayHistory(String substr);

    void setView(HistoryView view);

    void destroy();
}