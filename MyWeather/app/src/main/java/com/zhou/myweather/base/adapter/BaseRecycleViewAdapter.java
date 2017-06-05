package com.zhou.myweather.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public abstract class BaseRecycleViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{
	protected OnItemClickListener itemClickListener;
	protected OnItemLongClickListener itemLongClickListener;
	@Override
	public void onBindViewHolder(T viewHolder, final int position) {
		viewHolder.itemView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(itemClickListener != null) itemClickListener.onItemClick(v, position);
			}
		});
		viewHolder.itemView.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if(itemLongClickListener != null) itemLongClickListener.onItemLongClick(v, position);
				return true;
			}
		});
	}
	
	public interface OnItemClickListener {
	     void onItemClick(View parent, int position);
	}
	public interface OnItemLongClickListener {
	     boolean onItemLongClick(View parent, int position);
	}
	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}
	public void setOnItemLongClickListener(
			OnItemLongClickListener itemLongClickListener) {
		this.itemLongClickListener = itemLongClickListener;
	}
}
