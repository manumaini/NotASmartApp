package com.covidapp.notasmartapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.covidapp.notasmartapp.POJO.Article;
import com.covidapp.notasmartapp.R;
import com.covidapp.notasmartapp.Views.WebActivity;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

public class HealthNewsAdapter extends RecyclerView.Adapter<HealthNewsAdapter.ViewHolder> {

    private List<Article> list;
    private Context context;

    public HealthNewsAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public HealthNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_news,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HealthNewsAdapter.ViewHolder holder, int position) {

        Article article = list.get(position);
        holder.title.setText(article.getTitle());
        holder.author.setText("-"+article.getAuthor());
        holder.desc.setText(article.getDescription());
        PushDownAnim.setPushDownAnimTo(holder.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url",article.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListData(List<Article> list){
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView author;
        public TextView desc;
        public ImageView next;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            author = itemView.findViewById(R.id.news_author);
            desc = itemView.findViewById(R.id.news_desc);
            next = itemView.findViewById(R.id.news_next);
        }
    }
}
