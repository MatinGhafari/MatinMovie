package ir.matin.matinfilm.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ir.matin.matinfilm.data.model.DetailResponse
import ir.matin.matinfilm.databinding.FragmentDetailBinding
import ir.matin.matinfilm.utils.TMDB_IMAGE_BASE_URL_W780
import ir.matin.matinfilm.utils.network.NetworkRequest
import ir.matin.matinfilm.viewmodel.DetailViewModel
import kotlin.apply
import kotlin.toString

@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()
    val viewModel by viewModels<DetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id =args.data.id!!
        Log.v("testData2" , id.toString())
      viewModel.getMovieDetail(id)
        observeData()

    }


    fun observeData() {
        viewModel.detailData.observe(viewLifecycleOwner) { data ->
            when (data) {
                is NetworkRequest.Loading -> {
                    Log.v("testData", "Loading")

                }

                is NetworkRequest.Error -> {
                    Toast.makeText(requireContext(), "Error ${data.error}", Toast.LENGTH_SHORT)
                        .show()
                    Log.v("testData" , data.error.toString())

                }

                is NetworkRequest.Success -> {
                    fetchData(data.data!!)

                }
            }
        }
    }

    fun fetchData(data: DetailResponse) {

        binding.apply {
            txtLanguage.text = data.originalLanguage
            txtOverview.text = data.overview
            txtMovieName.text = data.title
            txtMovieTime.text = data.releaseDate
            txtScoreMovie.text = data.voteAverage.toString()
            Glide.with(requireContext()).load(TMDB_IMAGE_BASE_URL_W780 + data.backdropPath)
                .into(imgBackgroundMovie)
        }
    }
}
