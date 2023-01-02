package com.example.flipkartgroceries.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class MessageBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
            val data = intent.extras
            if (data != null) {

                val smsRetrieverStatus = data?.get(SmsRetriever.EXTRA_STATUS) as Status
                val timeOut = false
                var otpCode: String? = null
                when (smsRetrieverStatus.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        val appMessage = data[SmsRetriever.EXTRA_SMS_MESSAGE] as String
                        otpCode = appMessage
                    }
                }
            }

        }
    }
}