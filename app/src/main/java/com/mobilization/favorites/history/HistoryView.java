package com.mobilization.favorites.history;

import com.mobilization.models.Translate;

import java.util.List;

/**
 * Created by mac on 04.04.17.
 */

public interface HistoryView {

    void showHistory(List<Translate> history);

    void deleteHistory(Translate t);

    void clearHistory();

    void addFavorite(Translate t);

    void loadingStarted();

    void loadingFailed(String errorMessage);

    void onHistoryClicked(Translate translation);
}