package edu.tpu.ruban.di

import edu.tpu.ruban.data.api.tables.CitiesTable
import edu.tpu.ruban.data.api.tables.UsersTable
import edu.tpu.ruban.data.datasource.database.rawtables.CitiesTablesImpl
import edu.tpu.ruban.data.datasource.database.rawtables.UsersTableImpl
import edu.tpu.ruban.data.repository.AuthRepositoryImpl
import edu.tpu.ruban.data.repository.CityRepositoryImpl
import edu.tpu.ruban.data.repository.UserRepositoryImpl
import edu.tpu.ruban.domain.repository.AuthRepository
import edu.tpu.ruban.domain.repository.CityRepository
import edu.tpu.ruban.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
//    single<CitiesTable> { DatabaseHelper() }
    single<CitiesTable> { CitiesTablesImpl() }
    single<UsersTable> { UsersTableImpl() }

    single<CityRepository> { CityRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}