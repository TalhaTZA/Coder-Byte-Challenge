package com.coderbyte.application.views.utils


class ItemClickListener<T>(
    val clickListener: (model: T) -> Unit
) {
    fun onClick(model: T) =
        clickListener(model)

}