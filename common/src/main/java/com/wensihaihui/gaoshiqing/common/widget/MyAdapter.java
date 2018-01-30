package com.wensihaihui.gaoshiqing.common.widget;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */

public abstract class MyAdapter<Datas> extends RecyclerView.Adapter<MyAdapter.MyViewHolder<Datas>>
        implements View.OnClickListener,View.OnLongClickListener,AdapterCallback<Datas>{

    @Override
    public void onViewRecycled(MyViewHolder<Datas> holder) {
        super.onViewRecycled(holder);
    }

    private AdapterListener<Datas> mListener;

    private List<Datas> mDataList;

    /**
     * 构造函数模块
     * @param
     * @param
     */

    public MyAdapter() {
        this(null);
    }

    public MyAdapter(AdapterListener<Datas> listener) {
        this(new ArrayList<Datas>(), listener);
    }

    public MyAdapter(List<Datas> dataList, AdapterListener<Datas> listener) {
        this.mDataList = dataList;
        this.mListener = listener;
    }


    /**
     *
     **
     * 我们的自定义监听器
     *
     * @param <Data> 范型
     */
    public interface AdapterListener<Data> {
        // 当Cell点击的时候触发
        void onItemClick(MyViewHolder holder, Data data);

        // 当Cell长按时触发
        void onItemLongClick(MyViewHolder holder, Data data);
    }




    /**
     * 复写默认的布局类型返回
     *
     * @param position 坐标
     * @return 类型，其实复写后返回的都是XML文件的ID
     */
    @Override
    public int getItemViewType(int position) {

        return getItemViewType(position, mDataList.get(position));
    }

    /**
     * 得到布局的类型
     *
     * @param position 坐标
     * @param data     当前的数据
     * @return XML文件的ID，用于创建ViewHolder
     */

    protected abstract int getItemViewType(int position, Datas data);



    @Override
    public MyViewHolder<Datas> onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View root = inflater.inflate(viewType, parent, false);

        MyViewHolder<Datas> holder = onCreateViewHolder(root,viewType);

        //    root = holder.itemView.findViewById(viewType);
        root.setOnLongClickListener(this);
        root.setOnClickListener(this);

        root = holder.itemView.findViewById(viewType);



        //    holder.callback = (AdapterCallback<Datas>) this;

        return holder;
    }

    /**
     * 得到一个新的ViewHolder
     *
     * @param root     根布局
     * @param viewType 布局类型，其实就是XML的ID
     * @return ViewHolder
     */
    protected abstract MyViewHolder<Datas> onCreateViewHolder(View root, int viewType);

    @Override
    public void onBindViewHolder(MyViewHolder<Datas> holder, int position) {
        Datas data = mDataList.get(position);
        Log.i("postin",position+"    +"+data);
        // 触发Holder的绑定方法\
        if(data!=null)
        {
            holder.bind(data,position);

        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    /**
     * 插入一条数据并通知插入
     *
     * @param data Data
     */
    public void add(Datas data) {
        mDataList.add(0,data);
        notifyItemInserted(0);

    }

    /**
     * 插入一堆数据，并通知这段集合更新
     *
     * @param dataList Data
     */
    public void add(Collection<Datas> dataList) {
        if (dataList != null && dataList.size() > 0) {
            int startPos = mDataList.size();
            mDataList.addAll(dataList);
            notifyItemRangeInserted(startPos, dataList.size());
        }
    }

    /**
     * 点击事件的回调
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (this.mListener != null) {
            // 得到ViewHolder当前对应的适配器中的坐标
//            int pos = viewHolder.getAdapterPosition();
//            // 回掉方法
//            this.mListener.onItemClick(viewHolder, mDataList.get(pos));

        }
    }

    @Override
    public void update(Datas data, MyViewHolder<Datas> holder) {
        // 得到当前ViewHolder的坐标
        int pos = holder.getAdapterPosition();
        if (pos >= 0) {
            // 进行数据的移除与更新
            mDataList.remove(pos);
            mDataList.add(pos, data);
            // 通知这个坐标下的数据有更新
            notifyItemChanged(pos);
        }
    }
    /**
     * 自己的ViewHolder
     * @param <Datas>
     */
    static abstract class MyViewHolder<Datas> extends RecyclerView.ViewHolder {
        protected Datas mData;
        private AdapterCallback<Datas> callback;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
        void bind(Datas data,int postion) {
            this.mData = data;
            onBind(data,postion);
        }

        /**
         * 当触发绑定数据的时候，的回调；必须复写
         *
         * @param data 绑定的数据
         */
        protected abstract void onBind(Datas data,int postion);

        public void updateData(Datas data) {
            if (this.callback != null) {
                this.callback.update(data, this);
            }
        }
    }

}
