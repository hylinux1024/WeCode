package net.angrycode.wehub.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装了RecycleAdapter
 * Created by lancelot on 2016/12/3.
 */

public abstract class BaseSimpleRecycleAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    protected List<T> mList = new ArrayList<>();
//    private int mCount;

    public void setData(List<T> list) {
        mList = list;
//        if (list != null) {
//            mCount = list.size();
//        }
        notifyDataSetChanged();
    }

    public void addData(T t) {
        if (mList != null) {
            mList = new ArrayList<>();
        }
        mList.add(t);
        notifyItemInserted(mList.size() - 1);
    }

    public List<T> getData() {
        return mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutRes = getItemLayout(viewType);
        if (layoutRes > 0) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(viewType), parent, false);
            return new ViewHolder(itemView);
        }
        return null;
    }

    /**
     * 获取item layout
     *
     * @return
     */
    public abstract int getItemLayout(int viewType);

    /**
     * 渲染item
     *
     * @param holder
     * @param position
     */
    public abstract void onRender(ViewHolder holder, int position);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(view -> mOnItemClickListener.onItemClick(holder.itemView, position));
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(view -> {
                mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                return true;
            });
        }
        onRender(holder, holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, int position);
    }
}
