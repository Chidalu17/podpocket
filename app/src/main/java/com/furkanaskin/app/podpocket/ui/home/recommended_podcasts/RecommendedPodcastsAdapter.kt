package com.furkanaskin.app.podpocket.ui.home.recommended_podcasts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.furkanaskin.app.podpocket.R
import com.furkanaskin.app.podpocket.core.BaseAdapter
import com.furkanaskin.app.podpocket.databinding.ItemRecommendedPodcastsBinding
import com.furkanaskin.app.podpocket.service.response.RecommendationsItem

/**
 * Created by Furkan on 30.04.2019
 */

class RecommendedPodcastsAdapter(private val callBack: (RecommendationsItem) -> Unit) : BaseAdapter<RecommendationsItem>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<ItemRecommendedPodcastsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_recommended_podcasts,
            parent,
            false
        )
        val viewModel = RecommendedPodcastsListItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemRecommendedPodcastsBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<RecommendationsItem>() {
    override fun areContentsTheSame(oldItem: RecommendationsItem, newItem: RecommendationsItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: RecommendationsItem, newItem: RecommendationsItem): Boolean =
        oldItem.id == newItem.id
}