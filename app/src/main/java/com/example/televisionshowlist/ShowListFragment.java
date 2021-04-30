package com.example.televisionshowlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ShowListFragment extends Fragment {

    private RecyclerView mShowRecyclerView;
    private ShowAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_list, container, false);

        mShowRecyclerView = (RecyclerView) view
                .findViewById(R.id.show_recycler_view);
        mShowRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ShowLab showLab = ShowLab.get(getActivity());
        List<Show> shows = showLab.getShows();

        if (mAdapter == null) {
            mAdapter = new ShowAdapter(shows);
            mShowRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ShowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Show mShow;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mWatchedImageView;
        private ImageView mRecommendImageView;

        public ShowHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_show, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.show_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.show_date);
            mWatchedImageView = (ImageView) itemView.findViewById(R.id.show_watched);
            mRecommendImageView = (ImageView) itemView.findViewById(R.id.show_recommend);
        }

        public void bind(Show show) {
            mShow = show;
            mTitleTextView.setText(mShow.getTitle());
            mDateTextView.setText(mShow.getDate().toString());
            mWatchedImageView.setVisibility(show.isWatched() ? View.VISIBLE : View.GONE);
            mRecommendImageView.setVisibility(show.isRecommend() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mShow.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();

            Intent intent = ShowActivity.newIntent(getActivity(), mShow.getId());
            startActivity(intent);
        }
    }

    private class ShowAdapter extends RecyclerView.Adapter<ShowHolder> {
        private List<Show> mShows;

        public ShowAdapter(List<Show> shows) {
            mShows = shows;
        }

        @Override
        public ShowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ShowHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ShowHolder holder, int position) {
            Show show = mShows.get(position);
            holder.bind(show);
        }

        @Override
        public int getItemCount() {
            return mShows.size();
        }
    }
}
