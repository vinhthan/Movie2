package com.thanmanhvinh.movieandnews.utils.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.thanmanhvinh.movieandnews.R


abstract class RecyclerAdapter<T>(
    context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val loadMore = 1
    private val normal = 0
    private var mContext: Context = context

    private var mList: MutableList<T> = ArrayList()

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    private lateinit var mRecyclerView: LoadMoreRecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        if (recyclerView is LoadMoreRecyclerView) {
            this.mRecyclerView = recyclerView
        }
    }

    fun updateListSeeAll(list: List<T>) {
        if (mRecyclerView.isRefresh()) {
            mList.clear()
            mRecyclerView.endRefresh()
        }
        if (mRecyclerView.isLoadMore()) {

            mRecyclerView.endLoadMore()
            if (list.count() == 0){
                if (mList.count() > 0){
                    mList.removeAt(itemCount-1)
                    notifyDataSetChanged()
                }
            }else {
                mList.addAll(list)
                notifyItemInserted(itemCount)
            }
            return
        }



        mList.addAll(list)
        notifyDataSetChanged()
    }

    abstract var layoutResource: Int
    abstract fun createViewHolder(view: View): RecyclerView.ViewHolder
    abstract fun bindHolder(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemCount(): Int {
        if (mRecyclerView.isLoadMore()) {
            return mList.size + 1
        }
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (mRecyclerView.isLoadMore() && position == itemCount - 1) {
            return loadMore
        }
        return normal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == loadMore) {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_loading, parent, false)
            val layoutParams = view.layoutParams
            if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
                layoutParams.isFullSpan = true
                view.layoutParams = layoutParams
                val lm = mRecyclerView.layoutManager as StaggeredGridLayoutManager
                lm.invalidateSpanAssignments()
            }
            return LoadingViewHolder(view)
        }
        val view = mLayoutInflater.inflate(layoutResource, parent, false)
        return createViewHolder(view)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoadingViewHolder) {
            return
        }

        if (!mRecyclerView.skipLoadMore && !mRecyclerView.endOfPage && !mRecyclerView.isRefresh() && !mRecyclerView.isLoadMore() && position == itemCount - 1) {

            mRecyclerView.post {
                notifyItemInserted(position)
                mRecyclerView.startLoadMore()
            }

        } else {
            bindHolder(holder, position)
        }
    }


    fun getItem(position: Int): T {
        return mList[position]
    }

}

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): LoadingViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            return LoadingViewHolder(view)
        }
    }
}




