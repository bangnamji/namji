package com.example.recyclercalendar.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclercalendar.adapter.InfiniteRecyclerCalendarAdapter
import com.example.recyclercalendar.model.InfiniteRecyclerCalendarConfiguration


class InfiniteRecyclerCalendarView : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun initialise(
        configuration: InfiniteRecyclerCalendarConfiguration,
        dateSelectListener: InfiniteRecyclerCalendarAdapter.OnDateSelected
    ) {
        this.setItemViewCacheSize(10)
        this.setHasFixedSize(true)

        val infiniteRecyclerCalendarAdapter: InfiniteRecyclerCalendarAdapter =
            InfiniteRecyclerCalendarAdapter(
                configuration = configuration,
                dateSelectListener = dateSelectListener
            )

        adapter = infiniteRecyclerCalendarAdapter

        scrollToPosition(Int.MAX_VALUE / 2)
    }
}