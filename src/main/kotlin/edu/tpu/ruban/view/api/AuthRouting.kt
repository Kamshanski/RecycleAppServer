package edu.tpu.ruban.controller.auth

import edu.tpu.ruban.customLibs.serverexceptions.IncorrectCredentialsException
import edu.tpu.ruban.customLibs.serverexceptions.UserAlreadyExistsException
import edu.tpu.ruban.customLibs.serverexceptions.UserNotFoundException
import edu.tpu.ruban.data.datasource.database.DatabaseHelper
import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.lib.build.Build
import edu.tpu.ruban.lib.security.Encryptor
import edu.tpu.ruban.view.extensions.respondError
import edu.tpu.ruban.view.extensions.respondSuccess
import edu.tpu.ruban.view.extensions.respondSuccessText
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*


fun Application.configureAuthRouting() = routing {

    route("/auth") {

        delete("/delete") {
            try {
                val repo = DatabaseHelper()
                val deletionApplication = call.receive<Credentials>()
                val user = repo.findUser(deletionApplication.login) ?: throwUserNotFound(deletionApplication.login)
                val validPassword = Encryptor.verifyPassword(deletionApplication.password, user.hash)
                if (!validPassword) {
                    throw IncorrectCredentialsException()
                }
                val count = repo.deleteUser(deletionApplication.login)
                if (count <= 0) {
                    throwUserNotFound(deletionApplication.login)
                }

                call.respondSuccessText("User ${deletionApplication.login} successfully deleted")
            } catch (ex: Exception) {
                call.respondError(ex)
            }
        }


        // TODO: test
        get("/get_all") {
            val repo = DatabaseHelper()
            val users = repo.getAllUsers()
            call.respondSuccess(users)
        }
    }
}



// util
fun throwUserNotFound(login: String) : Nothing {
    if (Build.IS_DEBUG_MODE) {
        throw throw UserNotFoundException(login)
    } else {
        throw IncorrectCredentialsException()
    }
}
