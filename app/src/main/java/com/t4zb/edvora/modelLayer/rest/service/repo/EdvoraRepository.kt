package com.t4zb.edvora.modelLayer.rest.service.repo

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.t4zb.edvora.modelLayer.rest.service.api.GetEdvoreEndpointApi
import com.t4zb.edvora.modelLayer.rest.service.event.EdvoraModel
import com.t4zb.edvora.modelLayer.rest.service.request.RetrofitClientInstance
import com.t4zb.edvora.util.showLogDebug
import com.t4zb.edvora.util.showLogError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EdvoraRepository(val app: Application) {

    val edvoraData = MutableLiveData<List<EdvoraModel>>()

    @WorkerThread
    fun callWebServiceEdvora(){
        val retrofit = RetrofitClientInstance.buildRetrofit(app.applicationContext)
        val service = retrofit!!.create(GetEdvoreEndpointApi::class.java)


        service.getAllEdvore().enqueue(object :
            Callback<List<EdvoraModel>> {
            override fun onResponse(
                call: Call<List<EdvoraModel>>,
                response: Response<List<EdvoraModel>>
            ) {
                if (response.isSuccessful) {
                    edvoraData.postValue(response!!.body())
                      showLogDebug(TAG,response!!.body().toString())
                }
            }

            override fun onFailure(call: Call<List<EdvoraModel>>, t: Throwable) {
                //     showLogError(TAG, t.printStackTrace().toString())
                showLogError(TAG,t.message.toString())
            }
        })

    }
    init {
        CoroutineScope(Dispatchers.IO).launch {
            callWebServiceEdvora()
        }
    }


    companion object{
        const val TAG = "Edvora Repository";
    }


}