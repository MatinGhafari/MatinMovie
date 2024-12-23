package ir.matin.matinfilm.ui.allmovie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.matin.matinfilm.data.model.PopularResponse
import ir.matin.matinfilm.databinding.ItemAllMovieBinding
import ir.matin.matinfilm.utils.TMDB_IMAGE_BASE_URL_W780
import ir.matin.matinfilm.utils.base.BaseDiffUtils
import javax.inject.Inject

class AllMovieAdapter @Inject constructor() :
    RecyclerView.Adapter<AllMovieAdapter.PopularViewHolder>() {
    lateinit var binding: ItemAllMovieBinding
    private var items = emptyList<PopularResponse.Result>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PopularViewHolder {
        binding = ItemAllMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder()

    }

    override fun onBindViewHolder(
        holder: PopularViewHolder,
        position: Int,
    ) = holder.bindData(items[position])


    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position


    override fun getItemCount(): Int =items.size



    inner class PopularViewHolder() : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: PopularResponse.Result) {
            binding.apply {
                txtMovieName.text = item.title
                val poster = TMDB_IMAGE_BASE_URL_W780 + item.posterPath
                Log.d("testData",  poster)
                Glide.with(itemView.context).load(poster)
                    .into(imgMovie)

                root.setOnClickListener {
                    onItemClickListener?.let {  it(item) }
                }
            }
        }
    }
    private var onItemClickListener: ((PopularResponse.Result) -> Unit)? = null

    fun setOnClickListener(listener: (PopularResponse.Result) -> Unit) {
        onItemClickListener =listener


    }

    fun setData(data: List<PopularResponse.Result>) {
        val adapterDiff = BaseDiffUtils(items, data)
        val diff = DiffUtil.calculateDiff(adapterDiff)
        items = data
        diff.dispatchUpdatesTo(this)


    }


}