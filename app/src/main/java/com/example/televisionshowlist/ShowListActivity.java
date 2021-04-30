package com.example.televisionshowlist;

import androidx.fragment.app.Fragment;

public class ShowListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ShowListFragment();
    }

}
