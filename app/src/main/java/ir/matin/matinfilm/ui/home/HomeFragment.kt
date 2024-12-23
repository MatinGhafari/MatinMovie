package ir.matin.matinfilm.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.matin.matinfilm.data.model.PopularResponse
import ir.matin.matinfilm.databinding.FragmentHomeBinding
import ir.matin.matinfilm.ui.home.adapter.PopularAdapter
import ir.matin.matinfilm.ui.home.adapter.TopRatedAdapter
import ir.matin.matinfilm.ui.home.adapter.TrendingAdapter
import ir.matin.matinfilm.utils.network.NetworkRequest
import ir.matin.matinfilm.viewmodel.HomeViewModel
import ir.matin.matinfilm.viewmodel.NetworkStatusViewModel

import javax.inject.Inject

@Suppress("DEPRECATION", "UNCHECKED_CAST")
@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    val viewModel: HomeViewModel by viewModels<HomeViewModel>()
    private val networkStatusViewModel: NetworkStatusViewModel by viewModels()


    @Inject
    lateinit var adapterPopular: PopularAdapter

    @Inject
    lateinit var adapterTopRated: TopRatedAdapter

    @Inject
    lateinit var adapterTrending: TrendingAdapter

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkStatusViewModel.networkStatus.observe(viewLifecycleOwner) { isConnected ->

            if (isConnected) {
                initRecyclerViewPopular(emptyList())
                initRecyclerViewTopRated(emptyList())
                initRecyclerViewTrending(emptyList())
                observePopularData()
                observeTopRatedData()
                observeTrendingData()
            } else {
                Snackbar.make(binding.main, "Net is Gone!!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(
                        Color.BLUE
                    ).show()
            }

        }

        binding.imgSearch.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        binding.txtSeeAll.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAllMovieFragment("Popular")
            findNavController().navigate(action)

        }

    }

    private fun observeTrendingData() {
        viewModel.trendingData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkRequest.Error -> {
                    binding.recyclerTrending.veil()
                    Toast.makeText(
                        requireContext(),
                        it.error ?: "Unknown Error!",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.v("testData88" , it.error.toString())


                }

                is NetworkRequest.Loading -> {
                    binding.recyclerTrending.veil()
                    Log.v("testData88" , "loading")

                }

                is NetworkRequest.Success -> {
                    binding.recyclerTrending.unVeil()
                    val data = it.data?.results ?: emptyList()
                    Log.v("testData88" , it.data!!.results.toString())
                    initRecyclerViewTrending(data as List<PopularResponse.Result>)
                }
            }


        }


    }

    private fun observePopularData() {
        viewModel.popularData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkRequest.Loading -> {
                    binding.recyclerPopular.veil()
                }

                is NetworkRequest.Error -> {
                    binding.recyclerPopular.veil()
                    Toast.makeText(
                        requireContext(),
                        it.error ?: "Unknown Error!",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.v("testData" , "error")
                }

                is NetworkRequest.Success -> {
                    binding.recyclerPopular.unVeil()
                    val data = it.data?.results ?: emptyList()
                    initRecyclerViewPopular(data as List<PopularResponse.Result>)
                }
            }
        }
    }

    fun observeTopRatedData() {
        viewModel.topRatedData.observe(viewLifecycleOwner) { data ->
            when (data) {
                is NetworkRequest.Error -> {
                    binding.recyclerTopRated.veil()
                    Log.v("testData77", "error")
                }

                is NetworkRequest.Loading -> {
                    binding.recyclerTopRated.veil()
                    Log.v("testData77", "loading")

                }

                is NetworkRequest.Success -> {
                    binding.recyclerTopRated.unVeil()
                    initRecyclerViewTopRated(data.data!!.results as List<PopularResponse.Result>)
                    Log.v("testData77", data.data.results.toString())


                }
            }

        }

    }

    private fun initRecyclerViewTrending(trendingResponseResults: List<PopularResponse.Result>) {

        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.recyclerTrending.apply {
            setAdapter(adapterTrending)
            unVeil()
            addVeiledItems(5)
            setLayoutManager(layoutManager)
        }

        adapterTrending.setData(trendingResponseResults)

        adapterTrending.setOnClickListener { data ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
            findNavController().navigate(action)
        }


    }

    private fun initRecyclerViewTopRated(topRatedResponseResults: List<PopularResponse.Result>) {


        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.recyclerTopRated.apply {
            setAdapter(adapterTopRated)
            unVeil()
            addVeiledItems(5)
            setLayoutManager(layoutManager)
        }

        adapterTopRated.setData(topRatedResponseResults)

        adapterTopRated.setOnClickListener { data ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
            findNavController().navigate(action)
        }


    }

    fun initRecyclerViewPopular(popularResponseResults: List<PopularResponse.Result>) {

        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.recyclerPopular.apply {
            setAdapter(adapterPopular)
            unVeil()
            addVeiledItems(5)
            setLayoutManager(layoutManager)
        }

        adapterPopular.setData(popularResponseResults)

        adapterPopular.setOnClickListener { data ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
            findNavController().navigate(action)
        }

    }
}


