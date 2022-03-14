package com.t4zb.edvora.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.t4zb.edvora.modelLayer.rest.service.repo.EdvoraRepository

class SharedViewModel(app: Application): AndroidViewModel(app) {

    val dataRepo = EdvoraRepository(app)
}