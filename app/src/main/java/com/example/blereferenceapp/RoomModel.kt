package com.example.blereferenceapp


class RoomModel(
    var id: Long = 0,
    var room_name: String? = null,
    var building_name: String? = null,
    var building_address: String? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    var uuid: String? = null,
    var major: Int? = null,
    var minor: Int? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var floor_id: Int? = null
)