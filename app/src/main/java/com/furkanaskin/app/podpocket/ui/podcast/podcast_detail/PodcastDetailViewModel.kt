package com.furkanaskin.app.podpocket.ui.podcast.podcast_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.furkanaskin.app.podpocket.core.BaseViewModel
import com.furkanaskin.app.podpocket.core.Resource
import com.furkanaskin.app.podpocket.core.Status
import com.furkanaskin.app.podpocket.db.AppDatabase
import com.furkanaskin.app.podpocket.service.PodpocketAPI
import com.furkanaskin.app.podpocket.service.response.PodcastRecommendations
import com.furkanaskin.app.podpocket.service.response.Podcasts
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Furkan on 2019-07-06
 */

class PodcastDetailViewModel  @Inject constructor(api: PodpocketAPI, appDatabase: AppDatabase) : BaseViewModel(api, appDatabase) {

    var podcast: ObservableField<Podcasts> = ObservableField()
    val podcastRecommendationsLiveData = MutableLiveData<Resource<PodcastRecommendations>>()


    fun getPodcastRecommendations(podcastId: String, explicitContent: Int) {
        baseApi?.let { baseApi ->
            baseApi.getPodcastRecommendations(podcastId, explicitContent)
                    .subscribeOn(Schedulers.io())
                    .map { Resource.success(it) }
                    .onErrorReturn { Resource.error(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .autoDisposable(this)
                    .subscribe {
                        when (it?.status) {
                            Status.SUCCESS -> podcastRecommendationsLiveData.postValue(it)
                            Status.LOADING -> ""
                            Status.ERROR -> Timber.e(it.error)
                        }
                    }
        }
    }
}