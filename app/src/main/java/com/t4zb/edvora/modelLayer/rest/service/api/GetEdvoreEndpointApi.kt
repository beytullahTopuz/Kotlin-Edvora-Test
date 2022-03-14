package com.t4zb.edvora.modelLayer.rest.service.api

import com.t4zb.edvora.modelLayer.rest.service.event.EdvoraModel
import retrofit2.http.GET
import retrofit2.Call

interface GetEdvoreEndpointApi {
@GET("/rides")
fun getAllEdvore():Call<List<EdvoraModel>>

}