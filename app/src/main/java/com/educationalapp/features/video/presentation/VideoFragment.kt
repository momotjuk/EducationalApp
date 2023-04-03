package com.educationalapp.features.video.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.navigation.fragment.findNavController
import com.educationalapp.App
import com.educationalapp.core.presentation.BaseFragment
import com.educationalapp.databinding.FragmentVideoBinding
import com.educationalapp.features.home.VideoService
import com.educationalapp.features.home.domain.model.Video
import org.koin.core.component.KoinComponent

class VideoFragment : BaseFragment(), KoinComponent {

    private lateinit var binding: FragmentVideoBinding
    private lateinit var video: Video
    private val videoService: VideoService
        get() = (activity?.applicationContext as App).videoService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        video = videoService.getVideo(requireArguments().getLong("idVideo")).apply {
            videoURL = "https://rr2---sn-3c27sn7y.googlevideo.com/videoplayback?expire=1677558253&ei=jS39Y-LBJYL81gLbwYfACQ&ip=37.229.12.35&id=o-AGW05-UIQovCKRK-lMI0UAeT-F8PvHcHSgTNKQQzQRA9&itag=18&source=youtube&requiressl=yes&pcm2=yes&gcr=ua&spc=H3gIhiy5DtUncfQb2rLFozdPTGIqmk_RCw8ekaq4ASGLgZ9ijQ&vprv=1&mime=video%2Fmp4&ns=N_JQOFuwc3KISphHP6gqMxUL&cnr=14&ratebypass=yes&dur=288.438&lmt=1665483230492346&fexp=24007246&c=WEB&txp=4538434&n=BU6BF4MHddkmqQ&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cpcm2%2Cgcr%2Cspc%2Cvprv%2Cmime%2Cns%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAImRdcxwgRg-Ei7w_QvMcaMQmVUMHALIcCpv5bzGZzzmAiEAqAleT4BGvH_TvZuQZRyPDHdnldM0kEsGmw4ZoTO9Jiw%3D&rm=sn-3tp8nu5g-qo3s7e,sn-3tp8nu5g-3c2e77z&req_id=727e0e195d24a3ee&redirect_counter=2&cms_redirect=yes&cmsv=e&ipbypass=yes&mh=S4&mm=30&mn=sn-3c27sn7y&ms=nxu&mt=1677536432&mv=m&mvi=2&pl=19&lsparams=ipbypass,mh,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRgIhAL8wXJo6hA9zVbNadb58wLJEsHx6CWdSdV0CgfFjI4SXAiEArnNhxV4LFmfvSpRcEIUAdQoxcFJRAkw4qMh9sTqHvSE%3D"
        }

        initToolbar()
        initPlayer()
        initTitle()
        initComments()
    }

    override fun initObservers() {

    }

    private fun initToolbar() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.toolbarTitleTextView.text = video.name
    }

    private fun initPlayer() {
        val mediaController = MediaController(this.context)
        with(binding.videoPlayerView) {
            mediaController.setAnchorView(this)
            this.setMediaController(mediaController)
            this.setVideoURI(Uri.parse(video.videoURL))
            this.requestFocus()
            this.start()
        }
    }

    private fun initTitle() {
        binding.videoTitleTextView.text = video.name

    }

    private fun initComments() {

    }
}