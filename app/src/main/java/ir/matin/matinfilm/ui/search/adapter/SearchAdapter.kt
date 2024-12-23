package ir.matin.matinfilm.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.matin.matinfilm.data.model.PopularResponse
import ir.matin.matinfilm.databinding.ItemSearchBinding
import ir.matin.matinfilm.utils.TMDB_IMAGE_BASE_URL_W780
import ir.matin.matinfilm.utils.base.BaseDiffUtils
import javax.inject.Inject

class SearchAdapter @Inject constructor() : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    lateinit var binding: ItemSearchBinding
    private var items = emptyList<PopularResponse.Result>()

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: PopularResponse.Result) {

            val rate = item.voteAverage?.div(2)
            binding.apply {
                txtMovieName.text = item.title
                materialRatingBar.rating = rate!!.toFloat()
                Glide.with(itemView.context).load(TMDB_IMAGE_BASE_URL_W780 +item.posterPath).into(imgBackgroundMovie)


                root.setOnClickListener {
                    onItemClickListener?.let {  it(item) }
                }



            }


        }

    }
    private var onItemClickListener: ((PopularResponse.Result) -> Unit)? = null

    fun setOnClickListener(listener: (PopularResponse.Result) -> Unit) {
        onItemClickListener = listener

    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()

    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])


    }

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()
    fun setData(data: List<PopularResponse.Result>) {
        val adapterDiff = BaseDiffUtils(items, data)
        val diff = DiffUtil.calculateDiff(adapterDiff)
        items = data
        diff.dispatchUpdatesTo(this)


    }


}





