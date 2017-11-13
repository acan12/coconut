package app.beelabs.com.coconut.ui.adapter;//package app.clappingape.com.androidca.ui.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import app.clappingape.com.androidca.R;
//import app.clappingape.com.androidca.model.pojo.Article;
//
///**
// * Created by clappingape on 8/25/17.
// */
//
//public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
//    List<Article> mItems;
//
//    public CardAdapter() {
//        super();
//        mItems = new ArrayList<Article>();
//    }
//
//    public void addData(Article github) {
//        mItems.add(github);
//        notifyDataSetChanged();
//    }
//
//    public void clear() {
//        mItems.clear();
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.recycler_view, viewGroup, false);
//        ViewHolder viewHolder = new ViewHolder(v);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        Article github = mItems.get(i);
//        viewHolder.login.setText(github.getTitle());
//        viewHolder.repos.setText("repos: " + github.getAuthor());
//        viewHolder.blog.setText("blog: " + github.getDescription());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mItems.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView login;
//        public TextView repos;
//        public TextView blog;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            login = (TextView) itemView.findViewById(R.id.author);
//            repos = (TextView) itemView.findViewById(R.id.title);
//            blog = (TextView) itemView.findViewById(R.id.desc);
//        }
//    }
//}