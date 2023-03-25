PowerAMP Example Code
=====================

I was looking to manage the queue of PowerAMP but the ability to programmatically add tracks into the PowerAMP queue doesn't seem possible.

This code is the flip-side and listens for a PowerAMP notification that a track is being played and outputs the playing track filename to the debug log.

Only notifies on track change and play (ignores if the track is paused to avoid duplication of notifications)

Hope this is useful for someone :)