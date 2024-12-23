package ir.matin.matinfilm.ui.allmovie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.matin.matinfilm.R
import ir.matin.matinfilm.data.model.PopularResponse
import ir.matin.matinfilm.databinding.FragmentAllMovieBinding
import ir.matin.matinfilm.ui.allmovie.adapter.AllMovieAdapter
import ir.matin.matinfilm.ui.detail.DetailFragmentArgs
import ir.matin.matinfilm.ui.home.HomeFragmentDirections
import ir.matin.matinfilm.utils.network.NetworkRequest
import ir.matin.matinfilm.viewmodel.HomeViewModel
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class AllMovieFragment : Fragment() {
    lateinit var binding: FragmentAllMovieBinding
    val viewModel by viewModels<HomeViewModel>()
    lateinit var movieType: LiveData<NetworkRequest<PopularResponse>>
    private val args: AllMovieFragmentArgs by navArgs()

    @Inject
    lateinit var adapter: AllMovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkMoviesType(args.title)
            observeData()
    }
    private fun observeData() {
        movieType.observe(viewLifecycleOwner) { value ->

            when (value) {
                is NetworkRequest.Error -> {
                    binding.recyclerPopular.veil()
                }

                is NetworkRequest.Loading -> {
                    binding.recyclerPopular.veil()
                }

                is NetworkRequest.Success -> {
                    initRecycler(value)

                }
            }

        }
    }

    private fun initRecycler(success: NetworkRequest.Success<PopularResponse>) {
        binding.recyclerPopular.unVeil()

        val layoutManager =
            GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false)

        binding.recyclerPopular.apply {
            setAdapter(adapter)
            unVeil()
            addVeiledItems(20)
            setLayoutManager(layoutManager)
        }
        adapter.setData(success.data!!.results as List<PopularResponse.Result>)

        adapter.setOnClickListener { data ->

            val action = AllMovieFragmentDirections.actionAllMovieFragmentToDetailFragment(data)
            findNavController().navigate(action)

        }



    }

    private fun checkMoviesType(moviesType: String) {
        binding.txtTypeMovies.text=args.title

        when(moviesType){
            "Popular"->{ movieType =viewModel.popularData }

        }
    }
}