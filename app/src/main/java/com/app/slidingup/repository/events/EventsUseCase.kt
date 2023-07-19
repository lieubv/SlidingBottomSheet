package com.app.slidingup.repository.events

import androidx.lifecycle.MediatorLiveData
import com.app.slidingup.api.NetworkState
import com.app.slidingup.extensions.NonNullMediatorLiveData
import com.app.slidingup.model.events.Event
import com.app.slidingup.model.events.EventDescription
import com.app.slidingup.model.events.EventImages
import com.app.slidingup.model.events.EventLocation
import com.app.slidingup.model.events.EventName
import com.app.slidingup.model.events.EventsApiResponse
import retrofit2.HttpException

class EventsUseCase(private val eventsRepository : EventsRepository)
{
    suspend fun executeQuery(events : NonNullMediatorLiveData<NetworkState<EventsApiResponse>>)
            : MediatorLiveData<NetworkState<EventsApiResponse>> {

        events.postValue(NetworkState.Loading())
        try
        {
            val eventImage = EventImages("https://images2.thanhnien.vn/Uploaded/haoph/2021_11_19/ngoctrinh-gejd-455.jpg")

            val eventName = EventName("Alex", "Alex", "Alex", "Alex")
            val eventLocation = EventLocation(20.58, 105.8876)
            val eventDescription = EventDescription("Hello from Alex", listOf(eventImage))
            val event = Event("1", eventName, eventLocation, eventDescription)

            val eventName2 = EventName("LieuBV", "LieuBV", "LieuBV", "LieuBV")
            val eventLocation2 = EventLocation(20.69, 105.9976)
            val event2 = Event("1", eventName2, eventLocation2, eventDescription)

            val response = EventsApiResponse(listOf(event, event2))
            //val response = eventsRepository.getEvents()
            events.postValue(NetworkState.Success(response))
        }
        catch (exception : HttpException) {
            events.postValue(NetworkState.Error(exception.code(),null))
        }

        return events
    }
}