package edu.tpu.ruban.controller.recycleapi

import edu.tpu.ruban.presentation.io.GetDumpsterInput
import edu.tpu.ruban.presentation.io.GetDumpsterOutput
import edu.tpu.ruban.presentation.io.GetProductRequest
import edu.tpu.ruban.presentation.io.GetProductResponse
import edu.tpu.ruban.data.datasource.database.DatabaseHelper
import edu.tpu.ruban.view.extensions.respondSuccess
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureRecycleApiRouting() = routing {

    get("/product") {
        val input = call.receive<GetProductRequest>()

        val repo = DatabaseHelper()

        val products = repo.getProductInfo(input.barcode, input.barcodeType, input.languageId)

        call.respondSuccess(GetProductResponse(products))
    }

    get("/dumpster") {
        val input = call.receive<GetDumpsterInput>()

        val repo = DatabaseHelper()

        val dumpsters = repo.getDumpsters(input.productTypeId, input.cityId, input.languageId)

        call.respondSuccess(GetDumpsterOutput(input.productTypeId, dumpsters))
    }


}
