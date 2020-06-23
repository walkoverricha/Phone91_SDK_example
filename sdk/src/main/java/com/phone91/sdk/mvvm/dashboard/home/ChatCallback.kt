package com.phone91.sdk.mvvm.dashboard.home

import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.databind.JsonNode
import com.phone91.sdk.model.ChatObject
import com.phone91.sdk.utils.JsonUtil
import com.pubnub.api.PubNub
import com.pubnub.api.callbacks.SubscribeCallback
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult

class ChatCallback(
    var chatAdapter: ChatAdapter,
    var rvPosts: RecyclerView
) : SubscribeCallback() {
    override fun status(pubnub: PubNub?, status: PNStatus?) {
    }

    override fun presence(pubnub: PubNub?, presence: PNPresenceEventResult?) {
    }

    override fun message(pubnub: PubNub?, message: PNMessageResult?) {
        try {
//            Log.v(
//                "",
//                "message(" + JsonUtil.asJson(message).toString() + ")"
//            )
            val jsonMsg: JsonNode = message!!.message
            val chatObject: ChatObject = JsonUtil.convert(jsonMsg, ChatObject::class.java)
//           var  messageObject=Gson().fromJson(message!!.message.asText(), MessageObject::class.java)
            chatAdapter.add(chatObject)
            rvPosts.smoothScrollToPosition(chatAdapter.itemCount-1)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}