package com.example.mytask.view

import android.graphics.drawable.ClipDrawable.VERTICAL
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.databinding.ActivityMainBinding
import com.example.mytask.model.Posts
import com.example.mytask.network.NetworkResult
import com.example.mytask.ui.theme.MyTaskTheme
import com.example.mytask.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MyAdapter
     val myViewModel : MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView (binding.root)
//        myViewModel.getAllPost()
setupRecycler()
attcahObserver()
    }

    private fun attcahObserver() {
        lifecycleScope.launch {
            myViewModel.responseMessage_.collect {

   when(it){
       is NetworkResult.Error -> {

       }
       is NetworkResult.Loading -> {

       }
       is NetworkResult.Success -> {

           it.data?.posts?.let { it1 -> myViewModel.list.addAll(it1) }
            count=it.data?.total!!
           adapter.notifyDataSetChanged()
       }
       null -> TODO()
   }
            }
        }
    }
    var count=0

    private fun setupRecycler() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        // pass it to rvLists layoutManager
        binding.rv.setLayoutManager(layoutManager)

        // initialize the adapter,
        // and pass the required argument
        adapter = MyAdapter(myViewModel.list)

        // attach adapter to the recycler view
        binding.rv.adapter = adapter
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView.canScrollVertically(1)){
                   if(count>myViewModel.list.size) {
                       myViewModel.getAllPost()
                       Log.d("222", "onScrollStateChanged: callNw"+"~~~"+count+"~~~"+myViewModel.list.size)
                   }
                    //function that add new elements to my recycler view
                }
            }

        })

}}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTaskTheme {
        Greeting("Android")
    }
}