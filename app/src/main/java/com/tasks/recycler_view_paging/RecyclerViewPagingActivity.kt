package com.tasks.recycler_view_paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tasks.navigationcomponent.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

class RecyclerViewPagingActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_paging)
        recyclerView = findViewById(R.id.recAc_Rec)
        val myPagingDataAdapter = MyPagingDataAdapter()
        recyclerView.adapter = myPagingDataAdapter
        val pagingSource = MyPagingSource(MyPagingSource.MyRepository())
        val pagingConfig = PagingConfig(pageSize = 20)
        val pager = Pager(config = pagingConfig, pagingSourceFactory = { pagingSource })
        val myPagingData: Flow<PagingData<MyData>> = pager.flow.cachedIn(lifecycleScope)

        lifecycleScope.launchWhenStarted {
            myPagingData.collectLatest {
                myPagingDataAdapter.submitData(lifecycle,it)
            }
         }
    }
}

class MyPagingDataAdapter :
    PagingDataAdapter<MyData, MyPagingDataAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_recycler_view_paging_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val myData = getItem(position)
        myData?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MyData>() {
            override fun areItemsTheSame(oldItem: MyData, newItem: MyData): Boolean {
                return oldItem.age == newItem.age
            }

            override fun areContentsTheSame(oldItem: MyData, newItem: MyData): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var txtHeader: TextView

        init {
            txtHeader = itemView.findViewById(R.id.txtHeader)
        }

        fun bind(it: MyData) {
            txtHeader.setText("${it.name} ${it.age}")

        }

    }
}

class MyPagingSource(private val myRepository: MyRepository) : PagingSource<Int, MyData>() {
    class MyRepository {
        fun loadData(page: Int, pageSize: Int): List<MyData> {
            return arrayListOf(MyData("Mostafa " + page + "  " + pageSize, page ))
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyData> {
        try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val data = myRepository.loadData(page, pageSize)
            return LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MyData>): Int {
        return -1
    }
}

data class MyData(val name: String, val age: Int)