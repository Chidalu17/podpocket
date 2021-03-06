package com.furkanaskin.app.podpocket.ui.feed.global_feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.furkanaskin.app.podpocket.R

/**
 * Created by Furkan on 2019-05-26
 */

class GlobalFeedContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_global_feed_container, container, false)
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)

        if (menuVisible) {
            // This routes the system back button to this Fragment
            requireFragmentManager().beginTransaction()
                .setPrimaryNavigationFragment(this)
                .commit()
        }
    }
}
