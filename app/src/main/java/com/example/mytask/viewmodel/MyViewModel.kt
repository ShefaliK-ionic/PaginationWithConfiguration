package com.example.mytask.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytask.model.PostData
import com.example.mytask.model.Posts
import com.example.mytask.network.NetworkResult
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MyViewModel @Inject constructor(val myRepository: MyRepository): ViewModel() {


  var limit=10
  var skip=-10
//  private  var _state:NetworkResult<PostData?>?=NetworkResult<NetworkResult.Loading()>
    var responseMessage: MutableStateFlow<NetworkResult<PostData>?> =MutableStateFlow(NetworkResult.Loading())
   public val responseMessage_: StateFlow<NetworkResult<PostData>?> =responseMessage

    init {
        getAllPost()

    }
    var list=ArrayList<Posts>()

    fun getAllPost(){

        skip=skip+limit

   viewModelScope.launch {

       myRepository.getAllPost(skip, limit).collect {
               responseMessage.value=it
               Log.d("222", "getAllPost: "+it.data?.posts+"~~"+skip+"~~~"+limit)
           }
   }

    }

}