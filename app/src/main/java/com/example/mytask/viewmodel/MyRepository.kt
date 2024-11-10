package com.example.mytask.viewmodel

import android.util.Log
import com.example.mytask.model.PostData
import com.example.mytask.network.ApiInterface
import com.example.mytask.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class MyRepository @Inject constructor(var apiInterface: ApiInterface) {

 fun getAllPost(skip:Int,limit:Int):Flow<NetworkResult<PostData>> = flow{
try {
    emit(NetworkResult.Loading())
    emit(NetworkResult.Success(apiInterface.getPost(skip, limit)))
}catch (e:Exception) {
    Log.d("222", "getAllPost: exce"+e)
    emit(NetworkResult.Error(e.message))

}
 }.flowOn(Dispatchers.IO)



}