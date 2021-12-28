package edu.tpu.ruban.di

import edu.tpu.ruban.presentation.viewmodel.AuthViewModel
import edu.tpu.ruban.presentation.viewmodel.CitiesViewModel
import edu.tpu.ruban.presentation.viewmodel.UserViewModel
import org.koin.dsl.module

val presenterModule = module {
    factory { CitiesViewModel(get(), get(), it.get()) }
    factory { UserViewModel(get(), get()) }
    factory { AuthViewModel(get()) }
}