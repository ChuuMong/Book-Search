package io.chuumong.booksearch.ui.adapter;


import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.chuumong.booksearch.R;
import io.chuumong.booksearch.data.model.Book;
import io.chuumong.booksearch.data.model.Search;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private final List<Book> books;
    private OnClickBookItemListener listener;

    @Inject
    public SearchAdapter() {
        books = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_book_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bind(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void add(Search search) {
        if (!search.getBooks().isEmpty()) {
            this.books.addAll(search.getBooks());
            notifyDataSetChanged();
        }
    }

    public void clear() {
        books.clear();
        notifyDataSetChanged();
    }

    public void setListener(OnClickBookItemListener listener) {
        this.listener = listener;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout)
        RelativeLayout layout;

        @BindView(R.id.text_title)
        TextView tvTitle;

        @BindView(R.id.text_price)
        TextView tvPrice;
        @BindView(R.id.image_price_arrow)
        ImageView tvPriceArrow;
        @BindView(R.id.text_discount)
        TextView tvDiscount;

        @BindView(R.id.text_author)
        TextView tvAuthor;
        @BindView(R.id.text_publisher)
        TextView tvPublisher;
        @BindView(R.id.text_pub_date)
        TextView tvPubDate;

        @BindView(R.id.text_description)
        TextView tvDesciption;

        @BindView(R.id.image_book)
        ImageView ivBook;

        SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Book book) {
            tvTitle.setText(Html.fromHtml(book.getTitle()));

            if (TextUtils.isEmpty(book.getDiscount())) {
                tvPrice.setText(String.format(Locale.getDefault(), "%,d원",
                        Integer.valueOf(book.getPrice())));
                tvPrice.setPaintFlags(0);
                tvPriceArrow.setVisibility(View.GONE);
            } else {
                tvPrice.setText(String.format(Locale.getDefault(), "%,d원",
                        Integer.valueOf(book.getPrice())));
                tvDiscount.setText(String.format(Locale.getDefault(), "%,d원",
                        Integer.valueOf(book.getDiscount())));
                tvPrice.setPaintFlags(tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tvPriceArrow.setVisibility(View.VISIBLE);
            }

            tvAuthor.setText(Html.fromHtml(book.getAuthor()));
            tvPublisher.setText(Html.fromHtml(book.getPublisher()));
            tvPubDate.setText(book.getPubdate());
            tvDesciption.setText(Html.fromHtml(book.getDescription()));


            Glide.with(itemView.getContext()).load(book.getImage()).into(ivBook);

            layout.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClickBookItem(book);
                }
            });
        }
    }

    public interface OnClickBookItemListener {
        void onClickBookItem(Book book);
    }
}
