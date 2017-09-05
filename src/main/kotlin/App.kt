import com.amazon.speech.speechlet.*
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.SimpleCard
import java.lang.Math.random

class AppRequestHandler : SpeechletRequestStreamHandler(AppSpeechlet(), supportedApplicationIds) {
    companion object {
        private val supportedApplicationIds = HashSet<String>()

        init {
            supportedApplicationIds.add("amzn1.ask.skill.10d44503-fe2d-43b8-aad1-414851d540a0")
        }
    }
}

class AppSpeechlet : Speechlet {

    override fun onSessionStarted(request: SessionStartedRequest?, session: com.amazon.speech.speechlet.Session?) {}

    override fun onSessionEnded(request: SessionEndedRequest?, session: com.amazon.speech.speechlet.Session?) {}

    override fun onIntent(request: IntentRequest, session: com.amazon.speech.speechlet.Session): SpeechletResponse {
        val intent = request.intent
        val intentName = intent?.name

        if ("DisputeResolverIntent" == intentName) {
            return appResponse(intent.slots["NAME"]!!.value)
        } else {
            throw SpeechletException("Invalid Intent")
        }
    }

    override fun onLaunch(request: LaunchRequest?, session: com.amazon.speech.speechlet.Session?): SpeechletResponse {
        return appResponse("Paul Louis Corentin")
    }
}

private fun appResponse(nameList: String): SpeechletResponse {
    val names = nameList.split(" ")

    // Choose someone randomly
    val position = (random() * names.size).toInt()

    val speech = PlainTextOutputSpeech()
    speech.text = names[position]

    val card = SimpleCard()
    card.title = "Conflict resolver"
    card.content = "Selected person: " + names[position]

    return SpeechletResponse.newTellResponse(speech, card);
}