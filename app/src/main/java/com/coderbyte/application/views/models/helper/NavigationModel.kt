package com.coderbyte.application.views.models.helper

import android.os.Bundle
import androidx.core.os.bundleOf


data class NavigationModel(
    val id: Int,
    val bundle: Bundle = bundleOf()
)