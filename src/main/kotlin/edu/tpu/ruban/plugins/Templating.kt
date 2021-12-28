package edu.tpu.ruban.plugins

import freemarker.cache.*
import io.ktor.freemarker.*
import io.ktor.application.*

const val TEMPLATES_DIR = "templates"

fun Application.configureHtmlTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, TEMPLATES_DIR)
    }

}











































//    install(StandardResponseFeature)

//
//// copied from io.ktor.freemarker
//class StandardResponseFeature() {
//    public companion object : ApplicationFeature<ApplicationCallPipeline, Configuration, StandardResponseFeature> {
//        val gson: Gson = Gson()
//        override val key: AttributeKey<StandardResponseFeature> = AttributeKey("StandardResponseFeature")
//
//        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): StandardResponseFeature {
//            val feature = StandardResponseFeature()
//            pipeline.sendPipeline.intercept(ApplicationSendPipeline.Transform) { value ->
//                if (value is StandardResponse) {
//                    val response = feature.process(value)
//                    proceedWith(response)
//                }
//            }
//            return feature
//        }
//    }
//    private fun process(content: StandardResponse): OutgoingContent = with(content) {
//        val result = TextContent(text = gson.toJson(content), contentType)
//        return result
//    }
//}
