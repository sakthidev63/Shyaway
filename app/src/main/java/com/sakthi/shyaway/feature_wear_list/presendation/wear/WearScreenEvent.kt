package com.sakthi.shyaway.feature_wear_list.presendation.wear

sealed interface WearScreenEvent {
    data object OnSearch : WearScreenEvent
    data object Trending : WearScreenEvent
    data object Latest : WearScreenEvent
}