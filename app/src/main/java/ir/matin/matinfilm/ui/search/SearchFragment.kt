package ir.matin.matinfilm.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.matin.matinfilm.data.model.PopularResponse
import ir.matin.matinfilm.databinding.FragmentSearchBinding
import ir.matin.matinfilm.ui.search.adapter.SearchAdapter
import ir.matin.matinfilm.utils.network.NetworkRequest
import ir.matin.matinfilm.viewmodel.SearchViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    @Inject
    lateinit var adapter: SearchAdapter

    lateinit var binding:FragmentSearchBinding
    private var searchtext=""
    val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       binding =FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchViewMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false

            }
            override fun onQueryTextChange(p0: String?): Boolean {
                searchtext=p0!!
                Log.v("testSearch" , searchtext)
                if (searchtext.length>2){
                    lifecycleScope.launch{
                    viewModel.getSearch(searchtext)

                    }


                }else{
                    adapter.setData(emptyList())
                }


                return true
            }
        })
            onItemClicked()
            loadData()
}

    fun showError(msg:String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()

    }

    fun loadData() {
        viewModel.searchData.observe(viewLifecycleOwner) {
            when(it){
                is NetworkRequest.Error ->{  showError(it.error?:"Unknown Error") }
                is NetworkRequest.Loading -> {   }
                is NetworkRequest.Success -> {  initSearchList(it.data!!.results as List<PopularResponse.Result>)
                    Log.v("testSearch" , it.data!!.results.toString())
                }
            }
            }


        }
    @SuppressLint("NotifyDataSetChanged")
    fun initSearchList(results: List<PopularResponse.Result>) {

        binding.recyclerSearch.adapter =adapter
        binding.recyclerSearch.layoutManager= LinearLayoutManager(context , RecyclerView.VERTICAL , false)
        adapter.setData(results)
        adapter.notifyDataSetChanged()
        



    }

    fun onItemClicked() {
        adapter.setOnClickListener { data->
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(data)
            findNavController().navigate(action)
            

        }
    }
}
