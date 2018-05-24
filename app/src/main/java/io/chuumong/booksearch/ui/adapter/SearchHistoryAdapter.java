package io.chuumong.booksearch.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.chuumong.booksearch.R;
import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.util.DateUtil;
import io.chuumong.booksearch.util.UiUtil;

/**
 * Created by jonghunlee on 2018-05-24.
 */
public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {

    private final List<SearchHistory> searchHistories;

    private OnClickSearchHistoryListener listener;

    @Inject
    public SearchHistoryAdapter() {
        searchHistories = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchHistoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_search_history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryViewHolder holder, int position) {
        holder.bind(searchHistories.get(position));
    }

    @Override
    public int getItemCount() {
        return searchHistories.size();
    }

    public void setListener(OnClickSearchHistoryListener listener) {
        this.listener = listener;
    }

    public void addAll(List<SearchHistory> searchHistories) {
        this.searchHistories.clear();
        this.searchHistories.addAll(searchHistories);
        notifyDataSetChanged();
    }

    public void delete(int position) {
        if (position != -1) {
            searchHistories.remove(position);
            notifyItemRemoved(position);
        }
    }

    class SearchHistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_history)
        TextView tvHistory;

        @BindView(R.id.text_date)
        TextView tvDate;

        @BindView(R.id.button_delete)
        ImageButton ibDelete;

        SearchHistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(SearchHistory searchHistory) {
            if (getAdapterPosition() == 0) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
                params.topMargin = (int) UiUtil.convertDpToPx(8);
                itemView.setLayoutParams(params);
            }

            tvHistory.setText(searchHistory.getSearch());
            tvDate.setText(DateUtil.parserHistoryDate(searchHistory.getDate()));

            ibDelete.setOnClickListener(
                    v -> listener.onClickDelete(searchHistory, getAdapterPosition()));

            itemView.setOnClickListener(v -> listener.onClickItem(searchHistory));
        }
    }

    public interface OnClickSearchHistoryListener {
        void onClickItem(SearchHistory searchHistory);

        void onClickDelete(SearchHistory searchHistory, int position);
    }
}
