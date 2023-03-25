package com.osman.powerampsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log

class APIReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null) {
            when (intent.action) {
                PowerampAPI.ACTION_STATUS_CHANGED_EXPLICIT -> {
                    context.registerReceiver(null, IntentFilter(PowerampAPI.ACTION_TRACK_CHANGED))
                        ?.let { trackIntent ->
                            trackIntent.getBundleExtra(PowerampAPI.EXTRA_TRACK)?.let { bundle ->
                                bundle.putInt(
                                    PowerampAPI.Track.POSITION,
                                    intent.getIntExtra(PowerampAPI.Track.POSITION, -1)
                                )
                                bundle.putBoolean(
                                    PowerampAPI.EXTRA_PAUSED,
                                    intent.getBooleanExtra(PowerampAPI.EXTRA_PAUSED, false)
                                )
                                bundle.putBoolean(
                                    PowerampAPI.PAUSED,
                                    intent.getBooleanExtra(PowerampAPI.PAUSED, false)
                                )
                                if (!intent.getBooleanExtra(PowerampAPI.PAUSED, false))
                                    Log.d(
                                        "PowerAMP",
                                        "Song Path : $" + bundle.getString(PowerampAPI.Track.PATH)
                                    )
                            }
                        }
                }

                PowerampAPI.ACTION_TRACK_CHANGED_EXPLICIT -> {
                    context.registerReceiver(null, IntentFilter(PowerampAPI.ACTION_STATUS_CHANGED))
                        ?.let { statusIntent ->
                            intent.getBundleExtra(PowerampAPI.EXTRA_TRACK)?.let { bundle ->
                                bundle.putInt(PowerampAPI.Track.POSITION, 0)
                                bundle.putBoolean(
                                    PowerampAPI.EXTRA_PAUSED,
                                    statusIntent.getBooleanExtra(PowerampAPI.EXTRA_PAUSED, true)
                                )
                                Log.d(
                                    "PowerAMP",
                                    "Song Path : $" + bundle.getString(PowerampAPI.Track.PATH)
                                )
                            }
                        }
                }
            }
        }
    }
}