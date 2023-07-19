package com.app.slidingup.ui.events.viewmodel

import android.util.Log
import com.app.slidingup.api.NetworkState
import com.app.slidingup.base.BaseViewModel
import com.app.slidingup.extensions.NonNullMediatorLiveData
import com.app.slidingup.model.events.PolylineData
import com.app.slidingup.repository.events.PolyLineUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class PolyLineViewModel(private val polyLineUseCase : PolyLineUseCase) : BaseViewModel() {

    // FOR DATA --
    fun getPolyLineData(markerLatLng : LatLng, currentLatLng : LatLng) : NonNullMediatorLiveData<NetworkState<List<PolylineData>>> {

        Log.d("getPolyLineData", "marker: $markerLatLng, to current: $currentLatLng")
        val polyLineList = NonNullMediatorLiveData<NetworkState<List<PolylineData>>>()

        ioScope.launch { polyLineUseCase.executeQuery(polyLineList,markerLatLng,currentLatLng) }

        return polyLineList
    }
}