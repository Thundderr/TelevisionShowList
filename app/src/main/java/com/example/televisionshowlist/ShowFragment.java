package com.example.televisionshowlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;
import java.util.UUID;

public class ShowFragment extends Fragment {

    private static final String ARG_SHOW_ID = "show_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Show mShow;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mWatchedCheckBox;
    private CheckBox mRecommendCheckBox;

    public static ShowFragment newInstance(UUID showId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SHOW_ID, showId);

        ShowFragment fragment = new ShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID showId = (UUID) getArguments().getSerializable(ARG_SHOW_ID);
        mShow = ShowLab.get(getActivity()).getShow(showId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show, container, false);

        mTitleField = (EditText) v.findViewById(R.id.show_title);
        mTitleField.setText(mShow.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
                //Blank
            }

            @Override
            public void onTextChanged(
                CharSequence s, int start, int before, int count) {
                mShow.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Blank
            }
        });

        mDateButton = (Button) v.findViewById(R.id.show_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "Changing date...", Toast.LENGTH_SHORT)
                        .show();

                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mShow.getDate());
                dialog.setTargetFragment(ShowFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mWatchedCheckBox = (CheckBox) v.findViewById(R.id.show_watched);
        mWatchedCheckBox.setChecked(mShow.isWatched());
        mWatchedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getActivity(),
                        "Updated Watching", Toast.LENGTH_SHORT)
                        .show();
                mShow.setWatched(isChecked);
            }
        });

        mRecommendCheckBox = (CheckBox) v.findViewById(R.id.show_recommend);
        mRecommendCheckBox.setChecked(mShow.isRecommend());
        mRecommendCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getActivity(),
                        "Updated Recommend", Toast.LENGTH_SHORT)
                        .show();
                mShow.setRecommend(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mShow.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mShow.getDate().toString());
    }
}
