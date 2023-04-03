package com.educationalapp.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.educationalapp.App
import com.educationalapp.R
import com.educationalapp.core.extensions.toast
import com.educationalapp.databinding.FragmentHomeBinding
import com.educationalapp.core.presentation.BaseFragment
import com.educationalapp.features.home.VideoService
import com.educationalapp.network.model.repository.UserResponse
import com.educationalapp.network.model.repository.Video
import com.educationalapp.network.model.repository.VideoResponse
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class HomeFragment : BaseFragment(), KoinComponent {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var adapter: VideoAdapter
    private val videoService: VideoService
        get() = (activity?.applicationContext as App).videoService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun loadData() {
        viewModel.getUser()
        viewModel.getVideo()
        viewModel.getVideos()
    }

    override fun initUI() {
        loadData()
        initToolbar()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = VideoAdapter()
        adapter.onItemClick = {
            onItemClicked(it)
        }
        adapter.data = videoService.getVideos()

        val layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun initToolbar() {
        binding.searchTextInput.addTextChangedListener {
            videoService.filterVideo(it.toString())
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.profileButton -> {
                    findNavController().navigate(R.id.navigateToProfileFragment)
                    true
                }
                R.id.sortButton -> {
                    videoService.reverseSort()
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                    true
                }
                else -> false
            }
        }
    }

    override fun initObservers() {
        observeLiveData(viewModel.getUserResponse) { user ->
            hideProgress()
            handleUIState(
                user,
                handleSuccess = ::observeUser,
                handleError = { toast(it.getMessage()) }
            )
        }

        observeLiveData(viewModel.getVideoResponse) { video ->
            hideProgress()
            handleUIState(
                video,
                handleSuccess = ::observeVideo,
                handleError = { toast(it.getMessage()) }
            )
        }

        observeLiveData(viewModel.getVideosResponse) { videos ->
            hideProgress()
            handleUIState(
                videos,
                handleSuccess = ::observeVideos,
                handleError = { toast(it.getMessage()) }
            )
        }
    }

    private fun observeUser(user: UserResponse) {
        user.username
    }

    private fun observeVideo(videos: Video) {
        videos.id
    }

    private fun observeVideos(videos: VideoResponse) {

    }

    private fun onItemClicked(idVideo: Long) {
        val bundle = bundleOf("idVideo" to idVideo)
        findNavController().navigate(R.id.navigateToVideoFragment, bundle)
    }
}