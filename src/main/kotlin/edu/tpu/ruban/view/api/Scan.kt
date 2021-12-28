package edu.tpu.ruban.view.api

import edu.tpu.ruban.data.datasource.database.DatabaseHelper
import edu.tpu.ruban.di.inject
import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.presentation.io.GetProductRequest
import edu.tpu.ruban.presentation.io.GetProductResponse
import edu.tpu.ruban.presentation.viewmodel.AuthViewModel
import edu.tpu.ruban.presentation.viewmodel.ScanViewModel
import edu.tpu.ruban.view.extensions.respondError
import edu.tpu.ruban.view.extensions.respondSuccess
import edu.tpu.ruban.view.extensions.safeGet
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*


fun Route.scan() = route("auth") {
    // get Token
    safeGet {
        val input = call.receive<GetProductRequest>()

        val vm by inject<ScanViewModel>()

        val products = repo.getProductInfo(input.barcode, input.barcodeType, input.languageId)

        call.respondSuccess(GetProductResponse(products))
    }
}