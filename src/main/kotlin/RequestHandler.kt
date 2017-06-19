import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

/**
 * Created by marcelo on 6/19/17.
 */

class LambdaHandler : RequestHandler<String, String> {

    override fun handleRequest(input: String, context: Context): String {
        val logger = context.logger

        val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val person = mapper.readValue<Person>(input)
        logger.log("Hello ${person.name} ${person.lastName}!")
        return "OK"
    }

    data class Person(val name: String, val lastName: String)
}