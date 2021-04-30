package com.example.televisionshowlist;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShowLab {
    private static ShowLab sShowLab;

    private List<Show> mShows;

    public static ShowLab get(Context context) {
        if (sShowLab == null) {
            sShowLab = new ShowLab(context);
        }
        return sShowLab;
    }

    private ShowLab(Context context) {
        mShows = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Show show = new Show();
            show.setTitle("Show #" + i);
            show.setWatched(i % 2 == 0);
            show.setRecommend(i % 4 == 0);
            mShows.add(show);
        }
    }

    public List<Show> getShows() {
        return mShows;
    }

    public Show getShow(UUID id) {
        for (Show show : mShows) {
            if (show.getId().equals(id)) {
                return show;
            }
        }
        return null;
    }
}
