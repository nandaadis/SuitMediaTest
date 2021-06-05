package com.example.suitmediatest.data.dummy

import android.util.EventLog
import com.example.suitmediatest.R
import com.example.suitmediatest.data.model.EventModel

object dummy_event {

    var dataEvent: MutableList<EventModel> = mutableListOf<EventModel>(
        EventModel(0, "Jakarta", "1-12-2020", R.drawable.img_cityview1, -6.21462, 106.84513),
        EventModel(1, "Tangerang", "2-1-2021", R.drawable.img_cityview2, -6.178306, 106.631889),
        EventModel(2, "Bogor", "23-2-2021", R.drawable.img_cityview3, -6.595038, 	106.816635),
        EventModel(3, "Depok", "7-3-2021", R.drawable.img_cityview1, 	-6.385589, 	106.830711),
        EventModel(4, "Bekasi", "1-4-2021", R.drawable.img_cityview2, 	-6.241586,	106.992416 )
    )

}