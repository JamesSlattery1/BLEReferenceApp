package com.example.blereferenceapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class EventModel(
    var type: String?,
    var data: EventDataModel
) : Parcelable