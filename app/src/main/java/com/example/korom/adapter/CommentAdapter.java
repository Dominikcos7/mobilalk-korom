package com.example.korom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.korom.R;
import com.example.korom.model.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> implements Filterable {
    private ArrayList<Comment> mItemsData;
    private ArrayList<Comment> mItemsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    public CommentAdapter(Context context, ArrayList<Comment> itemsData) {
        mItemsData = itemsData;
        mItemsDataAll = itemsData;
        mContext = context;
    }
    private Filter commentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Comment> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length() == 0){
                results.count = mItemsDataAll.size();
                results.values = mItemsDataAll;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Comment c : mItemsDataAll){
                    if(c.getUsername().contains(filterPattern)){
                        filteredList.add(c);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }


            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mItemsData = (ArrayList<Comment>) results.values;
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.discussion_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        Comment currentItem = mItemsData.get(position);
        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return mItemsData.size();
    }

    @Override
    public Filter getFilter() {
        return commentFilter;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView username;
        private TextView content;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.commentUsername);
            content = itemView.findViewById(R.id.commentContent);
            ratingBar = itemView.findViewById(R.id.commentRatingBar);
        }

        public void bindTo(Comment currentItem) {
            username.setText(currentItem.getUsername());
            content.setText(currentItem.getContent());
            ratingBar.setRating(currentItem.getRating());
        }
    }
}
