package com.example.recyclercalendar.model

import androidx.appcompat.app.AppCompatActivity
import java.util.*


class InfiniteRecyclerCalendarConfiguration(
    calenderViewType: CalenderViewType,
    calendarLocale: Locale,
    includeMonthHeader: Boolean,
    selectionMode: SelectionMode
) : RecyclerCalendarConfiguration(
    calenderViewType, calendarLocale, includeMonthHeader
) {
    var selectionMode: SelectionMode = SelectionModeNone()

    init {
        this.selectionMode = selectionMode
    }

    open class SelectionMode {
        enum class TYPE {
            NONE
        }

        var selectionType: TYPE = TYPE.NONE
    }

    class SelectionModeNone : SelectionMode() {
        init {
            this.selectionType = TYPE.NONE
        }
    }

}