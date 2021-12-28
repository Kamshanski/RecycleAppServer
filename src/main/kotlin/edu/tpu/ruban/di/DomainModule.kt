package edu.tpu.ruban.di

import edu.tpu.ruban.domain.scenario.GrantTokenScenario
import edu.tpu.ruban.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetCityByIdUseCase(get()) }
    factory { GetCityMatchingUseCase(get()) }

    factory { SelectUsersUseCase(get()) }
    factory { SelectUserByLoginUseCase(get()) }
    factory { RegistrationUseCase(get()) }
    factory { GrantTokenScenario(get(), get()) }
}