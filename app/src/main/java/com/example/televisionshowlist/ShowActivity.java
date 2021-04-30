package com.example.televisionshowlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class ShowActivity extends SingleFragmentActivity {

    private static final String EXTRA_SHOW_ID =
            "com.example.TelevisionShowList.show_id";

    public static Intent newIntent(Context packageContext, UUID showId) {
        Intent intent = new Intent(packageContext, ShowActivity.class);
        intent.putExtra(EXTRA_SHOW_ID, showId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID showId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_SHOW_ID);
        return ShowFragment.newInstance(showId);
    }

}