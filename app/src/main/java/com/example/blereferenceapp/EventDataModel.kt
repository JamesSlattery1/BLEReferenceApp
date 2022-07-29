package com.example.blereferenceapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class EventDataModel(
    @SerializedName("polling_frequency")
    var pollingFrequency: Double,
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("lat")
    var latitude: Double?,
    @SerializedName("lng")
    var longitude: Double?,
    @SerializedName("radius")
    var radius: Double?,
    @SerializedName("threat_type")
    var threatType : String?,
    @SerializedName("triggered_by_id")
    var triggeredById : Int?,
    @SerializedName("triggered_by_name")
    var triggeredByName : String?,
    @SerializedName("triggered_by_surname")
    var triggeredBySurname : String?

) : Parcelable